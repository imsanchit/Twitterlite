package com.example.sanchit.twitterlite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;


public class NaiveBayesExample {

    public static String[] readLines(URL url) throws IOException {

        Reader fileReader = new InputStreamReader(url.openStream(), Charset.forName("UTF-8"));
        List<String> lines;
        try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            lines = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
                //		System.out.println(line);
            }
        }

        return lines.toArray(new String[lines.size()]);
    }

/*    public static void main(String[] args) throws IOException {

        Map<String, URL> trainingFiles = new HashMap<>();
        trainingFiles.put("English", NaiveBayesExample.class.getResource("/training.language.en.txt"));
        trainingFiles.put("French", NaiveBayesExample.class.getResource("/training.language.fr.txt"));
        trainingFiles.put("German", NaiveBayesExample.class.getResource("/training.language.de.txt"));

        Map<String, String[]> trainingExamples = new HashMap<>();
        for(Map.Entry<String, URL> entry : trainingFiles.entrySet()) {
		    trainingExamples.put(entry.getKey(), readLines(entry.getValue()));
        }

        NaiveBayes nb = new NaiveBayes();
        nb.setChisquareCriticalValue(6.63);
        nb.train(trainingExamples);

        NaiveBayesKnowledgeBase knowledgeBase = nb.getKnowledgeBase();

        nb = null;
        trainingExamples = null;

        nb = new NaiveBayes(knowledgeBase);
        String exampleEn = "ram";
        String outputEn = nb.predict(exampleEn);
        System.out.format("The sentense \"%s\" was classified as \"%s\".%n", exampleEn, outputEn);

        String exampleFr = "bghugughghughjkhiugy";
        String outputFr = nb.predict(exampleFr);
        System.out.format("The sentense \"%s\" was classified as \"%s\".%n", exampleFr, outputFr);

        String exampleDe = "tournoi";
        String outputDe = nb.predict(exampleDe);
        System.out.format("The sentense \"%s\" was classified as \"%s\".%n", exampleDe, outputDe);
    }  */
}