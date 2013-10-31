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
            + "(PLAYERID    INTEGER        PRIMARY KEY AUTOINCREMENT,"
            + " NAME        TEXT       NOT NULL,"
            + " AGE         INTEGER        NOT NULL,"
            + " PLAYERTYPE  INTEGER        NOT NULL,"
            + " LEVELID     INTEGER        NOT NULL,"
            + " HEALTH      INTEGER        NOT NULL,"
            + " RESOURCES   INTEGER        NOT NULL,"
            + " GOLD        INTEGER        NOT NULL,"
            + " FOREIGN KEY (LEVELID) REFERENCES LEVEL(LEVELID),"
            + " FOREIGN KEY (PLAYERTYPE) REFERENCES LEVEL(TYPEID))";
    
    private static final String createPlayerTypeTable = "CREATE TABLE PLAYERTYPE"
            + "(TYPEID    INTEGER    PRIMARY KEY AUTOINCREMENT,"
            + " DESCRIPTION      TEXT       NOT NULL)";
    
    private static final String createQuestionTable = "CREATE TABLE QUESTION "
            + "(QUESTIONID    INTEGER        PRIMARY KEY AUTOINCREMENT,"
            + " QUESTION      TEXT       NOT NULL)";
    
    private static final String createAnswerTable = "CREATE TABLE ANSWER "
            + "(ANSWERID    INTEGER        PRIMARY KEY AUTOINCREMENT,"
            + " ANSWER      TEXT       NOT NULL)";
    
    private static final String createConsequenceTable = "CREATE TABLE CONSEQUENCE "
            + "(CONSEQUENCEID    INTEGER        PRIMARY KEY AUTOINCREMENT,"
            + " CONSEQUENCE      TEXT       NOT NULL,"
            + " CONSEQUENCEVALUE INTEGER        NOT NULL)";
    
    private static final String createLevelTable = "CREATE TABLE LEVEL "
            + "(LEVELID    INTEGER        PRIMARY KEY AUTOINCREMENT,"
            + " LEVEL      TEXT       NOT NULL,"
            + " LEVEL_VALUE       INTEGER   NOT NULL)";
            
    
    private static final String createQuestionAnswerConsequenceTable = 
            "CREATE TABLE QAC (QACID INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " LEVELID         INTEGER         NOT NULL,"
            + " QUESTIONID      INTEGER         NOT NULL,"
            + " CONSEQUENCEID   INTEGER         NOT NULL,"
            + " ANSWERID        INTEGER         NOT NULL,"
            + " FOREIGN KEY(LEVELID) REFERENCES LEVEL(LEVELID),"
            + " FOREIGN KEY(QUESTIONID) REFERENCES QUESTION(QUESTIONID),"
            + " FOREIGN KEY(CONSEQUENCEID) REFERENCES CONSEQUENCE(CONSEQUENCEID),"
            + " FOREIGN KEY(ANSWERID) REFERENCES ANSWER(ANSWERID))";
            
    
    
    private static final String selectPlayerType = "SELECT TYPEID, DESCRIPTION "
            + "FROM PLAYERTYPE";
    
    private static final String insertPlayerType = "INSERT INTO PLAYERTYPE (DESCRIPTION) VALUES (?)";
    private static final String insertLevel = "INSERT INTO LEVEL (LEVEL, LEVEL_VALUE) VALUES (?,?)";
    private static final String insertPlayer = "INSERT INTO PLAYER (NAME, AGE, PLAYERTYPE, LEVELID, HEALTH, RESOURCES, GOLD) VALUES (?,?,?,?,?,?,?)";

    public static String getInsertPlayer() {
        return insertPlayer;
    }

    public static String getInsertLevel() {
        return insertLevel;
    }
    
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

    public static String getCreateLevelTable() {
        return createLevelTable;
    }

    public static String getInsertPlayerType() {
        return insertPlayerType;
    }

 

    public static String getCreateQuestionAnswerConsequenceTable() {
        return createQuestionAnswerConsequenceTable;
    }

    public static String getCreatePlayerTypeTable() {
        return createPlayerTypeTable;
    }

    public static String getSelectPlayerType() {
        return selectPlayerType;
    }
            
            
              
    
}
