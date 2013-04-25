/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playersquest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import utility.SimpleFile;

/**
 *
 * @author Lane
 */
public class GameData extends SimpleFile{    
    private ArrayList<ArrayList<String>> listOfData;
    private int arrayListTracker = 0;
    private int arrayValueTracker = 0;
    
    public GameData (String fileName) throws FileNotFoundException, IOException{
        super(fileName);     
        this.getListOfData();
    }
    
    
    private int getMaxPrefixValue() throws IOException{
        String temp;
        int maxValue = -1;
        int tValue = -1;
        while((temp = this.getData()) != null){
            tValue = this.getPrefixValue(temp);
            if(tValue > maxValue){
                maxValue = tValue;
            }//if(tValue > maxValue){
        }//while((temp = this.getData()) != null){
        return maxValue;
    }
    
    /*
     * This method loads the ArrayList with string values for consequences.
     */
    private void getListOfData() throws IOException{
        String temp;
        
        int maxArrayValue = getMaxPrefixValue();
        
        this.resetFilePointer();
        if(maxArrayValue > 0){
            listOfData = new ArrayList<ArrayList<String>>(maxArrayValue);
            // Create the ArrayLists for all of the numeric values from the text file.
            for(int i = 0; i < maxArrayValue; i++){
                ArrayList<String> tempAL = new ArrayList<String>();   
                listOfData.add(tempAL);
            }//for(int i = 0; i < maxArrayValue; i++){
            while((temp = this.getData()) != null){       
                if(!temp.isEmpty()&& temp.length()> 1){
                    int prefixVal;
                    // Set the ArrayLists for the specified consequence prefix
                    prefixVal = this.getPrefixValue(temp) -1;
                    listOfData.get(prefixVal).add(temp);                
                }//if(!temp.isEmpty()){
            }//while((temp = this.getData()) != null){        
        }//if(maxArrayValue > 0){
    }    
    
    /*
     * This method gets the specified consequence based on the location in the game.
     */
    public String getNextRandomGameData(int locationInGame) throws IndexOutOfBoundsException {        
        int random, lngValue;
        Random rg = new Random();
        lngValue = locationInGame -1; 
        if(!listOfData.isEmpty() && listOfData.size() > 0 && lngValue >= 0 && lngValue <= listOfData.size()){
            random =  rg.nextInt(listOfData.get(lngValue).size());
            return listOfData.get(lngValue).get(random);       
        }        
        return null;      
    }
    
    public String getNextGameData()throws IndexOutOfBoundsException{
        String strRet;        
        
        //if(listOfData.size() )
        //if(this.arrayListTracker >= (listOfData.size()) || (this.arrayValueTracker >= listOfData.get(arrayListTracker).size())){
        if(!hasNext()){
            return null;
        }
        //System.out.println("List:[" + String.valueOf(arrayListTracker) + "] Value:[" + String.valueOf(arrayValueTracker) + "]");
        strRet = listOfData.get(this.arrayListTracker).get(this.arrayValueTracker);
        
        if(this.arrayValueTracker >= (listOfData.get(this.arrayListTracker).size() - 1)){
            this.arrayValueTracker = 0;
            this.arrayListTracker++;        
            /*if(this.arrayListTracker >= (listOfData.size())){
                this.arrayListTracker = 0;
                
            }//if(this.arrayListTracker >= (listOfData.size())){
            */
        }else{
            this.arrayValueTracker++;    
            
        }           
              
        return strRet;      
    }
    
    public boolean hasNext(){
        if(this.arrayListTracker >= (listOfData.size()) || (this.arrayValueTracker >= listOfData.get(arrayListTracker).size())){
            return false;
        }
        return true;
    }
    
    
    /*
     * This method gets the prefix value from the String "consequence" passed in 
     * the argument seperated by the ";" character.
     *
     */
    private int getPrefixValue(String consequence){
        String strArray[];
        int prefixValue = -1;
        // Split the String based on ";" and return first string value
        // return this value as an integer.
        if(consequence != null && !consequence.isEmpty()){
            strArray = consequence.split(";");
            if(strArray != null && strArray.length > 1){
                prefixValue = Integer.valueOf(strArray[0]);
                return prefixValue;
            }//if(strArray != null && strArray.length > 1){
        }//if(consequence != null && !consequence.isEmpty()){
        
        return -1;
    }
    
    /*
     * This method gets the prefix value from the String "strValue" passed in 
     * the argument seperated by the ";" character.
     *
     */
    private int getSuffixValue(String strValue){
        String strArray[];
        int suffixValue = -1;
        // Split the String based on ";" and return first string value
        // return this value as an integer.
        if(strValue != null && !strValue.isEmpty()){
            strArray = strValue.split(";");
            if(strArray != null){
                suffixValue = Integer.valueOf(strArray[strArray.length -1]);
                return suffixValue;
            }//if(strArray != null && strArray.length > 1){
        }//if(strValue != null && !strValue.isEmpty()){        
        return -1;
    }
    
}
