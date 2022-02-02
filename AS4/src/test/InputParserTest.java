import Pars.InputParser;
import Pars.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class InputParserTest {
    private File file;

    @Before
    public void before(){
        file = new File("C:\\Users\\User\\OneDrive\\Робочий стіл\\UTP\\Tutorials\\AS4\\src\\input.txt");
        Assert.assertTrue(file.exists());
    }

    @Test
    public void parse() throws Exception{
        List<Person> list = InputParser.parse(file);
        Assert.assertEquals(9, list.size());
        System.out.println(list);
    }


}
