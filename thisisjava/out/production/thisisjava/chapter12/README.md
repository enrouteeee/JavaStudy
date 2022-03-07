이 글은 '이것이 자바다' 책의 내용을 읽고 정리한 것 입니다.

## 1.  멀티스레드 개념

#### 1\. 프로세스와 스레드

운영체제에서는 실행중인 하나의 어플리케이션을 프로세스(process)라고 한다. 사용자가 애플리케이션을 실행하면 운영체제로부터 실행에 필요한 메모리를 할당받아 애플리케이션의 코드를 실행한다.

하나의 애플리케이션이 다중 프로세스를 만들기도 한다. 크롬 브라우저를 두 개 실행시키면 두 개의 크롬 프로세스가 생성된 것이다.

멀티 스레드를 이용해서 하나의 프로세스가 두 가지 이상의 작업을 처리할 수 있다.

스레드는 하나의 코드 실행 흐름이다.

멀티 프로세스가 애플리케이션 단위의 멀티 태스킹이라면 멀티 스레드는 애플리케이션 내부에서의 멀티 태스킹이라고 볼 수 있다.

멀티 스레드는 하나의 프로세스 내부에 생성되기 때문에 하나의 스레드가 예외를 발생기키면 프로세스 자체가 종료될 수 있어 다른 스레드에게 영향을 미치게 된다. 그렇기 때문에 멀티 스레드에서는 예외 처리에 만전을 기해야 한다.

멀티 스레드는 다양한 곳에서 사용된다. 대용량 데이터의 처리 시간을 줄이기 위해 데이터를 분할해서 병렬로 처리하는 곳에서 사용되기도 하고, UI를 가지고 있는 애플리케이션에서 네트워크 통신을 하기 위해 사용되기도 한다. 또한 다수 클라이언트의 요청을 처리하는 서버를 개발할 때에도 사용된다.

#### 2\. 메인 스레드

모든 자바 애플리케이션은 메인 스레드가 main() 메소드를 실행하면서 시작된다. main()메소드의 마지막 코드를 실행시키거나 return 문을 만나면 실행이 종료된다.

메인 스레드는 필요에 따라 작업 스레드들을 만들어서 병렬로 코드를 실행할 수 있다. 즉 멀티 스레드를 생성해서 멀티 태스킹을 수행한다.

싱글 스레드 애플리케이션에서는 메인 스레드가 종료되면 프로세스도 종료된다. 하지만 멀티 스레드 애플리케이션에서는 실행 중인 스레드가 하나라도 있다면, 프로세스는 종료되지 않는다. 메인 스레드가 작업 스레드보다 먼저 종료되더라도 작업 스레드가 계속 실행중이라면 프로세스는 종료되지 않는다.

## 2\. 작업 스레드 생성과 실행

#### 1\. Thread 클래스로부터 직접 생성

java.lang.Thread 클래스로부터 작업 스레드 객체를 직접 생성하려면 Runnable을 매개값으로 갖는 생성자를 호출해야 한다.

```
Thread thread = new Thread(Runnable target);
```

Runnable은 작업 스레드가 실행할 수 있는 코드를 가지고 있는 객체라고 해서 붙여진 이름이다.

Runnable은 인터페이스 타입이기 때문에 구현 객체를 만들어 대입해야 한다. Runnable에는 run() 메소드 하나가 정의되어 있는데, 구현 클래스는 run()을 재정의해서 작업 스레드가 실행할 코드를 작성해야 한다.

```
public class Task implements Runnable {
    @Override
    public void run() {
        스레드가 실행할 코드;
    }
}

Runnable tsak = new Task();
Thread thread = new Thread(task);
```

Runnable 구현 객체를 매개값으로 Thread 생성자를 호출하면 비로소 작업 스레드가 생성된다.

익명 객체로 Runnable을 구현해도 된다. Runnable 인터페이스는 run() 메소드 하나만 정의되어 있기 때문에 함수적 인터페이스이다. 따라서 람다식을 매개값으로 사용할 수도 있다.

```
Thread thread = new Thread(new Runnable(){
	public void run(){
    	스레드가 실행할 코드;
    }
});

Thread thread = new Thread(() -> {
	스레드가 실행할 코드;
});
```

작업 스레드는 생성되는 즉시 실행되는 것이 아니라, start() 메소드를 호출해야만 비로소 실행된다.

```
thread.start();
```

start() 메소드가 호출되면, 작업 스레드는 매개값으로 받은 Runnable의 run() 메소드를 실행하면서 자신의 작업을 처리한다.

#### 2\. Thread 하위 클래스로부터 생성

Thread의 하위 클래스로 작업 스레드를 정의하면서 작업 내용을 포함시킬 수도 있다.

```
public class WorkerThread extends Thread {
	@Override
    public void run() {
    	스레드가 실행할 코드;
    }
}

Thread thread = new WorkerThread();

익명객체
Thread thread = new Thread() {
	public void run() {
    	스레드가 실행할 코드;
    }
}

thread.start();
```

#### 3\. 스레드의 이름

디버깅할 때 어떤 스레드가 어떤 작업을 하는지 조사할 목적으로 가끔 사용된다. 메인 스레드는 "main"이라는 이름을 가지고 있고, 우리가 직접 생성한 스레드는 자동적으로 "Thread-n"이라는 이름으로 설정된다. setName()을 통해 다른 이름으로 설정할 수 있다.

```
thread.setName("스레드 이름");
thread.getName();	//스레드 이름 알고 싶을 때
```

만약 스레드 객체의 참조를 가지고 있지 않다면, Thread의 정적 메소드인 currentThread()로 코드를 실행하는 현재 스레드의 참조를 얻을 수 있다.

```
Thread thread = Thread.currentThread();
```

## 3\. 스레드 우선순위

동시성은 멀티 작업을 위해 하나의 코어에서 멀티 스레드가 번갈아가며 실행하는 성질을 말한다.

병렬성은 멀티 작업을 위해 멀티 코어에서 개별 스레드를 동시에 실행하는 성질을 말한다.

스레드의 개수가 코어의 수보다 많을 경우, 스레드를 어떤 순서에 의해 동시성으로 실행할 것인가를 결정해야 하는데, 이것을 스레드 스케줄링이라고 한다. 스레드 스케줄링에 의해 스레드들은 아주 짧은 시간에 번갈아가면서 그들의 run() 메소드를 조금씩 실행한다.

자바의 스레드 스케줄링은 우선순위(Priority) 방식과 순환 할당(Round Robin) 방식을 사용한다.

스레드의 우선순위 방식은 스레드 객체에 우선순위 번호를 부여할 수 있기 때문에 개발자가 코드로 제어할 수 있다. 순환 할당 방식은 자바 가상 기계에 의해서 정해지기 때문에 코드로 제어할 수 없다.

```
thread.setPriority(우선순위);	// 1~10 까지, 1이 가장 우선순위 낮음, 부여하지 않으면 5
```

## 4\. 동기화 메소드와 동기화 블록

#### 1\. 공유 객체를 사용할 때의 주의할 점

멀티 스레드 프로그램에서는 스레드들이 객체를 공유해서 작업해야 하는 경우가 있다. 이 경우, 스레드 A를 사용하던 객체가 스레드 B에 의해 상태가 변경될 수 있기 때문에 스레드 A가 의도했던 것과는 다른 결과를 산출할 수도 있다.

#### 2\. 동기화 메소드 및 동기화 블록

스레드가 사용 중인 객체를 다른 스레드가 변경할 수 없도록 하려면 스레드 작업이 끝날 때까지 객체에 잠금을 걸어서 다른 스레드가 사용할 수 없도록 해야 한다. 멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역을 임계 영역(critical section)이라고 한다.

자바는 임계 영역을 지정하기 위해 동기화(synchronized) 메소드와 동기화 블록을 제공한다. 스레드가 객체 내부의 동기화 메소드 또는 블록에 들어가면 즉시 객체에 잠금을 걸어 다른 스레드가 임계 영역 코드를 실행하지 못하도록 한다. 메소드 선언에 synchronized 키워드를 붙이면 된다. synchronized 키워드는 인스턴스와 정적 메소드 어디든 붙일 수 있다.

```
public synchronized void method() {
	임계 영역;	//단 하나의 스레드만 실행
}
```

메소드 전체 내용이 아니라, 일부 내용만 임계 영역으로 만들고 싶다면 동기화 블록을 만들면 된다.

```
public void method() {
	//여러 스레드가 실행 가능 영역
    ...
    synchronized(공유객체) {
    	임계 영역	//단 하나의 스레드만 실행
    }
    //여러 스레드가 실행 가능 영역
    ...
}
```

## 5\. 스레드의 상태

스레드의 start() 메소드를 호출하면 곧바로 실행되지 않고, 대기 상태가 된다. 실행 대기 상태란 아직 스케줄링이 되지 않아서 실행을 기다리고 있는 상태를 말한다. 실행 대기 상태에 있는 스레드 중에서 스레드 스케줄링으로 선택된 스레드가 비로서 CPU를 점유하고 run() 메소드를 실행한다. 이때를 실행(Running) 상태라고 한다.

일시 정지 : 1.waiting 2.timed\_wating 3.blocked

이러한 스레드의 상태를 코드에서 확인할 수 있도록 자바5부터 Thread 클래스에 getState() 메소드가 추가되었다. 각 상태에 따라서 Thread.State 열거 상수를 리턴한다.

## 6\. 스레드의 상태 제어

#### 1\. 주어진 시간동안 일시 정지 sleep()

실행중인 스레드를 일정 시간 멈추게 하고 싶다면 Thread 클래스의 정적 메소드인 sleep() 을 사용하면 된다.

```
try {
	Thread.sleep(1000);
} catch (InterruptedException e) {
	// interrupt() 메소드가 호출되면 실행
}
```

일시정지 상태에서 주어진 시간이 되기 전에 interrupt() 메소드가 호출되면 InterruptedException이 발생하기 때문에 예외 처리가 필요하다.

#### 2\. 다른 스레드에게 실행 양보 yield()

스레드가 처리하는 작업은 반복적인 실행을 위해 for문이나 while문을 포함하는 경우가 많다. 가끔은 이 반복문들이 무의미한 반복을 하는 경우가 있다. 이것보다는 다른 스레드에게 실행을 양보하고 자신은 실행 대기 상태로 가는 것이 전체 프로그램 성능에 도움이 된다. yield() 메소드는 호출한 스레드는 실행 대기 상태로 돌아가고 동일한 우선순위 또는 높은 우선순위를 갖는 다른 스레드가 실행 기회를 가질 수 있도록 해준다.

```
public void run() {
	while(true) {
    	if(work) {
        	System.out.println("ThreadA 작업 내용");
        } else {
        	Thread.yield();
        }
    }
}
```

#### 3\. 다른 스레드의 종료를 기다림 join()

스레드는 독립적으로 실행하는 것이 기본이지만 다른 스레드의 작업을 이어받아 해야 하는 경우 등 다른 스레드가 종료될 때까지 기다렸다가 실행해야 하는 경우가 발생할 수도 있다.

```
public class SumThread extends Thread {

    private long sum;

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }

    @Override
    public void run() {
        for(int i=1; i<=100; i++){
            sum+= i;
        }
    }
}

public class JoinExample {
    public static void main(String[] args) {
        SumThread sumThread = new SumThread();
        sumThread.start();

        try {
            sumThread.join();   //sumThread가 종료할 때까지 메인 스레드를 일시 정지시킴
        } catch (InterruptedException e) {

        }

        System.out.println("1~100 합 : " + sumThread.getSum());
    }
}
```

#### 4\. 스레드 간 협엽 wait(), notify(), notifyAll()

경우에 따라서는 두 개의 스레드를 교대로 번갈아가며 실행해야 할 경우가 있다. 정확한 교대 작업이 필요한 경우, 자신의 작업이 끝나면 상대방 스레드를 일시 정지 상태에서 풀어주고, 자신은 일시정지 상태로 만드는 것이다. 이 방법의 핵심의 공유 객체에 있다. 공유 객체는 두 스레드가 작업할 내용을 각각 동기화 메소드로 구분해 놓는다. 한 스레드가 작업을 완료하면 notify() 메소드를 호출해서 일시 정지 상태에 있는 다른 스레드를 실행 대기 상태로 만들고, 자신은 두 번 작업을 하지 않도록 wait() 메소드를 호출하여 일시 정지 상태로 만든다.

주의할 점은 이 메소드들은 동기화 메소드 또는 동기화 블록 내에서만 사용할 수 있다.

#### 5\. 스레드의 안전한 종료 stop 플래그, interrupt()

스레드는 자신의 run() 메소드가 모두 실행되면 자동적으로 종료된다. 경우에 따라서는 실행 중인 스레드를 즉시 종료할 필요가 있다. stop() 메소드는 사용 중이던 자원들이 불안전한 상태로 남겨지기 때문에 deprecated 되었다.

**stop 플래그를 이용하는 방법**

stop 플래그를 이용해서 run() 메소드의 종료를 유도한다.

```
public class XXXThread extends Thread {
	private boolean stop;	//stop 플래그 필드
    
    public void run(){
    	while(!stop){
        	스레드가 반복 실행하는 코드;
        }
        //스레드가 사용한 자원 정리
    }
}
```

**interrupt() 메소드를 이용하는 방법**

interrupt() 메소드는 스레드가 일시 정지 상태에 있을 때 InterruptedException 예외를 발생시키는 역할을 한다. 이것을 이용하면 run() 메소드를 정상 종료시킬 수 있다.

## 7\. 데몬 스레드

데몬(daemon) 스레드는 주 스레드의 작업을 돕는 보조적인 역할을 수행하는 스레드이다. 주 스레드가 종료되면 데몬 스레드는 강제적으로 자동 종료되는데, 그 이유는 주 스레드의 보조 역할을 수행하므로 주 스레드가 종료되면 데몬 스레드의 존재 의미가 없어지기 때문이다.

예로 워드프로세서의 자동 저장, 미디어 플레이어의 동영상 및 음악 재생, 가비지 컬렉터 등이 있는데, 이 기능들은 주 스레드가 종료되면 같이 종료된다.

스레드를 데몬 스레드로 만들기 위해서는 주 스레드가 데몬이 될 스레드의 setDemon(true)를 호출해주면 된다.

```
public static void main(String[] args) {
	AutoSaveThread thread = new AutoSaveThread();
    thread.setDaemon(true);
    thread.start();
    ...
}
```

주의할 점은 start() 메소드가 호출되고 나서 setDaemon(true)를 호출하면 IllegalThreadStateException이 발생하기 때문에 start() 메소드 호출 전에 setDaemon(true)를 호출해야 한다.

현재 실행중인 스레드가 데몬인지 아닌지 구별하는 방법은 isDaemon() 메소드의 리턴값을 조사하면 된다. 데몬 스레드일 경우 true를 리턴한다.

## 8\. 스레드 그룹

스레드 그룹은 관련된 스레드를 묶어서 관리할 목적으로 이용된다. JVM이 실행되면 system 스레드 그룹을 만들고, JVM운영에 필요한 스레드들을 생성해서 system 스레드 그룹에 포함시킨다. 그리고 system의 하위 스레드 그룹으로 main을 만들고 메인 스레드를 main 스레드 그룹에 포함시킨다. 스레드는 반드시 하나의 스레드 그룹에 포함되는데, 명시적으로 스레드 그룹에 포함시키지 않으면 기본적으로 자신을 생성한 스레드와 같은 스레드 그룹에 속하게 된다. 기본적으로 main 스레드 그룹에 속하게 된다.

#### 1\. 스레드 그룹 이름 얻기

현재 스레드가 속한 스레드 그룹의 이름을 얻기

```
ThreadGroup group = Thread.currentThread().getThreadGroup();
String groupName = group.getName();
```

Thead 의 정적 메소드인 getAllStackTreaces()를 이용하면 프로세스 내에서 실행하는 모든 스레드에 대한 정보를 얻을 수 있다.

```
Map(Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
```

getAllStackTraces() 메소드는 Map 타입의 객체를 리턴하는데, 키는 스레드 객체이고 값은 스레드의 상태 기록들을 갖고 있는 StackTraceElement\[\] 배열이다.

#### 2\. 스레드 그룹 생성

스레드 그룹을 만들고 싶다면 ThreadGroup 객체를 만들면 된다. ThreadGroup 이름만 주거나, 보무 ThreadGroup과 이름을 매개값으로 줄 수 있다.

```
ThreadGroup tg = new ThreadGroup(String name);
ThreadGroup tg = new ThreadGroup(ThreadGroup parent, String name);
```

Thread 객체를 생성할 때 생성자 매개값으로 스레드 그룹을 지정하면 된다.

```
Thread t = new Thread(THreadGroup group, Runnable target);
Thread t = new Thread(THreadGroup group, Runnable target, String name);
Thread t = new Thread(THreadGroup group, Runnable target, String name, long stackSize);
Thread t = new Thread(THreadGroup group, String name);
```

Runnable 타입의 target은 Runnable 구현 객체를 말하며, String 타입의 name은 스레드의 이름이다. 그리고 long타입의 stackSize는 JVM이 이 스레드에 할당할 stack 크기이다.

#### 3\. 스레드 그룹의 일괄 interrupt()

스레드를 스레드 그룹에 포함시키면 어떤 이점이 있을까? 스레드 그룹에서 제공하는 interrupt() 메소드를 이용하면 그룹 내에 포함된 모든 스레드들을 일괄 interrupt할 수 있다.

interrupt() 이외에도 ThreadGroup 이 가지고 있는 주요 메소드들이 있다.

## 9\. 스레드풀

병렬 작업 처리가 많아지면 스레드 개수가 증가되고 그에 따른 스레드 생성과 스케줄링으로 인해 CPU가 바빠져 메모리 사용량이 늘어난다. 따라서 애플리케이션의 성능이 저하된다. 갑작스런 병렬 작업의 폭증으로 인한 스레드의 폭증을 막으려면 스레드풀(ThreadPool)을 사용해야 한다. 스레드풀은 작업 처리에 사용되는 스레드를 제한된 개수만큼 정해 놓고 작업 큐(Queue)에 들어오는 작업들을 하나씩 스레드가 맡아 처리한다. 작업 처리가 끝난 스레드는 다시 작업 큐에서 새로운 작업을 가져와 처리한다. 그렇기 때문에 작업 처리 요청이 폭증되어도 스레드의 전체 개수가 늘어나지 않으므로 애플리케이션의 성능이 급격히 저하되지 않는다.

java.util.concourrent 패키지에서 ExecutorService인터페이스와 Executors 클래스를 제공하여 스레드풀을 생성하고 사용할 수 있도록 제공하고 있다. Executors의 다양한 정적 메소드를 이용해서 ExecutorService 구현 객체를 만들 수 있는데, 이것이 바로 스레드풀이다.

#### 1\. 스레드풀 생성 및 종료

**스레드풀 생성**

| 메소드명(매개 변수) | 초기 스레드 수 | 코어 스레드 수 | 최대 스레드 수 |
| --- | --- | --- | --- |
| newCachedThreadPool() | 0 | 0 | Integer.MAX\_VALUE |
| newFixedThreadPool(int nTHreads) | 0 | nThreads | nThreads |

```
ExecutorService executorService = Executors.newCachedThreadPool();
ExecutorService executorService = Executors.newFixedThreadPool(
	Runtime.getRuntime().availableProcessors()
);
```

직접 코어 스레드 개수와 최대 스레드 개수를 설정할 수도 있다.

```
ExecutorService threadPool = new ThreadPoolExecutor(
	3,		//코어 스레드 수
    100,	//최대 스레드 수
    120L,	//놀고 있는 시간
    TimeUnit.SECONDS,	//놀고 있는 시간 단위
    new SynchronousQueue<Runnable>()	//작업 큐
);
```

**스레드풀 종료**

스레드풀의 스레드는 기본적으로 데몬 스레드가 아니기 때문에 main 스레드가 종료되더라도 작업을 처리하기 위해 계속 실행 상태로 남아있다. 그래서 main() 메소드가 실행이 끝나도 애플리케이션 프로세스는 종료되지 않는다. 애플리케이션을 종료하려면 스레드풀을 종료시켜 스레드들이 종료 상태가 되도록 처리해주어야 한다.

void shutdown() : 현재 처리중인 작업뿐만 아니라 작업 큐에 대기하고 있는 모든 작업을 처리한 뒤에 스레드풀을 종료시킨다.

List<Runnable> shutdownNow() : 현재 작업 처리중인 스레드를 interrupt해서 작업 중지를 시도하고 스레드풀을 종료시킨다. 리턴값은 작업 큐에 미처리된 작업의 목록이다.

boolean awaitTermination(long timeout, TimeUnit unit) : shutdown() 메소드 호출 이후, 모든 작업 처리를 timeout 시간 내에 완료 되면 true를 리턴하고, 완료하지 못하면 작업 처리 중인 스레드를 interrupt하고 false를 리턴한다.

```
//남아있는 작업 마무리하고 스레드풀을 종료
executorService.shutdown();
//낭마있는 작업과는 상관없이 강제로 종료
executorService.shutdownNow();
```

#### 2\. 작업 생성과 처리 요청

**작업 생성**

하나의 작업은 Runnable 또는 Callable 구현 클래스로 표현한다. Runnable과 Callable의 차이점은 작업 처리 완료 후 리턴값이 있느냐 없느냐이다.

```
Runnable task = new Runnable() {
	@Override
    public void run() {
    	...
    }
}

Callable<T> task = new Callable<T>() {
	@Override
    public T call( ) throws Exception {
    	...
        return T;
    }
}
```

스레드풀의 스레드는 작업 큐에서 Runnable 또는 Callable객체를 가져와 run()과 call() 메소드를 실행한다.

**작업 처리 요청**

작업 처리 요청이란 ExecutorService의 작업 큐에 Runnable 또는 Callable 객체를 넣는 행위를 말한다. ExecutorService는 작업 처리 요청을 위해 다음 두 가지 종류의 메소드를 제공한다.

| 리턴 타입 | 메소드명(매개 변수) | 설명 |
| --- | --- | --- |
| void | execute(Runnable command) | \-Runnable을 작업 큐에 저장   \-작업 처리 결과를 받지 못함 |
| Future<?>   Future<V>   Future<V> | submit(Runnable task)   submit(Runnable task, V result)   submit(Callable<V> task) | \-Runnable 또는 Callable을 작업 큐에 저장   \-리턴된 Future를 통해 작업 처리 결과를 얻을 수 있음 |

execute() 작업 처리 도중 예외가 발생하면 스레드가 종료되고 해당 스레드는 스레드풀에서 제거된다. 따라서 스레드풀은 다른 작업 처리를 위해 새로운 스레드를 생성한다. 반면에 submit()은 작업 처리 도중 예외가 발생하더라도 스레드는 종료되지 않고 다음 작업을 위해 재사용된다. 그렇기 때문에 가급적이면 스레드의 생성 오버헤더를 줄이기 위해서 submit()을 사용하는 것이 좋다.

#### 3\. 블로킹 방식의 작업 완료 통보

submit() 메소드는 Future 객체를 리턴한다. Future 객체는 작업 결과가 아니라 작업이 완료될 때까지 기다렸다가(지연했다가=블로킹되었다가) 최종 결과를 얻는데 사용된다. Future를 지연완료 객체라고 한다. Future의 get() 메소드를 호출하면 스레드가 작업을 완료할 때까지 블로킹되었다가 작업을 완료하면 처리 결과를 리턴한다. 이것이 블로킹을 사용하는 작업 완료 통보 방식이다.

V get() : 작업이 완료될 때까지 블로킹되었다가 처리 결과 V를 리턴

V get(long timeout, TimeUnit unit) : timeout 시간 전에 작업이 완료되면 결과 V를 리턴하지만, 작업이 완료되지 않으면 TimeoutException을 발생시킴

Future를 이용한 블로킹 방식의 작업 완료 통보에서 주의할 점은 작업을 처리하는 스레드가 작업을 완료하기 전까지는 get() 메소드가 블로킹되므로 다른 코드를 실행할 수 없다. 만약 UI를 변경하고 이벤트를 처리하는 스레드가 get() 메소드를 호출하면 작업을 완료하기 전까지 UI를 변경할 수도 없고 이벤트를 처리할 수도 없게 된다. 그렇기 때문에 get()메소드를 호출하는 스레드는 새로운 스레드이거나 스레드풀의 또 다른 스레드가 되어야 한다.

```
//새로운 스레드를 생성해서 호출
new Thread(new Runnable() {
	@Override
    public void run() {
    	try {
        	future.get();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
}).start();

//스레드풀의 스레드가 호출
executorService.submit(new Runnable() {
	@Override
    public void run() {
    	try {
        	future.get();
        } catch(Exception e) {
        	e.printStackTrace();
        }
    }
});
```

Future 객체는 작업 결과를 얻기 위한 get() 메소드 이외에도 다음과 같은 메소드를 제공한다.

boolean cancel(boolean mayInterruptIfRunning) : 작업 처리가 진행 중일 경우 취소시킴

boolean isCancelled() : 작업이 취소되었는지 여부

boolean isDone() : 작업 처리가 완료되었는지 여부

**리턴값이 없는 작업 완료 통보**

결과값이 없는 작업 처리 요청은 submit(Runnable task) 메소드를 이용하면 된다. 결과값이 없음에도 불구하고 Future 객체를 리턴하는데, 이것은 스레드가 작업 처리를 정상적으로 완료했는지, 아니면 작업 처리 도중에 예외가 발생했는지 확인하기 위해서이다.

```
Future future = executorService.submit(task);
```

작업 처리가 정상적으로 완료되었다면 Future의 get() 메소드는 null을 리턴하지만 스레드가 작업처리 도중 interrupt되면 InterruptedException을 발생시키고, 작업 처리 도중 예외가 발생하면 ExecutionException을 발생시킨다.

```
try {
	future.get();
} catch (InterruptedException e) {
} catch (ExecutionException e) {
}
```

**리턴 값이 있는 작업 완료 통보**

```
Future<T> future = executorService.submit(task);
```

스레드풀의 스레드가 Callable 객체의 call() 메소드를 모두 실행하고 T 타입의 값을 리턴하면, Future<T> 의 get() 메소드는 블로킹이 해제되고 T타이의 값을 리턴하게 된다.

```
try {
	T result = future.get();
} catch (InterruptedException e) {
} catch (ExecutionException e) {
}
```

**작업 처리 결과를 외부에 저장**

상황에 따라서 스레드가 작업한 결과를 외부 객체에 저장해야 할 경우도 있다. 예를 들어 Result 공유 객체로 두 개 이상의 스레드 작업을 취합할 목적으로 이용된다.

ExecutorService의 submit(Runnable task, V result) 메소드를 사용할 수 있다.

```
Result result = ...;
Runnable task = new Task(Result);
Future<Result> future = executorService.submit(task, result);
result = future.get();
```

**작업 완료 순으로 통보**

작업 요청 순서대로 작업 처리가 완료되는 것은 아니다. 작업의 양과 스레드 스케줄링에 따라 먼저 요청한 작업이 나중에  완료되는 경우도 발생한다. 처리 결과도 순차적으로 이용할 필요가 없다면 작업 처리가 완료된 것부터 결과를 얻어 이용하면 된다. 스레드풀에서 작업 처리가 완료된 것만 통보받는 방법이 있는데, CompletionService를 이용하는 것이다. 처리 완료된 작업을 가져오는 poll()과 take() 메소드를 제공한다.

#### 4\. 콜백 방식의 작업 완료 통보

콜백이란 애플리케이션이 스레드에게 작업 처리를 요청한 후, 스레드가 작업을 완료하면 특정 메소드를 자동 실행하는 기법을 말한다. 이때 자동 실행되는 메소드를 콜백 메소드라고 한다.

ExecutorService는 콜백을 위한 별도의 기능을 제공하지 않는다. 하지만 Runnable 구현 클래스를 작성할 때 콜백 기능을 구현할 수 있다. 콜백 메소드를 가진 클래스가 있어야 하는데, 직접 정의해도 좋고 java.nio.channels.CompletionHandler를 이용해도 좋다. 이 인터페이스는 NIO 패키지에 포함되어 있는데 비동기 통신에서 콜백 객체를 만들 때 사용된다.

```
CompletionHandler<V, A> callback = new CompletionHandler<V, A>() {
	@Override
    public void completed(V result, A attachment){
    	//작업을 정상 처리 완료했을 때 호출되는 콜백 메소드
    }
    
    @Override
    public void failed(Throwable exc, A attachment){
    	//작업 처리 도중 예외가 발생했을 때 호출되는 콜백 메소드
    }
};
```

```
public class CallbackExample {
    private ExecutorService executorService;

    public CallbackExample() {
        executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    private CompletionHandler<Integer, Void> callback =
            new CompletionHandler<Integer, Void>() {
                @Override
                public void completed(Integer result, Void attachment) {
                    System.out.println("completed() 실행: " + result);
                }

                @Override
                public void failed(Throwable exc, Void attachment) {
                    System.out.println("failed() 실행: " + exc.toString());
                }
            };

    public void doWork(final String x, final String y) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    int intX = Integer.parseInt(x);
                    int intY = Integer.parseInt(y);
                    int result = intX + intY;
                    callback.completed(result, null);
                } catch (NumberFormatException e){
                    callback.failed(e, null);
                }
            }
        };
        executorService.submit(task);
    }

    public void finish() {
        executorService.shutdown();
    }

    public static void main(String[] args) {
        CallbackExample example = new CallbackExample();
        example.doWork("3", "3");
        example.doWork("3", "삼");
        example.finish();
    }
}
```