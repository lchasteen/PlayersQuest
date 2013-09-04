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
    private String databaseClass;
    private String databaseName;
    
    
    public QuestConfiguration() {        
          ResourceBundle props = ResourceBundle.getBundle("resources.quest");                          
          consequencesFile = props.getString("consequences_file");       
          questionsFile = props.getString("questions_file");
          logFile = props.getString("log_file");         
          databaseName = props.getString("database_name");
          databaseClass = props.getString("database_class");
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

    public String getDatabaseClass() {
        return databaseClass;
    }


    public String getDatabaseName() {
        return databaseName;
    }

    
    
}
