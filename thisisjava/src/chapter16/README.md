이 글은 '이것이 자바다' 책의 내용을 읽고 정리한 것 입니다.

## 1\. 스트림 소개

스트림은 자바 8부터 추가된 컬렉션(배열 포함)의 저장 요소를 하나씩 참조해서 람다식(함수적-스타일)으로 처리할 수 있도록 해주는 반복자이다.

#### 1\. 반복자 스트림

자바 7이전까지는 List<String> 컬렉션에서 요소를 순차적으로 처리하기 위해 Iterator 반복자를 사용해왔다.

스트림으로 변경해보자

```
List<String> list = Arrays.asList("abc", "def", "ghi");
Iterator<String> iterator = list.iterator();
while(iterator.hasNext()) {
	String name = iterator.next();
    System.out.println(name);
}

Stream<String> stream = list.stream();
stream.forEach( name -> System.out.println(name) );
```

컬렉션(java.util.Collection)의 stream() 메소드로 스트림 객체를 얻고 나서 stream.forEach(name -> System.out.println(name)); 메소드를 통해 컬렉션의 요소를 하나씩 콘솔에 출력한다. forEach() 메소드는 다음과 같이 Consumer 함수적 인터페이스 타입의 매개값을 가지므로 컬렉션의 요소를 소비할 코드를 람다식으로 기술할 수 있다.

```
void forEach(Consumer<T> action)
```

#### 2\. 스트림의 특징

Iterator와 비슷한 역할을 하는 반복자이지만, 람다식으로 요소 처리 코드를 제공하는 점과 내부 반복자를 사용하므로 병렬 처리가 쉽다는 점 그리고 중간 처리와 최종 작업을 수행하는 점에서 많은 차이를 가지고 있다.

**람다식으로 요소 처리 코드를 제공한다.**

Stream이 제공하는 대부분의 요소 처리 메소드는 함수적 인터페이스 매개 타입을 가지기 때문에 람다식 또는 메소드 참조를 이용해서 요소 처리 내용을 매개값으로 전달할 수 있다.

**내부 반복자를 사용하므로 병렬 처리가 쉽다.**

외부 반복자란 개발자가 코드로 직접 컬렉션의 요소를 반복해서 가져오는 코드패턴을 말한다. index를 이용하는 for문 그리고 Iterator를 이용하는 while문은 모두 외부 반복자를 이용하는 것이다. 반면에 내부 반복자는 컬렉션 내부에서 요소들을 반복시키고, 개발자는 요소당 처리해야 할 코드만 제공하는 코드 패턴을 말한다.

컬렉션 내부에서 어떻게 요소를 반복시킬 것인가는 컬렉션에게 맡겨두고, 개발자는 요소 처리 코드에만 집중할 수 있다. 내부 반복자는 요소들의 반복 순서를 변경하거나, 멀티 코어 CPU를 최대한 활용하기 위해 요소들을 분배시켜 병렬 작업을 할 수 있게 도와주기 때문에 하나씩 처리하는 순차적 외부 반복자보다는 효율적으로 요소를 반복시킬 수 있다.

**스트림은 중간 처리와 최종 처리를 할 수 있다.**

중간 처리에서는 매핑, 필터링, 정렬을 수행하고 최종 처리에서는 반복, 카운팅, 평균, 총합 등의 집계 처리를 수행한다.

```
public class MapAndReduceExample {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("qew", 10),
                new Student("asd", 20),
                new Student("zxc", 30)
        );

        double avg = list.stream()
                .mapToInt(Student::getScore)
                .average()
                .getAsDouble();

        System.out.println("평균 점수 : " + avg);
    }
}
```

## 2\. 스트림의 종류

#### 1\. 컬렉션으로부터 스트림 얻기

```
public class FromCollectionExample {
    public static void main(String[] args) {
        List<Student> list = Arrays.asList(
                new Student("qew", 10),
                new Student("asd", 20),
                new Student("zxc", 30)
        );

        Stream<Student> stream = list.stream();
        stream.forEach(s -> System.out.println(s.getName()));
    }
}
```

#### 2\. 배열로부터 스트림 얻기

```
public class FromArrayExample {
    public static void main(String[] args) {
        String[] strArray = { "qewr", "asdf", "zxcv" };
        Stream<String> stringStream = Arrays.stream(strArray);
        stringStream.forEach(a -> System.out.print(a + ","));

        System.out.println();

        int[] intArray = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(intArray);
        intStream.forEach(a -> System.out.print(a + ","));
        System.out.println();
    }
}
```

#### 3\. 숫자 범위로부터 스트림 얻기

```
public class FromIntRangeExample {
    public static int sum;

    public static void main(String[] args) {
        IntStream stream = IntStream.rangeClosed(1, 100);
        stream.forEach(a -> sum += a);
        System.out.println("총합 : " + sum);
    }
}
```

#### 4\. 파일로부터 스트림 얻기

```
public class FromFileContentExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("src/sec02/stream_kind/linedata.txt");
        Stream<String> stream;

        stream = Files.lines(path, Charset.defaultCharset());
        stream.forEach(System.out::println);
        System.out.println();

        File file = path.toFile();
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        stream = br.lines();
        stream.forEach(System.out::println);
    }
}
```

#### 5\. 디렉토리로부터 스트림 얻기

```
public class FromDirectoryExample {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("C:/JavaProgramming/source");
        Stream<Path> stream = Files.list(path);
        stream.forEach(p -> System.out.println(p.getFileName()));
    }
}
```

## 3\. 스트림 파이프라인

대량의 데이터를 가공해서 축소하는 것을 일반적으로 리덕션(Reduction) 이라고 하는데, 데이터의 합계, 평균값, 카운팅, 최대값, 최소값 등이 대표적인 리덕션의 결과물이라고 볼 수 있다. 그러나 컬렉션의 요소를 리덕션의 결과물로 바로 집계할 수 없을 경우에는 집계하기 좋도록 필터링, 매핑, 정렬, 그룹핑 등의 중간 처리가 필요하다.

#### 1\. 중간 처리와 최종 처리

스트림은 데이터의 중간 처리와 최종 처리를 파이프라인(pipelines)으로 해결한다. 파이프라인은 여러 개의 스트림이 연결된어 있는 구조를 말한다. 파이프라인에서 최종 처리를 제외하고는 모두 중간 처리 스트림이다.

중간 스트림이 생성될 때 요소들이 바로 중간 처리되는 것이 아니라 최종 처리가 시작되기 전까지 중간 처리는 지연된다. 최종 처리가 시작되면 비로소 컬렉션의 요소가 하나씩 중간 스트림에서 처리되고 최종 처리까지 오게 된다.

Stream 인터페이스에는 필터링, 매핑, 정렬 등의 많은 중간 처리 메소드가 있는데, 이 메소드들은 중간 처리된 스트림을 리턴한다. 그리고 이 스트림에서 다시 중간 처리 메소드를 호출해서 파이프라인을 형성하게 된다.

```
public class StreamPipelines {
    public static void main(String[] args) {
        List<Member> list = Arrays.asList(
                new Member("qwe", Member.MALE, 30),
                new Member("asd", Member.FEMALE, 20),
                new Member("zxc", Member.MALE, 45),
                new Member("wer", Member.FEMALE, 27)
        );

        double ageAvg = list.stream()
                .filter(m -> m.getSex() == Member.MALE)
                .mapToInt(Member::getAge)
                .average()
                .getAsDouble();

        System.out.println("남자 평균 나이: " + ageAvg);
    }
}

public class Member {
    public static int MALE = 0;
    public static int FEMALE = 1;

    private String name;
    private int sex;
    private int age;

    public Member(String name, int sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}
```

#### 2\. 중간 처리 메소드와 최종 처리 메소드

## 4\. 필터링(distinct(), filter())

필터링은 중간 처리 기능으로 요소를 걸러내는 역할을 한다. 필터링 메소드인 distinct()와 filter()메소드는 모든 스트림이 가지고 있는 공통 메소드이다.

distinct() 메소드는 중복을 제거하는데, Stream의 경우 Object.equals(Object)가 true이면 동일한 객체로 판단하고 중복을 제거한다. IntStream, LongStream, DoubleStream은 동일값일 경우 중복을 제거한다.

```
public class FilteringExample {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("qwe","wer","qrt","wer","qdf","dfg");

        names.stream()
                .distinct()
                .forEach(n -> System.out.println(n));
        System.out.println();

        names.stream()
                .filter(n -> n.startsWith("q"))
                .forEach(n -> System.out.println(n));
        System.out.println();

        names.stream()
                .distinct()
                .filter(n -> n.startsWith("q"))
                .forEach(n -> System.out.println(n));
        System.out.println();

    }
}
```

## 5\. 매핑 flatMapXXX(), mapXXX(), asXXXStream(), boxed()

매핑은 중간 처리 기능으로 스트림의 요소를 다른 요소로 대체하는 작업을 말한다.

#### 1\. flatMapXXX() 메소드

요소를 대체하는 복수 개의 요소들로 구성된 새로운 스트림을 리턴한다.

```
public class FlatMapExample {
    public static void main(String[] args) {
        List<String> inputList1 = Arrays.asList("java8 lambda", "stream mapping");
        inputList1.stream()
                .flatMap(data -> Arrays.stream(data.split(" ")))
                .forEach(word -> System.out.println(word));
        System.out.println();

        List<String> inputList2 = Arrays.asList("10, 20, 30", "40, 50, 60");
        inputList2.stream()
                .flatMapToInt(data -> {
                    String[] strArr = data.split(",");
                    int[] intArr = new int[strArr.length];
                    for(int i=0; i<strArr.length; i++){
                        intArr[i] = Integer.parseInt(strArr[i].trim());
                    }
                    return Arrays.stream(intArr);
                })
                .forEach(number -> System.out.println(number));
    }
}
```

#### 2\. mapXXX() 메소드

요소를 대체하는 요소로 구성된 새로운 스트림을 리턴한다.

```
public class MapExample {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 10),
                new Student("신용권", 20),
                new Student("유미선", 30)
        );

        studentList.stream()
                .mapToInt(Student::getScore)
                .forEach(score -> System.out.println(score));
    }
}
```

#### 3\. asDoubleStream(), asLongStream(), boxed() 메소드

asDoubleStream() 메소드는 IntStream의 int요소 또는 LongStream의 long 요소를 double 요소로 타입 변환해서 DoubleStream을 생성한다. 마찬가지로 asLongStream() 메소드는 IntStream의 int요소를 long 요소로 타입 변환해서 LongStream을 생성한다. boxed() 메소드는 int, long, double 요소를 Interger, Long, Double 요소로 박싱해서 Stream을 생성한다.

```
public class AsDoubleStreamAndBoxedExample {
    public static void main(String[] args) {
        int[] intArray = {1, 2, 3, 4, 5};

        IntStream intStream = Arrays.stream(intArray);
        intStream
                .asDoubleStream()
                .forEach(d -> System.out.println(d));

        System.out.println();

        intStream = Arrays.stream(intArray);
        intStream
                .boxed()
                .forEach(obj -> System.out.println(obj.intValue()));
        
    }
}
```

## 6\. 정렬 sorted()

스트림은 요소가 최종 처리되기 전에 중간 단계에서 요소를 정렬해서 최종 처리 순서를 변경할 수 있다.

```
public class SortingExample {
    public static void main(String[] args) {
        IntStream intStream = Arrays.stream(new int[] {5, 3, 2, 1, 4});
        intStream
                .sorted()
                .forEach(n -> System.out.println(n + "," ));
        System.out.println();

        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 30),
                new Student("임한길", 20),
                new Student("임두길", 10),
                new Student("임세길", 15)
        );

        studentList.stream()
                .sorted()
                .forEach(s -> System.out.println(s.getScore() + ","));
        System.out.println();

        studentList.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(s -> System.out.println(s.getScore() + ","));
    }
}

public class Student implements Comparable<Student> {
    private String name;
    private int score;

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int compareTo(Student o) {
        return Integer.compare(score, o.score);
    }
}
```

## 7\. 루핑 peek(), forEach()

루핑은 요소 전체를 반복하는 것을 말한다. 루핑하는 메소드는 peek(), forEach()가 있다.

이 두 메소드는 기능에서는 동일하지만, 동작 방식은 다르다. peek()는 중간 처리 메소드이고, forEach()는 최종 처리 메소드이다.

peek()는 중간 처리 단계에서 전체 요소를 루핑하면서 추가적인 작업을 하기 위해 사용한다. 최종 처리 메소드가 실행되지 않으면 지연되기 때문에 반드시 최종 처리 메소드가 호출되어야 동작한다.

```
//동작 x
intStream
	.filter( a -> a%2 == 0 )
    .peek( a -> System.out.println(a) )

//동작 o
intStream
	.filter( a -> a%2 == 0 )
    .peek( a -> System.out.println(a) )
    .sum()
```

```
public class LoopingExample {
    public static void main(String[] args) {
        int[] intArr = {1, 2, 3, 4, 5};

        System.out.println("[peek()를 마지막에 호출한 경우]");
        Arrays.stream(intArr)
                .filter(a -> a % 2 == 0)
                .peek(n -> System.out.println(n));

        System.out.println("최종 처리 메소드를 마지막에 호출한 경우]");
        int total = Arrays.stream(intArr)
                .filter(a -> a % 2 == 0)
                .peek(n -> System.out.println(n))
                .sum();
        System.out.println("총합: " + total);

        System.out.println("[forEach()를 마지막에 호출한 경우]");
        Arrays.stream(intArr)
                .filter(a -> a % 2 == 0)
                .forEach(n -> System.out.println(n));
    }
}
```

## 8\. 매칭 allMatch(), anyMatch(), noneMatch()

스트림 클래스는 최종 처리 단계에서 요소들이 특정 조건에 만족하는지 조사할 수 있도록 세 가지 매칭 메소드를 제공하고 있다.

allMatch() 메소드는 모든 요소들이 매개값으로 주어진 Predicate의 조건을 만족하는지 조사하고, anyMatch() 메소드는 최소한 한 개의 요소가 매개값으로 주어진 Predicate의 조건을 만족하는지 조사한다. noneMatch() 는 모든 요소들이 매개값으로 주어진 Predicate의 조건을 만족하지 않는지 조사한다.

```
public class MatchExample {
    public static void main(String[] args) {
        int[] intArr = {2, 4, 6};

        boolean result = Arrays.stream(intArr)
                .allMatch(a -> a % 2 == 0);
        System.out.println("모두 2의 배수인가 ? " + result);

        result = Arrays.stream(intArr)
                .anyMatch(a -> a % 3 == 0);
        System.out.println("하나라도 3의 배수가 있는가 ? " + result);

        result = Arrays.stream(intArr)
                .noneMatch(a -> a % 3 == 0);
        System.out.println("3의 배수가 없는가 ? " + result);
    }
}
```

## 9\. 기본 집계 sum(), count(), average(), max(), min()

집계(Aggregate)는 최종 처리 기능으로 요소들을 처리해서 카운팅, 합계, 평균값, 최대값, 최소값 등과 같이 하나의 값으로 산출하는 것을 말한다. 집계는 대량의 데이터를 가공해서 축소하는 리덕션(Reduction)이라고 볼 수 있다.

#### 1\. 스트림이 제공하는 기본 집계

```
public class AggregateExample {
    public static void main(String[] args) {
        long count = Arrays.stream(new int [] {1,2,3,4,5})
                .filter(n -> n%2 == 0)
                .count();
        System.out.println("2의 배수 개수 : " + count);

        long sum = Arrays.stream(new int [] {1,2,3,4,5})
                .filter(n -> n%2 == 0)
                .sum();
        System.out.println("2의 배수의 합 : " + sum);

        double average = Arrays.stream(new int [] {1,2,3,4,5})
                .filter(n -> n%2 == 0)
                .average()
                .getAsDouble();
        System.out.println("2의 배수의 평균 : " + average);

        int max = Arrays.stream(new int [] {1,2,3,4,5})
                .filter(n -> n%2 == 0)
                .max()
                .getAsInt();
        System.out.println("최대값 : " + max);

        int min = Arrays.stream(new int [] {1,2,3,4,5})
                .filter(n -> n%2 == 0)
                .min()
                .getAsInt();
        System.out.println("최대값 : " + min);

        int first = Arrays.stream(new int [] {1,2,3,4,5})
                .filter(n -> n%3 == 0)
                .findFirst()
                .getAsInt();
        System.out.println("첫번째 3의 배수 : " + first);
    }
}
```

#### 2\. Optional 클래스

Optional, OptionalDouble, OptionalInt, OptionalLong. 이 클래스들은 저장하는 값의 타입만 다를 뿐 제공하는 기능은 거의 동일하다. Optional 클래스는 집계값만 저장하는 것이 아니라, 집계 값이 존재하지 않을 경우 디폴트 값을 설정할 수도 있고, 집계 값을 처리하는 Consumer도 등록할 수 있다.

## 10\. 커스텀 집계 reduce()

다양한 집계 결과물을 만들 수 있도록 reduce() 메소드도 제공한다.

```
public class ReductionExample {
    public static void main(String[] args) {
        List<Student> studentList = Arrays.asList(
                new Student("홍길동", 92),
                new Student("임두길", 95),
                new Student("임세길", 88),
                new Student("임네길", 98)
        );

        int sum = studentList.stream()
                .map(Student::getScore)
                .reduce((a,b) -> a+b)
                .get();

        System.out.println("sum : " + sum);
    }
}
```

## 11\. 수집 collect()

스트림은 요소들을 필터링 또는 매핑한 후 요소들을 수집하는 최종 처리 메소드인 collect()를 제공하고 있다. 이 메소드를 이용하면 필요한 요소만 컬렉션으로 담을 수 있고, 요소들을 그룹핑한 후 집계(리덕션)할 수 있다.

#### 1\. 필터링한 요소 수집

Stream의 collect 메소드는 필터링 또는 매핑된 요소들을 새로운 컬렉션에 수집하고, 이 컬렉션을 리턴한다.

```
public class ToListExample {
    public static void main(String[] args) {
        List<Member> list = Arrays.asList(
                new Member("qwe", Member.MALE, 30),
                new Member("asd", Member.FEMALE, 20),
                new Member("zxc", Member.MALE, 45),
                new Member("wer", Member.FEMALE, 27)
        );

        //남학생들만
        List<Member> maleList = list.stream()
                .filter(s -> s.getSex() == Member.MALE)
                .collect(Collectors.toList());
        maleList.stream()
                .forEach(s -> System.out.println(s.getName()));
        
    }
}
```

## 12\. 병렬 처리

멀티 코어 CPU환경에서 하나의 작업을 분할해서 각각의 코어가 병렬적으로 처리하는 것을 말하는데, 병렬 처리의 목적은 작업 처리 시간을 줄이기 위한 것이다. 자바 8부터 요소를 병렬 처리할 수 있도록 하기 위해 병렬 스트림을 제공하기 때문에 컬렉션(배열)의 전체 요소 처리 시간을 줄여 준다.

#### 1\. 동시성과 병렬성

**데이터 병렬성**

전체 데이터를 쪼개어 서브 데이터들로 만들고 이 서브 데이터들을 병렬 처리해서 작업을 빨리 끝내는 것을 말한다. 자바 8에서 지원하는 병렬 스트림은 데이터 병렬성을 구현한 것이다. 멀티 코어의 수만큼 대용량 요소를 서브 요소들로 나누고, 각각의 서브 요소들을 분리된 스레드에서 병렬 처리시킨다.

**작업 병렬성**

서로 다른 작업을 병렬 처리하는 것을 말한다. 작업 병렬성의 대표적인 예는 웹 서버이다. 웹 서버는 각각의 브라우저에서 요청한 내용을 개별 스레드에서 병렬로 처리한다.