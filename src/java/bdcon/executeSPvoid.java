/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bdcon;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

/**
 *
 * @author LEON
 */
public class executeSPvoid {
    
    //Variable global de tipo Connection con
    private Connection con = null;
    
    
   public void exec(String nsp, HashMap map) {
        CallableStatement cstmt = null;
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://DESKTOP-8RPU7VD:1433;databaseName=CDISPRUEBA;user=test;password=test";
            setCon(DriverManager.getConnection(connectionUrl));

            cstmt = getCon().prepareCall("{call " + nsp + "}");
            for (Object key : map.keySet()) {
                cstmt.setString(key.toString(), map.get(key).toString());
            }
            cstmt.execute();
            
        } catch (Exception e) {
            System.out.println("Excepcion:" + e.toString());
        }
    }

    /**
     * @return the con
     */
    public Connection getCon() {
        return con;
    }

   
    public void setCon(Connection con) {
        this.con = con;
    }

}
