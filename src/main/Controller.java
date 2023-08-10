package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;
import java.util.TreeMap;

import javax.swing.JOptionPane;

public class Controller {
	
	Properties properties = new Properties();
	FileInputStream inputStream;

	private static Scanner scanner;
    private static String codeFileLocation;
    private static File feeDBFile;

    public static Scanner templateScanner;
    public static String templatesFolderLocation;

    public static ArrayList<String> insuranceNames;
	public static HashMap<String, HashMap<String, ArrayList>> codes;
	public static TreeMap<String, String> codeDescriptions;
	public static TreeMap<String, String> favoriteCodeDescriptions;
	public static HashMap<String, ArrayList> insuranceCompaniesMap;
	public static int favoritesLimit = 40;

	public String userName;
	public String userTitle;
	public locationOfCare locationOfCare = new locationOfCare();

	public class locationOfCare {

			String name = "Southeast Neuroscience Center";
			String address = "128 Neuroscience Ct, Gray, LA 70359";
			String phone = "(985)917-3007";
			String fax = "(985)917-3010";

			public locationOfCare() {

			}

	}

	public static boolean hideCodesSetting = false;

	public Controller() {
		readConfigFile();
		readCodeFile();

	}

	public void readConfigFile() {
		
		try {
			inputStream = new FileInputStream("config.properties");
			properties.load(inputStream);
			codeFileLocation = properties.getProperty("codeFileLocation");
			templatesFolderLocation = properties.getProperty("templatesFolderLocation");
			
			if(properties.containsKey(System.getProperty("user.name"))) {
				userName = properties.getProperty(System.getProperty("user.name")).split("\\|")[0];
				userTitle = properties.getProperty(System.getProperty("user.name")).split("\\|")[1];	
			}	
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There was an error trying to find the config file.");
			return;
		}
		
	}
	
	public static void readCodeFile() {

		insuranceNames = new ArrayList<>();

		/*

		CODES HASHMAP (Explanation)

		<K,V> (K (String) = code Number : V (HashMap<String, ArrayList>)= <K2, V2>) ->

			-> (K2 (String) = insurance Name : V2 (Arraylist<String>) = [0] = code Description : [1] = code Cost (for that ins))

		*/


		codes = new HashMap<>();
        codeDescriptions = new TreeMap<>();
        favoriteCodeDescriptions = new TreeMap<>();

        try {
        	feeDBFile = new File(codeFileLocation);
        	scanner = new Scanner(feeDBFile);
		}catch (Exception e) {
			return;
		}

        String currentCode = "";
        String currentDescription = "";
        //String procedure = "";

        String[] input = scanner.nextLine().split(",");

        for (int i = 3; i < input.length; i++) {
            insuranceNames.add(input[i]);
            //System.out.print(input[i] + " | ");
        }

        while (scanner.hasNextLine()) {
        	//Redeclare this map so that a new (empty) map is made for each code
        	insuranceCompaniesMap = new HashMap<>();

            input = scanner.nextLine().split(",");

            //Input[] === CPT Code[0], CPT Description[1], Procedure/Department[2], Cost[3+]

            currentCode = input[0];
            currentDescription = input[1];
            //procedure = input[2];

            if(input.length < insuranceNames.size() + 3){
            	//This ensures that if there is a missing element on the end, it will still make an array with the correct size.
                String[] temp = Arrays.copyOf(input, insuranceNames.size() + 3);
                input = temp;
            }



            int insuranceNamesIndex = 0;

            for (int i = 3; i < insuranceNames.size() + 3; i++) {
            //for loop to add each code information to each individual ins. company.
                if(input[i] == null || input[i].trim() == ""){
                	//if the input at I is blank or null there is no data -> No amount
                    input[i] = "0";
                }

                ArrayList<String> currentCodeInfo = new ArrayList<>();
                currentCodeInfo.add(currentDescription);
                currentCodeInfo.add(input[i]);

                //System.out.println(cptCode+ " " + tempInput);

                insuranceCompaniesMap.put(insuranceNames.get(insuranceNamesIndex++), currentCodeInfo);
                codes.put(currentCode, insuranceCompaniesMap);
            }

            codeDescriptions.put(currentCode,currentDescription);

            if(favoriteCodeDescriptions.size() < favoritesLimit) {
            	favoriteCodeDescriptions.put(currentCode + " | " + currentDescription, "");
            }

        }

        Collections.sort(insuranceNames);


	}

	public void setUserName(String x) {

		userName = x;

	}

	public void setTitle(String x) {

		userTitle = x;

	}

	public void saveUserInfo(String x, String y) {
		
		FileOutputStream outputStream;
		
		try {
			outputStream = new FileOutputStream("config.properties");
			
			if(!properties.containsKey(System.getProperty("user.name"))) {
				
				properties.put(System.getProperty("user.name"), x + "|" + y);
				properties.store(outputStream, null);
				System.out.println("(saveUserInfo) saving new user");
				
			}else {
				
				properties.setProperty(System.getProperty("user.name"), x + "|" + y);
				properties.store(outputStream, null);
				System.out.println("(saveUserInfo) saving current user");
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "There was an error trying to save to the config file.");
			e.printStackTrace();
			return;
		}
		
		
		
		
	}
		
	
	}
