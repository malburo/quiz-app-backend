package com.example.Quiz.MarkResolver;

public class MarkResolverResponse {
    private int correctAnswer;
    private int wrongAnswer;
    private int undone;
    private int totalPoint;

    public MarkResolverResponse(int correctAnswer, int wrongAnswer, int notDone, int totalPoint) {
        this.correctAnswer = correctAnswer;
        this.wrongAnswer = wrongAnswer;
        this.undone = notDone;
        this.totalPoint = totalPoint;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(int wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public int getUndone() {
        return undone;
    }

    public void setUndone(int undone) {
        this.undone = undone;
    }

    public int getTotalPoint() {
        return totalPoint;
    }

    public void setTotalPoint(int totalPoint) {
        this.totalPoint = totalPoint;
    }

}
