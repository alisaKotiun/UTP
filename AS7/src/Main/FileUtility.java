package Main;

import Predicates.ContentPredicate;
import Predicates.NamePredicate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FileUtility {
    private FileUtility(){}

    public static List<File> searchByName(File directory, String string) throws IOException {
        if(directory.exists() && directory.isDirectory() && directory.canRead()){
            Predicate<File> predicate = NamePredicate.NamePredicate(string);
            return search(directory, predicate);
        }
        return null;
    }

    public static List<File> searchByContent(File directory, String string) throws IOException {
        if(directory.exists() && directory.isDirectory() && directory.canRead()){
            Predicate<File> predicate = ContentPredicate.ContentPredicate(string);
            return search(directory, predicate);
        }
        return null;
    }

    private static List<File> search(File directory, Predicate<File> predicate) throws IOException {
        Path path = Paths.get(String.valueOf(directory));
        List<File> files = Files
                .walk(path)
                .parallel()
                .filter(p -> p.toFile().isFile())
                .map(a -> a.toFile())
                .filter(predicate)
                .sequential()
                .collect(Collectors.toList());

        return files;
    }
}
