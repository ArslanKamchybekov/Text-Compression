import binary_version.CompressorBinary;
import binary_version.DecompressorBinary;
import number_version.Compressor;
import number_version.Decompressor;

import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        // Files paths
        String inputFilePath = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/input.txt";
        String outputFilePath = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/output.sc";
        String decompressedFilePath = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/readable.txt";

//        String outputFilePathBinary = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/output.bin";
//        String decompressedFilePathBinary = "/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/readableBinary.txt";

        // Compress
        Compressor compressor = new Compressor(inputFilePath, outputFilePath);
        HashMap<String, Integer> wordCodeMap = compressor.compress();

        // Decompress
        Decompressor decompressor = new Decompressor(outputFilePath, decompressedFilePath);
        decompressor.decompress(wordCodeMap);

//        CompressorBinary compressorBinary = new CompressorBinary(inputFilePath, outputFilePathBinary);
//        HashMap<String, Integer> wordCodeMap1 = compressorBinary.compress();
//        DecompressorBinary decompressorBinary = new DecompressorBinary(outputFilePath, decompressedFilePathBinary);
//        decompressorBinary.decompress(wordCodeMap1);

        System.out.println("Compression and decompression completed successfully.");
    }
}
