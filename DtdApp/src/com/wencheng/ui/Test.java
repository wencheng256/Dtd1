package com.wencheng.ui;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] split = "property|many-to-one|one-to-one|component|dynamic-component|properties|any|map|set|list|bag|idbag|array|primitive-array".split("\\|");
		for(int i = 0; i<split.length; i++){
			System.out.println(split[i]);
		}
	}

}
