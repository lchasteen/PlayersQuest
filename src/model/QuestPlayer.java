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


    private void setConsequenceCode(int code, boolean isCorrect) {
        int tval = 0;   
        skipPlayersTurn = false;
        // Code 100 - 200 step 5: Loose gold
        if (code >= 100 && code <= 200) {
            for (int i = 100; i <= 200; i += 5) {
                if (code == i) {
                    tval = (code - 100);
                    if(isCorrect){
                        tval += this.getAmountOfGold();
                    }else{
                       tval = this.getAmountOfGold() - tval;
                    }
                    this.setAmountOfGold(tval);
                }//if(code == i){
            }//for(int i = 100; i <= 200; i+=5 ){

            // Code 201 - 300 step 2: Loose health
        } else if (code > 200 && code <= 300) {
            for (int i = 200; i <= 300; i += 2) {
                if (code == i) {
                    if(isCorrect){
                        tval += this.getHealth();
                    }else{
                       tval -= this.getHealth();
                    }
                    this.setHealth(tval);
                }//if(code == i){
            }//for(int i = 200; i <= 300; i+=2){

            // Code 301 - 400 step 2: Loose resources
        } else if (code > 300 && code <= 400) {
            for (int i = 300; i <= 400; i += 2) {
                if (code == i) {
                    if(isCorrect){
                        tval += this.getResources();
                    }else{
                       tval -= this.getResources();
                    }
                    this.setResources(tval);
                }//if(code == i){
            }//for(int i = 300; i <= 400; i+=2){

            // Codes 401 and above.
        } else {
            switch (code) {
                // sudden death!
                case 401:
                    this.setHealth(0);
                    break;
                // all your men die!
                case 402:
                    this.setResources(0);
                    break;
                // you loose all of your money!
                case 403:
                    this.setAmountOfGold(0);
                    break;
                // you loose a turn!
                case 404:
                    this.setSkipPlayersTurn(true);
                    break;
            }
        }
        // reset values if they are less than zero
        if(this.getHealth() < 0){
            this.setHealth(0);
        }
        if(this.getAmountOfGold() < 0){
            this.setAmountOfGold(0);
        }
        if(this.getResources()< 0){
            this.setResources(0);
        }
           
        
    }

    /*
     * This method generates a proper name from the String argument name that 
     * is upper case for the start of each letter, removes numbers and special 
     * characters.
     * 
     * @param name
     */
    public static String getProperName(String name) {

        String stringSplitUp[], totalString = null;
        // Regex strip everthing exept leters and spaces.
        name = name.replaceAll("[^A-Za-z ]", "_").trim();

        if (name != null && !name.isEmpty() && name.length() > 1) {
            stringSplitUp = name.split(" ");
            if (stringSplitUp != null && stringSplitUp.length >= 1) {
                // For each String array element make the first letter upper then the rest lower.
                for (int i = 0; i < stringSplitUp.length; i++) {
                    // Make the string lower case all except for the first letter
                    if (stringSplitUp[i].length() >= 1) {
                        String restOfSubString = stringSplitUp[i].substring(1, stringSplitUp[i].length()).toLowerCase();
                        String capFirstLetterThenRest = stringSplitUp[i].substring(0, 1).toUpperCase() + restOfSubString;
                        // Add everthing together
                        if (totalString != null) {
                            totalString = totalString + " " + capFirstLetterThenRest;
                        } else {
                            totalString = capFirstLetterThenRest;
                        }//if(totalString != null){

                    } else {
                        if (totalString != null) {
                            // Make upper case first letter then combine the rest of the string.                        
                            totalString = totalString + " " + stringSplitUp[i].substring(0, 1).toUpperCase();
                        } else {
                            totalString = stringSplitUp[i].substring(0, 1).toUpperCase();
                        }//if(totalString != null){
                    }//if(stringSplitUp[i].length() >= 1){

                }//for(int i = 0; i < stringSplitUp.length; i++){
            }//if(stringSplitUp != null && stringSplitUp.length >= 1){
            return totalString.trim();
        }
        return null;
    }
    
    public String getStats(){
        String retString = null;
        retString = "Health:["+ this.getHealth() +"] ";
        retString += "Gold:[" + this.getAmountOfGold() +"] ";
        retString += "Resources:[" + this.getResources() +"]";
        return retString;
    }

    
    @Override
    public String toString(){
        String retString = null;
        
        retString = "Name:["+ this.getName()+"] ";
        retString += "Age:[" + this.getAge()+"] ";
        retString += "Level:["+ this.getLevel()+"] ";
        retString += "Type:["+this.getType()+"] ";
        retString += this.getStats();
        return retString;
        
    }
}
