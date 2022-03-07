package chapter14;

public class UsingThis {
    public int outterField = 10;

    class Inner {
        int innerFiled = 20;

        void method() {
            //람다식
            MyFunctionalInterface fi= () -> {
                System.out.println("outterField: " + outterField);
                System.out.println("outterField: " + UsingThis.this.outterField + "\n");

                System.out.println("innerField: " + innerFiled);
                System.out.println("innerField: " + this.innerFiled + "\n");
            };
            fi.method();
        }
    }
}
