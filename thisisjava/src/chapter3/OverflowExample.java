package chapter3;

public class OverflowExample {
    public static void main(String[] args) {
        try {
            int result = safeAdd(200000000, 200000000);
            System.out.println("result = " + result);
        } catch (ArithmeticException e) {
            System.out.println("오버플로우 발생");
        }
    }

    private static int safeAdd(int left, int right) {
        if(right > 0) {
            if(left > (Integer.MAX_VALUE - right)) {
                throw new ArithmeticException("오버플로우 발생");
            }
        } else {
            if(left < (Integer.MAX_VALUE - right)) {
                throw new ArithmeticException("오버플로우 발생");
            }
        }
        return left + right;
    }
}
