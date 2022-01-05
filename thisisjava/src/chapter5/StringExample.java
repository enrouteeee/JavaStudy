package chapter5;

public class StringExample {
    public static void main(String[] args) {
        String str1 = "자바";
        String str2 = "자바";

        String str3 = new String("자바");
        String str4 = new String("자바");
        System.out.println("str1==str2 :" + (str1==str2));
        System.out.println("str1.equals(str2) :" + (str1.equals(str2)));
        System.out.println("str3==str4 :" + (str3==str4));
        System.out.println("str3.equals(str4) :" + (str3.equals(str4)));
    }
}
