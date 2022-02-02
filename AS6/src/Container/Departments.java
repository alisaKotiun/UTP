package Container;

import Types.Department;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Departments {
    private Set<Department> departmentSet;

    public Departments(){
        departmentSet = new HashSet<>();
    }

    public void add(Department department){
        if(department != null){
            departmentSet.add(department);
        }
    }

    public List<Department> convert(){
        List<Department> list = new ArrayList<>();
        for(Department d : departmentSet){
            list.add(d);
        }
        return list;
    }

    public Set<Department> getDepartmentSet() {
        return departmentSet;
    }

    @Override
    public String toString() {
        String result = "";
        for(Department d : departmentSet){
            result += d.toString();
        }
        return result;
    }
}
