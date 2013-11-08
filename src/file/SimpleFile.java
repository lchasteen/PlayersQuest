/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package file;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

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
    
    public SimpleFile(String fileName) throws FileNotFoundException, IOException{
            randomFile = new RandomAccessFile(fileName, "rw");            
        
    }
    public void setData(String toWrite) throws IOException{
        if(toWrite != null && !toWrite.isEmpty()){
            randomFile.writeBytes(toWrite);            
        }
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
