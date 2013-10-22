/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        
        
        Class.forName(QuestConfiguration.getDatabaseClass());
        Connection c = DriverManager.getConnection(QuestConfiguration.getDatabaseName());
        PreparedStatement s = c.prepareStatement(Queries.getInsertLevel());        
        s.setString(1, levelDescription);     
        s.setInt(2, levelValue);
        s.execute();
        s.close();
        c.close();
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
  
}
