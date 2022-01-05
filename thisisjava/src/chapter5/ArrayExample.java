package chapter5;

public class ArrayExample {
    public static void main(String[] args) {
        int[] intArr = {0, 10, 20, 30};
        int[] intArr2 = new int[6];
        System.arraycopy(intArr, 0, intArr2, 0, 4);

        for (int i : intArr2) {
            System.out.print(i+" ");
        }
    }
}
