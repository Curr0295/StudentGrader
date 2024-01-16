package com.example.studentgrader;

public class StudentGrade {
    private String studentName;
    private double score;
    private int test_Id;

    public StudentGrade(String studentName, double score, int test_Id) {
        this.studentName = studentName;
        this.score = score;
        this.test_Id = test_Id;
    }

    public String getStudentName() {
        return studentName;
    }

    public double getGrade() {
        return score;
    }

    public int getTestId(){
        return test_Id;
    }
}