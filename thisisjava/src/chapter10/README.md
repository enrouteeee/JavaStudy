이 글은 '이것이 자바다' 책의 내용을 읽고 정리한 것 입니다.

## 1.  예외와 예외 클래스

오류 - 에러, 예외

컴퓨터 하드웨어의 오동작 또는 고장으로 인해 응용프로그램 실행 오류가 발생하는 것을 자바에서는 에러라고 한다.

예외란 사용자의 잘못된 조작 또는 개발자의 잘못된 코딩으로 인해 발생하는 프로그램 오류를 말한다. 예외가 발생되면 프로그램은 곧바로 종료된다는 점에서 에러와 동일하다. 그러나 예외는 예외 처리(Exception Handling)를 통해 프로그램을 종료하지 않고 정상 실행 상태가 유지되도록 할 수 있다.

예외 - 일반 예외(Exception)/컴파일러 체크 예외, 실행 예외(Runtime Exception)

컴파일하는 과정에서 예외 처리 코드가 필요한지 검사, 예외 처리 코드를 검사하지 않음

자바에서는 예외를 클래스로 관리한다. JVM은 프로그램을 실행하는 도중에 예외가 발생하면 해당 예외 클래스로 객체를 생성한다. 그리고 나서 예외 처리 코드에서 예외 객체를 이용할 수 있도록 해준다.

모든 예외 클래스들은 java.lang.Exception 클래스를 상속받는다.

일반 예외와 실행 예외 클래스를 구별하는 방법은 일반 예외는 Exception을 상속받지만 Runtime Exception을 상속받지 않는 클래스들이고, 실행 예외는 RuntimeException을 상속받은 클래스들이다. JVM은 RumtimeException을 상속했는지 여부를 보고 실행 예외를 판단한다.

## 2\. 실행 예외

실행예외는 자바 컴파일러가 체크 하지 않기 때문에 오로지 개발자의 경험에 의해서 예욀 처리 코드를 삽입해야 한다.

NullPointerException

자바 프로그램에서 가장 빈번하게 발생하는 실행 예외로 객체 참조가 없는 상태, 즉 null 값을 갖는 참조 변수로 객체 접근 연산자인 도트(.)를 사용했을 때 발생한다.

ArrayIndexOutOfBoundsException

배열에서 인덱스 범위를 초과하여 사용할 경우 실행 예외인 java.lang.ArrayIndexOutOfBoundsException이 발생한다.

NumberFormatException

문자열로 되어 있는 데이터를 숫자로 변경하는 경우가 자주 발생한다. 문자열을 숫자로 변환하는 방법은 여러가지가 있지만 가장 많이 사용되는 코도는 다음과 같다.

Integer.parseInt(String s) -> int : 주어진 문자열을 정수로 변환해서 리턴

Double.parseDouble(String s) -> double : 주어진 문자열을 실수로 변환해서 리턴

이 메소드들은 매개값이 문자열이 숫자로 변환될 수 있다면 숫자를 리턴하지만, 숫자로 변환될 수 없는 문자가 포함되어 있다면 java.lang.NumberFormatException을 발생시킨다.

ClassCastException

타입 변환(casting)은 상위 클래스와 하위 클래스 간에 발생하고 구현 클래스와 인터페이스 간에 발생한다.

억지로 타입 변환을 시도할 경우 ClassCastException이 발생한다. 타입 변환 전에 타입 변환이 가능한지 instanceof 연산자로 확인하는 것이 좋다.

## 3\. 예외 처리 코드

try-catch-finally 블록을 이용한다. 생성자 내부와 메소드 내부에서 작성되어 일반 예외와 실행 예외가 발생할 경우 예외 처리를 할 수 있도록 해준다.

```
try {

	예외 발생 가능 코드
    
} catch(예외클래스 e) {

	예외 처리
    
} finally {

	항상 실행
    
}
```

try 블록과 catch 블록에서 return문을 사용하더라도 finally 블록은 항상 실행된다.

## 4\. 예외 종류에 따른 처리 코드

다중 catch

```
try {

} catch(ArrayIndexOutOfBoundException e) {

} catch(NumberFormatException e) {

}
```

catch 블록이 여러개라 할지라도 단 하나의 catch 블록만 실행된다. 동시 다발적으로 예외가 발생하지 않고, 하나의 예외가 발생하면 즉시 실행을 멈추고 해당 catch 블록으로 이동하기 때문이다.

다중 catch 블록을 사용할 때 주의할 점은 상위 예외 클래스가 하위 예외 클래스보다 아래쪽에 위치해야 한다. 예외를 처리해줄 catch 블록은 위에서부터 차례대로 검색된다.

```
// 잘못된 try catch
try {

} catch(Exception e) {

} catch(ArrayIndexOutOfBoundsException e) {

}
```

멀티 catch

```
try {

} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {

}
```

## 5\. 자동 리소스 닫기

자바 7에서 새로 추가된 try-with-resources를 사용하면 예외 발생 여부와 상관없이 사용했던 리소스 객체(각종 입출력 스트림, 서버 소켓, 소켓, 각종 채널)의 close() 메소드를 호출해서 안전하게 리소스를 닫아준다.

리소스 : 데이터를 읽고 쓰는 객체

```
//자바 6 이전

FileInputStream fis = null;
try {
	fis = new FileInputStream("file.txt");
    ...
} catch(IOException e) {
	...
} finally {
	if(fis != null){
    	try {
        fis.close();
        } catch (IOException e) {}
	}
}
```

finally 블록에서 다시 try-catch를 사용해서 close() 메소드를 예외 처리해야 하므로 다소 복잡하게 보인다.

```
try(FileInputStream fis = new FIleInputStream("file.txt")) {
	...
} catch(IOException e) {
	...
}
```

try 블록이 정상적으로 실행을 완료했거나 도중에 예외가 발생하게 되면 자동적으로 FileInputStream의 close() 메소드가 호출된다. try{} 에서 예외가 발생하면 우선 close()로 리소스를 닫고 catch 블록을 실행한다. 만약 복수 개의 리소스를 사용해야 한다면 다음과 같이 작성할 수 있다.

```
try(FileInputStream fis = new FIleInputStream("file.txt");
	FileOutputStream fos = new FileOutputStream("file2.txt")
) {
	...
} catch(IOException e) {
	...
}
```

try-with-resourse를 사용하기 위해서는 조건이 있는데, 리소스 객체는 java.lang.AutoCloseable 인터페이스를 구현하고 있어야 한다. AutoCloseable에는 close() 메소드가 정의되어 있는데 try-with-resources는 바로 이 close() 메소드를 자동 호출한다. API 도큐먼트에서 AutoCloseable 인터페이스를 찾아 "All Known Implementing Classes."를 보면 try-with-resources와 함께 사용할 수 있는 리소스가 어떤 것이 있는지 알 수 있다.

직접 AutoCloseable 을 구현할 수도 있다.

```
public class FileInputStream implements AutoCloseable {
	private String file;
    
    public FileInputStream(String file) {
    	this.file = file;
    }
    
    public void read() {
    	System.out.println(file + "을 읽습니다.");
    }
    
    @Override
    public void close() throw Exception {
    	System.out.println(file + "을 닫습니다.");
    }
}
```

## 6\. 예외 떠넘기기

메소드 내부에서 예외가 발생할 수 있는 코드를 작성할 때 try-catch 블록으로 예외를 처리하는 것이 기본이지만, 경우에 따라서는 메소드를 호출한 곳으로 예외를 떠넘길 수도 있다. 이때 사용하는 키워드가 throws이다. throws 키워드는 메소드 선언부 끝에 작성되어 메소드에서 처리하지 않은 예외를 호출한 곳으로 떠넘기는 역할을 한다.throws 키워드 뒤에는 떠넘길 예외 클래스를 쉼표로 구분해서 나열해주면 된다.

```
리턴타입 메소드명(매개변수, ...) throws 예외클래스1, 예외클래스2, ... {
}
```

throws 키워드가 붙어있는 메소드는 반드시 try 블록 내에서 호출되어야 한다. 그리고 catch 블록에서 떠넘겨 받은 예외를 처리해야 한다.

```
public void method1() {
	try {
    	method2();
    } catch(ClassNotFoundException e) {
    
    }
}

public void method2() throws ClassNotFoundException {
	Class clazz = Class.forName("java.lang.String2");
}
```

method1() 에서도 try-catch 블록으로 예외를 처리하지 않고 throws 키워드로 다시 예외를 떠넘길 수 있다. 그러면 method1() 을 호출하는 곳에서 결국 try-catch 블록을 사용해서 예외를 처리해야 한다.

## 7\. 사용자 정의 예외와 예외 발생

자바 표준 API에서 제공하는 예외 클래스만으로는 다양한 종류의 예외를 표현할 수 없다. 애플리케이션 서비스와 관련된 예외를 애플리케이션 예외라고 하며, 애플리케이션 예외는 사용자가 직접 정의해서 만들어야 하므로 사용자 정의 예외라고도 한다.

```
public class XXXException extends [ Exception | RuntimeException ] {
	public XXXException() { }
    public XXXException(String message) { super(message); }
}
```

사용자 정의 예외 클래스 이름은 Exception으로 끝나는 것이 좋다. 필드, 생성자, 메소드 선언들을 포함할 수 있지만 대부분 생성자 선언만을 포함한다. 생성자는 두개를 선언하는 것이 일반적인데, 하나는 매개 변수가 없는 기본 생성자이고, 다른 하나는 예외 발생 원인(예외 메시지)을 전달하기 위해 String 타입의 매개 변수를 갖는 생성자이다. String 타입의 매개 변수를 갖는 생성자는 상위 클래스의 생성자를 호출하여 예외 메시지를 넘겨준다. 예외 메시지의 용도는 catch {\] 블록의 예외 처리 코드에서 이용하기 위해서이다.

```
public class BalanceInsufficientException extens Exception {
	public BalanceInsufficientException() { }
    public BalanceInsufficientException(String message) {
    	super(message);
    }
}
```

**예외 발생시키기**

```
throw new XXXException();
throw new XXXException("메시지");
```

예외 발생 코드를 가지고 있는 메소드는 내부에서 try-catch 블록으로 예외를 처리할 수 있지만, 대부분은 자신을 호출한 곳에서 예외를 처리하도록 throws 키워드로 예외를 떠넘긴다.

## 8\. 예외 정보 얻기

try 블록에서 예외가 발생되면 예외 객체는 catch 블록의 매개 변수에서 참조하게 되므로 매개 변수를 이용하면 예외 객체의 정보를 알 수 있다. 모든 예외 객체는 Exception 클래스를 상속하기 때문에 Exception이 가지고 있는 메소드들을 모든 예외 객체에서 호출할 수 있다. 가장 많이 사용되는 메소드는 getMessage() 와 prinStackTrace() 이다. 예외를 발생시킬 때 String 타입의 메시지를 갖는 생성자를 이용하였다면, 메시지는 자동적으로 예외 객체 내부에 저장된다. 이와 같은 예외 메시지는 catch 블록에서 getMessage() 메소드의 리턴값으로 얻을 수 있다.

printStackTrace() 메소드는 예외 발생 코드를 추적해서 모두 콘솔에 출력한다. 어떤 예외가 어디에서 발생했는지 상세하게 출력해주기 때문에 프로그램을 테스트하면서 오류를 찾을 때 활용된다.

```
throw new XXXException("예외 메시지");

} catch(Exception e) {
	String message = e.getMessage();
    
    e.printStackTrace();
}
```