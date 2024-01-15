package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileOutput {
    public static void main(String[] args) {
        // Предполагается, что у вас есть список групп строк
        List<List<String>> groups = new ArrayList<>();
        // Заполните список групп строк

        try {
            FileWriter writer = new FileWriter("output.txt");

            writer.write("Количество групп с более чем одним элементом: " + groups.size() + "\n");
            for (List<String> group : groups) {
                writer.write("Группа " + (groups.indexOf(group) + 1) + "\n");
                for (String line : group) {
                    writer.write(line + "\n");
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}