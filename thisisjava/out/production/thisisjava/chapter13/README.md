이 글은 '이것이 자바다' 책의 내용을 읽고 정리한 것 입니다.

## 1\. 왜 제네릭을 사용해야 하는가?

Java5 부터 제네릭(Generic)타입이 새로 추가되었는데, 제네릭 타입을 이용함으로써 잘못된 타입이 사용될 수 있는 문제를 컴파일 과정에서 제거할 수 있게 되었다. 제네릭은 컬렉션, 람다식, 스트림, NIO에서 널리 사용된다.

제네릭은 클래스와 인터페이스, 그리고 메소드를 정의할 때 타입(type)을 파라미터(parameter)로 사용할 수 있도록 한다. 타입 파라미터는 코드 작성 시 구체적인 타입으로 대체되어 다양한 코드를 생성하도록 해준다.

**컴파일 시 강한 타입 체크를 할 수 있다.**

실행 시 타입 에러가 나는 것보다는 컴파일 시에 미리 타입을 강하게 체크해서 에러를 사전에 방지하는 것이 좋다.

**타입 변환(casting)을 제거한다.**

비제네릭 코드는 불필요한 타입 변환을 하기 때문에 프로그램 성능에 악영향을 미친다.

```
List list = new ArrayList();
list.add("hello");
String str = (String) list.get(0);

List<String> list = new ArrayList<String>();
list.add("hello");
String str = list.get(0);
```

## 2\. 제네릭 타입 class<T>, interface<T>

제네릭 타입은 타입을 파라미터로 가지는 클래스와 인터페이스를 말한다.

```
public class 클래스명<T> { ... }
public interface 인터페이스명<T> { ... }
```

타입 파라미터는 변수명과 동일한 규칙에 따라 작성할 수 있지만, 일반적으로 대문자 알파벳 한 글자로 표현한다.

제네릭 타입을 실제 코드에서 사용하려면 타입 파라미터에 구체적인 타입을 지정해야 한다.

Object 타입을 사용하면 모든 종류의 자바 객체를 저장할 수 있다는 장점은 있지만, 저장할 때 타입 변환이 발생하고, 읽어올 때에도 타입 변환이 발생한다. 이러한 타입 변환이 빈번해지면 전체 프로그램 성능에 좋지 못한 결과를 가져올 수 있다. 모든 종류의 객체를 저장하면서 타입 변환이 발생하지 않도록 하는 방법이 제네릭에 있다.

```
public class Box<T> {
	private T t;
    public T get() { return t; }
    public void set(T t) { this.t = t; }
}

Box<String> box = new Box<String>();
box.set("hello");
String str = box.get();
```

필드 타입이 String 으로 변경되었고, 저장, 읽어올 때에도 String 타입만 가능하도록 변경되었다. 타입 변환도 발생하지 않는다.

## 3\. 멀티 타입 파라미터 class<K, V, ...>, interface<K, V, ...>

제네릭 타입은 두 개 이상의 멀티 타입 파라미터를 사용할 수 있다.

```
public class Product<T, M> {
	private T kind;
    private M model;
    
    public T getKind() { return this.kind; }
    public M getModel() { return this.model; }
    
    public void setKind(T kind) { this.kind = kind; }
    public void setModel(M model) { this.model = model; }
}
```

```
public class ProductExample {
    public static void main(String[] args) {
        Product<Tv, String> product1 = new Product<Tv, String>();
        //Product<Tv, String> product1 = new Product<>();
        product1.setKind(new Tv());
        product1.setModel("스마트Tv");
        Tv tv = product1.getKind();
        String tvModel = product1.getModel();
    }
}
```

자바 7부터 제네릭 타입 파라미터의 중복 기술을 줄이기 위해 다이아몬드 연산자 <>를 제공한다. 자바 컴파일러는 타입 파라미터 부분에 <> 연산자를 사용하면 타입 파라미터를 유추해서 자동으로 설정해준다.

## 4\. 제네릭 메소드 <T, R> R method(T t)

제네릭 메소드는 매개 타입과 리턴 타입으로 타입 파라미터를 갖는 메소드를 말한다.

```
public <타입파라미터, ...> 리턴타입 메소드명(매개변수, ...) { ... }

public <T> Box<T> boxing(T t) { ... }
```

제네릭 메소드는 두 가지 방식으로 호출할 수 있다. 코드에서 타입 파라미터의 구체적인 타입을 명시적으로 지정해도 되고, 컴파일러가 매개값의 타입을 보고 구체적인 타입을 추정하도록 할 수 있다.

```
리턴타입 변수 = <구체적타입> 메소드명(매개값);
리턴타입 변수 = 메소드명(매개값);

Box<Integer> box = <Integer>boxing(100);
Box<Integer> box = boxing(100);
```

## 5\. 제한된 타입 파라미터 <T extends 최상위타입>

타입 파라미터에 지정되는 구체적인 타입을 제한할 필요가 종종 있다.

상위 타입은 클래스 뿐만 아니라 인터페이스도 extends를 사용한다.

```
public <T extends 상위타입> 리턴타입 메소드(매개변수, ...) { ... }
```

## 6\. 와일드카드 타입 <?>, <? extends ...>, <? super ...>

코드에서 ?를 일반적으로 와일드카드(wildcard)라고 부른다. 제네릭 타입을 매개값이나 리턴타입으로 사용할 때 구체적인 타입 대신에 와일드카드를 다음과 같이 세 가지 형태로 사용할 수 있다.

<?> : Unbounded Wildcards(제한없음)

타입 파라미터를 대치하는 구체적인 타입으로 모든 클래스나 인터페이스 타입이 올 수 있다.

<? extends 상위타입> : Upper Bounded Wildcards(상위 클래스 제한)

타입 파라미터를 대치하는 구체적인 타입으로 상위 타입이나 하위 타입만 올 수 있다.

<? super 하위타입> : Lower Bounded Wildcards(하위 클래스 제한)

타입 파라미터를 대치하는 구체적인 타입으로 하위 타입이나 상위 타입이 올 수 있다.

## 7\. 제네릭 타입의 상속과 구현

제네릭 타입도 다른 타입과 마찬가지로 부모 클래스가 될 수 있다. 

```
public class ChildProduct<T, M> extends Product<T, M> { ... }
public class ChildProduct<T, M, C> extends Product<T, M> { ... }
```

자식 제네릭 타입은 추가적으로 타입 파라미터를 가질 수 있다. 

제네릭 인터페이스를 구현한 클래스도 제네릭 타입이 되는데, 제네릭 인터페이스를 구현한 클래스도 제네릭 타입이어야 한다.

```
public interface Storage<T> {
	public void add(T item, int index);
    public T get(int index);
}

public class StorageImpl<T> implements Storage<T> {
	private T[] array;
    
    public StorageImpl(int capacity) {
    	this.array = (T[]) (new Object[capacity]);
    }
    
    @Override
    public void add(T item, int index) {
    	array[index] = item;
    }
    
    @Override
    public T get(int index) {
    	return array[index];
    }
}
```