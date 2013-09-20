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
import java.sql.Statement;
import java.util.ArrayList;
import model.PlayerType;
import utility.Queries;
import utility.QuestConfiguration;

/**
 *
 * @author lchastee
 */
public class QuestPlayerTypeImpl {
  private  ArrayList <PlayerType> al;
  
  public QuestPlayerTypeImpl(String description) throws ClassNotFoundException, SQLException{
      addPlayerType(description);
  }
  
  
  public void addPlayerType(String description)throws ClassNotFoundException, SQLException{
      if(description!= null && !description.isEmpty()){
        Connection c = null;
        PreparedStatement s = null;
        
        
        Class.forName(QuestConfiguration.getDatabaseClass());
        c = DriverManager.getConnection(QuestConfiguration.getDatabaseName());
        s = c.prepareStatement(Queries.getInsertPlayerType());        
        s.setString(1, description);     
        s.execute();
        s.close();
        c.close();
      } else {          
          throw new SQLException("Invalid Player Type!");
      }
  }

    public ArrayList<PlayerType> getAll()  throws ClassNotFoundException, SQLException{
       String sqlstr;      
      
       Connection c = null;
       Statement s = null;
       ResultSet rs = null;     
       al = new ArrayList<PlayerType>();     
           
       Class.forName(QuestConfiguration.getDatabaseClass());
       c = DriverManager.getConnection(QuestConfiguration.getDatabaseName());
       s = c.createStatement();
       
       sqlstr = Queries.getSelectPlayerType();
       rs = s.executeQuery(sqlstr);
       
       while(rs.next()){
           PlayerType p = new PlayerType();
           p.setType(rs.getInt(1));
           p.setDescription(rs.getNString(2));           
           al.add(p);
       }
      
       rs.close();
       s.close();
       c.close();
       return al;
    }
  

    
    
    
}
