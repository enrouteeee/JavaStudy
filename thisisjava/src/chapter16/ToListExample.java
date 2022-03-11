package chapter16;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ToListExample {
    public static void main(String[] args) {
        List<Member> list = Arrays.asList(
                new Member("qwe", Member.MALE, 30),
                new Member("asd", Member.FEMALE, 20),
                new Member("zxc", Member.MALE, 45),
                new Member("wer", Member.FEMALE, 27)
        );

        //남학생들만
        List<Member> maleList = list.stream()
                .filter(s -> s.getSex() == Member.MALE)
                .collect(Collectors.toList());
        maleList.stream()
                .forEach(s -> System.out.println(s.getName()));
        
    }
}
