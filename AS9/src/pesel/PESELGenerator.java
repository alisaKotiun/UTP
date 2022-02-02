package pesel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PESELGenerator {

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");

    private static String year;
    private static String month;
    private static String day;
    private static int zzz;
    private static int x;

    public static String generatePESEL(Date date){
        String part = generatePart(date);
        return part + extractCheckSum(part);
    }

    private static String generatePart(Date date){

        year = extractYear(date);
        month = extractMonth(date);
        day = extractDay(date);
        zzz = extractZZZ();
        x = extractX();

        return year + month + day + zzz + x;
    }

    private static String extractYear(Date date){
        String st = format.format(date);
        st = st.substring(2, 4);
        return st;
    }

    private static String extractMonth(Date date){
        String y = format.format(date).substring(0, 4);
        int ye = Integer.parseInt(y);
        String m = format.format(date).substring(5, 7);
        int mo = Integer.parseInt(m);

        if(ye >= 1800 && ye <= 1899){ mo+=80;}
        if(ye >= 2000 && ye <= 2099){ mo+=20;}
        if(ye >= 2100 && ye <= 2199){ mo+=40;}
        if(ye >= 2200 && ye <= 2299){ mo+=60;}

        String result = "";
        return result + (mo<10 ? "0" + mo : mo);
    }

    private static String extractDay(Date date){
        String st = format.format(date);
        st = st.substring(8);
        return st;
    }

    private static int extractZZZ(){
        Random random = new Random();
        return random.nextInt(900) + 100;
    }

    private static int extractX(){
        Random random = new Random();
        return random.nextInt(2);
    }

    private static int extractCheckSum(String part){
        String [] num = part.split("");
        int[] multiplier = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int result = 0;
        for(int i = 0; i < num.length; i++){
            result += Integer.parseInt(num[i]) * multiplier[i];
        }
        result = result % 10;

        return (result != 0 ? 10-result : 0);
    }


}
