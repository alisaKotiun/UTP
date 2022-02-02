package Main;

import Predicates.ContentPredicate;
import Predicates.NamePredicate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;


public class ZipUtility {
    private ZipUtility(){}

    public static List<String> searchByName(File directory, String string) throws IOException {
        if(directory.exists()){
            ZipFile zipFile = new ZipFile(directory);
            Predicate<String> predicate = NamePredicate.NamePredicateZ(string);
            return search(zipFile, predicate);
        }
        return null;
    }

    public static List<String> searchByContent(File directory, String string) throws IOException {
        if(directory.exists()){
            ZipFile zipFile = new ZipFile(directory);
            Predicate<String> predicate = ContentPredicate.ContentPredicateZ(directory, string);
            return search(zipFile, predicate);
        }
        return null;
    }

    private static List<String> search(ZipFile directory, Predicate<String> predicate) throws IOException {

        List<String> files = directory
                .stream()
                .parallel()
                .filter(a -> !((ZipEntry) a).isDirectory())
                .map(a -> ((ZipEntry) a).getName())
                .filter(predicate)
                .collect(Collectors.toList());

        return files;
    }
}
