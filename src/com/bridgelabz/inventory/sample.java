package com.bridgelabz.inventory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

//import org.json.simple.parser.ParserException;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class sample {
	public static void main(String[] args) throws Exception{

        JSONParser parser = new JSONParser();

        try {     
            Object obj =  parser.parse(new FileReader("/home/bridgeit/Desktop/invent.json"));

            JSONObject jsonObject =  (JSONObject) obj;

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            String city = (String) jsonObject.get("city");
            System.out.println(city);

            String job = (String) jsonObject.get("job");
            System.out.println(job);

            // loop array
            JSONArray cars = (JSONArray) jsonObject.get("cars");
            Iterator<String> iterator = cars.iterator();
            while (iterator.hasNext()) {
             System.out.println(iterator.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

