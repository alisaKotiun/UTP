import Pars.Assignment08Exception;
import Pars.PersonDatabase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class PersonDatabaseTest {
    private File file;
    private File DBFile;
    private PersonDatabase data;
    private DataOutputStream dataOutput;
    private DataInputStream dataInput;


    @Before
    public void before() throws Exception{
        file = new File("C:\\Users\\User\\OneDrive\\Робочий стіл\\UTP\\Tutorials\\AS8\\src\\input.txt");
        DBFile = new File("C:\\Users\\User\\OneDrive\\Робочий стіл\\UTP\\Tutorials\\AS8\\src\\data.txt");
        data = new PersonDatabase(file);
        dataOutput = new DataOutputStream(new FileOutputStream(DBFile));
        dataInput = new DataInputStream(new FileInputStream(DBFile));
    }

    @Test
    public void serializeAndDeserialize() throws Exception {
        data.serialize(dataOutput);
        PersonDatabase data1 = PersonDatabase.deserialize(dataInput);

        Assert.assertEquals(5, data1.sortedByFirstName().size());
        System.out.println(data1.sortedByFirstName());
    }
}
