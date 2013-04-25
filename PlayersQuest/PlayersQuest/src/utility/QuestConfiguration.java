/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;
import java.util.ResourceBundle;


/**
 *
 * @author Lane
 */
public class QuestConfiguration {
    
    private String consequencesFile;
    private String questionsFile;
    private String logFile;
    
    
    public QuestConfiguration() {        
          ResourceBundle props = ResourceBundle.getBundle("resources.quest");                          
          consequencesFile = props.getString("consequences_file");       
          questionsFile = props.getString("questions_file");
          logFile = props.getString("log_file");         
    }
    public String getConsequencesFile(){
        return this.consequencesFile;
    }
    public String getQuestionsFile(){
        return this.questionsFile;
    }    
    public String getLogFile(){
        return this.logFile;
    }
    
    
}
