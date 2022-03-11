package chapter16;

import java.util.Arrays;
import java.util.List;

public class FilteringExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("qwe","wer","qrt","wer","qdf","dfg");

        names.stream()
                .distinct()
                .forEach(n -> System.out.println(n));
        System.out.println();

        names.stream()
                .filter(n -> n.startsWith("q"))
                .forEach(n -> System.out.println(n));
        System.out.println();

        names.stream()
                .distinct()
                .filter(n -> n.startsWith("q"))
                .forEach(n -> System.out.println(n));
        System.out.println();

    }
}
