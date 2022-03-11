package chapter16;

import java.util.Arrays;
import java.util.List;

public class ReductionExample {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 92),
                new Student("임두길", 95),
                new Student("임세길", 88),
                new Student("임네길", 98)
        );

        int sum = studentList.stream()
                .map(Student::getScore)
                .reduce((a,b) -> a+b)
                .get();

        System.out.println("sum : " + sum);
    }
}
