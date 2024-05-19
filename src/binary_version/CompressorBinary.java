package binary_version;

import java.io.*;
import java.util.HashMap;

public class CompressorBinary {

    private final String inputFilePath;
    private final String outputFilePath;

    public CompressorBinary(String inputFilePath, String outputFilePath) {
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
                    wordCodeMap.put(word, code++);
                }
            }

            // Compress into output file (binary format)
            DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(outputFilePath));
            for (String word : words) {
                int wordCode = wordCodeMap.get(word);
                dataOutputStream.writeInt(wordCode); // Write code as 4-byte binary
            }
            dataOutputStream.close();
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
        return wordCodeMap;
    }
}
