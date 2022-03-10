이 글은 '이것이 자바다' 책의 내용을 읽고 정리한 것 입니다.

## 1\. 컬렉션 프레임워크 소개

다수의 객체를 저장해 두고 필요할 때마다 꺼내서 사용하는 경우가 많다. 배열은 쉽게 생성하고 사용할 수 있지만, 저장할 수 있는 객체 수가 배열을 생성할 때 결정되기 때문에 불특정 다수의 객체를 저장하기에는 문제가 있다.

자바는 객체들을 효율적으로 추가, 삭제, 검색할 수 있도록 java.util 패키지에 컬렉션과 관련된 인터페이스와 클래스들을 포함시켜 놓았다. 이들을 총칭해서 컬렉션 프레임워크라고 부른다.

컬렉션 프레임워크의 주요 인터페이스로는 List, Set, Map이 있다.

| 인터페이스 분류 |  | 특징 | 구현 클래스 |
| --- | --- | --- | --- |
| Collection | List | 순서를 유지하고 저장   중복 저장 가능 | ArrayList, Vector, LinkedList |
| Set | 순서를 유지하지 않고 저장   중복 저장 안 됨 | HashSet, TreeSet |
| Map |  | 키와 값이 순서쌍으로 저장   키는 중복 저장 안 됨 | HashMap, Hashtable,   TreeMap, Properties |

## 2\. List 컬렉션

객체를 일렬로 늘어놓은 구조를 가지고 있다. 객체를 인덱스로 관리하기 때문에 객체를 저장하면 자동 인덱스가 부여되고 인덱스로 객체를 검색, 삭제할 수 있는 기능을 제공한다. 객체 자체를 저장하는 것이 아니라 객체의 번지를 참조한다.

| 기능 | 메소드 | 설명 |
| --- | --- | --- |
| 객체   추가 | boolean add(E e) | 주어진 객체를 맨 끝에 추가 |
| void add(int index, E element) | 주어진 인덱스에 객체를 추가 |
| E set(int index, E element) | 주어진 인덱스에 저장된 객체를 주어진 객체로 바꿈 |
| 객체   검색 | boolean contains(Object o) | 주어진 객체가 저장되어 있는지 여부 |
| E get(int index) | 주어진 인덱스에 저장된 객체를 리턴 |
| boolean isEmpty() | 컬렉션이 비어 있는지 조사 |
| int size() | 저장되어 있는 전체 객체 수를 리턴  |
| 객체   삭제 | void clear() | 저장된 객체를 모두 삭제 |
| E remove(int index) | 주어진 인덱스에 저장된 객체를 삭제 |
| boolean remove(Object o) | 주어진 객체를 삭제 |

#### 1\. ArrayList

ArrayList는 List의 구현 클래스로, ArrayList에 객체를 추가하면 객체가 인덱스로 관리된다. 일반 배열과 같이 인덱스로 객체를 관리한다는 점에서 유사하지만, 배열은 생성할 때 크기가 고정되고 사용 중에 크기를 변경할 수 없지만, ArrayList는 저장 용량을 초과한 객체들이 들어오면 자동적으로 저장 용량이 늘어난다.

```
list<String> list = new ArrayList<String>();
list.add("홍길동");
String name = list.get(0);
```

ArrayList에 객체를 추가하면 인덱스 0부터 차례대로 저장된다. 특정 인덱스의 객체를 제거하면 바로 뒤 인덱스부터 마지막 인덱스까지 모두 앞으로 1씩 당ㄱ진다. 마찬가지로 특정 인덱스에 객체를 삽입하면 해당 인덱스부터 마지막 인덱스까지 모두 1씩 밀려난다.

따라서 빈번한 객체 삭제와 삽입이 일어나는 곳에서는 ArrayList를 사용하는 것이 바람직하지 않다. 이런 경우라면 LinkedList를 사용하는 것이 좋다. 인덱스 검색이나, 맨 마지막에 객체를 추가하는 경우에는 ArrayList가 더 좋은 성능을 발휘한다.

ArrayList를 생성하고 런타임 시 필요에 의해 객체들을 추가하는 것이 일반적이지만, 고정된 객체들로 구성된 List를 생성할 때도 있다. 이런 경우 Arrays.asList(T... a) 메소드를 사용하는 것이 간편하다.

```
List<T> list = Arrays.asList(T... a);
List<String> list1 = Arrays.asList("홍길동", "신용권", "김자바");
```

#### 2\. Vector

Vector는 ArrayList와 동일한 내부 구조를 가지고 있다. 다른 점은 동기화된 메소드로 구성되어 있기 때문에 멀티 스레드가 동시에 이 메소드들을 실행할 수 없고, 하나의 스레드가 실행을 완료해야만 다른 스레드를 실행할 수 있다. 그래서 멀티 스레드 환경에서 안전하게 객체를 추가, 삭제할 수 있다.

```
List<E> list = new Vector<E>();
```

#### 3\. LinkedList

LinkedList는 List구현 클래스이므로 ArrayList와 사용 방법은 똑같지만 내부 구조는 완전 다르다. ArrayList는 내부 배열에 객체를 저장해서 인덱스로 관리하지만, LinkedList는 인접 참조를 링크해서 체인처럼 관리한다. 빈번한 객체 삭제와 삽입이 일어나는 곳에서 좋은 성능을 발휘한다.

```
List<E> list = new LinkedList<E>();
```

## 3\. Set 컬렉션

List컬렉션은 저장 순서를 유지하고 있지만, Set컬렉션은 저장 순서가 유지되지 않는다. 또한 객체를 중복해서 저장할 수 없고, 하나의 null만 저장할 수 있다. Set 컬렉션에는 HashSet, LinkedHashSet, TreeSet 등이 있다.

| 기능 | 메소드 | 설명 |
| --- | --- | --- |
| 객체   추가 | boolean add(E e) | 주어진 객체를 저장, 객체가 성공적으로 저장되면 true를 리턴하고 중복 객체면 false를 리턴 |
| 객체   검색 | boolean contains(Object o) | 주어진 객체가 저장되어 있는지 여부 |
| boolean isEmpty() | 컬렉션이 비어 있는지 조사 |
| Iterator<E> iterator() | 저장된 객체를 한 번씩 가져오는 반복자 리턴 |
| int size() | 저장되어 있는 전체 객체 수 리턴 |
| 객체   삭제 | void clear() | 저장된 모든 객체를 삭제 |
| boolean remove(Object o) | 주어진 객체를 삭제 |

Set컬렉션은 인덱스로 객체를 검색해서 가져오는 메소드가 없다. 대신 전체 객체를 대상으로 한번씩 반복해서 가져오는 반복자를 제공한다.

```
Set<String> set = ...;
Iterator<String> iterator = set.iterator();
while(iterator.hasNext()) {
	String str = iterator.next();
    if(str.equals("홍길동")) {
    	iterator.remove();	// 실제 Set 컬렉션에서 객체가 제거됨
    }
}

for(String str : set) {
}
```

#### 1\. HashSet

HashSet은 Set인터페이스의 구현 클래스이다.

```
Set<E> set = new HashSet<E>();
```

HashSet은 객체들을 순서 없이 저장하고 동일한 객체는 중복 저장하지 않는다. 동일한 객체란 꼭 같은 인스턴스를 뜻하지는 않는다. HashSet은 객체를 저장하기 전에 먼저 객체의 hashCode() 메소드를 호출해서 해시코드를 알아낸다. 그리고 이미 저장되어 있는 객체들의 해시코드와 비교한다. 만약 동일한 해시코드가 있다면 다시 equals() 메소드로 두 객체를 비교해서 true가 나오면 동일한 객체로 판단하고 중복 저장을 하지 않는다.

## 4\. Map 컬렉션

map 컬렉션은 키(key)와 값(value)으로 구성된 Entry 객체를 저장하는 구조를 가지고 있다. 키와 값은 모두 객체이다. 키는 중복 저장될 수 없지만, 값은 중복 저장될 수 있다. 기존에 저장된 키와 동일한 키로 저장하면 기존의 값은 없어지고 새로운 값으로 대치된다.

Map 컬렉션에는 HashMap, Hashtable, LinkedHashMap, Properties, TreeMap 등이 있다.

| 기능 | 메소드 | 설명 |
| --- | --- | --- |
| 객체   추가 | V put(K key, V value) | 주어진 키로 값을 저장, 새로운 키일 경우 null을 리턴하고 동일한 키가 있을 경우 값을 대체하고 이전 값을 리턴 |
| 객체   검색 | boolean containsKey(Object key) | 주어진 키가 있는지 여부 |
| boolean containsValue(Object value) | 주어진 값이 있는지 여부 |
| Set<Map.Entry<K,V>> entrySet() | 키와 값의 쌍으로 구성된 모든 Map.Entry 객체를 Set에 담아서 리턴 |
| V get(Object key) | 주어진 키가 있는 값을 리턴 |
| boolean isEmpty() | 컬렉션이 비어 있는지 여부 |
| Set<K> keySet() | 모든 키를 Set 객체에 담아서 리턴 |
| int size() | 저장된 키의 총 수를 리턴 |
| Collection<V> values() | 저장된 모든 값을 Collection에 담아서 리턴 |
| 객체   삭제 | void clear() | 모든 Map.Entry(키와 값)를 삭제 |
| V remove(Object Key) | 주어진 키와 일치하는 Map.Entry를 삭제하고 값을 리턴 |

```
Map<K, V> map = ...;
Set<K> keySet = map.keySet();
Iterator<K> keyIterator = keySet.iterator();
while(keyIterator.hasNext()) {
	K key = keyIterator.next();
    V value = map.get(K);
}

Set<Map.Entry<K, V>> entryIterator = entrySet.interator();
while(entryIterator.hasNext()) {
	Map.Entry<K, V> entry = entryIterator.next();
    K key = entry.getKey();
    V value = entry.getValue();
}
```

#### 1\. HashMap

HashMap은 Map 인터페이스를 구현한 대표적인 Map 컬렉션이다. HashMap의 키로 사용할 객체는 hashCode() 와 equals() 메소드를 재정의해서 동등 객체가 될 조건을 정의해야 한다.

주로 키 타입은 String타입을 많이 사용하는데, String은 문자열이 같을 경우 동등 객체가 될 수 있도록 hashCode()와 equals() 메소드가 재정의되어 있다. HashMap을 생성하기 위해서는 키 타입과 값 타입을 파라미터로 주고 기본 생성자를 호출하면 된다.

```
Map<K, V> map = new HashMap<K, V>();
```

#### 2\. Hashtable

Hashtable은 HashMap과 동일한 내부 구조를 가지고 있다. 차이점은 Hashtable은 동기화된 메소드로 구성되어 있기 때문에 멀티 스레드가 동시에 이 메소드들을 실행할 수는 없고, 하나의 스레드가 실행을 완료해야만 다른 스레드를 실행할 수 있다.

## 5\. 검색 기능을 강화시킨 컬렉션

컬렉션 프레임워크는 검색 기능을 강화시킨 TreeSet과 TreeMap을 제공하고 있다. 이 컬렉션들은 이진 트리를 이용해서 계층적 구조를 가지면서 객체를 저장한다.

#### 1\. TreeSet

이진트리를 기반으로 한 Set 컬렉션이다. 하나의 노드는 노드값이 value와 왼쪽과 오른쪽 자식 노드를 참조하기 위한 두 개의 변수로 구성된다. TreeSet에 객체를 저장하면 자동으로 정렬되는데 부모값과 비교해서 낮은 것은 왼쪽 노드에, 높은 것은 오른쪽 자식 노드에 저장한다.

```
TreeSet<E> treeSet = new TreeSet<E>();
```

Set 인터페이스 타입 변수를 대입해도 되지만 TreeSet 클래스 타입으로 대입한 이유는 객체를 찾거나 범위 검색과 관련된 메소드를 사용하기 위해서이다.

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| E | first() | 제일 낮은 객체를 리턴 |
| E | last() | 제일 높은 객체를 리턴 |
| E | lower(E e) | 주어진 객체보다 바로 아래 객체를 리턴 |
| E | higher(E e) | 주어진 객체보다 바로 위 객체를 리턴 |
| E | floor(E e) | 주어진 객체와 동등한 객체가 있으면 리턴, 만약 없다면 주어진 객체의 바로 아래의 객체를 리턴 |
| E | ceiling(E e) | 주어진 객체와 동등한 객체가 있으면 리턴, 만약 없다면 주어진 객체의 바로 위의 객체를 리턴 |
| E | pollFirst() | 제일 낮은 객체를 꺼내오고 컬렉션에서 제거함 |
| E | pollLast() | 제일 높은 객체를 꺼내오고 컬렉션에서 제거함 |

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| Iterator<E> | descendingIterator() | 내림차순으로 정렬된 Iterator를 리턴 |
| NavigableSet<E> | desendingSet() | 내림차순으로 정렬된 NavigableSet을 반환 |

#### 2\. TreeMap

이진 트리를 기반으로 한 Map 컬렉션이다.

#### 3\. Comparable과 Comparator

TreeSet과 TreeMap의 키는 저장과 동시에 자동 오름차순으로 정렬되는데, 수자 타입일 경우에는 값으로 정렬하고, 문자열 타입일 경우에는 유니코드로 정렬한다. TreeSet과 TreeMap은 정렬을 위해 java.lang.Comparable을 구현한 객체를 요구하는데, 사용자 정의 클래스도 Comparable을 구현한다면 자동 정렬이 가능하다. compareTo() 메소드를 오버라이딩하면 된다.

```
public class Person implements Comparable<Person> {
	public String name;
    public int age;
    
    public Person(String name, int age){
    	this.name = name;
        this.age = age;
    }
    
    @Override
    public int compareTo(Person o){
		if(age<o.age) return -1;
        else if(age == o.age) return 0;
        else return 1;
	}
}
```

## 6\. LIFO와 FIFO 컬렉션

LIFO 자료구조를 제공하는 스택 클래스와 FIFO 자료구조를 제공하는 큐 인터페이스를 제공하고 있다.

#### 1\. Stack

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| E | push(E item) | 주어진 객체를 스택에 넣는다. |
| E | peek() | 스택의 맨 위 객체를 가져온다. 객체를 스택에서 제거하지 않는다. |
| E | pop() | 스택의 맨 위 객체를 가져온다. 객체를 스택에서 제거한다. |

```
Stack<E> stack = new Stack<E>();
```

```
public class StackExample {
    public static void main(String[] args) {
        Stack<Coin> coinBox = new Stack<>();

        coinBox.push(new Coin(100));
        coinBox.push(new Coin(50));
        coinBox.push(new Coin(500));
        coinBox.push(new Coin(10));

        while(!coinBox.isEmpty()) {
            Coin coin = coinBox.pop();
            System.out.println("꺼내온 동전 : " + coin.getValue() + "원");
        }
    }
}

public class Coin {
    private int value;

    public Coin(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
```

#### 2\. Queue

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| boolean | offer(E e) | 주어진 객체를 넣는다. |
| E | peek() | 객체 하나를 가져온다. 객체를 큐에서 제거하지 않는다. |
| E | pop() | 객체 하나를 가져온다. 객체를 큐에서 제거한다. |

Queue 인터페이스를 구현한 대표적인 클래스는 LinkedList이다. LinkedList는 List인터페이스를 구현했기 때문에 List 컬렉션이기도 하다.

```
Queue<E> queue = new LinkedList<E>();
```

```
public class QueueExample {
    public static void main(String[] args) {
        Queue<Message> messageQueue = new LinkedList<>();

        messageQueue.offer(new Message("sendMail", "홍길동"));
        messageQueue.offer(new Message("sendSMS", "신용권"));
        messageQueue.offer(new Message("sendKakaotalk", "홍두께"));

        while(!messageQueue.isEmpty()){
            Message message = messageQueue.poll();
            switch (message.command) {
                case "sendMail":
                    System.out.println(message.to + "님에게 메일을 보냅니다.");
                    break;
                case "sendSMS":
                    System.out.println(message.to + "님에게 SMS를 보냅니다.");
                    break;
                case "sendKakaotalk":
                    System.out.println(message.to + "님에게 카카오톡을 보냅니다.");
                    break;
            }
        }
    }
}

public class Message {
    public String command;
    public String to;

    public Message(String command, String to) {
        this.command = command;
        this.to = to;
    }
}
```

## 7\. 동기화된 컬렉션

컬렉션 프레임워크의 대부분의 클래스들은 싱글 스레드 환경에서 사용할 수 있도록 설계되었다. 그렇기 때문에 여러 스레드가 동시에 컬렉션에 접근한다면 의도하지 않게 요소가 변경될 수 있는 불안전한 상태가 된다. Vactor와 Hashtable은 동기화된 메소드로 구성되어 있기 때문에 멀티 스레드 환경에서 안전하게 요소를 처리할 수 있지만, ArrayList와 HashSet, HashMap은 동기화된 메소드로 구성되어 있지 않아 멀티 스레드 환경에서 안전하지 않다.

경우에 따라서는 ArrayList, HashSet, HashMap을 싱글 스레드 환경에서 사용하다가 멀티 스레드 환경으로 전달할 필요도 있을 것이다. 이런 경우를 대비해서 프레임워크는 비동기화된 메소드를 동기화된 메소드로 래핑하는 Collection의 synchronizedXXX() 메소드를 제공하고 있다.

| 리턴 타입 | 메소드 | 설명 |
| --- | --- | --- |
| List<T> | synchronizedList(List<T> lsit) | List를 동기화된 List로 리턴 |
| Map<K,V> | synchronizedMap(Map<K,V> m) | Map을 동기화된 Map으로 리턴 |
| Set<T> | synchronizedSet<Set<T> s) | Set을 동기화된 Set으로 리턴 |

## 8\. 병렬 처리를 위한 컬렉션

동기화된(synchronized) 컬렉션은 멀티 스레드 환경에서 하나의 스레드가 요소를 안전하게 처리하도록 도와주지만, 전체 요소를 빠르게 처리하지는 못한다. 하나의 스레드가 요소를 처리할 때 전체 잠금이 발생하여 다른 스레드는 대기 상태가 된다. 그렇기 때문에 멀티 스레드가 병렬적으로 컬렉션의 요소들을 처리할 수 없다. 자바는 멀티 스레드가 컬렉션의 요소를 병렬적으로 처리할 수 있도록 특별한 컬렉션을 제공하고 있다. java.util.concurrent 패키지의 ConcurrentHashMap과 ConcurrentLinkedQueue이다. ConcurrentHashMap은 Map 구현 클래스이고, ConcurrentLinkedQueue는 Queue 구현 클래스이다.

ConcurrentHashMap을 사용하면 스레드에 안전하면서도 멀티 스레드가 요소를 병렬적으로 처리할 수 있다. 부분 잠금을 사용하기 때문이다.