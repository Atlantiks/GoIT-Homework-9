package homework9;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {

        int nextChar;
        List<String> words = new ArrayList<>();
        List<Character> allChars = new ArrayList<>();
        Map<String,Integer> wordsFrequency = new HashMap<>();

        try (FileInputStream txtParser = new FileInputStream("/Java/GoIT/src/homework9/words.txt")) {
            do {
                if ((nextChar = txtParser.read()) != -1) allChars.add((char)nextChar);
            } while (nextChar != -1);

        } catch (IOException e) {
            System.out.println("Произошла ошибка ввода/вывода данных");
        }

        StringBuilder sample = new StringBuilder();
        for (Character c : allChars) {
            if (c > 96 && c < 123) {
                sample.append((char)c);
            } else {
                if (sample.toString().length() != 0) {
                    words.add(sample.toString());
                    sample = new StringBuilder();
                }
            }
        }
        words.add(sample.toString());

        for (int i = 0; i < words.size(); i++) {
            Integer oldValue = wordsFrequency.get(words.get(i));
            if (oldValue == null) {
                wordsFrequency.put(words.get(i), 1);
            } else {
                wordsFrequency.put(words.get(i), wordsFrequency.get(words.get(i)) + 1);
            }


        }

        wordsFrequency.entrySet().stream()
                      .sorted(Map.Entry.<String,Integer>comparingByValue()
                              .reversed())
                      .forEach(System.out::println);

    }
}
