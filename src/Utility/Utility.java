package Utility;
import java.util.Scanner;

public class Utility {
	static Scanner sc=new Scanner(System.in);
	
	public static int isInteger()
	{
		return sc.nextInt();
	}
	public static String isString()
	{
		return sc.nextLine();
	}
	public static long isLong() 
	{
		return sc.nextLong();
	}
	public static double isDouble()
	{
		return sc.nextDouble();
	}
	
	

}
