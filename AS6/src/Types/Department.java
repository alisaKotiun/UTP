package Types;

import Person.Teacher;

import java.text.Collator;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Department implements Comparable<Department>{
    private String name;
    private List<Teacher> list;

    public Department(String n){
        name = n;
        list = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher){
        list.add(teacher);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(list, that.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, list);
    }

    @Override
    public int compareTo(Department department) {
        Collator collator = Collator.getInstance();
        return collator.compare(department.name, name);
    }

    @Override
    public String toString() {
        return "\n" + name + "\n" + list.toString();
    }

    public List<Teacher> getList() {
        return list;
    }
}
