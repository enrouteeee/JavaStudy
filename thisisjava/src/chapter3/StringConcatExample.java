package chapter3;

public class StringConcatExample {
    public static void main(String[] args) {
        String str1 = "JDK" + 3 + 3.0;
        String str2 = 3 + 3.0 + "JDK";
        System.out.println("str1 = " + str1);   // JDK33.0
        System.out.println("str2 = " + str2);   // 6.0JDK
    }
}
