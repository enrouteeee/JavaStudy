이 글은 '이것이 자바다' 책의 내용을 읽고 정리한 것 입니다.

## 1\. 연산자와 연산식

프로그램에서 데이터를 처리하여 결과를 산출하는 것을 연산(operations)이라고 한다.

연산에 사용되는 표시를 기호를 연산자(operator)라고 하고, 연산되는 데이터는 피연산자(operand)라고 한다.

연산자와 피연산자를 이용하여 연산의 과정을 기술한 것을 연산식(expressions)이라고 부른다.

자바 언어에서는 산술, 부호, 문자열, 대입 등과 같은 다양한 연산자를 제공하고 있으며, 이 연산자들은 피연산자를 연산해서 값을 산출하는데, 산출되는 값의 타입은 연산자별로 다르다.

연산자는 필요로 하는 피연산자의 수에 따라 단항, 이항, 삼항 연산자로 구분된다.

```
++x;					//단항
x + y;					//다항
(sum>90) ? "A" : "B";			//삼항
```

연삭식은 반드시 하나의 값을 산출한다. 그렇기 때문에 하나의 값이 올 수 있는 곳이라면 어디든지 값 대신에 연산식을 사용할 수 있다.

```
int result = x + y;	// 연산식의 산출 값을 변수에 저장
boolean result = (x+y) < 5;	// 연산식은 다른 연산식의 피연산자 위치에도 올 수 있다
```

## 2\. 연산의 방향과 우선순위

연산식에서는 다양한 연산자가 복합적으로 구성된 경우가 많다.

프로그램에서는 연산자의 연산 방향과 연산자 간의 우선순위가 정해져 있다.

1.단항, 이항, 삼항 연산자 순으로 우선순위를 가진다.

2\. 산술, 비교, 논리, 대입 연산자 순으로 우선순위를 가진다.

3\. 단항과 대입 연산자를 제외한 모든 연산의 방향은 왼쪽에서 오른쪽이다.

4\. 복잡한 연산식에는 괄호()를 사용해서 우선순위를 정해준다.

## 3\. 단항 연산자

부호 연산자(+, -), 증감 연산자(++, --), 논리 부정 연산자(!), 비트 반전 연산자(~)가 있다.

#### A. 부호 연산자

정수 및 실수 리터럴 앞에 붙여 양수 및 음수를 표현한다.

주의할 점은 부호 연산자의 산출 타입은 int타입이 된다는 것이다.

```
short s = 100;
short result = -s;	//컴파일 에러
int result = -s;
```

#### B. 증감 연산자

변수의 값을 1증가 시키거나 1감소 시키는 연산자이다. boolean 타입을 제외한 모든 기본(primitive)타입에 사용할 수 있다.

변수의 앞과 뒤에 붙는지에 따라 연산 수행 전에 실행되는지 후에 실행되는지 결정한다.

#### C. 논리 부정 연산자

true를 false로, false를 true로 변경하기 때문에 boolean 타입에만 사용할 수 있다.

#### D. 비트 반전 연산자

정수 타입의 피연산자에만 사용되며, 피연산자를 2진수로 표현했을 때 비트값인 0을 1로, 1은 0으로 반전한다.

주의할 점은 부호연산자와 마찬가지로 산출 타입은 int 타입이 된다는 것이다.

## 4\. 이항 연산자

산술 연산자, 문자열 연결 연산자, 대입 연산자, 비교 연산자, 논리 연산자, 비트 논리 연산자, 비트 이동 연산자 등이 있다.

#### A. 산술 연산자(+, -, \*, /, %)

boolean 타입을 제외한 모든 기본 타입에 사용할 수 있다.

다음과 같은 규칙이 적용된다.

1\. 피연산자들이 모두 정수 타입이고, int 타입보다 크기가 작은 타입일 경우 모두 int 타입으로 변환 후, 연산을 수행한다. 따라서 연산의 산출 타입은 int이다.

2\. 피연산자들이 모두 정수 타입이고, long 타입이 있을 경우 모두 long 타입으로 변환 후, 연산을 수행한다. 따라서 연산의 산출 타입은 long이다.

3\. 피연산자 중 실수 타입이 있을 경우, 크기가 큰 실수 타입으로 변환 후, 연산을 수행한다. 따라서 연산의 산출 타입은 산출 타입이다.

오버플로우 탐지

산술 연산시 주의할 점은 산출값이 산출 타입으로 표현 가능한지 살펴봐야 한다.

```
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
```

정확한 계산은 정수 사용

정확하게 계산해야 할 때는 부동소수점 타입을 사용하지 않는 것이 좋다.

```
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
```

NaN, Infinity

#### B. 문자열 연결 연산자(+)

\+ 는 문자열을 서로 결합하는 연산자이다.

피연산자 중 한쪽이 문자열이면 +연산자는 문자열 연결 연산자로 사용된다. 순서에 따라 의도한 결과 나오지 않을 수 있으니 주의하자.

```
public class StringConcatExample {
    public static void main(String[] args) {
        String str1 = "JDK" + 3 + 3.0;
        String str2 = 3 + 3.0 + "JDK";
        System.out.println("str1 = " + str1);   // JDK33.0
        System.out.println("str2 = " + str2);   // 6.0JDK
    }
}
```

#### C. 비교 연산자(<, <=, >, >=, ==, !=)

boolean 타입을 산출한다.

\== 비교할 때 주의하자. 자세한 내용은 참조타입때 살펴보자.

#### D. 논리 연산자(&&, ||, &, |, ^, !)

boolean 타입만 사용할 수 있다.

#### E. 비트 연산자(&, |, ^, ~, <<, >>, >>>)

비트 단위로 연산한다. 0과 1로 표현이 가능한 정수 타입만 비트 연산을 할 수 있다.

논리 연산자(&, |, ^, ~) int 타입으로 자동 타입 변환후 연산 수행

이동 연산자(<<, >>, >>>)

#### F. 대입 연산자(=, +=, -=, \*=, /=, %=, &=, ^=, |=, <<=, >>=, >>>=)

## 5\. 삼항 연산자(?:)

조건식(피연산자1) ? 값 또는 연산식(피연산자2) : 값 또는 연산식(피연산자3)

조건식 연산하여 true가 나오면 결과는 피연산자2, false가 나오면 피연산자3