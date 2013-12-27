/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utility.DatabaseConnection;
import utility.Queries;
import utility.QuestConfiguration;

/**
 *
 * @author lchastee
 */
public class QuestLevelImpl {
    
    public QuestLevelImpl(){
        
    }
    
     public final void addLevel(String levelDescription, int levelValue)throws ClassNotFoundException, SQLException{
      if(levelDescription!= null && !levelDescription.isEmpty() && levelValue > 0){
        
        
        //Class.forName(QuestConfiguration.getDatabaseClass());
        //Connection c = DriverManager.getConnection(QuestConfiguration.getDatabaseName());
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertLevel());        
        s.setString(1, levelDescription);     
        s.setInt(2, levelValue);
        s.execute();
        s.close();
        //c.close();
      } else {          
          throw new SQLException("Invalid Player Type!");
      }
  }
     
       public final void createLevelTable() throws ClassNotFoundException, SQLException{
        
           int levelMax = 10, inc = 10;           
           String [] levelName = {"One","Two","Three","Four","Five","Six",
                                  "Seven","Eight","Nine","Ten"};
        
           for(int i = 0; i < levelMax; i++){
               
               this.addLevel(levelName[i],inc);
               inc +=levelMax;
           }
        
    }
       
        public final void addPlayerType(String description)throws ClassNotFoundException, SQLException, IllegalArgumentException{
      if(description!= null && !description.isEmpty()){
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertPlayerType());        
        s.setString(1, description);     
        s.execute();
        s.close();
        //c.close();
      } else {          
          throw new IllegalArgumentException("Invalid Player Type!");
      }
  }
  
  
  public final int addPlayerTypeGetID(String description)throws ClassNotFoundException, SQLException, IllegalArgumentException{
      int retval = -1;
      if(description!= null && !description.isEmpty()){
        
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertPlayerType());        
        PreparedStatement s1 = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertPlayerTypeGetID());
         
        s.setString(1, description);     
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
          throw new IllegalArgumentException("Invalid Player Type!");
      }
        return retval;
  }
  
  
    public final void createPlayerTypeTable() throws ClassNotFoundException, SQLException, IllegalArgumentException{
        
        String[] characterName = {"Knight", "Nobleman","Healer","Thief","Wizzard"};
        
           for(int i = 0; i < characterName.length; i++){
               this.addPlayerType(characterName[i]);
           }
        
    }

  
}
