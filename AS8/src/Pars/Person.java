package Pars;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
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


    // assignment 8
    public void serialize(DataOutputStream output) throws Assignment08Exception {
        try {
            output.writeUTF(firstName);
            output.writeUTF(surname);
            output.writeLong(birthDate.getTime());
        } catch (IOException e) {
            throw new Assignment08Exception("error text", e);
        }
    }

    public static Person deserialize(DataInputStream input) throws Assignment08Exception {
        try {
            String fName = input.readUTF();
            String lName = input.readUTF();
            Date dOB = new Date(input.readLong());
            return new Person(fName, lName, dOB);
        } catch (IOException e) {
            throw new Assignment08Exception("error text", e);
        }
    }
}
