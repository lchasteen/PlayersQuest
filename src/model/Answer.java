/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lchastee
 */
public class Answer {
    private int AnswerID;
    private String Answer;
    private int QuestionID;

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int QuestionID) {
        this.QuestionID = QuestionID;
    }
    
    

    public int getAnswerID() {
        return AnswerID;
    }

    public void setAnswerID(int AnswerID) {
        this.AnswerID = AnswerID;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String Answer) {
        this.Answer = Answer;
    }
    
    @Override
    public String toString(){
        String retStr = null;
        
        
        retStr = "Answer ID:[" + this.getAnswerID() +"] ";
        retStr += "Answer:[" + this.getAnswer() +"] ";
        
        return retStr;
    }
    
    
}
