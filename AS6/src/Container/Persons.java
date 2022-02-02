package Container;

import Person.Nationality;
import Person.Person;

import java.text.Collator;
import java.util.*;

public class Persons {
    private Set<Person> personSet;

    public Persons(){
        personSet = new HashSet<Person>();
    }

    public void add(Person person){
        if(person != null){
            personSet.add(person);
        }
    }

    public List<Person> filterAndSort(Nationality nationality){
        List<Person> result = new ArrayList<Person>();
        Collator collator = Collator.getInstance(nationality.getLocale());
        for(Person person : personSet){
            if(person.getNationality() == nationality)
                result.add(person);
        }
        result.sort(new Comparator<Person>() {
            @Override
            public int compare(Person person1, Person person2) {
                return collator.compare(person1.getFirstName(), person2.getFirstName());
            }
        });
        return result;
    }
}
