package Generator;

import Container.*;
import Person.Degree;
import Person.Nationality;
import Person.Student;
import Person.Teacher;
import Types.Department;
import Types.StudentGroup;
import Types.Subject;

import java.util.*;

public class MainGenerator {

    private Students students;
    private StudentGroups studentGroups;
    private Departments departments;
    private Subjects subjects;
    private Teachers teachers;

    public MainGenerator(){
        departments = generateDepartments();
        teachers = generateTeachers();
        subjects = generateSubjects(departments);
        studentGroups = generateStudentGroups();
        students = generateStudents();
    }

    private Students generateStudents(){
        Students result = new Students();
        for(int i = 0; i < 108; i++){
            Student s = student(i);
            result.add(s);
                studentGroups.convert().get(i%12).addStudent(s);
                subjects.convert().get(i%10).addToSubject(s);
        }
        return result;
    }

    private Teachers generateTeachers(){
        Teachers result = new Teachers();
        for(int i = 0; i < 10; i++){
            Teacher t = teacher(i);
            result.add(t);
                departments.convert().get(i%3).addTeacher(t);
        }
        return result;
    }

    private Departments generateDepartments(){
        Departments result = new Departments();
        for(int i = 0; i < 3; i ++){
            Department d = department(i);
            result.add(d);
        }
        return result;
    }

    private StudentGroups generateStudentGroups(){
        StudentGroups result = new StudentGroups();
        for(int i = 0; i < 12; i++){
            result.add(studentGroup(i));
        }
        return result;
    }

    private Subjects generateSubjects(Departments d){
        Subjects result = new Subjects();
        for(int i = 0; i < 10; i++){
            Department dep = d.convert().get(i%3);
            Teacher t = randomTeacher(dep.getList());
            result.add(subject(i, dep, t));
        }
        return result;
    }

    private Teacher randomTeacher(List<Teacher> teachers){
        Random random = new Random();
        int i = random.nextInt(teachers.size());
        return teachers.get(i);
    }

    private Student student(int i){
        String firstName = "Student" + i;
        String lastName = "StudentLastName" + i;
        Date date = DateGenerator.generateDate();
        Nationality nationality = Nationality.getNationality();
        String no = "s" + i;

        Student student = new Student(firstName, lastName, date, nationality, no);
        return student;
    }

    private Teacher teacher(int i){
        String firstName = "Teacher" + i;
        String lastName = "TeacherLastName" + i;
        Date date = DateGenerator.generateDate();
        Nationality nationality = Nationality.getNationality();
        Date date2 = DateGenerator.generateDate();
        Degree degree = DegreeGenerator.generateDegree();

        Teacher teacher = new Teacher(firstName, lastName, date, nationality, date2, degree);
        return teacher;
    }

    private StudentGroup studentGroup(int i){
        StudentGroup result = new  StudentGroup("StudentGroup_" + i);
        return result;
    }

    private Department department(int i){
        Department result = new Department("Department_" + i);
        return result;
    }

    private Subject subject(int i, Department d, Teacher t){
        Subject result = new Subject( "Subject_" + i, d, t);
        return result;
    }

    ///////getters


    public Departments getDepartments() {
        return departments;
    }

    public StudentGroups getStudentGroups() {
        return studentGroups;
    }

    public Students getStudents() {
        return students;
    }

    public Subjects getSubjects() {
        return subjects;
    }

    public Teachers getTeachers() {
        return teachers;
    }
}
