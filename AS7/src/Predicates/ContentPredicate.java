package Predicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.function.Predicate;
import java.util.zip.ZipFile;

public class ContentPredicate {
    public static Predicate<File> ContentPredicate(String string){
        return f -> {
            try {
                String s = Files.readString(Paths.get(f.getPath()));
                return s.contains(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        };
    }

    public static Predicate<String> ContentPredicateZ(File file, String string){
        return f -> {
            try {
                String p = f.replace("/", "\\");
                String s = file.getPath() + "\\" + p;
                String fi = Files.readString(Paths.get(s));
                return fi.contains(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        };
    }
}
