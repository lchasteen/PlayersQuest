/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import file.AnswerFile;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import file.GameFile;
import model.Answer;
import model.Consequence;
import model.Question;
import model.QuestionAnswerConsequence;
import utility.DatabaseConnection;
import utility.Queries;
import utility.QuestConfiguration;


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
    /*
    public QuestionAnswerConsequenceImpl(String questionFileName, String consequenceFileName) throws FileNotFoundException, IOException, SQLException {
        //super(questionFileName);
        //cons = new GameFile(consequenceFileName);
        //dontAskTheseAgain = new ArrayList<Integer>();
        
    }
    
    
    
    public boolean getNextRandomQAC(int level){        
        if(level > 0){
            return false;
        }//if(positionInGame < 1){        
        
        return true;
    }
    * 
    * */ 
    
    public void addQAC(QuestionAnswerConsequence qac) throws SQLException, IllegalArgumentException{
        if(qac != null){
            addQAC(qac.getLevelID(),qac.getQuestion().getQuestion(),qac.getAnswer().getAnswer(),qac.getConsequence().getConsequence(),qac.getConsequence().getConsequenceValue());
        }else{
            throw new IllegalArgumentException("Class:QuestionAnswerConsequenceImpl Method:addQAC(QuestionAnswerConsequence); QAC Argument is null!");
        }
    }
    
    
    public QuestionAnswerConsequence getQAC(int qacID) throws SQLException{
        QuestionAnswerConsequence qac = new QuestionAnswerConsequence();        
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getSelectQAC());                
        s.setInt(1, qacID);     
        ResultSet rs =  s.executeQuery();
        
        while(rs.next()){
            qac.setQACID(rs.getInt("QACID"));
            
            qac.setQuestion(this.getQuestion(rs.getInt("QUESTIONID")));
            qac.setAnswer(this.getAnswer(rs.getInt("ANSWERID")));
            qac.setConsequence(this.getConsequence(rs.getInt("CONSEQUENCEID")));            
        }
        s.close();
        return null;
    }
    
    private void addQAC(int level, String question, String answer, String consequence, int consequenceID) throws SQLException, IllegalArgumentException{
        int ques, ans, con, consID;
        
        ques = addQuestion(question);        
        
        ans = addAnswer(answer,ques);        
        
        con = addConsequence(consequence, consequenceID);                
  
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertQAC());        
        
        s.setInt(1, level);     
        s.setInt(2, ques);
        s.setInt(3, con);
        s.setInt(4, ans);

        
        s.execute();        
        s.close();
        
    }
    
    private int addQuestion(String question) throws SQLException, IllegalArgumentException{        
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
          throw new IllegalArgumentException("Invalid question!");
      }
        return retval;
        
    }
    
    private Question getQuestion(int questionID)throws SQLException, IllegalArgumentException{
        Question ques = new Question();
        String strRet = null;
        PreparedStatement s1 = DatabaseConnection.getConnection().prepareStatement(Queries.getSelectQuestion());
        s1.setInt(1, questionID);
        ResultSet rs =  s1.executeQuery();
        while(rs.next()){
            ques.setQuestionID(questionID);
            ques.setQuestion(rs.getString("QUESTION"));
        }
        return ques;
    }
    
    private Consequence getConsequence(int consequenceID)throws SQLException, IllegalArgumentException{
        Consequence con = new Consequence();
        int tQuestionID;
        String tQuestion = null;
        
        PreparedStatement s1 = DatabaseConnection.getConnection().prepareStatement(Queries.getSelectConsequence());
        s1.setInt(1, consequenceID);
        ResultSet rs =  s1.executeQuery();
        while(rs.next()){
            //con.setAnswerID(answerID);
            con.setConsequenceID(consequenceID);
            con.setConsequence(rs.getString("CONSEQUENCE"));
            con.setConsequenceValue(rs.getInt("CONSEQUENCEVALUE"));
            
        }
        rs.close();
        s1.close();
        
        return con;
    }
    
    
    private Answer getAnswer(int answerID) throws SQLException, IllegalArgumentException{
        Answer ans = new Answer();
        int tQuestionID;
        String tQuestion = null;
        
        PreparedStatement s1 = DatabaseConnection.getConnection().prepareStatement(Queries.getSelectAnswer());
        s1.setInt(1, answerID);
        ResultSet rs =  s1.executeQuery();
        while(rs.next()){
            ans.setAnswerID(answerID);
            ans.setAnswer(rs.getString("ANSWER"));
            tQuestionID = rs.getInt("QUESSTIONID");                               
            ans.setQuestionID(tQuestionID);            
        }
        rs.close();
        s1.close();
        
        
        return ans;
    }
    
    private ArrayList <Answer> getAnswersForQuestion(int questionID) throws SQLException, IllegalArgumentException{
        
        return null;
    }
    
    //private String getAnswer(int answerID) throws SQLException, IllegalArgumentException{
    
    private int addAnwer(Answer answer)throws SQLException, IllegalArgumentException{
        int retval = -1;
        addAnswer(answer.getAnswer(), answer.getQuestionID());
        return retval;
    }
    
    private int addAnswer(String answer, int question) throws SQLException, IllegalArgumentException{
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
          throw new IllegalArgumentException("Invalid answer!");
      }
        return retval;
    }
    
    private int addConsequence(String consequence, int consequenceValue) throws SQLException, IllegalArgumentException{
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
          throw new IllegalArgumentException("Invalid consequence!");
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
    
    public void populateQuestionAnswerTables() throws SQLException, FileNotFoundException, IOException {
        GameFile gf = new GameFile(QuestConfiguration.getQuestionsFile());
        //gf.
        while(gf.hasNext()){    
            this.addQAC(gf.getNextQuestionAnswerConsequence());            
        }
    }
    
    
    public void populateAnswerTable() throws SQLException, FileNotFoundException, IOException {
        AnswerFile af = new AnswerFile(QuestConfiguration.getAnswerFile());
        //gf.
        while(af.hasNext()){    
            this.addAnwer(af.getNextAnswer());            
        }
    }

  
    /*
    public boolean getNextQuestion()throws IndexOutOfBoundsException, IllegalArgumentException{
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
                throw new IllegalArgumentException("Method:= getNextQuestion, error getting consequence! POS:=" + this.thisPosition);
            }
        }//if(getOnlyQuestion != null && getOnlyQuestion.length > 2)                
        return true;        
    }
    */
   
    
    
    
    
   
    
    
    
}
