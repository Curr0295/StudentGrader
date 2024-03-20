package com.example.studentgrader;

public class StudentGrade {
    private final String studentName;
    private final double score;
    private final int test_Id;

    public StudentGrade(String studentName, double score, int test_Id) {
        this.studentName = studentName;
        this.score = score;
        this.test_Id = test_Id;
    }

    public double getGrade() {
        return score;
    }

    public int getTestId(){
        return test_Id;
    }
}