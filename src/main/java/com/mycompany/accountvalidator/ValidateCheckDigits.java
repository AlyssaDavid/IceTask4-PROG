/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.accountvalidator;
    import java.io.*;
import java.util.*;
/**
 *
 * @author alyss
 */



public class ValidateCheckDigits {
    public static void main(String[] args) {
        // Input file with account numbers
        String inputFile = "accounts.txt";   
        // Output file for valid accounts
        String outputFile = "valid_accounts.txt"; 

        try (
            Scanner scanner = new Scanner(new File(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile))
        ) {
            while (scanner.hasNextLine()) {
                String account = scanner.nextLine().trim();

                // Check format: must be 6 digits
                if (account.length() == 6 && account.matches("\\d{6}")) {
                    if (isValidAccount(account)) {
                        System.out.println(account + " → VALID");
                        writer.println(account); // Save valid accounts
                    } else {
                        System.out.println(account + " → INVALID");
                    }
                } else {
                    System.out.println(account + " → INVALID FORMAT");
                }
            }
            System.out.println("\n✅ Validation complete. Valid accounts saved to " + outputFile);
        } catch (IOException e) {
            System.out.println("Error reading/writing files: " + e.getMessage());
        }
    }

    // Validation method
    public static boolean isValidAccount(String account) {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            sum += Character.getNumericValue(account.charAt(i));
        }
        int remainder = sum % 10;
        int lastDigit = Character.getNumericValue(account.charAt(5));
        return remainder == lastDigit;
    }
}


