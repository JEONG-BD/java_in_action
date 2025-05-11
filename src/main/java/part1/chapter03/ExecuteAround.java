package part1.chapter03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ExecuteAround {

  
    private static final String FILE = "/Users/jbd/upload/data.txt";
  
    public static void main(String... args) throws IOException {
  
      String result = processFileLimited();
      System.out.println(result);
  
      System.out.println("--- one line");
  
      String oneLine = processFile((BufferedReader b) -> b.readLine());
      System.out.println(oneLine);
      System.out.println("--- two line");
      
      String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
      System.out.println(twoLines);

      System.out.println("--- all line");

      String allLines = processFile((BufferedReader b) -> {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = b.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
      });
      System.out.println(allLines);
    }
  
    public static String processFileLimited() throws IOException {
      try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
        return br.readLine();
      }
    }
    
    public static String processFile(BufferedReaderProcessor p) throws IOException {
      try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {
        return p.process(br);
      }
    }
  
    public interface BufferedReaderProcessor {
  
      String process(BufferedReader b) throws IOException;
  
    }
}