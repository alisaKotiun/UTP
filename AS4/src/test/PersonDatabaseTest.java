import Pars.InputParser;
import Pars.Person;
import Pars.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PersonDatabaseTest {
    private File file;
    private PersonDatabase data;
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

    @Before
    public void before() throws Exception{
        file = new File("C:\\Users\\User\\OneDrive\\Робочий стіл\\UTP\\Tutorials\\AS4\\src\\input.txt");
        Assert.assertTrue(file.exists());
        data = new PersonDatabase(file);
    }

    @Test
    public void sortedByFirstName(){
        List<Person> list = data.sortedByFirstName();
        Assert.assertEquals(9, list.size());
        System.out.println(list);
    }

    @Test
    public void sortedBySurnameFirstNameAndBirthDate(){
        List<Person> list = data.sortedBySurnameFirstNameAndBirthDate();
        Assert.assertEquals(9, list.size());
        System.out.println(list);
    }

    @Test
    public void sortedByBirthDate(){
        List<Person> list = data.sortedByBirthDate();
        Assert.assertEquals(9, list.size());
        System.out.println(list);
    }

    @Test
    public void bornOnDay() throws ParseException {
        Date date = format.parse( "2002-04-13");
        List<Person> list = data.bornOnDay(date);
        Assert.assertEquals(2, list.size());

        System.out.println(list);

        date = format.parse( "2010-04-13");
        list = data.bornOnDay(date);
        Assert.assertNull(list);
    }
}
