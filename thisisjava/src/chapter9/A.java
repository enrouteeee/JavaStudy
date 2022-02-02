package chapter9;

public class A {
    class B {
        B() { } //  생성자
        int field1; // 인스턴스 필드
        //static int field2;    // 정적 필드 x
        void method1() { }  // 인스턴스 메소드
        //static void method2() { } // 정적 메소드 x
    }

    static class C {
        C() { }
        int field1;
        static int field2;
        void method1() {}
        static void method2() {}
    }

    void method() {
        class D{
            D() { }
            int field1;
            //static int field2;
            void method1(){}
            //static void method2() { }
        }

        D d = new D();
        d.field1 = 3;
        d.method1();
    }

    void outMethod(final int arg1, int arg2) {
        final int var1 = 1;
        int var2 = 2;

        class LocalClass {
            void method() {
                int result = arg1 + arg2 + var1 + var2;
            }
        }
        /*
        class LocalClass {
            //필드로 복사
            int arg2 = 매개값;
            int var2 = 2;
            void method() {
                //로컬 변수로 복사
                int arg1 = 매개값;
                int var1 = 1;
                int result = arg1 + arg2 + var1 + var2;
            }
        }
        */
    }
}
