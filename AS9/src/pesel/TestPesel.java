package pesel;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestPesel {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    private static Date date;
    private static String PESEL;
    private static Method method1, method2, method3;

    @BeforeClass
    public static void before(){
        date = DateGenerator.generateDate();
        PESEL = PESELGenerator.generatePESEL(date);


        System.out.println(format.format(date));
        System.out.println(PESEL);



        try {
            method1 = Pesel.class.getDeclaredMethod("validate", String.class);
            method1.setAccessible(true);

            method2 = Pesel.class.getDeclaredMethod("getDate", String.class);
            method2.setAccessible(true);

            method3 = Pesel.class.getDeclaredMethod("getSex", String.class);
            method3.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void validate() throws Exception {
        boolean result = (boolean) method1.invoke(null, PESEL);
        Assert.assertTrue(result);
    }

    @Test
    public void getDate() throws Exception {
        Date result = (Date) method2.invoke(null, PESEL);
        Assert.assertEquals(date, result);
        System.out.println(format.format(result));
    }

    @Test
    public void getSex() throws Exception {
        Sex result = (Sex) method3.invoke(null, PESEL);
        Assert.assertNotNull(result);
        System.out.println(result);
    }
}
