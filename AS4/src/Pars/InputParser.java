package Pars;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputParser {
    private static String patternName = "\\p{L}+";
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
    private static String patternYear = "\\d{4}";
    private static String patternMonth = "(0[1-9]|1[012])";
    private static String patternDay = "(0[1-9]|[12][0-9]|3[01])";
    private static String datePattern = patternYear+"-"+patternMonth+"-"+patternDay;

    public static List<Person> parse(File file) throws Exception{
        List<Person> result = new ArrayList<Person>();

        String book = file.getPath();
        try (Stream<String> lines =
                     Files.lines(Paths.get(book)))
        {
            result = lines
                    .map(line -> create(line))
                    .filter(p -> p!=null)
                    .collect(Collectors.toList());
        } catch(IOException e) {
            System.out.println("Something wrong...");
            e.printStackTrace();
            System.exit(1);
        }
        return result;
    }

    public static Person create(String line) {
        Person person;
        String[]arr = line.split("\\s+");
            if(arr.length != 3) return null;
            try {
                String name = checkName(arr[0]);
                String surname = checkName(arr[1]);
                Date date = checkDate(arr[2]);
                person = (name != null && surname != null && date != null
                        ? new Person(name, surname, date)
                        : null);
                return person;
            }catch(ParseException e) {
                System.out.println("Something wrong...");
                e.printStackTrace();
                System.exit(1);
            }
            return null;
    }

    public static String checkName(String line){
        String result = null;
        if(Pattern.compile(patternName).matcher(line).matches()){
            result = line.substring(0,1).toUpperCase() + line.substring(1).toLowerCase();
        }
        return result;
    }

    public static Date checkDate(String line) throws ParseException {
        Date result = null;
        if(Pattern.compile(datePattern).matcher(line).matches()){
            return format.parse(line);
        }
        return result;
    }

}
