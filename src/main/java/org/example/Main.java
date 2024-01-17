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
        String line;
        int lineCounter = 0;
        LinkedHashSet<String> lines = new LinkedHashSet<>();
        List<String> words;
        Map<WordPosition, Set<Integer>> wordsRow = new HashMap<>();
        try {
            //https://github.com/danilbo/TestFileReader/raw/master/src/main/resources/test.txt.gz
            //https://github.com/PeacockTeam/new-job/releases/download/v1.0/lng-4.txt.gz
            //URL url = new URL("https://github.com/danilbo/TestFileReader/blob/master/src/main/resources/test.txt.gz");
            URL url = new URL("https://github.com/danilbo/TestFileReader/raw/master/src/main/resources/test.txt.gz");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInputStream));

            while ((line = reader.readLine()) != null) {
                boolean finished = true;
                words = List.of(line.split(";"));
                for (String word : words) {
                    if (!wordIsValid(word)) {
                        finished = false;
                        break;
                    }
                }

                if (!finished || lines.contains(line)) continue;
                lines.add(line);

                for (int i = 0; i < words.size(); i++) {
                    lines.add(line);
                    Set<Integer> lineNumbers = wordsRow.computeIfAbsent(new WordPosition(words.get(i), i), (k) -> new HashSet<Integer>());
                    lineNumbers.add(lines.size() - 1);
                }
            }
            for (String s : lines) {
                System.out.println(s);
            }

            for (WordPosition s : wordsRow.keySet()) {
                if (wordsRow.get(s).size() > 1) {
                    System.out.println(s);
                    System.out.println(wordsRow.get(s));
                }
            }
            System.out.println();
            System.out.println();

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean wordIsValid(String word) {
        return word.matches("\"[a-zA-Z0-9]*\"");
    }
}