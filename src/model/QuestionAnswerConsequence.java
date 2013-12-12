/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lchastee
 */
public class QuestionAnswerConsequence extends Consequence {
   
    private int levelID; // QuestionAnswerConsequence level: > numbers = harder questions.
    private int qacID;

    public int getQACID() {
        return qacID;
    }

    public void setQACID(int qacID) {
        this.qacID = qacID;
    }
    
    

    
    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
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
