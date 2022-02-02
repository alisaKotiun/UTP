package Person;

import Generator.PESELGenerator;

import java.text.CollationKey;
import java.text.Collator;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public abstract class Person implements Comparable<Person> {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private Nationality nationality;
    private String pesel;

    public Person(String fn, String ln, Date d, Nationality n){
        firstName = fn;
        lastName = ln;
        dateOfBirth = d;
        nationality = n;
        pesel = PESELGenerator.generatePESEL(d);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public String getPesel() {
        return pesel;
    }

    @Override
    public int compareTo(Person person) {
        if(person == null) return 1;
        int result;
        Collator collator = Collator.getInstance(new Locale("pl"));
        result = collator.compare(firstName, person.getFirstName());
        if(result != 0) {return result;}
        result = collator.compare(lastName, person.getLastName());
        if(result != 0) {return result;}
        return dateOfBirth.compareTo(person.getDateOfBirth());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName) &&
                Objects.equals(dateOfBirth, person.dateOfBirth) &&
                nationality == person.nationality &&
                Objects.equals(pesel, person.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dateOfBirth, nationality, pesel);
    }
}
