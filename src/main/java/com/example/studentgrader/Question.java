package com.example.studentgrader;

public class Question {
    private final String questionText;
    private final int questionNumber;
    private final String correctAnswer;
    private final int questionId;

    public Question(int questionId, int questionNumber, String questionText, String correctAnswer) {
        this.questionId = questionId;
        this.questionNumber = questionNumber;
        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

}

