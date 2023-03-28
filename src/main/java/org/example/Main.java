package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println(calc(reader.readLine()));
    }

    public static String calc(String input) {
        String[] values = input.split(" ");
        boolean romeX = false;
        boolean romeY = false;
        int answer = 0;
        int x = 0;
        int y = 0;
        String action = null;

        if (values.length < 2 || values.length > 3) {
            throw new RuntimeException();
        }

        if(isNumeric(values[0])) {
            x = Integer.parseInt(values[0]);
        } else {
            romeX = true;
            x = conversionFromRoman(values[0]);
        }

        if(isNumeric(values[2])) {
            y = Integer.parseInt(values[2]);
        } else {
            romeY = true;
            y = conversionFromRoman(values[2]);
        }

        if (!(x < 0 || x > 10 || y < 0 || y > 10 || (!romeX && romeY) || (romeX && !romeY))) {
            action = values[1];
        } else {
            throw new RuntimeException();
        }

        switch (action) {
            case "+" -> answer = x + y;
            case "-" -> answer = x - y;
            case "*" -> answer = x * y;
            case "/" -> answer = x / y;
        }

        if (romeX) {
            if (answer > 0) {
                return conversionToRoman(answer);
            } else {
                throw new RuntimeException();
            }
        } else {
            return String.valueOf(answer);
        }
    }

    public static int conversionFromRoman(String value) {
        for (int i = 0; i < 10; i++) {
            if (Roman.values()[i].name().equals(value)) {
                return i + 1;
            }
        }
        return 0;
    }

    public static String conversionToRoman(int value) {
        return String.valueOf(Roman.values()[value - 1]);
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}