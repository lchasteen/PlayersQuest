/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package impl;

import eventhandler.QuestListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author lchastee
 */
public class QuestPlayerImpl {

    private String name, characterName;
    // resources = the men or other    
    private int age, amountOfGold, resources, health;
    private boolean skipPlayersTurn = false;
    private QuestionImpl thisQuestion;
    private QuestListener listener;

    /**
     *
     * @param name
     * @param age
     * @param playerType
     * @param questionFileName
     * @param consequenceFileName
     * @throws FileNotFoundException
     * @throws IOException
     * @throws Exception
     */
    public QuestPlayerImpl(String name, int age, int playerType, String questionFileName, String consequenceFileName) throws FileNotFoundException, IOException, Exception {
        if (name != null && !name.isEmpty()
                && age > 0 && age <= 120
                && questionFileName != null && !questionFileName.isEmpty()
                && consequenceFileName != null && !consequenceFileName.isEmpty()) {
            this.name = getProperName(name);
            if(this.name == null ){
                throw new Exception("Invalid name.");
            }
            this.age = age;
            this.health = 100;
            this.amountOfGold = 1000;
            this.resources = 60;
            this.thisQuestion = new QuestionImpl(questionFileName, consequenceFileName);
            switch (playerType) {
                case 1:
                    characterName = "Knight " + this.name;
                    break;
                case 2:
                    characterName = "Nobleman " + this.name;
                    break;
                case 3:
                    characterName = "Healer " + this.name;
                    break;
                case 4:
                    characterName = "Thief " + this.name;
                    break;
                case 5:
                    characterName = "Wizzard " + this.name;
                    break;
                default:
                    characterName = this.name;
                    break;
            }

            this.thisQuestion.getNextQuestion();
        } else {
            throw new Exception("Name and(or) age must be valid.");
        }

    }

    /**
     *
     * @return
     */
    public boolean skipTurn() {
        boolean tempSkip = skipPlayersTurn;
        skipPlayersTurn = false;
        return tempSkip;
    }

    /**
     *
     * @param listener
     * @throws Exception
     */
    public void setListener(QuestListener listener) throws Exception {
        if (listener == null) {
            throw new Exception("Method:= setListener, QuestListener must not be null!");
        }
        this.listener = listener;
    }

    /**
     *
     */
    public void getQuestion() {
        this.listener.processGameResponse("("+ String.valueOf(thisQuestion.getQuestionNumber()) + ") " +this.characterName + ": " + thisQuestion.getQuestion() + " ");
    }

    /**
     *
     * @return @throws IndexOutOfBoundsException 
     * @throws IndexOutOfBoundsException
     * @throws Exception
     */
    public boolean getNextQuestion() throws IndexOutOfBoundsException, Exception {
        boolean res = thisQuestion.getNextQuestion();
        return res;
    }
    
    
    public boolean getNextRandomQuestion() throws IndexOutOfBoundsException, Exception {
        boolean res = thisQuestion.getNextQuestion();
        return res;
    }


    /*
     public boolean getQuestion(){
     String input = thisQuestion.getQuestion();
        
     if(input != null){
     this.listener.processGameResponse(input);
     return true;
     }
     return false;
     }
     * 
     */
    /**
     *
     * @param response
     */
    public void setResponse(String response) {
        if (thisQuestion.compareAnswer(response)) {
            this.listener.processGameResponse("CORRECT! " + this.characterName + "\n");
        } else {
            this.listener.processGameResponse("INCORRECT!");
            this.setConsequenceCode(thisQuestion.getConsequenceValue());
            this.listener.processGameResponse(thisQuestion.getConsequence() + "\n");
        }

    }

    /**
     *
     */
    public void getStats() {
        String retStat;

        retStat = "***Stats for " + this.characterName + "*** Gold:(" + Integer.valueOf(this.amountOfGold) + ") Health:("
                + Integer.valueOf(this.health) + ") Resources:("
                + Integer.valueOf(this.resources) + ")\n";

        this.listener.processGameResponse(retStat);

    }

    private int setConsequenceCode(int code) {

        skipPlayersTurn = false;
        // Code 100 - 200 step 5: Loose gold
        if (code >= 100 && code <= 200) {
            for (int i = 100; i <= 200; i += 5) {
                if (code == i) {
                    amountOfGold -= (code - 100);
                }//if(code == i){
            }//for(int i = 100; i <= 200; i+=5 ){

            // Code 201 - 300 step 2: Loose health
        } else if (code > 200 && code <= 300) {
            for (int i = 200; i <= 300; i += 2) {
                if (code == i) {
                    health -= (code - 200);
                }//if(code == i){
            }//for(int i = 200; i <= 300; i+=2){

            // Code 301 - 400 step 2: Loose resources
        } else if (code > 300 && code <= 400) {
            for (int i = 300; i <= 400; i += 2) {
                if (code == i) {
                    resources -= (code - 300);
                }//if(code == i){
            }//for(int i = 300; i <= 400; i+=2){

            // Codes 401 and above.
        } else {
            switch (code) {
                // sudden death!
                case 401:
                    health = 0;
                    break;
                // all your men die!
                case 402:
                    resources = 0;
                    break;
                // you loose all of your money!
                case 403:
                    amountOfGold = 0;
                    break;
                // you loose a turn!
                case 404:
                    skipPlayersTurn = true;
                    break;
            }
        }
        return 1;
    }

    /*
     * This method generates a proper name from the String argument name that 
     * is upper case for the start of each letter, removes numbers and special 
     * characters.
     * 
     * @param name
     */
    private String getProperName(String name) {

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
}
