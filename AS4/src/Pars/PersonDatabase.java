package Pars;

import comparator.BirthDateComparator;
import comparator.FirstNameComparator;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class PersonDatabase {
    private List<Person> list;

    public PersonDatabase(File f) throws Exception{
            list = InputParser.parse(f);
    }

    // external rule for ordering (based on Comparator --- FirstNameComparator)
    public List<Person> sortedByFirstName() {
        Collections.sort(list, new FirstNameComparator());
        return list;
    }

    // natural order (Comparable)
    public List<Person> sortedBySurnameFirstNameAndBirthDate() {
        Collections.sort(list, Comparator.naturalOrder());
        return list;
    }

    // external rule for ordering (based on Comparator --- BirthDateComparator)
    public List<Person> sortedByBirthDate() {
        Collections.sort(list, new BirthDateComparator());
        return list;
    }

    public List<Person> bornOnDay(Date date) {
        List<Person> personList = new ArrayList<>();
        if(date == null) return null;
        Map<Date, List<Person>> map = list
                .stream()
                .collect(Collectors.groupingBy(Person::getBirthDate));

         personList = map
                .get(date);

        return personList;
    }
}
