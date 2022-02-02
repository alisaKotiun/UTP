package DateCal;

import java.util.Calendar;
import java.util.Date;

public class CalDate {
    public static Date getDate(int y, int m, int d) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, y);
        cal.set(Calendar.MONTH, m);
        cal.set(Calendar.DAY_OF_MONTH, d);
        return cal.getTime();
    }
}

//deprecated date