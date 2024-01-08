import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class History {
    private String fileName;
    public void ArrayListSaver(String fileName) {
        this.fileName = fileName;
    }
    public void save(List<Token> arrayList) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (Token element : arrayList) {
                bufferedWriter.write(String.valueOf(element));
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
            System.out.println("Save to file " + fileName);
        } catch (IOException e) {
            System.out.println("Error " + fileName);
            e.printStackTrace();
        }
    }
}

