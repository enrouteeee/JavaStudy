package chapter12;

import javax.xml.transform.Result;

public class Task implements Runnable {
    Result result;
    Task(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        //작업 코드
        //처리 결과를 result 저장
    }
}
