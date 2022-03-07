package chapter14;

public class UsingLocalVariable {
    void method(int arg) {
        int localVar = 40;

        //final 특성을 가짐 변경 불가능
        //arg = 31;
        //localVar = 41;

        //람다식
        MyFunctionalInterface fi = () -> {
            //로컬 변수 읽기
            System.out.println("arg: " + arg);
            System.out.println("localVar: " + localVar);
        };
        fi.method();
    }
}
