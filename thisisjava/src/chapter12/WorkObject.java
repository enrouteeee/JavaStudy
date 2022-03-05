package chapter12;

public class WorkObject {
    public synchronized void methodA() {
        System.out.println("ThreadA의 methodA() 작업 실행");
        //일시 정지 상태에 있는 ThreadB를 실행 대기 상태로 만듦
        notify();
        try {
            //ThreadA를 일시 정지 상태로 만듦
            wait();
        } catch (InterruptedException e) {
        }
    }

    public synchronized void methodB() {
        System.out.println("ThreadA의 methodB() 작업 실행");
        //일시 정지 상태에 있는 ThreadA를 실행 대기 상태로 만듦
        notify();
        try {
            //ThreadB를 일시 정지 상태로 만듦
            wait();
        } catch (InterruptedException e) {
        }
    }
}
