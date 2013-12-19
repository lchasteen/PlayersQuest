/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author lchastee
 */
public class Consequence{
    private String Consequence;
    private int ConsequenceID;
    private int ConsequenceValue;

    public String getConsequence() {
        return Consequence;
    }

    public void setConsequence(String Consequence) {
        this.Consequence = Consequence;
    }

    public int getConsequenceID() {
        return ConsequenceID;
    }

    public void setConsequenceID(int ConsequenceID) {
        this.ConsequenceID = ConsequenceID;
    }

    public int getConsequenceValue() {
        return ConsequenceValue;
    }

    public void setConsequenceValue(int ConsequenceValue) {
        this.ConsequenceValue = ConsequenceValue;
    }
    
    
    @Override
    public String toString(){
        String retStr = null;
        
        
        retStr = "Consequence ID:[" + this.getConsequenceID() +"] ";
        retStr += "Consequence:[" + this.getConsequence() +"] ";
        retStr += "Consequence Value:[" + this.getConsequenceValue() +"] ";        
        return retStr;
    }
    
    
}
