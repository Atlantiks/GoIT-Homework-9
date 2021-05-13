package homework9;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JSON_Builder {
    public static void main(String[] args) {

        StringBuilder output = new StringBuilder();

        output.append('[').append("\n");

        String[] fields;
        String[] values;


        String[] header = parseFileForHeader();
        List<String> content = parseFileForData();

        for (int i = 0; i < header.length; i++) {
            content.remove(0);
        }



        fields = new String[header.length];
        values = new String[header.length];

        int valueIndex = 0;
        int numberOfEntries = content.size() / header.length;

        for (int j = 0; j < numberOfEntries; j++) {
            for (int i = 0; i < header.length; i++) {
                fields[i] = header[i];
                values[i] = content.get(valueIndex);
                valueIndex++;
            }
            output.append(newUser(fields,values));
            if (j != numberOfEntries - 1) {
                output.append(",\n");
            } else {
                output.append("\n");
            }
        }


        output.append("\n").append(']');

        System.out.println(output.toString());
        writeToFile(output.toString());


    }

    public static String newUser(String[] fields, String[] values) {

        StringBuilder output = new StringBuilder();
        output.append("\n").append('{').append("\n\n");

        for (int i = 0; i < fields.length; i++) {
            output
                    .append("\"")
                    .append(fields[i])
                    .append("\":");

            try (Scanner sc = new Scanner(values[i])) {
                if (sc.hasNextInt()) {
                    output.append(sc.nextInt());
                } else {
                    output.append("\"").append(values[i]).append("\"");
                }
            } catch (Exception e) {
                System.out.println("Exception");
            }

            if (i !=  fields.length - 1) output.append(",");
            output.append("\n\n");
        }

        output.append('}');
        return output.toString();
    }

    public static String[] parseFileForHeader() {

        StringBuilder output = new StringBuilder();

        try (FileInputStream fr = new FileInputStream("/Java/GoIT/src/homework9/file.txt")) {
            int c = fr.read();


            do {
                if (c == 10 || c == 13) break;
                if (c != -1) {
                    output.append((char)c);
                }
                c = fr.read();
            } while (c != -1);

        } catch (IOException e) {
            System.out.println("Ошибка");
        }

        return output.toString().trim().split(" ");

    }

    public static List<String> parseFileForData() {
        List<String> output = new ArrayList<>();
        StringBuilder content = new StringBuilder();

        try (FileInputStream fr = new FileInputStream("/Java/GoIT/src/homework9/file.txt")) {

            int c = fr.read();
            do {
                if (c != -1) {
                    content.append((char)c);
                }
                c = fr.read();
            } while (c != -1);
        } catch (IOException e) {
            System.out.println("Ошибка доступа");
        }

        StringBuilder sample = new StringBuilder();
        char[] b = content.toString().toCharArray();
        for (Character c : b) {
            if (c > 96 && c < 123 || c > 47 && c < 58) {
                sample.append((char)c);
            } else {
                if (sample.toString().length() != 0) {
                    output.add(sample.toString());
                    sample = new StringBuilder();
                }
            }
        }
        output.add(sample.toString());

        return output;
    }

    public static void writeToFile(String contentToWrite) {
        char[] chars = new char[contentToWrite.length()];
        contentToWrite.getChars(0,contentToWrite.length(),chars,0);

        byte[] buffer = contentToWrite.getBytes();

        try (FileOutputStream fr = new FileOutputStream("/Java/GoIT/src/homework9/user.json")) {
            fr.write(buffer);
        } catch (IOException e) {
            System.out.println("Ошибка");
        }

        try (FileWriter fr = new FileWriter("/Java/GoIT/src/homework9/user2.json")) {
            fr.write(chars);
        } catch (IOException e) {
            System.out.println("Ошибка");
        }
    }
}
