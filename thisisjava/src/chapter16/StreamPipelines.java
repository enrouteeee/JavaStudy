package chapter16;

import java.util.Arrays;
import java.util.List;

public class StreamPipelines {
    public static void main(String[] args) {
        List<Member> list = Arrays.asList(
                new Member("qwe", Member.MALE, 30),
                new Member("asd", Member.FEMALE, 20),
                new Member("zxc", Member.MALE, 45),
                new Member("wer", Member.FEMALE, 27)
        );

        double ageAvg = list.stream()
                .filter(m -> m.getSex() == Member.MALE)
                .mapToInt(Member::getAge)
                .average()
                .getAsDouble();

        System.out.println("남자 평균 나이: " + ageAvg);
    }
}
