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
import java.util.jar.JarFile;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;

public class JarUtility {
    private JarUtility(){}

    public static List<String> searchByName(File directory, String string) throws IOException {
        if(directory.exists()){
            JarFile jarFile = new JarFile(directory);
            Predicate<String> predicate = NamePredicate.NamePredicateZ(string);
            return search(jarFile, predicate);
        }
        return null;
    }

    public static List<String> searchByContent(File directory, String string) throws IOException {
        if(directory.exists()){
            JarFile jarFile = new JarFile(directory);
            Predicate<String> predicate = ContentPredicate.ContentPredicateZ(directory, string);
            return search(jarFile, predicate);
        }
        return null;
    }

    private static List<String> search(JarFile directory, Predicate<String> predicate) throws IOException {
        Path path = Paths.get(String.valueOf(directory));
        List<String> files = directory
                .stream()
                .filter(a -> !((ZipEntry) a).isDirectory())
                .map(a -> a.getName())
                .filter(predicate)
                .sequential()
                .collect(Collectors.toList());

        return files;
    }
}
