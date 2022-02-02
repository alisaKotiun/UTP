package Types;

import Person.Student;
import Person.Teacher;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subject implements Comparable<Subject>{
    private String name;
    private Department department;
    private Teacher lecturer;
    private List<Student> list;

    public Subject(String n, Department d, Teacher l){
        name = n;
        department = d;
        lecturer = l;
        list = new ArrayList<>();
    }

    public void addToSubject(Student student){
        list.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name) &&
                Objects.equals(department, subject.department) &&
                Objects.equals(lecturer, subject.lecturer) &&
                Objects.equals(list, subject.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, department, lecturer, list);
    }


    @Override
    public int compareTo(Subject subject) {
        Collator collator = Collator.getInstance();
        return collator.compare(name, subject.name);
    }

    @Override
    public String toString() {
        return "\n" + name + "[" + department + "; \n" + lecturer + "]";
    }
}
