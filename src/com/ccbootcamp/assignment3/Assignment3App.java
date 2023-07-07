package com.ccbootcamp.assignment3;


	import java.io.File;
	import java.io.FileNotFoundException;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;


	public class Assignment3App {
	    private static final String FILENAME = "data.txt";
	    private static final int MAX_LOGIN_ATTEMPTS = 5;

	    public static void main(String[] args) {
	        List<User> users = new ArrayList<>();
	        try {
	        	File myObj = new File (FILENAME);
	            Scanner scanner = new Scanner(myObj);
	            while (scanner.hasNextLine()) {
	                String[] userData = scanner.nextLine().split(",");
	                String username = userData[0].trim();
	                String password = userData[1].trim();
	                String name = userData[2].trim();
	                users.add(new User(username, password, name));
	            }
	            scanner.close();
	        } catch (FileNotFoundException e) {
	            System.out.println("File not found: " + FILENAME);
	            System.exit(1);
	        }

	        Scanner inputScanner = new Scanner(System.in);
	        int loginAttempts = 0;
	        while (loginAttempts < MAX_LOGIN_ATTEMPTS) {
	            System.out.print("Username: ");
	            String usernameInput = inputScanner.nextLine();
	            System.out.print("Password: ");
	            String passwordInput = inputScanner.nextLine();

	            boolean isLoginValid = false;
	            for (User user : users) {
	                if (user.getUsername().equals(usernameInput) && user.getPassword().equals(passwordInput)) {
	                    isLoginValid = true;
	                    System.out.println("Welcome " + user.getName());
	                    break;
	                }
	            }

	            if (isLoginValid) {
	                break;
	            } else {
	                loginAttempts++;
	                if (loginAttempts == MAX_LOGIN_ATTEMPTS) {
	                    System.out.println("Too many failed login attempts, you are now locked out.");
	                } else {
	                    System.out.println("Invalid login, please try again.");
	                }
	            }
	        }

	        inputScanner.close();
	    }
	}


