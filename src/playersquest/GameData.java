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
    
    
    /**
     *
     * @param fileName
     * @throws FileNotFoundException
     * @throws IOException
     */
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
    
    public String getNextRandomGameData()throws IndexOutOfBoundsException{
        return this.getNextRandomGameData(this.arrayListTracker + 1);
    }
    
    public String getNextRandomGameData(int locationInGame) throws IndexOutOfBoundsException {        
        int random, lngValue;
        Random rg = new Random();
        
        if(!listOfData.isEmpty() && listOfData.size() > 0 && locationInGame > 0 && locationInGame <= listOfData.size()){
            lngValue = locationInGame -1; 
            random =  rg.nextInt(listOfData.get(lngValue).size());
            return listOfData.get(lngValue).get(random);       
        }
        return null;
    }

    
    /*
     * This method gets the specified consequence based on the location in the game.
     */
    /**
     *
     * @param locationInGame
     * @param dataPosition
     * @return
     * @throws IndexOutOfBoundsException
     */
    public String getGameData(int locationInGame, int dataPosition) throws IndexOutOfBoundsException {                
        
        if(!listOfData.isEmpty() && listOfData.size() > 0 
           && locationInGame > 0 && locationInGame <= listOfData.size()
           && dataPosition > 0 && dataPosition <= listOfData.get(locationInGame).size()){
            
            int tempArrayNum = locationInGame -1; 
            int tempDataPos = dataPosition -1;
            
            return listOfData.get(tempArrayNum).get(tempDataPos);       
        }        
        return null;      
    }
    
    /**
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
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
    
    /**
     *
     * @return
     */
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
     * @param consequence
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
     * @param strValue      
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
   
    
    public int getDataSize(int locationInGame){
        if(!listOfData.isEmpty() && listOfData.size() > 0 && locationInGame > 0 && locationInGame <= listOfData.size()){            
            int tempPosition = locationInGame - 1;
            return listOfData.get(tempPosition).size();
        }
        return 0;
    }
    
    
    public int getDataArraySize(){
        if(listOfData != null && !listOfData.isEmpty()){
            return listOfData.size();
        }
        return 0;
    }
    
    public int getLocationInGame(){
        return this.arrayListTracker;
    }
    
    
    public boolean addToLocationInGame(){
        if(arrayListTracker >= listOfData.size() ){
            return false;
        }else{
            arrayListTracker++;
            return true;
        }
    }
    
    public int getLocationInData(){
        return this.arrayValueTracker;
    }
    
}
