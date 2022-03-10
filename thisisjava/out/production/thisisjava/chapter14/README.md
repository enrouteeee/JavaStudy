이 글은 '이것이 자바다' 책의 내용을 읽고 정리한 것 입니다.

## 1\. 람다식이란?

병렬 처리와 이벤트 지향 프로그램이에 적합한 함수적 프로그래밍이 부각되고 있다. 그래서 객체 지향 프로그램이과 함수적 프로그램이을 혼합함으로써 더욱 효율적인 프로그래밍이 될 수 있도록 프로그램 개발 언어가 변하고 있다.

자바는 함수적 프로그래밍을 위해 자바 8부터 람다식을 지원하면서 기존의 코드 패턴이 많이 달라졌다.

람다식은 익명 함수를 생성하기 위한 식으로 객체 지향 언어보다는 함수지향 언어에 가깝다. 자바에서 람다식을 수용한 이유는 자바 코드가 매우 간결해지고, 컬렉션의 요소를 필터리하거나 매핑해서 원하는 결과를 쉽게 집계할 수 있기 때문이다. 람다식의 형태는 매개 변수를 가진 코드 블록이지만, 런타임 시에는 익명 구현 객체를 생성한다.

```
//익명 구현 객체
Runnable runnable = new Runnable() {
	public void run() { ... }
};
//람다식
Runnable runnable = () -> { ... };
```

람다식은 "(매개변수)->{실행코드}" 형태로 작성되는데, 마치 함수 정의 형태를 띠고 있지만 런타임 시에 인터페이스의 익명 구현 객체로 생성된다. 어떤 인터페이스를 구현할 것인가는 대입되는 인터페이스가 무엇이냐에 달려있다. 위 코드는 Runnable 변수에 대입되므로 람다식을 Runnable의 익명 구현 객체를 생성하게 된다.

## 2\. 람다식의 기본 문법

```
(타입 매개변수, ...) -> { 실행문; ... }
```

매개 변수 타입은 런타임 시에 대입되는 값에 따라 자동으로 인식될 수 있기 때문에 람다식에서는 매개 변수의 타입을 일반적으로 언급하지 않는다.

하나의 매개 변수만 있다면 괄호 ()를 생략할 수 있고, 하나의 실행문만 있다면 중괄호 {}도 생략할 수 있다.

매개 변수가 없다면 람다식에서 매개 변수 자리가 없어지므로 빈 괄호 ()를 반드시 사용해야 한다.

중괄호를 실행하고 결과값을 리턴해야 한다면 return 문으로 결과값을 지정할 수 있다.

중괄호에  return문만 있을 경우, 람다식에서는 return 문을 사용하지 않고 작성하는 것이 정석이다.

```
(int a) -> { System.out.println(a); }
(a) -> { System.out.println(a); }
a -> System.out.println(a);
(x, y) -> { return x + y; };
(x, y) -> x+y
```

## 3\. 타겟 타입과 함수적 인터페이스

람다식은 대입될 인터페이스의 종류에 따라 작성 방법이 달라지기 때문에 람다식이 대입될 인터페이스를 람다식의 타겟 타입(target type)이라고 한다.

#### 1\. 함수적 인터페이스 @FunctionalInterface

모든 인터페이스를 람다식의 타겟 타입으로 사용할 수는 없다. 람다식이 하나의 메소드를 정의하기 때문에 두 개 이상의 추상 메소드가 선언된 인터페이스는 람다식을 이용해서 구현 객체를 생성할 수 없다. 하나의 추상 메소드가 선언된 인터페이스만이 람다식의 타겟 타입이 될 수 있는데, 이러한 인터페이스를 함수적 인터페이스라고 한다. 함수적 인터페이스를 작성할 대 두개 이상의 추상 메소드가 선언되지 않도록 컴파일러가 체킹해주는 기능이 있는데, 인터페이스 선언 시 @FunctionalInterface 어노테이션을 붙이면 된다.

```
@FunctionalInterface
public interface MyFunctionalInterface {
	public void method();
    public void otherMethod();	//컴파일 오류
}
```

#### 2\. 매개 변수와 리턴값이 없는 람다식

```
public interface MyFunctionalInterface {
	public void method();
}

MyFunctionalInterface fi = () -> { };
fi.method();
```

#### 3\. 매개 변수가 있는 람다식

```
public interface MyFuncionalInteface {
	public void method(int x);
}
//매개변수가 하나인 경우 괄호를 생략 가능
MyFunctionalInterface fi = (x) -> { ...} 또는 x -> { ... }
fi.method(5);
```

#### 4\. 리턴값이 있는 람다식

```
public interface MyFunctionalInterface {
	public int method(int x, int y);
}

MyFunctionalInteface fi = (x, y) -> { ...; return 값; };
MyFunctionalInteface fi = (x, y) -> x + y;	// return 문만 있는 경우 생략

int result = fi.method(2, 5);
```

## 4\. 클래스 멤버와 로컬 변수 사용

람다식의 실행 블록에는 클래스 멤버(필드와 메소드) 및 로컬 변수를 사용할 수 있다. 클래스의 멤버는 제약 사항 없이 사용 가능하지만, 로컬 변수는 제약 사항이 따른다.

#### 1\. 클래스의 멤버 사용

람다식 실행 블록에서는 클래스의 멤버인 필드와 메소드를 제약 사항 없이 사용할 수 있다. 하지만 this 키워드를 사용할 때에는 주의가 필요하다. 일반적으로 익명 객체 내부에서 this는 익명 객체의 참조이지만, 람다식에서 this는 내부적으로 생성되는 익명 객체의 참조가 아니라 람다식을 실행한 객체의 참조이다.

#### 2\. 로컬 변수 사용

람다식은 메소드 내부에서 주로 작성되기 때문에 로컬 익명 구현 객체를 생성시킨다고 봐야 한다. 람다식에서는 바깥 클래스의 필드나 메소드는 제한 없이 사용할 수 있으나, 메소드의 매개 변수 또는 로컬 변수를 사용하면 이 두 변수는 final 특성을 가져야 한다. 따라서 매개 변수 또는 로컬 변수를 람다식에서 읽는 것은 허용되지만, 람다식 내부 또는 외부에서 변경할 수 없다.

## 5\. 표준 API의 함수적  인터페이스

자바에서 제공되는 표준 API에서 한 개의 추상 메소드를 가지는 인터페이스들은 모두 람다식을 이용해서 익명 구현 객체로 표현이 가능하다.

자바 8부터는 빈번하게 사용되는 함수적 인터페이스는 java.util.function 표준 API 패키지로 제공한다.

#### 1\. Consumer 함수적 인터페이스

리턴값이 없는 accept() 메소드를 가지고 있다. accept() 메소드는 단지 매개값을 소비하는 역할만 한다.

```
Consumer<String> consumer = t -> { t를 소비하는 실행문 };
BiConsumer<String, String> consumer = (t, u) -> { t와 u를 소비하는 실행문 };
DoubleConsumer consumer = d -> { d를 소비하는 실행문 };
ObjIntConsumer<String> consumer = (t, i) -> { t와 i를 소비하는 실행문 };

public class ConsumerExample {
    public static void main(String[] args) {
        Consumer<String> consumer = t -> System.out.println(t + "8");
        consumer.accept("java");

        BiConsumer<String, String> biConsumer =(t, u) -> System.out.println(t + u);
        biConsumer.accept("Java", "8");

        DoubleConsumer doubleConsumer = d -> System.out.println("Java" + d);
        doubleConsumer.accept(8.0);

        ObjIntConsumer<String> objIntConsumer = (t, i) -> System.out.println(t + i);
        objIntConsumer.accept("Java", 8);
    }
}
```

#### 2\. Supplier 함수적 인터페이스

매개 변수가 없고 리턴값이 있는 getXXX() 메소드를 가지고 있다. 이 메소드들은 실행 후 호출한 곳으로 데이터를 리턴 하는 역할을 한다.

```
Supplier<String> supplier = () -> { ...; return "문자열"; }
IntSupplier supplier = () -> { ...; return int값; }

public class SupplierExample {
    public static void main(String[] args) {
        IntSupplier intSupplier = () -> {
            int num = (int) (Math.random() * 6) + 1;
            return num;
        };

        int num = intSupplier.getAsInt();
        System.out.println("눈의 수: " + num);
    }
}
```

#### 3\. Function 함수적 인터페이스

매개값과 리턴값이 있는 applyXXX() 메소드를 가지고 있다. 이 메소드들은 매개값을 리턴값으로 매핑하는 역할을 한다.

```
Function<Student, String> function = t -> { return t.getName(); }
Function<Student, String> function = t -> t.getName();

ToIntFunction<Student> function = t -> { return t.getScore(); }
ToIntFunciton<Student> function = t -> t.getScore();

public class FunctionExample2 {
    private static List<Student> list = Arrays.asList(
      new Student("홍길동", 90, 96),
      new Student("신용권", 95, 93)
    );

    public static double avg( ToIntFunction<Student> function ) {
        int sum = 0;
        for (Student student : list) {
            sum += function.applyAsInt(student);
        }
        double avg = (double) sum / list.size();
        return avg;
    }

    public static void main(String[] args) {
        double englishAvg = avg(s -> s.getEnglishScore());
        System.out.println("영어 평균 점수: " + englishAvg);

        double mathAvg = avg(s -> s.getMathScore());
        System.out.println("수학 평균 점수: " + mathAvg);
    }
}
```

#### 4\. Operator 함수적 인터페이스

Function과 동일하게 매개 변수와 리턴값이 있는 applyXXX() 메소드를 가지고 있다. 하지만 이 메소드들은 매개값을 리턴값으로 매핑하는 역할보다는 매개값을 이용해서 연산을 수행한 후 동일한 타입으로 리턴값을 제공하는 역할을 한다.

```
IntBinaryOperator operator = (a, b) -> { ...; return int값; };

public class OperatorExample {
    private static int[] scores = {92, 95, 87};

    public static int maxOrMin(IntBinaryOperator operator) {
        int result = scores[0];
        for (int score : scores) {
            result = operator.applyAsInt(result, score);
        }
        return result;
    }

    public static void main(String[] args) {
        //최대값 얻기
        int max = maxOrMin(
                (a, b) -> {
                    if(a >= b) return a;
                    else return b;
                }
        );
        System.out.println("최대값: " + max);

        //최소값 얻기
        int min = maxOrMin(
                (a, b) -> {
                    if(a <= b) return a;
                    else return b;
                }
        );
        System.out.println("최소값: " + min);
    }
}
```

#### 5\. Predicate 함수적 인터페이스

매개 변수와 boolean 리턴값이 있는 testXXX() 메소드를 가지고 있다. 이 메소드들은 매개값을 조사해서 true 또는 false를 리턴하는 역할을 한다.

```
Predicate<Student> predicate = t -> return t.getSex().equals("남자"); }
Predicate<Student> predicate = t -> t.getSex().equals("남자");
```

#### 6\. andThen()과 compose() 디폴트 메소드

디폴트 및 정적 메소드는 추상 메소드가 아니기 때문에 함수적 인터페이스에 선언되어도 여전히 함수적 인터페이스의 성질을 잃지 않는다. java.util.function 패키지의 함수적 인터페이스는 하나 이상의 디폴트 및 정적 메소드를 가지고 있다.

Consumer, Funciton, Operator 종류의 함수적 인터페이스는 andThen()과 compose() 디폴트 메소드를 가지고 있다. andThen() 과 compose() 디폴트 메소드는 두 개의 함수적 인터페이스를 순차적으로 연결하고, 첫 번째 처리결과를 두 번째 매개값으로 제공해서 최종 결과값을 얻을 때 사용한다. andThen()과 compose()의 차이점은 어떤 함수적 인터페이스부터 먼저 처리하느냐이다.

```
인터페이스AB = 인터페이스A.andThen(인터페이스B);
최종결과 = 인터페이스AB.method();

인터페이스AB = 인터페이스A.compose(인터페이스B);
최종결과 = 인터페이스AB.method();
```

#### 7\. and(), or(), negate() 디폴트 메소드와 isEqual() 정적 메소드

Predicate 종류의 함수적 인터페이스는 and(), or(), negate() 디폴트 메소드를 가지고 있다. 이 메소드들은 각각 논리 연산자인 &&, ||, !과 대응된다고 볼 수 있다.

#### 8\. minBy(), maxBy() 정적 메소드

BinaryOperator<T> 함수적 인터페이스는 minBy() 와 maxBy() 정적 메소드를 제공한다. 이 두 메소드는 매개값으로 제공되는 Comparator를 이용해서 최대 T와 최소 T를 얻는 BinaryOperator<T>를 리턴한다.

```
public class OperatorMinByMaxByExample {
    public static void main(String[] args) {
        BinaryOperator<Fruit> binaryOperator;
        Fruit fruit;

        binaryOperator = BinaryOperator.minBy( (f1, f2) -> Integer.compare(f1.price, f2.price));
        fruit = binaryOperator.apply(new Fruit("딸기", 6000), new Fruit("수박", 10000));
        System.out.println(fruit.name);

        binaryOperator = BinaryOperator.maxBy( (f1, f2) -> Integer.compare(f1.price, f2.price));
        fruit = binaryOperator.apply(new Fruit("딸기", 6000), new Fruit("수박", 10000));
        System.out.println(fruit.name);
    }
}
```

## 6\. 메소드 참조

메소드를 참조해서 매개 변수의 정보 및 리턴 타입을 알아내어, 람다식에서 불필요한 매개 변수를 제거하는 것이 목적이다. 람다식은 종종 기본 메소드를 단순히 호출만 하는 경우가 많다. 예를 들어 두 개의 값을 받아 큰 수를 리턴하는 Math 클래스의 max() 정적 메소드를 호출하는 람다식은 다음과 같다.

```
(left, right) -> Math.max(left, right);
```

람다식은 단순히 두 개의 값을 Math.max() 메소드의 매개값으로 전달하는 역할만 하기 때문에 다소 불편해 보인다. 이 경우에는 다음과 같이 메소드 참조를 이용하면 매우 깔끔하게 처리할 수 있다.

```
Math :: max;
```

메소드 참조도 람다식과 마찬가지로 인터페이스의 익명 구현 객체로 생성되므로 타겟 타입이 인터페이스의 추상 메소드가 어떤 매개 변수를 가지고, 리턴 타입이 무엇인가에 따라 달라진다. IntBinaryOperator 인터페이스는 두 개의 int 매개값을 받아 int 값을 리턴하므로 Math::max 메소드 참조를 대입할 수 있다.

```
IntBinaryOperator operator = Math :: max;
```

메소드 참조는 정적 또는 인스턴스 메소드를 참조할 수 있고, 생성자 참조도 가능하다.

#### 1\. 정적 메소드와 인스턴스 메소드 참조

정적(static) 메소드를 참조할 경우에는 클래스 이름 뒤에 :: 기호를 붙이고 정적 메소드 이름을 기술하면 된다.

```
클래스 :: 메소드
```

인스턴스 메소드일 경우에는 먼저 객체를 생성한 다음 참조 변수 뒤에 :: 기호를 붙이고 인스턴스 메소드 이름을 기술하면 된다.

```
참조변수 :: 메소드
```

#### 2\. 매개 변수의 메소드 참조

메소드는 람다식 외부의 클래스 멤버일 수도 있고, 람다식에서 제공되는 매개 변수의 멤버일 수도 있다. 이전 예제는 람다식 외부의 클래스 멤버인 메소드를 호출하였다. 그러나 다음과 같이 람다식에서 제공되는 a 매개 변수의 메소드를 호출해서 b 매개 변수를 매개값으로 사용하는 경우도 있다.

```
(a, b) -> { a.instanceMethod(b); }
```

이것을 메소드 참조로 표현하면 다음과 같다. a의 클래스 이름 뒤에 :: 기호를 붙이고 메소드 이름을 기술하면 된다. 작성 방법은 정적 메소드 참조와 동일하지만, a의 인스턴스 메소드가 참조되므로 전혀 다른 코드가 실행된다.

```
클래스 :: instanceMethod
```

#### 3\. 생성자 참조

메소드 참조는 생성자 참조도 포함한다.

```
(a, b) -> { return new 클래스(a, b); }
클래스 :: new

public class ConstructorReferencesExample {
    public static void main(String[] args) {
        Function<String, Member> function1 = Member::new;
        Member member1 = function1.apply("angel");

        BiFunction<String, String, Member> function2 = Member::new;
        Member member2 = function2.apply("신천사", "angel");
    }
}

public class Member {
    private String name;
    private String id;

    public Member() {
        System.out.println("Member() 실행");
    }

    public Member(String id) {
        System.out.println("Member(String id) 실행");
        this.id = id;
    }

    public Member(String name, String id) {
        System.out.println("Member(String name, String id) 실행");
        this.name = name;
        this.id = id;
    }

    public String getId() {
        return this.id;
    }
}
```