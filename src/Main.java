import java.io.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        //Read the input file
        HashMap<String, Integer> frequencyMap = new HashMap<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(
                    new FileReader("/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/input.txt"));
            String str;
            while((str = bufferedReader.readLine()) != null){
                String[] words = str.split("\\s+");
                for(String word: words) {
                    word = word.toLowerCase();
                    frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
        //Write the compressed file
        try{
            BufferedWriter bufferedWriter = new BufferedWriter(
                    new FileWriter("/Users/arslankamchybekov/Desktop/Intellij Projects/Text Compression/output.sc"));
            for(String word: frequencyMap.keySet()){
                int frequency = frequencyMap.get(word);
                bufferedWriter.write(word + " : " + frequency + "\n");
            }
        }catch (IOException e){
            e.getLocalizedMessage();
        }
    }
}