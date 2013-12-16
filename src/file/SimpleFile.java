/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Lane
 */
public class SimpleFile {
    //private File thisFile;
    //private BufferedReader randomFile;
    //private BufferedWriter writer;
    //private FileInputStream fread;
    //private FileWriter fwrite;
    
    private RandomAccessFile randomFile;
    
    private ArrayList<ArrayList<String>> listOfData;
    private int arrayListTracker = 0;
    private int arrayValueTracker = 0;
    
    public SimpleFile(String fileName) throws FileNotFoundException, IOException{
            randomFile = new RandomAccessFile(fileName, "rw");            
        
    }
    public void setData(String toWrite) throws IOException{
        if(toWrite != null && !toWrite.isEmpty()){
            randomFile.writeBytes(toWrite);            
        }
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
    public void getListOfData() throws IOException{
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
    
    
      /**
     *
     * @return
     * @throws IndexOutOfBoundsException
     */
    public String getNextGameData()throws IndexOutOfBoundsException{
        String strRet;        
        
        
        if(!hasNext()){
            return null;
        }
        
        strRet = listOfData.get(this.arrayListTracker).get(this.arrayValueTracker);
    
        
        
        if(this.arrayValueTracker >= (listOfData.get(this.arrayListTracker).size() - 1)){
            this.arrayValueTracker = 0;
            this.arrayListTracker++;        
            
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
    
    public String getData() throws IOException{        
        return randomFile.readLine();        
    }
    
    public void resetFilePointer() throws IOException{
        randomFile.seek(0);
    }
            
    
    public void closeFile() throws IOException{
        if(randomFile != null){
            randomFile.close();
        }        
    }
}
