/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import impl.QuestionAnswerConsequenceImpl;

/**
 *
 * @author lchastee
 */
public class QuestPlayer extends PlayerType{
    int playerType;
    int playerNumber;
    String playerName;
    private boolean skipPlayersTurn = false;

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    
    
    
    public boolean isSkipPlayersTurn() {
        return skipPlayersTurn;
    }

    public void setSkipPlayersTurn(boolean skipPlayersTurn) {
        this.skipPlayersTurn = skipPlayersTurn;
    }

    @Override
    public String toString(){
     String tString = null;
     
     tString = "Name:[" + this.getName() + "] ";     
     tString += "Player Type:[" + String.valueOf(this.getType())+ "] ";
     tString += "Age:[" + String.valueOf(this.getAge()) + "] ";       
     tString += "Level:[" + String.valueOf(this.getLevel()) + "] ";
     tString += "Health:[" + String.valueOf(this.getHealth()) + "] ";
     tString += "Resources:[" + String.valueOf(this.getResources()) + "] ";
     tString += "Gold:[" + String.valueOf(this.getAmountOfGold()) + "] ";
     
     
        return tString;
     
    }
    
    
}
