package com.rei.interview.mockup;

import java.util.Arrays;
import java.util.HashSet;

public class DictApi {
	private static final String[] WORDS = new String[] {
			"recreational","recreation","equipment","equip","incorporated","corporate",
			"superman","super","man","woman","cooperation","corporation",
			"and","band","bandana","can","dam","ebb","flow","gag","grand","hit","hip",
			"ink","jug","jagged","kilt","kiln","lab","laboratory","mad","mat","new","no",
			"one","on","pad","peek","peak","peel","phone","quiet","quaint","ran","run","rat",
			"sit","sat","sap","tug","tilt","topsy","under","underneath","very",
			"wilt","will","xylophone","zip","cat","at","tap","put","pat","up","super","bob",
			"catapult","interview","bag","gab","ban","bang","cop","cap","bat","tab",
			"cab","bad","dab","nab"
	};
	
	private static final HashSet<String> WORD_COLLECTION = new HashSet<String>(Arrays.asList(WORDS));
	
	public static boolean lookup(String str) {
		return WORD_COLLECTION.contains(str);
	}
}
