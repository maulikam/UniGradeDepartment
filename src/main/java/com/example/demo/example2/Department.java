package com.example.demo.example2;

import java.util.HashMap;
import java.util.Map;

public class Department {
    private Map<Student, Integer> studentGrades;

    public Department() {
        this.studentGrades = new HashMap<>();
    }

    public void addStudentGrade(Student student, Integer grade) {
        studentGrades.put(student, grade);
    }

    public void printStudentGrades() {
        for (Map.Entry<Student, Integer> entry : studentGrades.entrySet()) {
            Student student = entry.getKey();
            Integer grade = entry.getValue();
            System.out.println("Student: " + student.getName() + " " + student.getLastName() + ", Grade: " + grade);
        }
    }
}

