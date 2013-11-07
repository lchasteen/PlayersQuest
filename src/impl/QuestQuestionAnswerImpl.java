/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import java.sql.SQLException;

/**
 *
 * @author lchastee
 */
public class QuestQuestionAnswerImpl {
    
    public final void createAnswerTable() throws ClassNotFoundException, SQLException{
        
        String[] characterName = {"Knight", "Nobleman","Healer","Thief","Wizzard"};
        
           for(int i = 0; i < characterName.length; i++){
               //this.addPlayerType(characterName[i]);
           }        
    }
    
    public final void createQuestionTable()  throws ClassNotFoundException, SQLException{
        
    }
    
    public final void addAnswer(String answer) throws ClassNotFoundException, SQLException{
        
    }
}
