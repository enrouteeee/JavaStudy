package chapter11;

public class Member implements Cloneable {
    public String id;
    public String name;
    public String password;
    public int age;
    public boolean adult;
    public Key key;

    public Member(String id, String name, String password, int age, boolean adult, Key key) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.age = age;
        this.adult = adult;
        this.key = key;
    }

    public Member getMember() {
        Member cloned = null;
        try {
            cloned = (Member) clone();
        } catch (CloneNotSupportedException e) { }
        return cloned;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        //얇은 복사를 해서 기본타입 필드값을 복제한다.
        Member cloned = (Member) super.clone();
        cloned.key = new Key(this.key.getNumber());
        return cloned;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                ", adult=" + adult +
                '}';
    }
}
