/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lchastee
 */
public class SimpleLog {

  private static Logger theLogger =    Logger.getLogger(SimpleLog.class.getName());
  
  static {
        try {      
            FileHandler fileHandler = new FileHandler(QuestConfiguration.getLogFile());
            theLogger.addHandler(fileHandler);
        } catch (IOException ex) {
            Logger.getLogger(SimpleLog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(SimpleLog.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  }
  

  public static void setError( String message ) throws IOException {

        
    // We also have to set our logger to log finer-grained
    // messages
    theLogger.setLevel(Level.FINE);
    theLogger.fine(message);
    System.err.println(message);
    
    
}
}
