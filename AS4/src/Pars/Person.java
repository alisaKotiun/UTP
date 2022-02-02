package Pars;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person implements Comparable<Person>{

    private String firstName;
    private String surname;
    private Date birthDate;

    public Person(String f, String s, Date b) {
        firstName = f;
        surname = s;
        birthDate = b;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date1 = simpleDateFormat.format(birthDate);

        return firstName + " " + surname + " (" + date1 +")" ;
    }

    @Override
    public int compareTo(Person person) {
        int result = surname
                .compareTo(person.getSurname());
        if(result != 0) return result;
        result = firstName
                .compareTo(person.getFirstName());
        if (result != 0) return result;
        return birthDate.compareTo(person.getBirthDate());
    }
}
