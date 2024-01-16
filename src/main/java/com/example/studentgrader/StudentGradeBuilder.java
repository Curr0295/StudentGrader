package com.example.studentgrader;

public class StudentGradeBuilder {
    private String studentName;
    private double score;
    private int testId;

    public StudentGradeBuilder setStudentName(String studentName) {
        this.studentName = studentName;
        return this;
    }

    public StudentGradeBuilder setScore(double score) {
        this.score = score;
        return this;
    }

    public StudentGradeBuilder setTestId(int testId) {
        this.testId = testId;
        return this;
    }
    public StudentGradeBuilder getTestId(int testId) {
        this.testId = testId;
        return this;
    }



    public StudentGrade createStudentGrade() {
        return new StudentGrade(studentName, score, testId);
    }
}