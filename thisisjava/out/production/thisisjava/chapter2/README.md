이 글은 '이것이 자바다' 책의 내용을 읽고 정리한 것 입니다.

## 1.  변수

#### A. 변수

하나의 값을 저장할 수 있는 메모리 공간

프로그램은 작업을 처리하는 과정에서 필요에 따라 데이터를 메모리에 저장한다. 이때 변수를 사용한다.

프로그램에 의해서 수시로 값이 변동될 수 있기 때문에 변수라는 이름을 갖게 되었다.

한 가지 타입, 한 가지 값만 저장 할 수 있다.

#### B. 변수의 선언

변수를 사용하기 위해서는 변수를 선언해야 한다.

어떤 타입의 데이터를 저장할 것인지 그리고 변수 이름이 무엇인지를 결정한다.

작성 규칙

a. 첫 번째 글자는 문자이거나 '$', '_' 이어야 하고 숫자로 시작할 수 없다.

b. 영어 대소문자가 구분된다.

c. 자바 예약어는 사용할 수 없다.

d. 문자 수의 제한은 없다.

개발자는 변수의 이름을 보고, 이 변수가 어떤 값을 저장하고 있는지 쉽게 알 수 있도록 의미 있는 변수 이름을 지어주는 것이 좋다. 변수 이름의 길이는 프로그램 실행과는 무관하기 때문에 충분히 길어도 상관없다.

#### C. 변수의 사용

변수를 사용한다는 것은 변수에 값을 저장하고 읽는 행위를 말한다.

변수에 값을 저장할 때에는 대입연산자(=)를 사용한다.

변수를 선언하고 처음 값을 저장할 경우, 이러한 값을 초기값이라고 한다. 그리고 변수에 초기값을 주는 행위를 변수의 초기화라고 한다.

소스 코드 내에서 직접 입력된 값을 리터럴(literal)이라고 부른다.

정수리터럴 : **10진수** 0, 75, -100 / **8진수** 02, -04 / **16진수** 0x5, 0xA, 0xB3, 0xAC08

실수리터럴 : 0.25, -3.14, 5E7(5x10^7), 0.12E-5(0.12x10^-5)

문자리터럴 : 작은따옴표(')로 묵은 하나의 문자 : 'A', '가', '\t', '\n' (역슬래쉬 '\'가 붙은 문자 리터럴은 이스케이프 문자라고도 하는데, 특수한 용도로 사용된다.)

문자열리터럴 : 큰따옴표(")로 묶은 텍스트 : "가나다라", "탭 만큼 이동 \t 합니다.", "한줄 내려 쓰기 \n 합니다."

논리리터럴 : true, false

변수는 초기화가 되어야 읽을 수가 있고, 초기화되지 않은 변수는 읽을 수가 없다.

```
int value;			// 초기화되지 않은 변수 value
int result = value + 10;	// 컴파일 에러 발생
```

변수는 중괄호 {} 블록 내에서 선언되고 사용된다. 중괄호 블록을 사용하는 곳은 클래스, 생성자, 메소드가 있다.

변수는 선언된 블록 내에서만 사용이 가능하다. (제어문, 반복문 내에서 선언된 변수는 그 안에서만 사용이 가능하다.)

```
public class VariableExample {
    public static void main(String[] args) {
        int v1 = 15;
        if(v1 > 10) {
            int v2 = v1 - 10;
        }
        int v3 = v1 + v2 + 5; // v2변수를 사용할 수 없기 때문에 컴파일 에러가 생김
    }
}
```

## 2.  데이터 타입

모든 변수에는 타입이 있으며, 타입에 따라 저장할 수 있는 값의 종류와 범위가 달라진다.

#### A. 기본(원시: primitive)타입

정수, 실수, 문자, 논리 리터럴을 직접 저장하는 타입을 말한다.

자바는 기본적으로 정수 연산을 int 타입으로 수행한다. 그렇기 때문에 저장하려는 값이 정수 리터럴이라면 특별한 이유가 없는 한 int 타입 변수에 저장하는 것이 좋다. byte와 short이 int보다는 메모리 사용 크기가 작아서 메모리를 절약할 수는 있지만, 값의 범위가 작은 편이라서 연산 시에 범위를 초과하면 잘못된 결과를 얻기 쉽다.

#### B. 정수 타입

byte 타입

색상 정보 및 파일 또는 이미지 등의 이진 데이터를 처리할 때 주로 사용된다.

\+ 최상위 비트(MSB: Most significant Bit)는 정수값의 부호를 결정한다. 최상위 비트가 0이면 양의 정수, 1이면 음의 정수를 뜻한다.

음수의 경우 1은 0으로, 0은 1로 바꾸고 1을 더한 값에 -를 붙여주면 십진수가 된다.

ex: 11111110 -> 0000001 + 1 = 00000010 => -2

char 타입

자바는 모든 문자를 유니코드로 처리한다. 유니코드는 세계 각국의 문자들을 코드값으로 매핑한 국제 표준 규약이다. 유니코드는 0~65535 범위의 2byte 크기를 가진 정수값이다. 0~127까지는 아스키(ASCII) 문자가 할당되어 있다.

```
char c = 65;			// 특정 문자의 유니코드를 아는 경우 
char c = '\u0041';		// 10 진수 혹은 16진수로 저장할 수 있음

char c = 'A';			// 특정 문자의 유니코드를 알고 싶은 경우
int uniCode = c;		// chat 타입 변수를 int 타입 변수에 저장
```

int 타입

4byte로 표현되는 정수값을 저장할 수 있는 데이터 타입이다. 자바에서 정수 연산을 하기 위한 기본 타입이다.

예를 들어 byte 타입 또는 short 타입의 변수를 연산하면 int 타입으로 변환된 후 연산되고 연산의 결과 역시 int 타입이 된다. 이것은 자바에서 정수 연산을 4byte로 처리하기 때문이다. 따라서 byte 타입이나 short타입으로 변수를 선언한 것과 int 타입으로 변수를 선언한 것의 성능 차이는 거의 없다.

long 타입

8byte로 표현되는 정수값을 저장할 수 있는 데이터 타입이다. 수치가 큰 데이터를 다루는 프로그램에서는 long 타입이 필수적으로 사용된다. long 타입의 변수를 초기화할 때에는 정수값 뒤에 소문자 'l'이나 대문자 'L'을 붙일 수 있다. 이것은 4byte 정수 데이터가 아니라 8byte 정수 데이터임을 컴파일러에게 알려주기 위한 목적이다. int 타입의 저장 범위를 넘어서는 큰 정수는 반드시 소문자 'l'이나 대문자 'L'을 붙여야 한다. 그렇지 않으면 컴파일 에러가 난다.

#### C. 실수 타입

소수점이 있는 실수 데이터를 저장할 수 있는 타입으로, 메모리 사용 크기에 따라 float(4byte)과 double(8byte)이 있다.

정수와 달리 부동소수점(floating-point) 방식을 사용하기 때문에 훨씬 더 큰 범위의 값을 저장할 수 있다.

\+ m x 10^n : 부호 가수 지수

float에 비해 double이 약 두 배의 자릿수가 배정되어 있기 때문에 더 정밀한 값을 저장할 수 있다.

자바는 실수 리터럴의 기본 타입을 double로 간주한다.

실수 리터럴을 float타입 변수에 저장하려면 뒤에 소문자 'f'나 대문자 'F'를 붙여야한다.

```
double var1 = 3.14;
float var2 = 3.14;	// 컴파일 에러
float var3 = 3.141F;
```

#### D. 논리 타입

1byte로 표현되는 논리값을 저장할 수 있는 데이터 타입이다.

조건문과 제어문의 실행 흐름을 변경하는데 주로 이용된다.

## 3\. 타입 변환

타입 변환이란 데이터 타입을 다른 데이터 타입으로 변환하는 것을 말한다. 예를 들어 byte 타입을 int 타입으로 변환하거나 반대로 int 타입을 byte타입으로 변환하는 행위를 말한다. 타입 변환에는 두 가지 종류가 있다.

하나는 자동(묵시적) 타입 변한이고 다른 하나는 강제(명시적) 타입 변환이다.

#### A. 자동 타입 변환

프로그램 실행 도중에 자동적으로 타입 변환이 일어나는 것을 말한다. 자동 타입 변환은 작은 크기를 가지는 타입이 큰 크기를 가지는 타입에 저장될 때 발생한다.

큰 크기 타입 = 작은 크기 타입 : 자동 타입 변환 발생

byte(1) < short(2) < int(4) < long(8) < float(4) < double(8)

float은 4byte 이고 long은 8 byte 이지만, 보다 표현할 수 있는 값의 범위가 크기 때문이다.

char 타입의 경우 음수의 값이 저장될 수 없기 때문에 byte 타입을 char타입으로 자동 변환 시킬 수 없다.

#### B. 강제 타입 변환

강제적으로 큰 데이터 타입을 작은 데이터 타입으로 쪼개어서 저장하는 것을 강제 타입 변환(캐스팅: Casting)이라고 한다.

강제 타입 변환은 캐스팅 연산자 ()를 사용하는데, 괄호 안에 들어가는 타입은 쪼개는 단위이다.

작은 크기 타입 = (작은 크기 타입) 큰 크기 타입 : 강제 타입 변환 발생

```
int intValue = 103029770;
byte byteValue = (byte) intValue;	// 10 강제 타입 변환(캐스팅)
```

int 값을 byte 로 강제 타입할 경우 앞의 3byte의 값은 버려지고 끝 1byte만 byte타입 변수에 저장된다.

캐스팅 될 때, 값의 손실이 발생하지 않도록 예외처리를 잘 해주는 것이 좋다.

실수타입을 정수타입으로 변환시 정밀도 손실이 발생할 수 있다.(double -> int 로 정밀도 손실을 막아준다.)

#### C. 연산식에서의 자동 타입 변환

연산은 기본적으로 같은 타입의 피연산자 간에만 수행되기 때문에 서로 다른 타입의 피연산자가 있을 경우 두 피연산자 중 크기가 큰 타입으로 자동 변환된 후 연산을 수행한다.

예를 들어 int 타입과 double 타입의 연산시 int 타입의 피연산자가 double 타입으로 자동 변환되고 연산을 수행한다. 당연히 연산의 결과는 double이 된다.

int 타입의 결과를 원한다면 double타입을 int 타입으로 강제 변환하고 연산을 수행하면 된다.

자바는 정수 연산일 경우 int 타입을 기본으로 한다. 그 이유는 피연산자를 4byte 단위로 저장하기 때문에 byte, char, short은 4byte인 int 타입으로 변환된 후 연산이 수행된다. 따라서 연산의 결과도 int 타입이 된다.

만약 피연산자 중 하나가 long 타입이라면 다른 피연산자도 long 타입으로 자동 타입 변환되고 결과는 long 타입이 된다.

float 타입과 float 타입을 연산하면 연산의 결과는 float 타입으로 나오지만, 피연산자 중에 실수 리터럴이나 double 타입이 있다면 다른 피연산자도 double 타입으로 자동 타입 변환되어 연산되므로 결과는 double 타입으로 산출된다.