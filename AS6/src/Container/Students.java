package Container;

import Person.Nationality;
import Person.Student;
import Person.Teacher;

import java.text.Collator;
import java.util.*;

public class Students {
    private Set<Student> studentSet;

    public Students(){
        studentSet = new HashSet<Student>();
    }

    public void add(Student student){
        if(student != null){
            studentSet.add(student);
        }
    }

    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public List<Student> filterAndSort(Nationality nationality){
        List<Student> result = new ArrayList<Student>();
        Collator collator = Collator.getInstance(nationality.getLocale());
        for(Student student: studentSet){
            if(student.getNationality() == nationality)
                result.add(student);
        }
        result.sort(new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                return collator.compare(student1.getFirstName(), student2.getFirstName());
            }
        });
        return result;
    }

}
