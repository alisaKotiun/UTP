package Pars;

import comparator.BirthDateComparator;
import comparator.FirstNameComparator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class PersonDatabase {
    private List<Person> list;

    public PersonDatabase(){
        list = new ArrayList<>();
    }

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

    private void addToList(Person person){
        list.add(person);
    }



    // assignment 8 - factory method based on deserialization
    public static PersonDatabase deserialize(DataInputStream input) throws Assignment08Exception {
        try {
            PersonDatabase pd = new PersonDatabase();
            while(input.available() > 0) {
                Person person = Person.deserialize(input);
                pd.addToList(person);
            }
            input.close();
            return pd;
        } catch (IOException e) {
            throw new Assignment08Exception("error text", e);
        }
    }

    // assignment 8
    public void serialize(DataOutputStream output) throws Assignment08Exception {
        try {
            for (Person person: list) {
                person.serialize(output);
            }
            output.close();
        } catch (IOException e) {
            throw new Assignment08Exception("error text", e);
        }
    }
}
