package Test;

import Container.*;
import Generator.MainGenerator;
import Person.Nationality;
import Person.Student;
import Person.Teacher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MainTest {
    MainGenerator mainGenerator;
    @Before
    public void before(){
       mainGenerator = new MainGenerator();
    }


    @Test
    public void getStudents(){
        Students students = mainGenerator.getStudents();
        int size = students.getStudentSet().size();
        Assert.assertEquals(108, size);
        List<Student> list = students.filterAndSort(Nationality.Chinese);
        System.out.println(list);
    }

    @Test
    public void getStudentGroups(){
        StudentGroups studentGroups = mainGenerator.getStudentGroups();
        Assert.assertFalse(studentGroups.getStudentGroupSet().isEmpty());
        int size = studentGroups.getStudentGroupSet().size();
        Assert.assertEquals(12, size);
        System.out.println(studentGroups);
    }

    @Test
    public void getDepartments(){
        Departments departments = mainGenerator.getDepartments();
        Assert.assertFalse(departments.getDepartmentSet().isEmpty());
        int size = departments.getDepartmentSet().size();
        Assert.assertEquals(3, size);
        System.out.println(departments);
    }

    @Test
    public void getSubjects(){
        Subjects subjects = mainGenerator.getSubjects();
        int size = subjects.getSubjectSet().size();
        Assert.assertEquals(10, size);
        System.out.println(subjects);
    }

    @Test
    public void getTeachers(){
        Teachers teachers = mainGenerator.getTeachers();
        int size = teachers.getTeacherSet().size();
        Assert.assertEquals(10, size);
        List<Teacher> list = teachers.filterAndSort(Nationality.Polish);
        System.out.println(list);
    }


}
