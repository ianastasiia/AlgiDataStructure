import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        try {
            //запись заголовка в csv файл
            BufferedWriter writer = new BufferedWriter(new FileWriter("TestResult"));
            String delimiter = ";";
            StringBuilder s = new StringBuilder();
            List w = Arrays.asList("Количество входных данных", delimiter,
                    "Время работы алгоритмы", delimiter, "Количество итераций", "\n");
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

                //измерение времени работы и количества итерация
                long startTime = System.nanoTime();
                RadixSortLCD.radixSort(numbers, numbers.length);
                long endTime = System.nanoTime();

                //проверка работы корректности программы
                int[] output = RadixSortLCD.radixSort(numbers, numbers.length);
                Arrays.sort(numbers);
                if (!Arrays.equals(output, numbers)) {
                    System.out.println("TEST FAILED");
                    System.out.println(Arrays.toString(output));
                }

                //запись данных в csv файл
                s = new StringBuilder();
                w = Arrays.asList(numbers.length, delimiter,
                        endTime - startTime, delimiter, RadixSortLCD.iterationCount, "\n");
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



