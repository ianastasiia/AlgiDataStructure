import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InsertTest {
    public static void main(String[] args) {
        try {
            //запись заголовка в csv файл
            BufferedWriter writer = new BufferedWriter(new FileWriter("testsResult" + "/InsertTest"));
            String delimiter = ";";
            StringBuilder s = new StringBuilder();
            List w = Arrays.asList("Время работы алгоритмы", delimiter, "Количество итераций", "\n");
            for(Object str: w) {
                s.append(str);
            }
            writer.write(s.toString());

            for(int k = 1; k < 51; k++) {
                // считывание данных из файла
                Scanner sc = new Scanner(new File("tests" + "/test" + k));
                String in = sc.nextLine();
                String[] n = in.split(";");
                int[] numbers = new int[n.length];
                for (int i = 0; i < n.length; i++) {
                    numbers[i] = Integer.parseInt(n[i]);
                }

                //измерение времени работы и количества итераций
                AATree tree = new AATree();
                long totalTime = 0;
                int totalIterationsCount = 0;

                for(int i = 0; i < numbers.length; i++) {
                    long startTime = System.nanoTime();
                    tree.insert(i);
                    totalTime += System.nanoTime() - startTime;
                    totalIterationsCount += tree.iterationCount;
                }

                //запись данных в csv файл
                s = new StringBuilder();
                w = Arrays.asList(totalTime, delimiter, totalIterationsCount, "\n");
                for(Object str: w) {
                    s.append(str);
                }
                writer.write(s.toString());
            }
            writer.flush();
            writer.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }




    }
}



