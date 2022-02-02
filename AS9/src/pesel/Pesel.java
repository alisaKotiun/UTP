package pesel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Pesel {
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

    private static boolean validate(String pesel) throws Exception {
        if (pesel == null || pesel.length() != 11)
            return false;

        String[] arr = pesel.split("");
        int sum = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            sum += Integer.parseInt(arr[i]) * getMultiplier(i + 1);
        }

        int modulo = sum % 10;
        int lastD = Integer.parseInt(pesel.substring((pesel.length() - 1)));

        return ((modulo == 0) && lastD == 0) || lastD == 10 - modulo;
    }


    //--------------------

    private static Date getDate(String pesel) throws ParseException {
        int y1 = extractYear1(pesel);
        String year = "" + y1 + extractYear(pesel);
        String month = extractMonth(pesel, y1);
        String day = extractDay(pesel);
        String string = year + "-" + month + "-" + day;
        Date date = format.parse(string);
        return date;
    }

    //--------------------

    private static Sex getSex(String pesel){
        int sex = Integer.parseInt(pesel.substring(9, 10));
        return sex%2 == 0 ? Sex.F : Sex.M;
    }

    //-----------------------

    private static int getMultiplier(int index) throws Exception {

        switch (index % 4) {
            case 1: return 1;
            case 2: return 3;
            case 3: return 7;
            case 0: return 9;
        }

        throw new Exception("Something went wrong with the index calculation");
    }

    private static String extractYear(String pesel){
        String st = pesel.substring(0, 2);
        return st;
    }

    private static int extractYear1(String pesel){
        String st = pesel.substring(2, 4);
        if(Integer.parseInt(st) > 80) {return 18;}
        if(Integer.parseInt(st) > 60) {return 22;}
        if(Integer.parseInt(st) > 40) {return 21;}
        if(Integer.parseInt(st) > 20) {return 20;}

        return 19;
    }

    private static String extractMonth(String pesel, int cen){
        String st = pesel.substring(2, 4);
        int c = 0;
        switch (cen){
            case 18: c = 80; break;
            case 20: c = 20; break;
            case 21: c = 40; break;
            case 22: c = 60; break;
        }

        int month = Integer.parseInt(st) - c;
        if(month < 10) return "0" + month;
        return "" + month;
    }

    private static String extractDay(String pesel){
        return pesel.substring(4, 6);
    }
}
