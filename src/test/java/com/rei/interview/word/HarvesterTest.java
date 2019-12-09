package com.rei.interview.word;

import java.util.HashSet;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.rei.interview.util.Dictionary;

public class HarvesterTest {

	private Dictionary dict = new Dictionary();
	
	@Test
	public void nullInputTest() {
		Harvester harvester = new Harvester(dict);
		HashSet<String> output = harvester.harvest(null);
		assertEquals(0, output.size());
	}
	
	@Test
	public void emptyStringTest() {
		Harvester harvester = new Harvester(dict);
		HashSet<String> output = harvester.harvest("");
		assertEquals(0, output.size());
	}
	
	@Test
	public void validWordInputTest() {
		Harvester harvester = new Harvester(dict);
		HashSet<String> output = harvester.harvest("bang");
		assertEquals(5, output.size());
		assertTrue(output.contains("bang"));
		assertTrue(output.contains("gab"));
		assertTrue(output.contains("ban"));
		assertTrue(output.contains("bag"));
		assertTrue(output.contains("nab"));
	}
	
	@Test
	public void invalidWordContainingValidWordsTest() {
		Harvester harvester = new Harvester(dict);
		HashSet<String> output = harvester.harvest("dapam");
		assertEquals(3, output.size());
		assertTrue(output.contains("pad"));
		assertTrue(output.contains("dam"));
		assertTrue(output.contains("mad"));
	}
	
	@Test
	public void inputWithSpaceTest() {
		Harvester harvester = new Harvester(dict);
		HashSet<String> output = harvester.harvest("dapam bang");
		assertEquals(3, output.size());
		assertTrue(output.contains("pad"));
		assertTrue(output.contains("dam"));
		assertTrue(output.contains("mad"));
	}
	
	@Test
	public void maxLengthInput() {
		Harvester harvester = new Harvester(dict);
		HashSet<String> output = harvester.harvest("capatul");
		assertEquals(7, output.size());
		assertTrue(output.contains("pat"));
		assertTrue(output.contains("tap"));
		assertTrue(output.contains("at"));
		assertTrue(output.contains("cap"));
		assertTrue(output.contains("cat"));
		assertTrue(output.contains("up"));
		assertTrue(output.contains("put"));
	}
	
	@Test
	public void exceedMaxLengthInput() {
		Harvester harvester = new Harvester(dict);
		//should truncate and have identical results to the at max length test
		HashSet<String> output = harvester.harvest("catapultffhajildfhajkfajklfadjk");
		assertEquals(7, output.size());
		assertTrue(output.contains("pat"));
		assertTrue(output.contains("tap"));
		assertTrue(output.contains("at"));
		assertTrue(output.contains("cap"));
		assertTrue(output.contains("cat"));
		assertTrue(output.contains("up"));
		assertTrue(output.contains("put"));
	}
	
}
