package chapter12;

public class ThreadStateExample {
    public static void main(String[] args) {
        StatePrintThread statePrintThread = new StatePrintThread(new TargetThread());
        statePrintThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // interrupt() 메소드가 호출되면 실행
        }
    }
}