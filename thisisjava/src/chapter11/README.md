이 글은 '이것이 자바다' 책의 내용을 읽고 정리한 것 입니다.

## 1.  자바 API 도큐먼트

API는 라이브러리(library)라고 부르기도 하는데, 프로그램 개발에 자주 사용되는 클래스 및 인터페이스의 모응믈 말한다.

이 API들은 <JDK 설치 경로>\\jre\\lib\\rt.jar 라는 압축 파일에 저장되어 있다.

API 도큐먼트는 쉽게 API를 찾아 이용할 수 있도록 문서화한 것을 말한다.

[http://docs.oracle.com/javase/8/docs/api/](http://docs.oracle.com/javase/8/docs/api/)

[Java Platform SE 8

docs.oracle.com](http://docs.oracle.com/javase/8/docs/api/)

## 2\. java.lang과 java.util 패키지

#### 1\. java.lang 패키지

자바 프로그램의 기본적인 클래스를 담고 있는 패키지이다. import 없이 사용할 수 있다. ex) String System

Object : 자바 클래스의 최상위 클래스

System : 표준 입출력, 자바 가상 기계를 종료, 쓰레기 수집기 실행 요청

Class : 클래스를 메모리로 로딩할 때 사용

String : 문자열을 저장하고 여러 가지 정보를 얻을 때 사용

StringBuffer, StringBuilder : 문자열을 저장하고 내부 문자열을 조작할 때 사용

Math : 수학 함수를 이용할 때 사용

Wrapper(Byte, Short, Character, Integer, Float, Double, Boolean, Long) : 기본 타입의 데이터를 갖는 객체를 만듦, 문자열을 기본 타입으로 변환, 입력값 검사에 사용

#### 2\. java.util 패키지

자바 프로그램 개발에 조미료 같은 역할을 하는 클래스를 담고 있다. 컬렉션 클래스들이 대부분을 차지하고 있다.

Arrays : 배열을 조작할 때 사용

Calendar : 운영체제의 날짜와 시간을 얻을 때 사용

Date : 날짜와 시간 정보를 저장하는 클래스

Objects : 객체 비교, 널 여부 등을 조사할 때 사용

StringTokenizer : 특정 문자로 구분된 문자열을 뽑아낼 때 사용

Random : 난수를 얻을 때 사용

## 3\. Object 클래스

자바의 모든 클래스는 Object 클래스의 자식이거나 자손 클래스이다. Object는 자바의 최상위 부모 클래스에 해당한다.

필드가 없고, 메소드들로 구성되어 있다. 이 메소드들은 모든 클래스가 Object를 상속하기 때문에 모든 클래스에서 사용이 가능하다.

#### 1\. 객체비교 equals()

```
public boolean equals(Object obj) { ... }
```

두 객체가 동일한 객체라면 true를 리턴하고 그렇지 않으면 fasle를 리턴한다.

```
Object obj1 = new Object();
Object obj2 = new Object();

boolean result = obj1.equals(obj2);
boolean result = (obj1 == obj2);
```

equals() 메소드는 두 객체를 비교해서 논리적으로 동등하면 true를 리턴하고, 그렇지 않으면 false를 리턴한다. 저장하고 있는 데이터가 동일함을 뜻한다.

equals() 메소드를 재정의할 때에는 매개값이 기준 객체와 동일한 타입의 객체인지 먼저 확인해야 한다.

```
public class Member {

	private String id;
    
    public Member(String id) {
    	this.id = id;
    }
    
    @Override
    public boolean equals(Object obj){
    	if(obj instanceof Member) {
        	Member member = (Member) obj;
            if(id.equals(member.id)) {
            	return true;
            }
        }
        return fasle;
    }
}
```

#### 2\. 객체 해시코드 hashCode()

객체 해시코드란 객체를 식별할 하나의 정수값을 말한다.

Object 의 hashCode() 메소드는 객체의 메모리 번지를 이용해서 해시코드를 만들어 리턴하기 때문에 객체마다 다른 값을 가지고 있다. 논리적 동등 비교 시 hashCode()를 오버라이딩할 필요성이 있다.

HashSet, HashMap, Hashtable은 다음과 같은 방법으로 두 객체가 동등한지 비교한다.

우선 hashCode()값을 비교한다. 해시코드 값이 다르면 다른 객체로 판단하고, 같으면 equals 메소드로 다시 비교한다.

```
public class Key {
    private int number;

    public Key(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Key) {
            Key compareKey = (Key) obj;
            if(this.number == compareKey.number) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return number;
    }
}
```

결론적으로 객체의 동등 비교를 위해서는 Object의 equals() 메소드만 재정의하지 않고 hashCode() 메소드도 재정의해서 논리적 동등 객체일 경우 동일한 해시코드가 리턴되도록 해야 한다.

#### 3\. 객체 문자 정보 toString()

Object 클래스의 toString() 메소드는 객체의 문자 정보를 리턴한다. 객체의 문자 정보란 객체를 문자열로 표현한 것을 말한다. 기본적으로 Object 클래스의 toString() 메소드는 "클래스명@16진수해시코드"로 구성된 문자 정보를 리턴한다.

```
Object obj = new Object();
System.out.println(obj.toString());	// java.lang.Object@de6ced
```

하위 클래스에서 메소드를 재정의하여 간결하고 유익한 정보를 리턴하도록 되어 있다.

Date 클래스는 toString() 메소드를 재정의하여 현재 시스템의 날짜와 시간 정보를 리턴한다. 그리고 String 클래스는 toString 메소드를 재정의해서 저장하고 있는 문자열을 리턴한다.

콘솔에 출력하기 위한 System.out.println() 메소드를 사용할 때, 기본타입은 해당 값을 그대로 출력하고, 객체를 주면 객체의 toString() 메소드를 호출해서 리턴을 받아 출력하도록 되어 있다.

#### 4\. 객체 복제 clone()

**얇은 복제**

단순히 필드값을 복사해서 객체를 복제하는 것을 말한다. 필드값만 복제하기 때문에 필드가 기본 타입일 경우 값 복사가 일어나고, 참조 타입일 경우에는 객체의 번지가 복사된다.

clone() 메소드를 사용하기 위해서는 java.lang.Cloneable 인터페이스를 구현하고 있어야 한다.

구현하지 않으면 CloneNotSupportedException 예외가 발생한다. try-catch 구문이 필요하다.

```
try{
	Object obj = clone();

} catch(CloneNotSupportedException e) { }
```

얇은 복제의 경우 참조 타입 필드는 번지만 복제되기 때문에 원본 객체의 필드와 복제 객체의 필드는 같은 객체를 참조하게 된다. 복제 객체에서 참조 객체를 변경하면 원본에서도 변경된다.

**깊은 복제**

참조하고 있는 객체도 복제하는 것을 말한다. clone() 메소드를 재정의해서 사용한다.

#### 5\. 객체 소멸자 finalize()

참조하지 않은 배열이나 객체는 쓰레기 수집기(Garbage Collector)가 힙 영역에서 자동적으로 소멸시킨다. 쓰레기 수집기는 객체를 소멸하기 직전에 마지막으로 객체의 소멸자 finalize() 를 실행시킨다. 기본적으로 실행 내용이 없지만 만약 객체가 소멸되기 전에 마지막으로 사용했던 자원을 닫고 싶거나, 중요한 데이터를 저장하고 싶다면 Object 의 finalize() 를 재정의 할 수 있다.

## 4\. Objects 클래스

java.util.Objects 클래스는 객체 비교, 해시코드 생성, null 여부, 객체 문자열 리턴 등의 연산을 수행하는 정적 메소드들로 구성된 Object의 유틸리티 클래스이다.

int compare(T a, T b, Comparator<T> c) : 두 객체 a와 b를 Comparator를 사용해서 비교

boolean deepEquals(Object a, Object b) : 두 객체의 깊은 비교(배열의 항목까지 비교)

boolean equals(Object a, Object b) : 두 객체의 얇은 비교(번지만 비교)

int hash(Object... values) : 매개값이 저장된 배열의 해시코드 생성

int hashCode(Object o) : 객체의 해시코드 생성

boolean isNull(Object obj) : 객체가 null 인지 조사

boolean nonNull(Object obj) : 객체가 null 이 아닌지 조사

String toString(Object obj) : 객체의 toString() 리턴값 리턴

String toString(Object o, String nullDefault) : 객체의 toString() 리턴값 리턴, 첫 번째 매개값이 null일 경우 두 번째 매개값 리턴

## 5\. System 클래스

자바 프로그램은 운영체제상에서 바로 실행되는 것이 아니라 JVM 위에서 실행된다. 따라서 운영체제의 모든 기능을 가자 코드로 직접 접근하기란 어렵다. 하지만 java.lang 패키지에 속하는 System 클래스를 이용하면 운영체제의 일부 기능을 이용할 수 있다. 프로그램 종료, 키보드 입력, 모니터 출력, 메모리 정리, 현재 시간 읽기, 시스템 프로퍼티 읽기, 환경 변수 읽기 등이 가능하다. System 클래스의 모든 필드와 메소드는 정적필드와 정적메소드로 되어 있다.

exit() : 프로그램 종료

경우에 따라서는 강제적으로 JVM을 종료시킬 때도 있다. 현재 실행하고 있는 프로세스를 강제 종료시키는 역할을 한다.

gc() : 쓰레기 수집기 실행

쓰레기 수집기는 개발자가 직접 코드로 실행시킬 수 없고, JVM에게 가능한한 빨리 실행해 달라고 요청할 수는 있다. 되도록 사용하지 않도록 하자

currentTimeMillis(), nanoTime() : 현재 시각 읽기

밀리세컨드 단위와 나노세컨드 단위의 long 값을 리턴한다. 주로 프로그램 실행 소요 시간 측정에 사용된다.

getProperty() : 시스템 프로퍼티 읽기

시스템 프로퍼티는 JVM이 시작할 때 자동 설정되는 시스템 속성값을 말한다. 예를 들어 운영체제의 종류 및 자바 프로그램을 실행시킨 사용자 아이디, JVM 버전, 운영체제에서 사용되는 파일 경로 구분자 등이 여기에 속한다. key 와 value 로 구성되어 있다.

```
String value = System.getProperty(String key);
```

getenv() : 환경 변수 읽기

환경변수는 프로그램상의 변수가 아니라 운영체제에서 이름과 값으로 관리되는 문자열 정보이다.

```
String javaHome = System.getenv("JAVA_HOME");
System.out.println("JAVA_HOME: " + javaHome);
// [ JAVA_HOME ] C:\Program Files\Java\jdk1.8.0_05
```

## 6\. Class 클래스

자바는 클래스와 인터페이스의 메타 데이터를 java.lang 패키지에 소속된 Class 클래스로 관리한다. 여기서 메타 데이터란 클래스의 이름, 생성자 정보, 필드 정보, 메소드 정보를 말한다.

#### 1\. 객체 얻기(getClass(), forName())

```
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
```

#### 2\. 리플렉션(getDeclaredConstructors(), getDeclaredFields(), getDeclaredMethods())

Class 객체를 이용하면 클래스의 생성자, 필드, 메소드 정보를 알아낼 수 있다. 이것을 리플렉션(Reflection)이라고 한다.

#### 3\. 동적 객체 생성(newInstance())

## 7\. String 클래스

문자열은 데이터로서 아주 많이 사용된다. 생성, 추출, 비교, 찾기, 분리, 변환 등을 제공하는 메소드들을 잘 익혀두자.

#### 1\. String 생성자

파일의 내용을 읽거나 네트워크를 통해 받은 데이터는 보통 byte\[\] 배열이므로 이것을 문자열로 변환하기 위해 사용된다.

```
//배열 전체를 String 객체로 생성
String str = new String(byte[] bytes);
//지정한 문자셋으로 디코딩
String str = new String(byte[] bytes, String charsetName);
//배열의 offset 인덱스 위치부터 length만큼 String 객체로 생성
String str = new String(byte[] bytes, int offset, int length);
//지정한 문자셋으로 디코딩
String str = new String(byte[] bytes, int offset, int length, String charsetName);
```

#### 2\. String 메소드

char charAt(int index) : 특정 위치의 문자 리턴

boolean equals(Object anObject) : 두 문자열을 비교

byte\[\] getBytes() : byte\[\]로 리턴

byte\[\] getBytes(Charset charset) : 주어진 문자셋으로 인코딩한 byte\[\]로 리턴

int indexOf(String str) : 문자열 내에서 주어진 문자열의 위치를 리턴

int length() : 총 문자의 수를 리턴

String replace(CharSequence target, CharSequence replacement) : target 부분을 replacement로 대치한 새로운 문자열을 리턴

String substring(int beginIndex) : beginIndex 위치에서 끝까지 잘라낼 새로운 문자열을 리턴

String substring(int beginIndex, int endIndex) : beginIndex 위치에서 endIndex 전까지 잘라낸 새로운 문자열을 리턴

String toLowerCase() : 알파벳 소문자로 변환한 새로운 문자열을 리턴

String toUpperCase() : 알파벳 대문자로 변환한 새로운 문자열을 리턴

String trim() : 앞뒤 공백을 제거한 새로운 문자열을 리턴

String valueOf(int i), String valueOf(double d) : 기본 타입값을 문자열로 리턴

## 8\. StringTokenizer 클래스

문자열이 특정 구분자로 연결되어 있을 경우, 구분자를 기준으로 부분 문자열을 분리하기 위해서는 String의 split() 메소드를 이요하거나, java.util 패키지의 StringTokenizer 클래스를 이용할 수 있다. split()은 정규표현식으로 구분하고, StringTokenizer는 문자로 구분한다는 차이점이 있다.

#### 1\. spilt() 메소드

```
String[] result = "문자열".split("정규표현식");
```

```
public class StringSplitExample {
    public static void main(String[] args) {
        String text = "홍길동&이수홍,박연수,김자바-최명호";

        String[] names = text.split("&|,|-");

        for (String name : names) {
            System.out.println(name);
        }
    }
}
```

#### 2\. StringTokenizer 클래스

문자열이 한 종류의 구분자로 연결되어 있을 경우 손쉽게 문자열을 분리해 낼 수 있다.

```
StringTokenizer st = new StringTokenizer("문자열", "구분자");

String text = "홍길동/이수홍/박연수";
StringTokenizer st = new StringTokenizer(text, "/");
```

int countTokens() : 꺼내지 않고 남아 있는 토큰의 수

boolean hasMoreTokens() : 남아 있는 코느이 있는지 여부

String nextToken() : 토큰을 하나씩 꺼내옴

```
public class StringTokenizerExample {
    public static void main(String[] args) {
        String text = "홍길동/이수홍/박연수";

        StringTokenizer st = new StringTokenizer(text, "/");
        int countTokens = st.countTokens();
        for(int i=0; i<countTokens; i++) {
            String token = st.nextToken();
            System.out.println(token);
        }
        
        System.out.println();

        st = new StringTokenizer(text, "/");
        while( st.hasMoreTokens() ) {
            String token = st.nextToken();
            System.out.println(token);
        }
    }
}
```

## 9\. StringBuffer, StringBuilder 클래스

문자열을 저장하는 String은 내부의 문자열을 수정할  수 없다. 새로운 문자열을 리턴하는 것이다.

문자열을 변경하는 작업이 많을 경우네는 String 클래스를 사용하는 것보다는 java.lang 패키지의 StringBuffer 또는 StringBuilder 클래스를 사용하는 것이 좋다. 이 두 클래스는 내부 버퍼에 문자열을 저장해 두고, 그 안에서 추가, 수정, 삭제 작업을 할 수 있도록 설계되어 있다. String 처럼 객체를 만들지 않고도 문자열을 조작할 수 있다.

두 클래스의 차이점은 StringBuffer는 멀티 스레드 환경에서 사용할 수 있도록 동기화가 적용되어 있어 스레드에 안전하지만, StringBuilder는 단일 스레드 환경에서만 사용하도록 설계되어 있다.

```
StringBuilder sb = new StringBuilder();	// 16개 문자
StringBuilder sb = new StringBuilder(int capacity); // 주어진 개수만큼 문자
StringBuilder sb = new StringBuilder("Java"); // 주어진 문자열을 초기값
```

StringBuilder는 버퍼가 부족할 경우 자동으로 버퍼 크기를 늘리기 때문에 초기 버퍼의 크기는 그다지 중요하지 않다.

append(...) : 문자열 끝에 주어진 매개값을 추가

insert(int offset, ...) : 문자열 중간에 주어진 매개값을 추가

delete(int start, int end) : 문자열의 일부분을 삭제

deleteCharAt(int index) : 문자열에서 주어진 index의 문자를 삭제

replace(int start, int end, String str) : 문자열의 일부분을 다른 문자열로 대치reverse() : 문자열의 순서를 뒤바꿈

setCharAt(int index, char ch) : 문자열에서 주어진 index의 문자를 다른 문자로 대치

```
public class StringBuilderExample {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();

        sb.append("Java ");
        sb.append("Program Study");
        System.out.println(sb.toString());

        sb.insert(4, "2");
        System.out.println(sb.toString());

        sb.setCharAt(4, '6');
        System.out.println(sb.toString());

        sb.replace(6, 13, "Book");
        System.out.println(sb.toString());

        sb.delete(4, 5);
        System.out.println(sb.toString());

        int length = sb.length();
        System.out.println("length = " + length);

        String result = sb.toString();
        System.out.println("result = " + result);
    }
}
```

## 10\. 정규 표현식과 Pattern 클래스

문자열이 정해져 있는 형식으로 구성되어 있는지 검증해야 하는 경우가 있다. ex) email, 전화번호

## 11\. Arrays 클래스

배열 조작 기능을 가지고 있다. 배열 조작이란 배열의 복사, 항목 정렬, 항목 검색과 같은 기능을 말한다.

## 12\. Wrapper 클래스

자바는 기본 타입의 값을 갖는 객체를 생성할 수 있다. 이런 객체를 포장(Wrapper) 객체라고 하는데, 그 이유는 기본 타입의 값을 내부에 두고 포장하기 때문이다. 포장 객체의 특징은 포장하고 있는 기본 타입 값은 외부에서 변경할 수 없다. 만약 내부의 값을 변경하고 싶다면 새로운 포장 객체를 만들어야 한다.

박싱 하기 위해서는 포장 클래스의 생성자 매개값으로 기본 타입의 값 또는 문자열을 넘겨주면 된다.

Integer obj = new Integer(1000);

valueOf() 메소드를 이용할 수도 있다.

Integer obj = Integer.valueOf(1000);

언박싱하기 위해서는 각 포장 클래스마다 가지고 있는 "기본타입명+Value()" 메소드를 호출하면 된다.

int num = obj.intValue();

Integer obj = 100; // 자동 박싱 :

```
Integer obj = 100;	// 자동 박싱
int value1 = obj;	// 자동 언박싱
int value2 = obj + 100;	// 자동 언박싱
```

문자열을 기본 타입으로 변환

"parse+기본타입"

```
int num = Integer.parseInt("1000");
```

비교할 때는 equals() 메소드

```
Integer obj1 = 300;
Integer obj2 = 300;
(obj1 == obj2);	// false
obj1.equals(obj2);	//true
```

## 13\. Math, Random 클래스

#### 1\. Math 클래스

java.lang.Math 클래스는 수학 계산에 사용할 수 있는 메소드를 제공하고 있다.

int abs(int a)

double abs(double a) : 절대값

double deil(double a) : 올림값

double floor(double a) : 버림값

int max(int a, int b)

double max(double a, double b) : 최대값

int min(int a, int b)

double min(double a, double b) : 최소값

double random() : 랜덤값 -> int num = (int) (Math.random() \* n) + start;

double rint(double a) : 가까운 정수의 실수값

long round(double a) : 반올림값

#### 2\. Random 클래스

난수를 얻어내기 위해 다양한 메소드를 제공한다.

## 14\. Date, Calendar 클래스

시스템의 날짜와 시각을 읽을 수 있도록 java.util 패키지에서 두 클래스를 제공하고 있다.

#### 1\. Date 클래스

날짜를 표현하는 클래스이다. 객체 간에 날짜 정보를 주고 받을 때 주로 사용된다.

Date now = new Date(); // 현재 날짜를

특정 문자열 포맷으로 얻고 싶다면 java.text.SimpleDateFormat 클래스를 사용하자

```
public class DateExample {
    public static void main(String[] args) {
        Date now = new Date();
        String strNow1 = now.toString();
        System.out.println(strNow1);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
        String strNow2 = sdf.format(now);
        System.out.println(strNow2);
    }
}
```

#### 2\. Calendar 클래스

달력을 표현한 클래스이다. 추상 클래스로 new 연산자를 사용할 수 없다. getInstance() 메소드를 이용하면 현재 운영체제에 설정되어 있는 시간대를 기준으로 한 Calendar 하위 객체를 얻을 수 있다.

```
Calendar now = Calendar.getInstance();
```

```
public class CalendarExample {
    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();

        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);
        int amPm = now.get(Calendar.AM_PM);
        int hour = now.get(Calendar.HOUR);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
    }
}
```

## 15\. Format 클래스

형식 클래스는 java.text 패키지에 포함되어 있는데, 숫자 형식을 위해 DecimalFormat, 날짜 형식을 위해 SimpleDateFormat, 매개 변수화된 문자열 형식을 위해 MessageFormat 등을 제공한다.

## 16\. java.time 패키지

자바 7 이전까지는 Date와 Calendar 클래스를 이용해서 날짜와 시간 정보를 얻을 수 있었다. Date 클래스의 대부분의 메소드는 Depreated 되었고, Date의 용도는 단순히 특정 시점의 날짜 정보를 저장하는 역할만 한다. Calendar 클래스는 날짜와 시간 정보를 얻기에는 충분하지만, 날짜와 시간을 조작하거나 비교하는 기능이 불충분하다. 그래서 자바 8부터 날짜와 시간을 나타내는 여러 가지 API를 새롭게 추가했다. 이 API는 java.util 패키지에 없고 별도로 java.time 패키지와 하위 패키지로 제공된다.

#### 1\. 날짜와 시간 객체 생성

LocalDate : 로컬 날짜 클래스

LocalTime : 로컬 시간 클래스

LocalDateTime : 로컬 날짜 및 시간 클래스

ZonedDateTime : 특정 타임존의 날짜와 시간 클래스

Instant : 특정 시점의 Time-Stamp 클래스