package Predicates;

import java.io.File;
import java.util.function.Predicate;

public class NamePredicate {

    public static Predicate<File> NamePredicate(String string){
        Predicate<File> predicate = f -> f.getName().contains(string);
        return predicate;
    }

    public static Predicate<String> NamePredicateZ(String string){
        Predicate<String> predicate = f -> f.contains(string);
        return predicate;
    }

}
