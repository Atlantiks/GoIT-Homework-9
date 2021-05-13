package homework9;

import java.io.FileInputStream;
import java.io.IOException;

public class TelNumberParser {

    static boolean isOfFormatOne(String input) {

        char[] allChars = input.toCharArray();

        if (allChars[0] != '(') return false;
        if (allChars[4] != ')') return false;
        if (allChars[5] != ' ') return false;
        if (allChars[9] != '-') return false;

        int lastChrToCheck = allChars[allChars.length - 1] == 13 ? allChars.length - 1 : allChars.length;

        for (int i = 0; i < lastChrToCheck; i++) {
            if (i == 0 || i == 4 || i == 5 || i == 9) continue;
            if (allChars[i] > 57 || allChars[i] < 48) {
                return false;
            }
        }

        return true;
    }
    static boolean isOfFormatTwo(String input) {

        char[] allChars = input.toCharArray();

        if (allChars[3] != '-') return false;
        if (allChars[7] != '-') return false;

        int lastChrToCheck = allChars[allChars.length - 1] == 13 ? allChars.length - 1 : allChars.length;

        for (int i = 0; i < lastChrToCheck; i++) {
            if (i == 3 || i == 7) continue;
            if (allChars[i] > 57 || allChars[i] < 48) return false;
        }

        return true;
    }

    public static void main(String[] args) {
        int nextChar;
        StringBuilder result = new StringBuilder();

        try (FileInputStream txtParser = new FileInputStream("/Java/GoIT/src/homework9/Text.txt")) {
            do {
                if ((nextChar = txtParser.read()) != -1)
                    result.append((char)nextChar);
            } while (nextChar != -1);

        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода/вывода данных");
        }

        String[] txtLines = result.toString().split("\n");
        for (String txtLine : txtLines)
            if(isOfFormatOne(txtLine) || isOfFormatTwo(txtLine)) System.out.println(txtLine);

    }
}
