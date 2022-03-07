package chapter14;

import java.util.function.IntBinaryOperator;

public class MethodReferencesExample {
    public static void main(String[] args) {
        IntBinaryOperator operator;

        operator = (x, y) -> Calculator.staticMethod(x, y);
        operator = Calculator::staticMethod;

        Calculator obj = new Calculator();
        operator = (x, y) -> obj.instanceMethod(x, y);
        operator = obj::instanceMethod;
    }
}
