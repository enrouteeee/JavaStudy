package chapter13;

/*
Course<?>
수강생은 모든 타입이 될 수 있다.

Course<? extends Student>
수강생은 Student와 HighStudent만 될 수 있다.

Course<? super Worker>
수강생은 Worker와 Person만 될 수 있다.
 */

public class Course<T> {
    private String name;
    private T[] students;

    public Course(String name, int capacity) {
        this.name = name;
        students = (T[]) (new Object[capacity]);
    }

    public String getName() { return name; }
    public T[] getStudents() { return students; }

    public void add(T t) {
        for(int i=0; i<students.length; i++) {
            if(students[i] == null) {
                students[i] = t;
                break;
            }
        }
    }
}
