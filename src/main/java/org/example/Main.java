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

        Set<String> result = new HashSet<>();
        try {
            URL url = new URL("https://github.com/PeacockTeam/new-job/releases/download/v1.0/lng-4.txt.gz");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = connection.getInputStream();
            GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
            BufferedReader reader = new BufferedReader(new InputStreamReader(gzipInputStream));
            List<String> words = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                words = List.of(line.split(";"));
                for (String word: words) {

                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean wordIsValid(String word){
        return word.matches("\"[a-zA-Z0-9]*\"");
    }
}