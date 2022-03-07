package chapter14;

import java.util.function.ToIntBiFunction;

public class ArgumentMethodReferenceExample {
    public static void main(String[] args) {
        ToIntBiFunction<String, String> function;

        function = (a, b) -> a.compareToIgnoreCase(b);
        function = String::compareToIgnoreCase;
    }
}
