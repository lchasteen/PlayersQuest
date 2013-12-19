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
   
    private int levelID; // QuestionAnswerConsequence level: > numbers = harder questions.
    private int qacID;
    private Answer answer;
    private Consequence consequence;
    private Question question;

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    public Consequence getConsequence() {
        return consequence;
    }

    public void setConsequence(Consequence consequence) {
        this.consequence = consequence;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    

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
        
        retStr = question.toString();
        retStr += answer.toString();
        retStr += consequence.toString();
        
        return retStr;
    }
    
}
