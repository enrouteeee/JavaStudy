package chapter16;

import com.sun.source.doctree.SeeTree;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ParallelExample {
    public static void main(String[] args) {
        List<String> list = Arrays.asList(
                "qwe", "asd", "zxc", "wer", "sdf", "xcv"
        );

        Stream<String> stream = list.stream();
        stream.forEach(ParallelExample :: print);
        System.out.println();

        Stream<String> parallelStream = list.parallelStream();
        parallelStream.forEach(ParallelExample::print);
    }

    private static void print(String str) {
        System.out.println(str+ " : " + Thread.currentThread().getName());
    }
}
