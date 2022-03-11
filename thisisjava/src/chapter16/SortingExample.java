package chapter16;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

public class SortingExample {
    public static void main(String[] args) {
        IntStream intStream = Arrays.stream(new int[] {5, 3, 2, 1, 4});
        intStream
                .sorted()
                .forEach(n -> System.out.println(n + "," ));
        System.out.println();

        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 30),
                new Student("임한길", 20),
                new Student("임두길", 10),
                new Student("임세길", 15)
        );

        studentList.stream()
                .sorted()
                .forEach(s -> System.out.println(s.getScore() + ","));
        System.out.println();

        studentList.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(s -> System.out.println(s.getScore() + ","));
    }
}
