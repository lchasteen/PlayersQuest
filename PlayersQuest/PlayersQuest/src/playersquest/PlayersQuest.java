/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playersquest;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import utility.QuestConfiguration;

/**
 *
 * @author Lane
 */
public class PlayersQuest {

    private QuestConfiguration configuration;
    private Logger log;
    private FileHandler fileHandler;
    private GameData cons;
    private Question ques;
    
    
    
    public PlayersQuest(){
        
        // Set the log, configuration file, consequences, and question file.
        try{
            configuration = new QuestConfiguration();
            fileHandler = new FileHandler(configuration.getLogFile());
            log = Logger.getLogger("playersquest.playersquest");
            log.addHandler(fileHandler);
            log.setLevel(Level.ALL);                        
            cons = new GameData(configuration.getConsequencesFile());
            ques = new Question(configuration.getQuestionsFile(),configuration.getConsequencesFile());
        }catch(Exception e){
            if(log != null){
                log.log(Level.SEVERE,e.getMessage(),e);
            }else{
                e.printStackTrace();
            }                
        }finally{
            if(fileHandler != null){
                fileHandler.close();
            }       
            
        }   
        
    }
    
    public Question getQuiz(){
        return this.ques;
    }
  /*  
    public String getNextQuestion(){
                
        // Get the next random consequences.
        try{            
            return ques.getNextGameData();
        }catch(Exception e){
            if(log != null){
                log.log(Level.SEVERE,e.getMessage(),e);
            }else{
                e.printStackTrace();
            }                
        }
        return null;

    }
    
    public String getNextConsequence(int location){        
        
        // Get the next random consequences.
        try{            
            return cons.getNextRandomGameData(location);      
        }catch(Exception e){
            if(log != null){
                log.log(Level.SEVERE,e.getMessage(),e);
            }else{
                e.printStackTrace();
            }                
        }
        
        return null;
        
    }
    */
    public void endGame(){
        // Close files and perform ending game closeout.
        try{
            if(cons != null)cons.closeFile();
        }catch (Exception e){
            if(log != null){
                log.log(Level.SEVERE,e.getMessage(),e);
            }else{
                e.printStackTrace();
            }//else{                
        }//catch (Exception e){
    }
    
    
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            BufferedReader inStream; 
            PlayersQuest pc = new PlayersQuest();
            Question qxx;
            String  ans;            
            boolean continueLooping = true, moreQuestions;
            
            qxx = pc.getQuiz();
            inStream = new BufferedReader(new InputStreamReader(System.in));
            
            while(qxx.getNextQuestion()){
                try{                    
                    System.out.print(qxx.getQuestion() + " ");
                    ans = inStream.readLine();
                    // If the user entered exit then end.
                    if(ans.equalsIgnoreCase("exit")){                        
                        break;
                    }
                    // Check to see if the answer is correct.
                    if(!qxx.compareAnswer(ans)){
                        System.out.println("INCORRECT!!" + qxx.getConsequence());
                    }else{
                        System.out.println("CORRECT!!");
                    }//if(!qxx .compareAnswer(ans)){                    
                    
                }catch(Exception e){                   
                   e.printStackTrace();
                   continue;                    
                }//catch                    
            }// while
            
            inStream.close();
            /*
            System.out.println(pc.getNextConsequence(1));
            System.out.println(pc.getNextConsequence(1));
            System.out.println(pc.getNextConsequence(1));
            System.out.println(pc.getNextConsequence(2));
            System.out.println(pc.getNextConsequence(2));
            System.out.println(pc.getNextConsequence(2));
            System.out.println(pc.getNextConsequence(3));
            System.out.println(pc.getNextConsequence(3));
            System.out.println(pc.getNextConsequence(3));
            */
            pc.endGame();
        }catch (Exception e){
            
        }
        
    }
}
