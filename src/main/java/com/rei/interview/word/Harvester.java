package com.rei.interview.word;

import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rei.interview.util.Dictionary;

@RestController
@RequestMapping("/search")
public class Harvester {
	
	// fits Scrabble game piece, will not scale beyond
	private static final int MAX_LEN = 7;
	
	private Dictionary dictionary;
	
	@Autowired
	public Harvester(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
	
	@GetMapping("/{seed}")
	@ResponseBody
	public HashSet<String> harvest(@PathVariable("seed") String seed) {
		HashSet<String> validList = new HashSet<String>();
		if (seed == null) {
			return validList;
		}
		
		String adjustedSeed = adjustSeed(seed);
		HashSet<String> combinations = new HashSet<String>();
		combine(adjustedSeed, new StringBuilder(), 0, combinations);
		for (String combo : combinations) {
			permuteAndCheck(combo.toCharArray(), "", 0, validList);
		}
		
		return validList;
	}
	
	private String adjustSeed(String original) {
		// only take substring prior to first whitespace
		int origLength = original.length();
		int trimLength = (origLength > MAX_LEN) ? MAX_LEN : origLength;
		String trimmed = original.substring(0, trimLength);
		String[] spaceDelimited = trimmed.split(" ");
		String spacesRemoved = spaceDelimited[0];
		return spacesRemoved.toLowerCase();
	}
	
	private void combine(String str, StringBuilder buffer, int start, HashSet<String> combos) {
		for (int i=start; i<str.length(); i++) {
			buffer.append(str.charAt(i));
			combos.add(buffer.toString());
			if (i < str.length() - 1) {
				combine(str, buffer, i + 1, combos);
			}
			
			buffer.deleteCharAt(buffer.length() - 1);
		}
	}
	
	private void permuteAndCheck(char[] str, String prefix, int start, HashSet<String> permutations) {
		if (start == str.length - 1) {
			StringBuilder outputSb = new StringBuilder();
			outputSb.append(prefix);
			outputSb.append(str[str.length - 1]);
			String outputString = outputSb.toString();
			
			/*
			 * TODO: implement a wrapper with a trie to cache successful finds from DictApi and avoid a network call
			 * search the trie, call DictApi upon failure, if successful, then add to trie and return true,
			 * if failure then return false
			 */
			//System.out.println("looking up: " + outputString);
			if (this.dictionary.isEnglishWord(outputString)) {
				permutations.add(outputSb.toString());
			}
			
			return;
		}
		
		for (int i=start; i<str.length; i++) {
			//need to swap if i is greater than the start index
			boolean needSwap = i > start;
			if (needSwap) {
				swap(str, start, i);
			}
			
			String tmp = prefix + new String(str, start, 1);
			permuteAndCheck(str, tmp, start + 1, permutations);
			
			// undo the original swap
			if (needSwap) {
				swap(str, start, i);
			}
		}
	}
	
	private void swap(char[] charArr, int left, int right) {
        char tmp = charArr[left];
        charArr[left] = charArr[right];
        charArr[right] = tmp;
	}
}
