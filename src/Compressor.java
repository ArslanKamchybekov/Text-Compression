import java.io.*;
import java.util.HashMap;

public class Compressor {
    private final String inputFilePath;
    private final String outputFilePath;

    public Compressor(String inputFilePath, String outputFilePath) {
        this.inputFilePath = inputFilePath;
        this.outputFilePath = outputFilePath;
    }

    public HashMap<String, Integer> compress() {
        HashMap<String, Integer> wordCodeMap = new HashMap<>();
        try {
            // Read input file
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            StringBuilder inputText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                inputText.append(line).append("\n");
            }
            reader.close();

            // Identify words and assign codes
            int code = 0;
            String[] words = inputText.toString().split("\\s+");
            for (String word : words) {
                if (!wordCodeMap.containsKey(word)) {
                    wordCodeMap.put(word, code++); //Increment code as new word occurs
                }
            }

            // Compress into output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));
            for (String word : words) {
                int wordCode = wordCodeMap.get(word);
                writer.write(wordCode + " ");
            }
            writer.close();
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
        return wordCodeMap;
    }
}