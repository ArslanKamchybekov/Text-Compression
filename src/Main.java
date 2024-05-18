import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Files paths
        String inputFilePath = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/input.txt";
        String outputFilePath = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/output.sc";
        String decompressedFilePath = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/readable.txt";

        // Compress
        Compressor compressor = new Compressor(inputFilePath, outputFilePath);
        HashMap<String, Integer> wordCodeMap = compressor.compress();

        // Decompress
        Decompressor decompressor = new Decompressor(outputFilePath, decompressedFilePath);
        decompressor.decompress(wordCodeMap);

        System.out.println("Compression and decompression completed successfully!");
    }
}
