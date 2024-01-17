package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.io.FileWriter;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class Main {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String filePath = "C:/Projects/Java/FileReader/src/main/resources/lng.txt";
        String line;
        LinkedList<Set<Integer>> setList = new LinkedList<>();
        Set<String> lines = new HashSet<>();
        List<String> arrayOfLines = new ArrayList<>();
        List<String> words;
        Map<WordPosition, Set<Integer>> wordsRow = new HashMap<>();
        try {

            // Создаем объект File
            File file = new File(filePath);
            // Создаем FileReader для чтения файла
            FileReader fileReader = new FileReader(file);
            // Создаем BufferedReader для более эффективного чтения
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            // Читаем файл построчно
            while ((line = bufferedReader.readLine()) != null) {
                boolean finished = true;
                // Делим каждую строчку на слова
                words = List.of(line.split(";"));
                // В цикле проверяем каждое слово на валидность
                for (String word : words) {
                    if (!wordIsValid(word)) {
                        finished = false;
                        break;
                    }
                }

                if (!finished || lines.contains(line)) continue;
                if (lines.add(line)) arrayOfLines.add(line);

                for (int i = 0; i < words.size(); i++) {
                    lines.add(line);
                    Set<Integer> lineNumbers = wordsRow.computeIfAbsent(new WordPosition(words.get(i), i), (k) -> new HashSet<Integer>());
                    lineNumbers.add(lines.size() - 1);
                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (WordPosition s : wordsRow.keySet()) {
            if (wordsRow.get(s).size() > 1) {
                setList.add(wordsRow.get(s));
            }
        }

        List<Set<Integer>> groupLineIds = mergeSets(setList);
        printGroups(groupLineIds, arrayOfLines);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime/1000000000);

    }

    public static boolean wordIsValid(String word) {
        return word.matches("\"[a-zA-Z0-9]*\"");
    }

    public static List<Set<Integer>> mergeSets(LinkedList<Set<Integer>> inputList) {
        List<Set<Integer>> result = new ArrayList<>();

        for (int i = 0; i < inputList.size(); i++) {
            Set<Integer> currentSet = inputList.get(i);
            for (int j = i + 1; j < inputList.size(); j++) {
                if (i == j) continue;
                Set<Integer> commonElements = new HashSet<>(currentSet);
                commonElements.retainAll(inputList.get(j));
                if (!commonElements.isEmpty()) {
                    currentSet.addAll(inputList.get(j));
                    inputList.remove(j);
                }
            }
            result.add(currentSet);
        }

        result.sort((e1, e2) -> e2.size() - e1.size());

        return result;
    }

    public static void printGroups(List<Set<Integer>> groups, List<String> lines) {
        System.out.println("groups quantity: " + groups.size());
        for (int i = 0; i < groups.size(); i++) {
            System.out.println();
            System.out.println("Group " + (i + 1));
            for (Integer j : groups.get(i)) {
                System.out.println(lines.get(j));
            }
        }
    }
}