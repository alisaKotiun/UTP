package Person;

import java.util.Date;
import java.util.Objects;

public class Student extends Person{
    private String no;
    public Student(String fn, String ln, Date d, Nationality n, String no) {
        super(fn, ln, d, n);
        this.no = no;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(no, student.no);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), no);
    }

    @Override
    public String toString() {
        return "\n Student with no " + no +
                " [" + getFirstName() + " " + getLastName() + ", "
                + getNationality().getLocale().getCountry() + "]";
    }
}
