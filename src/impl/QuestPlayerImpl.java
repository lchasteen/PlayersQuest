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
import model.QuestPlayer;
import utility.DatabaseConnection;
import utility.Queries;
import utility.QuestConfiguration;


/**
 *
 * @author lchastee
 */
public class QuestPlayerImpl {



    public QuestPlayerImpl(){
        
    }
    
    public QuestPlayerImpl(String name, int age, int playerType) throws FileNotFoundException, IOException, IllegalArgumentException, SQLException {
       addNewPlayer(name,age,playerType);

    }
    
    public QuestPlayerImpl(QuestPlayer qp) throws FileNotFoundException, IOException, IllegalArgumentException, SQLException {
        addNewPlayer(qp);
    }
    
    public final void addNewPlayer(QuestPlayer qp) throws FileNotFoundException, IOException, IllegalArgumentException, SQLException {
      if(qp != null){        
        
        PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getInsertPlayer());        
        s.setString(1, qp.getName());     
        s.setInt(2, qp.getAge());
        s.setInt(3, qp.getType());
        s.setInt(4, qp.getLevel());
        s.setInt(5, qp.getHealth());
        s.setInt(6, qp.getResources());
        s.setInt(7, qp.getAmountOfGold());
        
        
        
        s.execute();
        s.close();
        //c.close();
      } else {          
          throw new SQLException("Method: addNewPlayer(), QuestPlayer is null!");
      }   
    }
    
    public void addNewPlayer(String name,int age, int playerType) throws FileNotFoundException, IOException, IllegalArgumentException, SQLException {
        QuestPlayer qc = new QuestPlayer();
       
        if (name != null && !name.isEmpty()) {
                qc.setName(QuestPlayer.getProperName(name));
        }
        else{            
            throw new IllegalArgumentException("Invalid name.");
        }
        if (age > 0){
            qc.setAge(age);
        }
        else{            
            throw new IllegalArgumentException("Invalid age. Player's age must be greater than zero.");
        }
        if(playerType > 0){
            qc.setType(playerType);
        }
        else {
            throw new IllegalArgumentException("Invalid player type.");
        }
                   
        qc.setHealth(QuestConfiguration.getHealth());
        qc.setAmountOfGold(QuestConfiguration.getMoney());
        qc.setResources(QuestConfiguration.getResources());          
        qc.setLevel(QuestConfiguration.getStartLevel());        
        
        //SimpleLog.setError(qc.toString());
        addNewPlayer(qc);
        
    
    }   
    
    
    public ArrayList<QuestPlayer> getAllPlayers()  throws ClassNotFoundException, SQLException{
       
       ArrayList<QuestPlayer> al = new ArrayList<QuestPlayer>();                
       PreparedStatement s = DatabaseConnection.getConnection().prepareStatement(Queries.getSelectAllPlayers());        
       
       ResultSet rs = s.executeQuery();
       
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
           System.out.println(qp.toString());
       }
      
       rs.close();
       s.close();
       return al;
    }
}
