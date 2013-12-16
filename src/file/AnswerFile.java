/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.FileNotFoundException;
import java.io.IOException;
import model.Answer;

/**
 *
 * @author lchastee
 */
public class AnswerFile extends SimpleFile{
    public AnswerFile(String FileName) throws FileNotFoundException, IOException{
        super(FileName);
        getListOfData();
    }
    
    
     public Answer getNextAnswer()throws IndexOutOfBoundsException, IllegalArgumentException{
        String strRet = null;
        Answer ans = null;        
        
        if(!hasNext() ){
            return null;
        }        
        strRet = getNextGameData();
        
        if(strRet == null ){
            return null;
        }        
        
        String strRetArray[] = strRet.split(";");        
        if(strRetArray.length >= 2 ){
            ans = new Answer();
            ans.setQuestionID(Integer.valueOf(strRetArray[0]));
            ans.setAnswer(strRetArray[1]);
            //ans.setAnswer(strRetArray[2]);
            //ans.setConsequence(strRetArray[3]);
            //ans.setConsequenceValue(Integer.valueOf(strRetArray[4]));
        }else{
            throw new IllegalArgumentException("Class:GameFile Method:getNextQuestionAnswerConsequence(); Row in file is not in right format!");
        }
            
        return ans;
        
    }
    
}
