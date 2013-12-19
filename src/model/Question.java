/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lchastee
 */
public class Question {
    private String Question;
    private int QuestionID;

    public String getQuestion() {
        return Question;
    }

    public void setQuestion(String Question) {
        this.Question = Question;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int QuestionID) {
        this.QuestionID = QuestionID;
    }
    
 
    @Override
    public String toString(){
        String retStr = null;
        
        retStr = "Question ID:["+ this.getQuestionID() + "] ";
        retStr += "Question:["+ this.getQuestion() + "] ";
        
        return retStr;
    }
}
