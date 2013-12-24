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
import java.sql.Statement;
import java.util.ArrayList;

import model.QuestPlayer;
import utility.DatabaseConnection;
import utility.Queries;


/**
 *
 * @author lchastee
 */
public class QuestPlayerTypeImpl extends QuestPlayerImpl{
  private  ArrayList <QuestPlayer> al;
  
  
  public QuestPlayerTypeImpl(){
      
  }
  
  
  public QuestPlayerTypeImpl(String name, int age, int playerType) throws FileNotFoundException, IOException, IllegalArgumentException, SQLException{
      super(name,age,playerType);
  }
  
  public QuestPlayerTypeImpl(String name, int age, String playerDescription) throws FileNotFoundException, IOException, IllegalArgumentException, ClassNotFoundException, SQLException{
      addPlayerTypeDescription(name,age,playerDescription);
  }
  
  public final void addPlayerTypeDescription(String name, int age, String playerDescription) throws ClassNotFoundException, SQLException, FileNotFoundException, IOException, IllegalArgumentException{
      int retval = addPlayerTypeGetID(playerDescription);      
      this.addNewPlayer(name, age, retval);      
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


    public ArrayList<QuestPlayer> getAllPlayers()  throws ClassNotFoundException, SQLException{
       String sqlstr;            
       
       
       al = new ArrayList<QuestPlayer>();                
      
       Statement s = DatabaseConnection.getConnection().createStatement();
       
       sqlstr = Queries.getSelectAllPlayers();
       ResultSet rs = s.executeQuery(sqlstr);
       
       while(rs.next()){
           
           QuestPlayer qp = new QuestPlayer();
           
           qp.setPlayerNumber(rs.getInt("PLAYERID"));
           qp.setName(rs.getString("NAME"));
           qp.setAge(rs.getInt("AGE"));
           qp.setType(rs.getInt("PLAYERTYPE"));
           qp.setHealth(rs.getInt("HEALTH"));
           qp.setResources(rs.getInt("RESOURCES"));
           qp.setAmountOfGold(rs.getInt("GOLD"));
           qp.setLevel(rs.getInt("LEVELID"));
           al.add(qp);
       }
      
       rs.close();
       s.close();
       //c.close();
       return al;
    }
    
    public final void createPlayerTypeTable() throws ClassNotFoundException, SQLException, IllegalArgumentException{
        
        String[] characterName = {"Knight", "Nobleman","Healer","Thief","Wizzard"};
        
           for(int i = 0; i < characterName.length; i++){
               this.addPlayerType(characterName[i]);
           }
        
    }
    
    
    public QuestPlayer getPlayer(int playerNumber)throws ClassNotFoundException, SQLException{
        QuestPlayer qp = new QuestPlayer();
        
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getSelectPlayer());        
        
        s.setInt(1, playerNumber);
        s.execute();
        ResultSet rs = s.getResultSet();
        
        if(rs != null){
            rs.next();
            qp.setPlayerNumber(rs.getInt("PLAYERID"));
            qp.setName(rs.getString("NAME"));
            qp.setAge(rs.getInt("AGE"));
            qp.setType(rs.getInt("PLAYERTYPE"));
            qp.setHealth(rs.getInt("HEALTH"));
            qp.setResources(rs.getInt("RESOURCES"));
            qp.setAmountOfGold(rs.getInt("GOLD"));
            qp.setLevel(rs.getInt("LEVELID"));
        }
        
        return qp;
        
    }
    
    

    
    
    
}
