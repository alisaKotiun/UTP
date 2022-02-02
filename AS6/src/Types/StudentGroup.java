package Types;

import Person.Student;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentGroup implements  Comparable<StudentGroup>{
    private String name;
    private List<Student> list;

    public StudentGroup(String n){
        name = n;
        list = new ArrayList<>();
    }

    public void addStudent(Student student){
        list.add(student);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup that = (StudentGroup) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public int compareTo(StudentGroup studentGroup) {
        Collator collator = Collator.getInstance();
        return collator.compare(name, studentGroup.name);
    }

    @Override
    public String toString() {

        return "\n" + name + "\n" + list.toString();
    }
}
