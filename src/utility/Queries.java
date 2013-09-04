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
            + " LEVEL       INT        NOT NULL)";

    public static String getCreatePlayerTable() {
        return createPlayerTable;
    }
            
            
              
    
}
