/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import impl.QuestionAnswerConsequenceImpl;
import java.util.ArrayList;
import model.Answer;
import model.Question;
import model.PlayerType;
/**
 *
 * @author lchastee
 */
public class Quiz {
    private  ArrayList <Integer> questionsAsked;
    
    
    public Quiz(PlayerType person){
        QuestionAnswerConsequenceImpl QI = new QuestionAnswerConsequenceImpl();
    }
    
    public Question getNextQuestion(){
        return null;
    }
    
    public ArrayList <Answer> getAnswersForQuestion(){
        return null;
    }
    
    public boolean validateAnswer(String answer){
        return false;
    }
    
    
}
