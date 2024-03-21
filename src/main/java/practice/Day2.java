package practice;

import java.util.Arrays;
import java.util.Comparator;

public class Day2 {
    public static  void SortArray(Integer[] numbers){
        int n = numbers.length;



        // Print the sorted array
        for (int number : numbers) {
            System.out.println(number);
        }

        // Perform bubble sort
       /* for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    // Swap numbers[j] and numbers[j + 1]
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }*/

        Arrays.sort(numbers);
        Arrays.asList(numbers).stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);

        for (int number : numbers) {
            System.out.println(number);
        }
    }
}
