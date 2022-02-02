import Pars.Person;
import Pars.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PersonTest {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
	private File DBFile;
	private DataOutputStream dataOutput;
	private DataInputStream dataInput;
	private Person person;


	@Before
	public void before() throws Exception{
		DBFile = new File("C:\\Users\\User\\OneDrive\\Робочий стіл\\UTP\\Tutorials\\AS8\\src\\data.txt");
		dataOutput = new DataOutputStream(new FileOutputStream(DBFile));
		dataInput = new DataInputStream(new FileInputStream(DBFile));
		person = new Person("Alisa", "Kotiun", format.parse("2002-04-13"));
	}

	@Test
	public void serializeAndDeserialize() throws Exception {
		person.serialize(dataOutput);
		Person newPerson = Person.deserialize(dataInput);

		Assert.assertEquals("Alisa", newPerson.getFirstName());
		Assert.assertEquals("Kotiun", newPerson.getSurname());
		Assert.assertEquals( format.parse("2002-04-13"), newPerson.getBirthDate());
	}
}