import Main.FileUtility;
import Main.ZipUtility;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

public class Tests{
    private File file, input, data;

    @Before
    public void before(){
        file = new File("C:\\Users\\User\\OneDrive\\Робочий стіл\\UTP\\Tutorials\\AS7\\src\\File");
        input = new File("C:\\Users\\User\\OneDrive\\Робочий стіл\\UTP\\Tutorials\\AS7\\src\\File\\input");
    }


    @Test
    public void searchByName() throws IOException {
        List<File> list = FileUtility.searchByName(file, "data");
        Assert.assertEquals(1, list.size());
        String string = list.get(0).getName();
        Assert.assertEquals("data", string);
    }

    @Test
    public void searchByContent() throws IOException {
        List<File> list = FileUtility.searchByContent(file, "ell");
        Assert.assertEquals(1, list.size());
        String string = Files.readString(Paths.get(input.getPath()));
        Assert.assertEquals("hello", string);
    }

    @Test
    public void zipTest() throws IOException{
        File file = new File("C:\\Users\\User\\OneDrive\\Робочий стіл\\UTP\\Tutorials\\AS7\\src\\Data.zip");
        List<String> list = ZipUtility.searchByName(file, "ta");
        Assert.assertEquals(1, list.size());
    }


}
