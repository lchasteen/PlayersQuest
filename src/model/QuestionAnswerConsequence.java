/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lchastee
 */
public class QuestionAnswerConsequence {
    private String Question, Answer, Consequence;    
    private int questionID;
    private int answerID;
    private int consequenceID;
    private int consequenceValue;
    private int levelID; // QuestionAnswerConsequence level: > numbers = harder questions.

    public int getConsequenceValue() {
        return consequenceValue;
    }

    public void setConsequenceValue(int consequenceValue) {
        this.consequenceValue = consequenceValue;
    }

    
    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }

    public String getConsequence() {
        return Consequence;
    }

    public void setConsequence(String Consequence) {
        this.Consequence = Consequence;
    }

    
    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
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
    
    @Override
    public String toString(){
        String retStr = null;
        
        retStr = "Question ID:["+ this.getQuestionID() + "] ";
        retStr += "Question:["+ this.getQuestion() + "] ";
        retStr += "Answer ID:[" + this.getAnswerID() +"] ";
        retStr += "Answer:[" + this.getAnswer() +"] ";
        retStr += "Consequence ID:[" + this.getConsequenceID() +"] ";
        retStr += "Consequence:[" + this.getConsequence() +"] ";
        retStr += "Consequence Value:[" + this.getConsequenceValue() +"] ";        
        return retStr;
    }
    
}
