/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lchastee
 */
public class QuestionAnswerConsequenceData {
    private String thisQuestion, thisAnswer, thisConsequence;    
    private int questionID;
    private int answerID;
    private int consequenceID;    
    private int levelID; // QuestionAnswerConsequenceData level: > numbers = harder questions.

    public String getThisAnswer() {
        return thisAnswer;
    }

    public void setThisAnswer(String thisAnswer) {
        this.thisAnswer = thisAnswer;
    }

    public String getThisConsequence() {
        return thisConsequence;
    }

    public void setThisConsequence(String thisConsequence) {
        this.thisConsequence = thisConsequence;
    }

    
    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    
    public String getThisQuestion() {
        return thisQuestion;
    }

    public void setThisQuestion(String thisQuestion) {
        this.thisQuestion = thisQuestion;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public int getAnswerID() {
        return answerID;
    }

    public void setAnswerID(int answerID) {
        this.answerID = answerID;
    }

    public int getConsequenceID() {
        return consequenceID;
    }

    public void setConsequenceID(int consequenceID) {
        this.consequenceID = consequenceID;
    }
    
    
    
    
    
    
    
}
