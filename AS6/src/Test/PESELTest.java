package Test;

import Generator.DateGenerator;
import Generator.PESELGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PESELTest {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    Date date;

    @Before
    public void before(){
        date = DateGenerator.generateDate();
        System.out.println(format.format(date));
    }


    @Test
    public void generatePESEL() throws Exception {
        String PESEL = PESELGenerator.generatePESEL(date);
        System.out.println(PESEL);
        Assert.assertTrue(checkPesel(PESEL));
    }

    private boolean checkPesel(String pesel) throws Exception {
        if (pesel == null || pesel.length() != 11)
                return false;

        String[] arr = pesel.split("");
        int sum = 0;

            for (int i = 0; i < arr.length - 1; i++) {
                sum += Integer.parseInt(arr[i]) * getMultiplier(i + 1);
            }

            int modulo = sum % 10;
            int lastD = Integer.parseInt(pesel.substring((pesel.length() - 1)));

            return (modulo == 0) && lastD == 0 ||
                    lastD == 10 - modulo;
    }

    private int getMultiplier(int index) throws Exception {

        switch (index % 4) {
            case 1: return 1;
            case 2: return 3;
            case 3: return 7;
            case 0: return 9;
        }

        throw new Exception("Something went wrong with the index calculation");
    }
}
