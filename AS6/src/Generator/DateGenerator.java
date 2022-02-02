package Generator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateGenerator {
    public static Date generateDate() {
        Date result = new Date();
        try {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Random random = new Random();
        int year = random.nextInt(499)+1800;
        int month = random.nextInt(11)+1;
        String mon = (month < 10 ? "0" + month : "" + month);
        int day = random.nextInt(27)+1;
        String d = (day < 10 ? "0" + day : "" + day);

        String date = "" + year + "-" + mon + "-" + d;
            result = format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
