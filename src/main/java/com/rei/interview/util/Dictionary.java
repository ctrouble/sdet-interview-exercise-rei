package com.rei.interview.util;

import org.springframework.stereotype.Component;

import com.rei.interview.mockup.DictApi;

@Component
public class Dictionary {

	public boolean isEnglishWord(String str) {
		return DictApi.lookup(str);
	}
}
