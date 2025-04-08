package com.slavacom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashSet;

public class TaskA {
    public static class Candidate {
        public String firstName, lastName, patronymic;
        public int birthYear, birthMonth, birthDay;

        public Candidate(String lastName, String firstName, String patronymic, String birthDay, String birthMonth, String birthYear) {
            this.lastName = lastName;
            this.firstName = firstName;
            this.patronymic = patronymic;
            this.birthDay = Integer.parseInt(birthDay);
            this.birthMonth = Integer.parseInt(birthMonth);
            this.birthYear = Integer.parseInt(birthYear);
        }

        public String generateCode() {
            HashSet<Character> uniqueChars = new HashSet<>();

            for (char c : lastName.toCharArray()) uniqueChars.add(c);
            for (char c : firstName.toCharArray()) uniqueChars.add(c);
            for (char c : patronymic.toCharArray()) uniqueChars.add(c);

            int firstChar = lastName.charAt(0) >= 'a' ? lastName.charAt(0) - 'a' + 1 : lastName.charAt(0) - 'A' + 1;
            firstChar *= 256;

            int dayNumsSums = detNumbersSum(birthDay) + detNumbersSum(birthMonth);


            int nums = dayNumsSums * 64 + uniqueChars.size() + firstChar;
            return convertToHex(nums);
        }
    }

    private static String convertToHex(int num) {
        String numStr = "0123456789ABCDEF";

        StringBuilder hex = new StringBuilder();

        int count = 0;
        while (num > 0 && count < 3) {
            hex.insert(0, numStr.charAt(num % 16));
            num /= 16;
            count++;
        }
        while (count < 3) {
            hex.insert(0, '0');
            count++;
        }
        return hex.toString();
    }

    public static int detNumbersSum(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new java.io.InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

        System.out.println();
        int n = Integer.parseInt(reader.readLine());
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split(",");
            Candidate candidate = new Candidate(input[0], input[1], input[2], input[3], input[4], input[5]);
            writer.write(candidate.generateCode());
            writer.write(' ');
        }
        reader.close();
        writer.flush();
        writer.close();


    }
}