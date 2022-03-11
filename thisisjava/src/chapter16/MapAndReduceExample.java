package chapter16;

import java.util.Arrays;
import java.util.List;

public class MapAndReduceExample {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("qew", 10),
                new Student("asd", 20),
                new Student("zxc", 30)
        );

        double avg = list.stream()
                .mapToInt(Student::getScore)
                .average()
                .getAsDouble();

        System.out.println("평균 점수 : " + avg);
    }
}
