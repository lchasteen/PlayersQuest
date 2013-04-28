/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playersquest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


/**
 *
 * @author Lane
 */
public class Question extends GameData{
    
    private String thisQuestion;
    private String thisAnswer;
    private String thisPosition;
    private String thisConsequence;
    private int thisConsequenceValue;    
    private GameData cons;
    private ArrayList<Integer>dontAskTheseAgain;   
    private int questionNumber = 0;


    /**
     *
     * @param fileName
     */
    public Question(String questionFileName, String consequenceFileName) throws FileNotFoundException, IOException {
        super(questionFileName);
        cons = new GameData(consequenceFileName);
        dontAskTheseAgain = new ArrayList<Integer>();
    }
    
    public boolean getNextRandomQuestion(int positionInGame){
        
        if(positionInGame < 1){
            return false;
        }//if(positionInGame < 1){
        
        return true;
    }
    
    /**
     * This method returns the data number associated with the data.
     * 
     * @return integer
     */
    public int getQuestionNumber(){
        return this.questionNumber;
    }
    
    public boolean getNextQuestion()throws IndexOutOfBoundsException, Exception{
        String getOnlyQuestion[];
        String rawData;
        
        // Reset these class variables
        thisPosition = "";
        thisQuestion = "";
        thisAnswer = "";
        thisConsequence = "";
        thisConsequenceValue = 0;
        
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
            this.questionNumber++;
            if(!getConsequenceForQuestion(getPosition())){
                throw new Exception("Method:= getNextQuestion, error getting consequence! POS:=" + this.thisPosition);
            }
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
    
    public int getConsequenceValue(){
        return this.thisConsequenceValue;
    }
    
    private boolean getConsequenceForQuestion(int tPosition) throws IndexOutOfBoundsException, NumberFormatException {
        String tstr;
        String tsplit[];
        tstr = this.cons.getNextRandomGameData(tPosition);
        if(tstr != null && !tstr.isEmpty()){
            tsplit = tstr.split(";");
            if(tsplit != null && tsplit.length > 1){
                if(tsplit.length > 2){
                    this.thisConsequenceValue = Integer.parseInt(tsplit[2]);
                    this.thisConsequence = tsplit[1];
                    return true;
                }//if(tsplit.length > 2){
            }//if(tsplit != null && tsplit.length > 1){
        }//if(tstr != null && !tstr.isEmpty()){
        return false;
    }
    
    
    
    public boolean compareAnswer(String answer){        
        if(answer != null && !answer.isEmpty() && thisAnswer != null && !thisAnswer.isEmpty()){            
            if(thisAnswer.equalsIgnoreCase(answer)){
                dontAskTheseAgain.add(new Integer(this.getQuestionNumber()));
                System.out.println(this.dontAskTheseAgain.toString());
                return true;
            }//if(thisAnswer.equalsIgnoreCase(answer)){            
        }//if(answer != null && !answer.isEmpty() && thisAnswer != null && !thisAnswer.isEmpty()){                        
        return false;
    }
    
   
    
    
    
}
