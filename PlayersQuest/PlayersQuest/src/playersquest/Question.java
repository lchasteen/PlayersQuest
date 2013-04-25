/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playersquest;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author Lane
 */
public class Question extends GameData{
    
    private String thisQuestion;
    private String thisAnswer;
    private String thisPosition;
    private String thisConsequence;
    private GameData cons;
    

    /**
     *
     * @param fileName
     */
    public Question(String questionFileName, String consequenceFileName) throws FileNotFoundException, IOException {
        super(questionFileName);
        cons = new GameData(consequenceFileName);                
    }
    
    public boolean getNextQuestion()throws IndexOutOfBoundsException{
        String getOnlyQuestion[];
        String rawData;
        
        // Reset these class variables
        thisPosition = "";
        thisQuestion = "";
        thisAnswer = "";
        thisConsequence = "";
        
        rawData = this.getNextGameData();
        if(rawData == null){
            return false;
        }//if(rawData == null){
        
        // Split up the raw data return string into the game position,
        // question, and the answer.
        getOnlyQuestion = rawData.split(";");
        if(getOnlyQuestion != null && getOnlyQuestion.length > 2){
            thisPosition = getOnlyQuestion[0].trim();
            thisQuestion = getOnlyQuestion[1].trim();
            thisAnswer = getOnlyQuestion[2].trim();
            thisConsequence = getConsequenceForQuestion(getPosition());
        }//if(getOnlyQuestion != null && getOnlyQuestion.length > 2)
        
        
        return true;
        
    }
    
    public String getAnswer(){
        return this.thisAnswer;
    }
    
    public String getQuestion(){
        return this.thisQuestion;
    }
    
    public int getPosition() throws NumberFormatException {
        return Integer.parseInt(this.thisPosition);
    }
    
    public String getConsequence(){
        return this.thisConsequence;
    }
    
    private String getConsequenceForQuestion(int tPosition) throws IndexOutOfBoundsException {
        String tstr;
        String tsplit[];
        tstr = this.cons.getNextRandomGameData(tPosition);
        if(tstr != null && !tstr.isEmpty()){
            tsplit = tstr.split(";");
            if(tsplit != null && tsplit.length > 1){
                return tsplit[1]; // return just the consequence string.
            }
        }//if(tstr != null && !tstr.isEmpty()){
        return null;
    }
    
    
    public boolean compareAnswer(String answer){        
        if(answer != null && !answer.isEmpty() && thisAnswer != null && !thisAnswer.isEmpty()){            
            if(thisAnswer.equalsIgnoreCase(answer)){
                return true;
            }//if(thisAnswer.equalsIgnoreCase(answer)){            
        }//if(answer != null && !answer.isEmpty() && thisAnswer != null && !thisAnswer.isEmpty()){                        
        return false;
    }
    
   
    
    
    
}
