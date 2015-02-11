// Colin 
// This class is for colours 


package SupaHotFyaBot;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.lang.*;
import java.math.*;
import java.text.*;


public class ColourMap{
	
	static HashMap<String, String> colour_map = new HashMap<String, String>();	
	static HashMap<String, String> reverse_colour_map = new HashMap<String, String>();		// so we can use the hex code as a key later on
	static HashMap<String, Double[]> rgb_map = new HashMap<String, Double[]>();		// with the rgb codes as the values 

	
	static String[] tokens;
	static String key;	// may not need to be static
	
	static String values;	// may not need to be static
	
	static List<String> keylist = new ArrayList<>();
	static List<String> hexlist = new ArrayList<>();
	static List<Double> rgblist = new ArrayList<>();
	static List<Integer[]> rgbgroups = new ArrayList<>();
	
	
		public static void fillHash() throws FileNotFoundException{
			
			Scanner scanner = new Scanner(new FileReader("/home/oscaricardo/Desktop/ColourCodes.txt"));
			
			while(scanner.hasNextLine()){
				
				tokens = scanner.nextLine().split("\t");
		
				key = tokens[0] + '_' + tokens[1];
				
				keylist.add(key);
						
				values = tokens[2];		
				
				String cleanvalues = values.replace("#", "").replaceAll("\\s+", "");	// remove the # and any whitespace
				
				hexlist.add(cleanvalues);	// store the values in the list so we can use them later without reading from file
				
				colour_map.put(key, cleanvalues);	// store in the colour map 
				reverse_colour_map.put(cleanvalues,  key);	// store in the reverse colour map 
			
				
			//	System.out.print(key);
			//	System.out.println("\t\t" + colour_map.get(key));	// gets the code associated with the key
	
			}
			
			
			scanner.close();
			
			
		}
		
		public static void fillWithRGB(){
			
		
			String[] hexvalue = new String[3];
			String temp;
			double[] tempval = new double[3];
		
			Double[][] floatarray = new Double[hexlist.size()][3];
			
			for(int i = 0; i < hexlist.size(); i++){
								
				temp = hexlist.get(i).toString();	
				
				int x = 0;
				
				for(int k = 0; k < 3; k ++)
				{
					hexvalue[k] = temp.substring(x, x+2);	// split every two pieces
					x = x + 2;
				}
				
				
				for(int j = 0; j < 3; j++){
					
					tempval[j] = (double)Integer.parseInt(hexvalue[j], 16);		// convert hex to double number 
				
					rgblist.add((tempval[j]));	
				

					floatarray[i][j] = tempval[j];
				
				}
				

				rgb_map.put(hexlist.get(i), floatarray[i]);
		
		
		
		
		
				// System.out.println("********" + Arrays.toString(floatarray[i]) + "******");
	

					
			}
			
			/*
			for(int x = 0; x < hexlist.size(); x++){
				
				System.out.println("(" + floatarray[x][0] + "," + floatarray[x][1] + "," + floatarray[x][2] + ")");
			
			}
			*/
			
				
				// System.out.println(rgb_map);	// need to find a way to print the ints as actual ints as opposed to memory addresses
				
				// System.out.print("** TEST: " + Arrays.toString(rgb_map.get(hexlist.get(0))));
			
			
		
		}
			
		static Double[] getRGB(String hexcode){
			String hex = hexcode.toUpperCase();
			return rgb_map.get(hex);	// returns the rgb number for a particular hexcode
	
			
		}
		
		public static Double[] blendColours(String colour1, String colour2, int percent){
			String hex1 = colour1.toUpperCase();		// so you can input lowercase hexcodes
			String hex2 = colour2.toUpperCase();
			
			Integer percentage = (percent);	// for now these are ints
			Integer inverse = (100 - percent);
			Double[] rgb1;
			Double[] rgb2;
			Double[] rgb3 = new Double[3];
			
			
			rgb1 = getRGB(hex1);
			rgb2 = getRGB(hex2);

			System.out.println("The names of the colours you entered are: " + reverse_colour_map.get(hex1) + " and " + reverse_colour_map.get(hex2));
			System.out.println("The RGB codes for the colours you entered are: " + Arrays.toString(rgb1) + " and " + Arrays.toString(rgb2));
			System.out.println("The hex codes for the numbers you entered are: " + hex1 + " and " + hex2);
			System.out.println("You want to blend " + percent + "% " + reverse_colour_map.get(hex1) + " with " + inverse + "% " + reverse_colour_map.get(hex2));
			
	
			Double newX = rgb1[0] * percentage;
			Double newA = rgb2[0] * inverse;
			Double new1 = (newX + newA)/100;
			
			Double newY = rgb1[1] * percentage;;
			Double newB = rgb2[1] * inverse;
			Double new2 = (newY + newB)/100;
			
			Double newZ = rgb1[2] * percentage;
			Double newC = rgb2[2] * inverse;
			Double new3 = (newZ + newC)/100;
	
			rgb3[0] = Double.valueOf(Math.round(new1));	// rounding
			rgb3[1] = Double.valueOf(Math.round(new2));	
			rgb3[2] = Double.valueOf(Math.round(new3));
			
			return rgb3; 
		}
		
		public static String toHex(Double[] decValue){
			
			Integer[] array = new Integer[3];
			
			for(int i = 0; i < 3; i++)
			{
				array[i] = decValue[i].intValue();
				array[i] = decValue[i].intValue();
				array[i] = decValue[i].intValue();
			}
			
			
			
			String hex = "[" + Integer.toHexString(array[0]) + Integer.toHexString(array[1]) + Integer.toHexString(array[2]) + "]";
			
		
	
			return hex;
		}
		
		public static void checkIfExists(String hexvalue){
			if(colour_map.containsValue(hexvalue.toUpperCase()) == true)
			{
				System.out.println("The new colour is actually in the ColourCodes file as: " + reverse_colour_map.get(hexvalue));
			}
			
			else{
				System.out.println("The new colour was not already in the ColourCodes file!");
			}
			
		}
		
		public static void main (String[] args) throws FileNotFoundException{

			new ColourMap();
			fillHash();
			fillWithRGB();
			
			Double[] blend1 = blendColours("a096cf", "9f021e", 50);		// test, it works! 
			String hexval1 = toHex(blend1);
			System.out.println("RGB value for new colour is: " + Arrays.toString(blend1));
			System.out.println("Hex value for new colour is: " + hexval1);
			checkIfExists(hexval1);
			
			
			Double[] blend2 = blendColours("ffc9d7", "cc4834", 60);
			String hexval2 = toHex(blend2);
			System.out.println("RGB value for new colour is: " + Arrays.toString(blend2));
			System.out.println("Hex value for new colour is: " + hexval2);
			checkIfExists(hexval2);
			
			
			
			
			
		}
}

