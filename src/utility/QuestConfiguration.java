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
    
    private final static String consequencesFile;
    private final static String questionsFile;
    private final static String logFile;
    private final static String databaseClass;
    private final static String databaseName;
    private final static int resources;
    private final static int money;
    private final static int health;
    private final static ResourceBundle props;
    
    
    
    static {        
         // ResourceBundle props = ResourceBundle.getBundle("resources.quest");                          
          props = ResourceBundle.getBundle("resources.quest");
          consequencesFile = props.getString("consequences_file");       
          questionsFile = props.getString("questions_file");
          logFile = props.getString("log_file");         
          databaseName = props.getString("database_name");
          databaseClass = props.getString("database_class");
          money = Integer.parseInt(props.getString("money"));
          resources = Integer.parseInt(props.getString("resources"));
          health = Integer.parseInt(props.getString("health"));
    }
    
    public static String getConsequencesFile(){
        return consequencesFile;
    }
    public static String getQuestionsFile(){
        return questionsFile;
    }    
    public static String getLogFile(){
        return logFile;
    }

    public static String getDatabaseClass() {
        return databaseClass;
    }


    public static String getDatabaseName() {
        return databaseName;
    }

    public static int getResources() {
        return resources;
    }

    
    public static int getMoney() {
        return money;
    }

    
    public static int getHealth() {
        return health;
    }

   
  

    
    
}
