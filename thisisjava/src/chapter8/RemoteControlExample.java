package chapter8;

public class RemoteControlExample {
    public static void main(String[] args) {
        RemoteControl rc = new Televison();

        RemoteControl rc_ = new RemoteControl() {
            @Override
            public void turnOn() {

            }

            @Override
            public void turnOff() {

            }

            @Override
            public void setVolume(int volume) {

            }
        };

    }
}
