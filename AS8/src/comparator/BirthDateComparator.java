package comparator;

import Pars.Person;

import java.util.Comparator;

public class BirthDateComparator implements Comparator<Person> {
    @Override
    public int compare(Person person1, Person person2) {
        return person1.getBirthDate().compareTo(person2.getBirthDate());
    }
}
