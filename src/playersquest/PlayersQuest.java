/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package playersquest;


import file.GameFile;
import impl.QuestionAnswerConsequenceImpl;
import impl.QuestPlayerImpl;
import eventhandler.QuestListener;
import game.Quiz;
import impl.QuestLevelImpl;
import java.io.BufferedReader;
import java.io.Console;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answer;
import model.Player;
import model.QuestPlayer;
import model.Question;
import utility.DatabaseConnection;
import utility.Queries;
import utility.QuestConfiguration;
import utility.SimpleLog;

/**
 *
 * @author Lane
 */
public class PlayersQuest {

    private QuestConfiguration configuration;
    private Logger log;
    private FileHandler fileHandler;
    private GameFile cons;
    private QuestionAnswerConsequenceImpl ques;
    
    private ArrayList<QuestPlayerImpl> pl;
    private int numberOfPlayers;
    private QuestListener listener;
    
    
    public PlayersQuest(QuestListener handler){
        
        // Set the log, configuration file, consequences, and question file.
        try{
           // configuration = new QuestConfiguration();
            fileHandler = new FileHandler(QuestConfiguration.getLogFile());
            log = Logger.getLogger("playersquest.playersquest");
            log.addHandler(fileHandler);
            log.setLevel(Level.ALL);                        
            pl = new ArrayList<QuestPlayerImpl>();
            listener = handler;
            
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
   
  
    
  
  
    public QuestPlayerImpl getPlayer(int playerNumber) throws IndexOutOfBoundsException{
        if(playerNumber > 0 && pl.size()>= playerNumber){
            return pl.get(playerNumber - 1);    
        }
        return null;
    }
    
    public int getNumberOfPlayers(){
        return pl.size();
    }
    
    
  /*
    public QuestionAnswerConsequenceImpl getQuiz(){
        return this.ques;
    }
  
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
            PlayersQuest pc;
            QuestHandler qh;
            qh = new QuestHandler();            
            
            //JLC 8.30.2013 SQLite
            Connection c = null;
            Statement s = null;
            
            
           try{
                Class.forName(QuestConfiguration.getDatabaseClass());
                c = DriverManager.getConnection(QuestConfiguration.getDatabaseName());
                s = c.createStatement();
                String dpe = Queries.getCreateLevelTable();
                String sql = Queries.getCreatePlayerTable();
                String sql2 = Queries.getCreatePlayerTypeTable();
                s.executeUpdate(dpe);
                s.executeUpdate(sql);
                s.executeUpdate(sql2);
                s.close();
                c.close();
           }catch(Exception e){
               e.printStackTrace();
           }
            
              
              //QuestPlayerTypeImpl qpl = new QuestPlayerTypeImpl();
              //qpl.createPlayerTypeTable();
              QuestPlayerImpl qlp = new QuestPlayerImpl();
              QuestLevelImpl qli = new QuestLevelImpl();
              qli.createPlayerTypeTable();
              qli.createLevelTable();
              
              
              
              //QuestPlayerImpl qpi = new QuestPlayerImpl("billy bob",10,1);
              qlp.addNewPlayer("billy bob",10,1);
              qlp.addNewPlayer("carl lewis", 11, 2);
              
              //qpl.getPlayer(1).toString();
              ArrayList <QuestPlayer> qp = qlp.getAllPlayers();
              Iterator<QuestPlayer> it = qp.iterator();
              
              while(it.hasNext()){
                  //System.out.println((QuestPlayer) it.next());
                  QuestPlayer ppp = it.next();
                  System.out.println(ppp.toString());
              }
              
              QuestionAnswerConsequenceImpl qaci = new QuestionAnswerConsequenceImpl();
              
              qaci.createTables();           
              
              //qaci.populateQuestionAnswerTables();
              //qaci.populateAnswerTable();
              
              Quiz quiz = new Quiz(qp.get(0));
              
              Quiz quiz1 = new Quiz(qp.get(1));
              
              //System.out.println(quiz.getNextRandomQuestion());
              //System.out.println(quiz1.getNextRandomQuestion());
              //quiz.getNextQuestion();
              
              
            
            inStream = new BufferedReader(new InputStreamReader(System.in));
            //Scanner inStream = new Scanner(System.in);
            //String  ans;            
            boolean continueLooping = true, moreQuestions;
            //Question qxx;
            QuestPlayerImpl p[];
            //Console console = System.console();
            int numOfPlayers = 0, playerType = 1;
            int playerTracker = 1;
            
            //pc = new PlayersQuest(qh);
            
            System.out.println("**************************************************************");
            System.out.println( "                  PLAYERS QUEST");
            System.out.println("**************************************************************");
            System.out.println("Only strongest and the most brave even dare to attempt");
            System.out.println("this quest. If you survive, then you will join the proud");
            System.out.println("few that have finished this quest and lived to tell the tale!\n");
            
           
            /*
            while(true){
                try{
                    System.out.print("Enter you name or end to finish:");
                    String ans = inStream.readLine();                    
                    if(ans.equalsIgnoreCase("end")){                        
                        break;
                    }                    
                    pc.addNewPlayer(ans, 10, playerType);
                    numOfPlayers++;
                    playerType++;
                }catch(Exception e){
                    //e.printStackTrace();
                    System.out.println(e.getMessage() + "aaaa");
                    continue;
                }
            }
            */ 
            
            //if(numOfPlayers > 0 ){
                System.out.println("Are you ready to begin!?");           
                System.out.println("Let's go!\n");


                while(true){
                    try{                    
                        //System.out.print(qxx.getQuestion() + " ");

                        /*
                        if(playerTracker > numOfPlayers){
                            playerTracker = 1;
                        }
                        */ 
                        //if(!pc.getPlayer(playerTracker).skipTurn()){//&& !pc.getPlayer(playerTracker).hasNext()){
                            System.out.println();
                            //pc.getPlayer(playerTracker).getQuestion();
                            //System.out.println("QUESTION");
                            Question tques = quiz.getNextRandomQuestion();
                            System.out.println(tques.getQuestion());
                            ArrayList <Answer> al = quiz.getMultipleChoiceAnswers();
                            
                            ListIterator li = al.listIterator();
                            int i = 1;
                            while(li.hasNext()){                                 
                                Answer ta = (Answer) li.next();
                                System.out.println(String.valueOf(i++) + ") " + ta.getAnswer());
                            }
                            System.out.print("Enter your selection or 'EXIT' to quit: ");
                            String ans = inStream.readLine();                            
                            //ans = inStream.nextLine();
                            // If the user entered exit then end.
                            if(ans.equalsIgnoreCase("exit")){                        
                                break;
                            }
                            // If the user keys "stat" then show his player status.
                            if(ans.equalsIgnoreCase("stat")){
                                //pc.getPlayer(playerTracker).getStats();
                                //pc.getPlayer(playerTracker).getQuestion();
                                continue;
                                //ans = inStream.readLine();
                            } 
                            
                            try{
                                int st = Integer.parseInt(ans);
                                st -= 1;
                                if(st < al.size()){
                                    if(quiz.validateAnswer(al.get(st))){
                                        System.out.println("Correct!");
                                    }else{
                                        System.out.println("WRONG!!! " + quiz.getConsequence().getConsequence());
                                    }
                                }
                                    
                            }catch (NumberFormatException nfe){
                                
                            }
            
                        

                        //playerTracker++;
                    }catch(Exception e){                   
                       e.printStackTrace();
                       //continue;                    
                       break;
                    }//catch                    
                }// while
                
            
            DatabaseConnection.closeConnection();
        }catch (Exception e){
           System.err.println( e.getClass().getName() + ": " + e.getMessage() );
      System.exit(0); 
        }
        
    }
    
    
}

    class QuestHandler implements QuestListener{

        @Override
        public void processGameResponse(String param) {
            System.out.print(param);
        }
    
    
    }    

