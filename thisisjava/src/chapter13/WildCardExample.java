package chapter13;

import java.util.Arrays;

public class WildCardExample {
    public static void registerCourse( Course<?> course ) {
        System.out.println(course.getName() + "수강생: " + Arrays.toString(course.getStudents()));
    }

    public static void registerCourseStudent( Course<? extends Student> course ) {
        System.out.println(course.getName() + "수강생: " + Arrays.toString(course.getStudents()));
    }

    public static void registerCourseWorker( Course<? super Worker> course ) {
        System.out.println(course.getName() + "수강생: " + Arrays.toString(course.getStudents()));
    }

    public static void main(String[] args) {
        Course<Person> personCourse = new Course<>("일반인과정", 5);
        Course<Worker> workerCourse = new Course<>("일반인과정", 5);
        Course<Student> studentCourse = new Course<>("일반인과정", 5);
        Course<HighStudent> highStudentCourse = new Course<>("일반인과정", 5);

        registerCourse(personCourse);
        registerCourseWorker(workerCourse);
        registerCourseStudent(studentCourse);
    }
}
