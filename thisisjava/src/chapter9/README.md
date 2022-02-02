이 글은 '이것이 자바다' 책의 내용을 읽고 정리한 것 입니다.

## 1.  중첩 클래스, 중첩 인터페이스

중첩 클래스란 클래스 내부에 선언한 클래스이다. 중첩클래스를 사용하면 두 클래스의 멤버들을 서로 쉽게 접근할 수 있다는 장점과 외부에는 불필요한 관계 클래스를 감춤으로써 코드의 복잡성을 줄일 수 있다.

```
class ClassName{
	class NestedClassName{
    }
}
```

인터페이스도 클래스 내부에 선언할 수 있다. 이런 인터페이스를 중첩 인터페이스라고 한다. 클래스와 긴밀한 관계를 맺는 구현 클래스를 만들기 위해서 사용한다.

```
class ClassName{
	interface NestedInterfaceName{
    }
}
```

## 2.  중첩 클래스

```
class A{
	//인스턴스 멤버 클래스
    class B{ ... }
    //정적 멤버 클래스
    static class C{ ... }
    //로컬 클래스
    void method(){
    	class D{ ... }
    }
}
```

인스턴스 멤버 클래스는 인스턴스 필드와 메소드만 선언이 가능하고 정적 필드와 메소드는 선언할 수 없다.

A 클래스 외부에서 인스턴스 멤버 클래스 B의 객체를 생성하려면 먼저 A 객체를 생성하고 B 객체를 생성해야 한다.

정적 멤버 클래스는 static 키워드로 선언된 클래스를 말한다. 정적 멤버 클래스는 모든 종류의 필드와 메소드를 선언할 수 있다.

A클래스 외부에서 정적 멤버 클래스 C의 객체를 생성하기 위해서는 A 객체를 생성할 필요가 없다.

```
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

    public static void main(String[] args) {
        A a = new A();
        A.B b = a.new B();
        b.field1 = 3;
        b.method1();
        
        A.C c = new A.C();
        c.field1 = 3;
        c.method1();
        A.C.field2 = 3;
        A.C.method2();
    }
}
```

로컬 클래스는 메소드 내에 선언된 중첩 클래스이다. 접근 제한자 및 static을 붙일 수 없다. 메소드 내부에서만 사용되기 때문이다.

인스턴스 필드와 메소드만 선언이 가능하고 정적 필드와 메소드는 선언할 수 없다.

```
void method() {
        class D{
            D() { }
            int field1;
            //static int field2;
            void method1(){}
            //static void vethod2() { }
        }
        
        D d = new D();
        d.field1 = 3;
        d.method1();
}
```

로컬 클래스는 메소드가 실행될 때 메소드 내에서 객체를 생성하고 사용해야 한다. 주로 비동기 처리를 위해 스레드 객체를 만들 때 사용한다.

## 3.  중첩 클래스의 접근 제한

인스턴스 멤버 클래스는 바깥 클래스의 인스턴스 필드의 초기값이나 인스턴스 메소드에서 객체를 생성할 수 있으나, 정적 필드의 초기값이나 정적 메소드에서는 객체를 생성할 수 없다.

정적 멤버 클래스는 모든 필드의 초기값이나 모든 메소드에서 객체를 생성할 수 있다.

인스턴스 멤버 클래스 안에서는 바깥 클래스의 모든 필드와 모든 메소드에 접글할 수 있다.

정적 멤버 클래스 안에서는 바깥 클래스의 정적 필드와 메소드에만 접근할 수 있고 인스턴스 필드와 메소드는 접근할 수 없다.

로컬 클래스 내부에서는 바깥 클래스의 필드나 메소드를 제한 없이 사용할 수 있다.

문제는 메소드의 매개 변수나 로컬 변수를 로컬  클래스에서 사용할 때이다. 로컬 클래스의 객체는 메소드 실행이 끝나도 힙 메모리에 존재해서 계속 사용될 수 있다. 매개 변수나 로컬 변수는 메소드 실행이 끝나면 스택 메모리에서 사라지기 때문에 로컬 객체에서 사용할 경우 문제가 발생한다. 이 문제를 해결하기 위해 컴파일 시 로컬 클래스에서 사용하는 매개 변수나 로컬 변수의 값을 로컬 클래스 내부에 복사해 두고 사용한다. 그리고 매개 변수나 로컬 변수가 수정되어 값이 변경되면 로컬 클래스에 복사해 둔 값과 달리지는 문제를 해결하기 위해 매개 변수나 로컬 변수를 final로 선언해서 수정을 막는다.

결론적으로 로컬 클래스에서 사용 가능한 것은 final로 선언된 매개 변수와 로컬 변수뿐이다. final 선언을 하지 않아도 여전히 값을 수정할 수 없는 final의 특성을 갖는다. final 키워드가 있으면 로컬 클래스의 메소드 내부에 지역 변수로 복사되지만, final 키워드가 없다면 로컬 클래스의 필드로 복사된다.

```
void outMethod(final int arg1, int arg2) {
        final int var1 = 1;
        int var2 = 2;

        class LocalClass {
            void method() {
                int result = arg1 + arg2 + var1 + var2;
            }
        }
        
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
        
    }
```

중첩 클래스에서 바깥 클래스 참조를 얻기위해서는 바깥클래스.this.필드 바깥클래스.this.메소드(); 를 이용한다.

this 키워드를 사용하면 바깥 클래스가 아니라 중첩 클래스의 객체 참조가 된다.

## 4\. 중첩 인터페이스

클래스의 멤보러 선언된 인터페이스를 말한다. 해당 클래스와 긴밀한 관계를 맺는 구현 클래스를 만들기 위해서이다. 특히 UI 프로그래밍에서 이벤트를 처리할 목적으로 많이 활용된다.

```
class A {
	interfacee I {
    	void method();
    }
}
```

Button을 클릭했을 때 이벤트를 처리하는 객체를 받고 싶다고 가정해보자. Button 내부에 선언된 중첩 인터페이스를 구현한 객체만 받아야 한다면 다음과 같이 Button 클래스를 선언하면 된다.

```
public class Button {
	
    OnClickListener listener;	//인터페이스 타입 필드
    
    //매개 변수의 다형성
    void setOnClickListener(OnClickListener listener) {
    	this.listener = listener;
    }
    
    //구현 객체의 onClick() 메소드 호출
    void touch() {
    	listener.onClick();
    }
    
    //중첩 인터페이스
    interface OnClickListener {
    	void onClick();
    }
}
```

```
public class CallListener implements Button.OnClickListener {
	
    @Overide
    public void onClick() {
    	System.out.println("전화를 겁니다.");
    }
}
```

```
public class MessageListener implements Button.OnClickListener {
	
    @Overide
    public void onClick() {
    	System.out.println("메시지를 보냅니다.");
    }
}
```

```
public class ButtonExample {
    public static void main(String[] args) {
        Button btn = new Button();

        btn.setOnClickListener(new CallListener());
        btn.touch();

        btn.setOnClickListener(new MessageListener());
        btn.touch();
    }
}
```

## 5\. 익명 객체

이름이 없는 개체를 말한다. 익명 객체는 단독으로 생성할 수 없고 클래스를 상속하거나 인터페이스를 구현해야만 생성할 수 있다.

필드의 초기값이나 로컬 변수의 초기값, 매개 변수의 매개값으로 주로 대입된다.