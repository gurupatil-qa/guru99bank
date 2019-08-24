package com.guru99bank.web;

import resources.base;

public class temp {

	
	public static void main(String[] args) {
		
		String s = "madam";
		String t = "";
		int len = s.length();
		for(int i=len-1;i>=0;i--)
		{
//			System.out.println(s.charAt(i));
			t = t+s.charAt(i);
		}
		
//		System.out.println(t);
		
		if(s.equals(t))
		{
			System.out.println("String is palindrome");
		}else {
			
			System.out.println("String is not palindrome");
		}
		
		System.out.println(base.getData());
	}
	
	
}
