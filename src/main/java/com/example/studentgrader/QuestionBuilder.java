package com.example.studentgrader;

public class QuestionBuilder {
    private int questionId;
    private int questionNumber;
    private String questionText;
    private String correctAnswer;

    public QuestionBuilder setQuestionId(int questionId) {
        this.questionId = questionId;
        return this;
    }

    public QuestionBuilder setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
        return this;
    }

    public QuestionBuilder setQuestionText(String questionText) {
        this.questionText = questionText;
        return this;
    }

    public QuestionBuilder setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
        return this;
    }

    public Question createQuestion() {
        return new Question(questionId, questionNumber, questionText, correctAnswer);
    }
}