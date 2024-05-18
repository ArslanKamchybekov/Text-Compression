import java.io.*;
import java.util.HashMap;

public class Decompressor {
    private final String compressedFilePath;
    private final String decompressedFilePath;

    public Decompressor(String compressedFilePath, String decompressedFilePath) {
        this.compressedFilePath = compressedFilePath;
        this.decompressedFilePath = decompressedFilePath;
    }

    public void decompress(HashMap<String, Integer> wordCodeMap) {
        try {
            // Read compressed file and decompress
            BufferedReader compressedReader = new BufferedReader(new FileReader(compressedFilePath));
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
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }
}