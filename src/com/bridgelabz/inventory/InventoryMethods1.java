package com.bridgelabz.inventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import Utility.Utility;

public class InventoryMethods1 {
	static Utility utill=new Utility();

	static ObjectMapper mapper=new ObjectMapper();
	
	static
	{
		mapper=new ObjectMapper();
	}
	
	public static String toConvert(Object object )
	{
		String invent="";	
		try {
		 invent=mapper.writeValueAsString(object);
		} catch (JsonGenerationException e) {
			System.out.println("Exception occured while convrting java object into json"+e.getMessage());
		} catch (JsonMappingException e) {
			System.out.println("Exception occured while convrting java object into json"+e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception occured while convrting java object into json"+e.getMessage());
		}
		return invent;
		
	}
//	
//	/**
//	 * Purpose : Method for reading json file
//	 * @return
//	 * @throws IOException
//	 */
//
//	public static String readFile() throws IOException
//	   {
//		 ObjectMapper mapper = new ObjectMapper();
//		 String line ="";
//		 File f = new File("/home/bridgeit/Desktop/invent.json");
//		 BufferedReader buf = new BufferedReader(new FileReader(f));  
//		 while ((line = buf.readLine()) != null) 
//		 {
//		      System.out.println(" " + line);
//	     }
//		return line;
//	   }
	
	
	/**
	 * Purpose : Reads data from the file
	 * 
	 * @param file is the name of the file from which data is to read
	 * @param list is the in which data is to be saved
	 * @return the list in which data are there
	 * @throws Exception
	 */
	public static List<Inventory> read(String file,List<Inventory> list) throws Exception 
	{
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			BufferedReader reader = new BufferedReader(new FileReader("/home/bridgeit/Desktop/inventory.json" ));
			String arrayToJson;
			if ((arrayToJson = reader.readLine()) != null) 
			{
				/* conversion of array to list */
				TypeReference<ArrayList<Inventory>> type = new TypeReference<ArrayList<Inventory>>() 
				{
				};
				/* Reading json list */
				list = objectMapper.readValue(arrayToJson, type);
				reader.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}


	
	/** 
	 * Purpose : Adding information of inventory products and returns inventory
	 * object after adding data into it. 
	 */
	public static Inventory addInventory()
	{
		Inventory inventory=new Inventory();
		Utility.isString();
		System.out.println("Enter product name ");
        inventory.setName(Utility.isString()); 	
        System.out.println("Enter Product Weight "+inventory.getName());
        inventory.setWeight(Utility.isLong()); 
        System.out.println("Enter Price of per product "+inventory.getName());
        inventory.setPrice(Utility.isLong());
        return inventory;
	}
	
	
	/**
	 * Purpose : Calculating total weight of all the products in the inventory
	 * and returns the calculated weight
	 */
	public static long totalWeight(List<Inventory> inventoryList)
	{
		long totalWeight = 0;
		for(Inventory inventory : inventoryList)
		{
			totalWeight=totalWeight+inventory.getWeight();
		}
		return totalWeight;
	}
	
	

	/**
	 * Purpose : Calculating total price of all the products in the inventory
	 * and returns the calculated total price
	 */
	public static long totalPrice(List<Inventory> inventoryList)
	{
		long totalPrice = 0;
		for(Inventory inventory : inventoryList) 
		{
			totalPrice = totalPrice+(inventory.getPrice()*inventory.getWeight());
		}
		return totalPrice;
	}
	
	

	/** 
	 * Purpose : Displaying all the products in the inventory
	 */
	public static void display(List<Inventory> inventoryList) 
	{
		System.out.println();
		for(Inventory inventory : inventoryList) 
		{
			System.out.println(inventory.toString());
		}
		
	}
	
	
	/**
	 * Purpose : Saving all the data into file
	 * 
	 * @param T is the list which contains all the details of the products
	 * @param file is the name of the file in which data is to be saved
	 */
	public static <T> void save(List<T> T, String file)
	{
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writeValue(new File("/home/bridgeit/Desktop/inventory.json" ), T);
			System.out.println("\n Saved");
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/* 
	 * Purpose : Removing and product from the inventory 
	 */
	public List<Inventory> removeInventory(List<Inventory> inventoryList) 
	{
		System.out.println("\nEnter the name which you want to remove");
		String name = utill.isString();
		int count = 0;
		List<Inventory> list = new ArrayList<>();
		for(Inventory inventary : inventoryList) 
		{
			if(name.equals(inventary.getName())) 
			{
				list.add(inventary);
				count++;
				System.out.println("\nRmoved");
			}
		}
		inventoryList.removeAll(list);
		if(count==0) {
			System.out.println("\nNo such data found");
		}
		return inventoryList;
	}



}
