package chapter11;

public class MemberExample {
    public static void main(String[] args) {
        Member original = new Member("blue", "홍길동", "1234", 25, true, new Key(2));

        Member cloned = original.getMember();
        cloned.password = "67890";

        System.out.println(original.key);
        System.out.println(cloned.key);
    }
}
