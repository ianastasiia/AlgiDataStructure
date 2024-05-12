import java.io.FileWriter;
import java.io.IOException;
import java.util.StringJoiner;

public class GenerateTest {
    public static void main(String[] args) throws IOException {
        try {
            for(int num = 1; num < 51; num++) {
                FileWriter writer = new FileWriter("tests" + "/test" + num);
                int countElems = 10000;
                StringJoiner joiner = new StringJoiner(";");
                for (int i = 0; i < countElems; i++) {
                    int randomValue = 1 + (int) (Math.random() * (100_000_000 - 1 + 1));
                    joiner.add(String.valueOf(randomValue));

                }
                writer.write(String.valueOf(joiner));
                writer.close();
            }
        }
        catch (IOException e) {
            System.out.println("Ошибка при записи в файл");
            e.printStackTrace();
        }
    }
}
