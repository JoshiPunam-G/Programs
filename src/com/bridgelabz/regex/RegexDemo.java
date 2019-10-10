package com.bridgelabz.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {
	 public String replaceWithFirst(String str,String replace)
	   {   
	        Pattern ptn = Pattern.compile("\\s+");
	        Matcher mtch = ptn.matcher(str);
	        return mtch.replaceAll(replace);
	    }
	 
	 public String replaceWithFull(String replace,String str2)
	 {
		 Pattern ptn=Pattern.compile("\\s+");
		 Matcher mtch=ptn.matcher(str2);
		 
		return replace;
		 
	 }
	public static void main(String[] args) {
		String str1=" Hello ";
		String str2="john smith ";
		RegexDemo rd=new RegexDemo();
	    String first=	rd.replaceWithFirst(str1, "john");
	   
		System.out.println(first);
	}
}
