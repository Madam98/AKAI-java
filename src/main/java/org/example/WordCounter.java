package org.example;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class WordCounter {
    private Map<String, Integer> wordCount;

    public WordCounter() {
        this.wordCount = new HashMap<>();
    }

    public void countWords(String[] sentences) {
        for (String sentence : sentences) {
            String[] words = sentence.toLowerCase().split(" ");
            for (String word : words) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
    }

    public Map<Integer, String> getMostCommonWords(int topN) {
        Map<Integer, String> mostCommonWords = wordCount.entrySet().stream()
                .collect(Collectors.groupingBy(Map.Entry::getValue, Collectors.mapping(Map.Entry::getKey, Collectors.joining(", "))));

        return mostCommonWords.entrySet().stream()
                .sorted(Map.Entry.<Integer, String>comparingByKey().reversed())
                .limit(topN)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}