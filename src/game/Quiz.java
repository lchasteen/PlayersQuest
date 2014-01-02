/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import impl.QuestionAnswerConsequenceImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;
import model.Answer;
import model.Consequence;
import model.Question;
import model.PlayerType;
import model.QuestionAnswerConsequence;
import utility.SimpleLog;
/**
 *
 * @author lchastee
 */
public class Quiz {
    
    private  ArrayList <Integer> questionsAnsweredCorrectly;
    private ArrayList <QuestionAnswerConsequence> qlist;
    private QuestionAnswerConsequenceImpl qi;
    
    private QuestionAnswerConsequence currentQAC;// Current Question: QuestionID
    private int rNum;
    
    public Quiz(PlayerType person)  throws SQLException, IllegalArgumentException{ 
        
        if(person != null){
            qi = new QuestionAnswerConsequenceImpl();
            questionsAnsweredCorrectly = new <Integer> ArrayList();
            currentQAC = null;
            // Get the list of questions for the player according to the player level.            
            qlist = qi.getQuestionsForLevel(person.getLevel());            
            Collections.shuffle(qlist);
            rNum = 0;
            // Add QuestionID to ArrayList for a list of questions to ask.
            
        }else{
            throw new IllegalArgumentException("Error in class Quizz, argument PlayerType is null!");
        }
    }
    
   
    
    public Question getNextRandomQuestion(){        
        
        
        if(qlist.isEmpty()){
            return null;
        }
        
        // Reset the counter.
        if(rNum >= qlist.size()){
            rNum = 0;
        }
        
        // set the current QuestionID
        if(qlist.size() > rNum){
            currentQAC = qlist.get(rNum);
        
        
        rNum++;
        
        return currentQAC.getQuestion();
        }
        return null;
    }
    
    public ArrayList <Answer> getMultipleChoiceAnswers() throws SQLException{
        ArrayList <Answer> al;
        int i = 1;
        if(currentQAC == null){ 
            return null;
        }
        al = qi.getAnswersForQuestion(currentQAC.getQuestion().getQuestionID());
        
        Collections.shuffle(al);
        return al;
    }
    
    public boolean validateAnswer(Answer ans){        
        if(ans != null && currentQAC != null){
            if(ans.getAnswerID() == currentQAC.getAnswer().getAnswerID() ){
                if(!qlist.isEmpty()){                   
                    qlist.remove(currentQAC);
                    return true;
                }                
            }
        }
        return false;
    }
    
    public Consequence getConsequence (){
        if(currentQAC != null){
            return currentQAC.getConsequence();
        }
        return null;
    }
    
    /*
    public boolean validateAnswer(int choice){
        return false;
    }
    * */
    
    
}
