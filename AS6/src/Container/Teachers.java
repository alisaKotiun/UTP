package Container;

import Person.Person;
import Person.Teacher;
import Person.Nationality;

import java.text.Collator;
import java.util.*;

public class Teachers {
    private Set<Teacher> teacherSet;

    public Teachers(){
        teacherSet = new HashSet<Teacher>();
    }

    public void add(Teacher teacher){
        if(teacher != null){
            teacherSet.add(teacher);
        }
    }

    public List<Teacher> filterAndSort(Nationality nationality){
        List<Teacher> result = new ArrayList<Teacher>();
        Collator collator = Collator.getInstance(nationality.getLocale());
        for(Teacher teacher : teacherSet){
            if(teacher.getNationality() == nationality)
                result.add(teacher);
        }
        result.sort(new Comparator<Teacher>() {
            @Override
            public int compare(Teacher teacher1, Teacher teacher2) {
                return collator.compare(teacher1.getFirstName(), teacher2.getFirstName());
            }
        });
        return result;
    }

    public Set<Teacher> getTeacherSet() {
        return teacherSet;
    }
}
