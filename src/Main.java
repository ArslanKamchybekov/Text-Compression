import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Files paths
        String inputFilePath = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/input.txt";
        String outputFilePath = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/output.sc";
        String decompressedFilePath = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/readable.txt";

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
            HashMap<String, Integer> wordCodeMap = new HashMap<>();
            int code = 0; // Code increment
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

            // Read compressed file and decompress
            BufferedReader compressedReader = new BufferedReader(new FileReader(outputFilePath));
            StringBuilder decompressedText = new StringBuilder();
            String compressedLine;
            while ((compressedLine = compressedReader.readLine()) != null) {
                String[] compressedWords = compressedLine.split("\\s+");
                for (String compressedWord : compressedWords) {
                    int wordCode = Integer.parseInt(compressedWord);
                    for (String key : wordCodeMap.keySet()) {
                        if (wordCodeMap.get(key) == wordCode) {
                            decompressedText.append(key).append(" ");
                            break;
                        }
                    }
                }
            }
            compressedReader.close();

            // Write decompressed file
            BufferedWriter decompressedWriter = new BufferedWriter(new FileWriter(decompressedFilePath));
            decompressedWriter.write(decompressedText.toString());
            decompressedWriter.close();

            // Compare files
            FileInputStream inputFileStream = new FileInputStream(inputFilePath);
            FileInputStream readableFileStream = new FileInputStream(decompressedFilePath);
            boolean filesMatch = true;
            int inputByte, readableByte;
            while ((inputByte = inputFileStream.read()) != -1 && (readableByte = readableFileStream.read()) != -1) {
                if (inputByte != readableByte) {
                    filesMatch = false;
                    break;
                }
            }
            inputFileStream.close();
            readableFileStream.close();

            if (filesMatch) {
                System.out.println("Files' memories match.");
            } else {
                System.out.println("Files' memories do not match.");
            }
            System.out.println("Compression and decompression completed successfully.");
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }
}