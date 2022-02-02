package Container;

import Types.Subject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Subjects {
    private Set<Subject> subjectSet;

    public Subjects(){
        subjectSet = new HashSet<>();
    }

    public void add(Subject subject){
        if(subject != null){
            subjectSet.add(subject);
        }
    }

    public List<Subject> convert(){
        List<Subject> list = new ArrayList<>();
        for(Subject s : subjectSet){
            list.add(s);
        }
        return list;
    }

    public Set<Subject> getSubjectSet() {
        return subjectSet;
    }

    @Override
    public String toString() {
        String result = "";
        for(Subject s : subjectSet){
            result += s.toString();
        }
        return result;
    }
}
