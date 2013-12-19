/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import model.Answer;
import model.Consequence;
import model.Question;
import model.QuestionAnswerConsequence;

/**
 *
 * @author Lane
 */
public class GameFile extends SimpleFile{    
    //private ArrayList<ArrayList<String>> listOfData;
    //private int arrayListTracker = 0;
    //private int arrayValueTracker = 0;
    
    
    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     * @throws IOException
     */
    public GameFile (String fileName) throws FileNotFoundException, IOException{
        super(fileName);     
        getListOfData();
    }
    
    
    public QuestionAnswerConsequence getNextQuestionAnswerConsequence()throws IndexOutOfBoundsException, IllegalArgumentException{
        String strRet = null;
        QuestionAnswerConsequence qac = null;        
        
        if(!hasNext() ){
            return null;
        }        
        strRet = getNextGameData();
        
        
        if(strRet == null ){
            return null;
        }        
        
        String strRetArray[] = strRet.split(";");        
        if(strRetArray.length >= 5 ){
            Question ques = new Question();
            Answer ans = new Answer();
            Consequence con = new Consequence();            
            qac = new QuestionAnswerConsequence();
            
            qac.setLevelID(Integer.valueOf(strRetArray[0]));
            // Quesion
            ques.setQuestionID(getLocationInData());
            ques.setQuestion(strRetArray[1]);            
            qac.setQuestion(ques);
            // Answer
            ans.setAnswerID(getLocationInData());
            ans.setAnswer(strRetArray[2]);
            qac.setAnswer(ans);
            //Consequence
            con.setConsequenceID(getLocationInData());
            con.setConsequence(strRetArray[3]);
            con.setConsequenceValue(Integer.valueOf(strRetArray[4]));
            qac.setConsequence(con);
        }else{
            throw new IllegalArgumentException("Class:GameFile Method:getNextQuestionAnswerConsequence(); Row in file is not in right format!");
        }
            
        return qac;
        
    }
    
}
