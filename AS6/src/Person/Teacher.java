package Person;

import java.util.Date;
import java.util.Objects;

public class Teacher extends Person{
    private Date hireDate;
    private Degree degree;
    public Teacher(String fn, String ln, Date d, Nationality n, Date h, Degree de) {
        super(fn, ln, d, n);
        hireDate = h;
        degree = de;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(hireDate, teacher.hireDate) &&
                degree == teacher.degree;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), hireDate, degree);
    }

    @Override
    public String toString() {
        return "\n Teacher [" + getFirstName() + " " + getLastName() +
                "; " + getNationality().getLocale().getCountry() + "; "+ degree;
    }
}
