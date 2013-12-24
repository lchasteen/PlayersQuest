/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import impl.QuestionAnswerConsequenceImpl;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ListIterator;
import model.Answer;
import model.Question;
import model.PlayerType;
import model.QuestionAnswerConsequence;
import utility.SimpleLog;
/**
 *
 * @author lchastee
 */
public class Quiz {
    private  ArrayList <Integer> questionsToAsk;
    private  ArrayList <Integer> questionsAnsweredCorrectly;
    private  ArrayList <Question> qlist;
    private QuestionAnswerConsequenceImpl qi;
    
    private Question currentQuestion;// Current Question: QuestionID
    
    
    public Quiz(PlayerType person)  throws SQLException, IllegalArgumentException{ 
        
        if(person != null){
            qi = new QuestionAnswerConsequenceImpl();
            questionsToAsk = new <Integer> ArrayList();
            questionsAnsweredCorrectly = new <Integer> ArrayList();
            currentQuestion = null;
            // Get the list of questions for the player according to the player level.            
            qlist = qi.getQuestionsForLevel(person.getLevel());            
            ListIterator it = qlist.listIterator();
            
            // Add QuestionID to ArrayList for a list of questions to ask.
            while(it.hasNext()){                
                Question q = (Question)it.next();                
                questionsToAsk.add(q.getQuestionID());
            }
        }else{
            throw new IllegalArgumentException("Error in class Quizz, argument PlayerType is null!");
        }
    }
    
   
    
    public Question getNextRandomQuestion(){        
        int rNum;
        
        rNum = 1 + (int)(Math.random() * (qlist.size()-1));
        
        if(qlist.isEmpty()){
            return null;
        }
        // set the current QuestionID
        currentQuestion = qlist.get(rNum);                          
        
        return currentQuestion;
    }
    
    public ArrayList <Answer> getMultipleChoiceAnswers() throws SQLException{
        int i = 1;
        if(currentQuestion == null){ 
            return null;
        }
        return qi.getAnswersForQuestion(currentQuestion.getQuestionID());
    }
    
    public boolean validateAnswer(Answer ans){        
        if(ans != null){
            if(ans.getQuestionID() == currentQuestion.getQuestionID() ){
                return true;
            }
        }
        return false;
    }
    
    /*
    public boolean validateAnswer(int choice){
        return false;
    }
    * */
    
    
}
