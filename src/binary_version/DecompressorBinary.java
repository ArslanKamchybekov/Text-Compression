package binary_version;

import java.io.*;
import java.util.HashMap;

public class DecompressorBinary {
    private final String compressedFilePath;
    private final String decompressedFilePath;

    public DecompressorBinary(String compressedFilePath, String decompressedFilePath) {
        this.compressedFilePath = compressedFilePath;
        this.decompressedFilePath = decompressedFilePath;
    }

    public void decompress(HashMap<String, Integer> wordCodeMap) {
        // Create reverse map from wordCodeMap
        HashMap<Integer, String> codeWordMap = new HashMap<>();
        for (String word : wordCodeMap.keySet()) {
            codeWordMap.put(wordCodeMap.get(word), word);
        }

        try {
            // Read the compressed file (binary format)
            DataInputStream dataInputStream = new DataInputStream(new FileInputStream(compressedFilePath));
            StringBuilder decompressedText = new StringBuilder();
            try {
                while (true) {
                    int wordCode = dataInputStream.readInt(); // Read code as 4-byte binary
                    String word = codeWordMap.get(wordCode);
                    if (word != null) {
                        decompressedText.append(word).append(" ");
                    } else {
                        throw new IOException("Invalid word code during decompression: " + wordCode);
                    }
                }
            } catch (EOFException e) {
                // End of file reached
            }
            dataInputStream.close();

            // Write decompressed text to output file
            BufferedWriter writer = new BufferedWriter(new FileWriter(decompressedFilePath));
            writer.write(decompressedText.toString().trim());
            writer.close();
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
    }
}
