package chapter7;

public class InstanceofExample {
    public static void main(String[] args) {
        Parent parent1 = new Child();
        Parent parent2 = new Parent();

        boolean result1 = parent1 instanceof Child;
        boolean result2 = parent1 instanceof Parent;
        boolean result3 = parent2 instanceof Child;
        boolean result4 = parent2 instanceof Parent;

        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        System.out.println(result4);
    }
}
