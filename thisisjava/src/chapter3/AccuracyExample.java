package chapter3;

public class AccuracyExample {
    public static void main(String[] args) {
        int apple = 1;
        double pieceUnit = 0.1;
        int number = 7;

        double result = apple - number*pieceUnit;

        System.out.println("result = " + result);   // 0.29999999999999993

        int totalPiece = 10;
        int temp = totalPiece - number;

        double result2 = temp/10.0;

        System.out.println("result2 = " + result2); // 0.3
    }
}
