package Container;

import Types.StudentGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StudentGroups {
    private Set<StudentGroup> studentGroupSet;

    public StudentGroups(){
        studentGroupSet = new HashSet<>();
    }

    public void add(StudentGroup studentGroup){
        if(studentGroup != null){
            studentGroupSet.add(studentGroup);
        }
    }

    public List<StudentGroup> convert(){
        List<StudentGroup> list = new ArrayList<>();
        for(StudentGroup sg : studentGroupSet){
            list.add(sg);
        }
        return list;
    }

    public Set<StudentGroup> getStudentGroupSet() {
        return studentGroupSet;
    }

    @Override
    public String toString() {
        String result = "";
        for(StudentGroup sg : studentGroupSet){
            result += sg.toString();
        }
        return result;
    }
}
