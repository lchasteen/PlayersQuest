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

    
    
    
}
