/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author lchastee
 */
public class Queries {
    private static final String createPlayerTable = "CREATE TABLE PLAYER "
            + "(PLAYERID    INT        PRIMARY KEY NOT NULL,"
            + " NAME        TEXT       NOT NULL,"
            + " AGE         INT        NOT NULL,"
            + " FOREIGN KEY (LEVELID) REFERENCES LEVEL(LEVELID))";
    
    private static final String createQuestionTable = "CREATE TABLE QUESTION"
            + "(QUESTIONID    INT        PRIMARY KEY NOT NULL,"
            + " QUESTION      TEXT       NOT NULL)";
    
    private static final String createAnswerTable = "CREATE TABLE ANSWER"
            + "(ANSWERID    INT        PRIMARY KEY NOT NULL,"
            + " ANSWER      TEXT       NOT NULL)";
    
    private static final String createConsequenceTable = "CREATE TABLE CONSEQUENCE"
            + "(CONSEQUENCEID    INT        PRIMARY KEY NOT NULL,"
            + " CONSEQUENCE      TEXT       NOT NULL,"
            + " CONSEQUENCEVALUE INT        NOT NULL)";
    
    private static final String createAnswerLevel = "CREATE TABLE LEVEL"
            + "(LEVELID    INT        PRIMARY KEY NOT NULL,"
            + " LEVEL      TEXT       NOT NULL,"
            + " LEVELID    INT        NOT NULL)";
    
    private static final String createQuestionAnswerConsequenceTable = 
            "CREATE TABLE QAC (QACID INT PRIMARY KEY NOT NULL,"
            + " FOREIGN KEY(LEVELID) REFERENCES LEVEL(LEVELID),"
            + " FOREIGN KEY(QUESTIONID) REFERENCES QUESTION(QUESTIONID),"
            + " FOREIGN KEY(CONSEQUENCEID) REFERENCES CONSEQUENCE(CONSEQUENCEID),"
            + " FOREIGN KEY(ANSWERID) REFERENCES ANSWER(ANSWERID))";
            
    
    
    public static String getCreatePlayerTable() {
        return createPlayerTable;
    }

    public static String getCreateQuestionTable() {
        return createQuestionTable;
    }

    public static String getCreateAnswerTable() {
        return createAnswerTable;
    }

    public static String getCreateConsequenceTable() {
        return createConsequenceTable;
    }

    public static String getCreateAnswerLevel() {
        return createAnswerLevel;
    }

    public static String getCreateQuestionAnswerConsequenceTable() {
        return createQuestionAnswerConsequenceTable;
    }
            
            
              
    
}
