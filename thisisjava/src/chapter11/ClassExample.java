package chapter11;

public class ClassExample {
    public static void main(String[] args) {
        Key key = new Key(1);
        Class clazz1 = key.getClass();
        System.out.println(clazz1.getName());
        System.out.println(clazz1.getSimpleName());
        System.out.println(clazz1.getPackage().getName());
        System.out.println();

        try {
            Class clazz2 = Class.forName("chapter11.Key");
            System.out.println(clazz2.getName());
            System.out.println(clazz2.getSimpleName());
            System.out.println(clazz2.getPackage().getName());
            System.out.println();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
