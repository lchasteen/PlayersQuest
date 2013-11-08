/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import file.GameFile;
import utility.DatabaseConnection;
import utility.Queries;


/**
 *
 * @author Lane
 */
public class QuestionAnswerConsequenceImpl {
    
    
    
    

    public QuestionAnswerConsequenceImpl(){
        
    }
    /**
     *
     * @param fileName
     */
    public QuestionAnswerConsequenceImpl(String questionFileName, String consequenceFileName) throws FileNotFoundException, IOException, SQLException {
        //super(questionFileName);
        //cons = new GameFile(consequenceFileName);
        //dontAskTheseAgain = new ArrayList<Integer>();
        
    }
    
    public boolean getNextRandomQuestion(int positionInGame){        
        if(positionInGame < 1){
            return false;
        }//if(positionInGame < 1){        
        return true;
    }
    
    
    
    
    public void addQAC(int level, String question, String answer, String consequence, int consequenceID) throws SQLException, Exception{
        int ques, ans, con, consID;
        ques = addQuestion(question);
        System.out.println(ques);
        
        ans = addAnswer(answer,ques);
        System.out.println(ans);
        
        con = addConsequence(consequence, consequenceID);        
        System.out.println(con);
  
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertQAC());        
        
        s.setInt(1, level);     
        s.setInt(2, ques);
        s.setInt(3, con);
        s.setInt(4, ans);

        
        s.execute();        
        s.close();
        
    }
    
    private int addQuestion(String question) throws SQLException, Exception{        
      int retval = -1;
      
      if(question!= null && !question.isEmpty()){
        
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertQuestion());        
        PreparedStatement s1 = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertQuestionTypeGetID());
         
        s.setString(1, question);     
        s.execute();
        
        ResultSet rs =  s1.executeQuery();
        while(rs.next()){
            retval = rs.getInt("LAST_ID");
        }
        rs.close();
        s.close();
        s1.close();
        //c.close();
      } else {          
          throw new Exception("Invalid question!");
      }
        return retval;
        
    }
    
    private int addAnswer(String answer, int question) throws SQLException, Exception{
      int retval = -1;
      
      if(answer!= null && !answer.isEmpty()){
        
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertAnswer());        
        PreparedStatement s1 = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertAnswerTypeGetID());
         
        s.setString(1, answer);     
        s.setInt(2, question);
        s.execute();
        
        ResultSet rs =  s1.executeQuery();
        while(rs.next()){
            retval = rs.getInt("LAST_ID");
        }
        rs.close();
        s.close();
        s1.close();        
      } else {          
          throw new Exception("Invalid answer!");
      }
        return retval;
    }
    
    private int addConsequence(String consequence, int consequenceValue) throws SQLException, Exception{
          int retval = -1;
      
      if(consequence!= null && !consequence.isEmpty() && consequenceValue > 0){
        
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertConsequence());        
        PreparedStatement s1 = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertConsequenceTypeGetID());
         
        s.setString(1,consequence);     
        s.setInt(2,consequenceValue);
        s.execute();
        
        ResultSet rs =  s1.executeQuery();
        while(rs.next()){
            retval = rs.getInt("LAST_ID");
        }
        rs.close();
        s.close();
        s1.close();
        //c.close();
      } else {          
          throw new Exception("Invalid consequence!");
      }
        return retval;
    }
    
    
    public void createTables() throws SQLException{
        
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getCreateQuestionTable());        
        PreparedStatement s1 = DatabaseConnection.getConnection().prepareStatement(Queries.getCreateAnswerTable());
        PreparedStatement s2 = DatabaseConnection.getConnection().prepareStatement(Queries.getCreateConsequenceTable());
        PreparedStatement s3 = DatabaseConnection.getConnection().prepareStatement(Queries.getCreateQuestionAnswerConsequenceTable());
        
        s.execute();
        s1.execute();
        s2.execute();
        s3.execute();
        
        s.close();
        s1.close();
        s2.close();
        s3.close();
    }
    
  
    /*
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
    */
   
    
    
    
    
   
    
    
    
}
