/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import dal.general;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;

/**
 *
 * @author LEON
 */
public class generalBR {

    
    private general gral = new general();
    
    public void insertarUsuario(HashMap map){
           
        try {
            gral.insertarUsuario(map);
        } catch (SQLException ex) {
            Logger.getLogger(generalBR.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public String consultarUsuarios(HashMap map) throws JSONException{
        
        try {
            return gral.consultarUsuarios(map);
        } catch (SQLException ex) {
            Logger.getLogger(generalBR.class.getName()).log(Level.SEVERE, null, ex);
            return "-1";
        }
    }
    
    public void actualizarUsuario(HashMap map) throws JSONException{
        
        try {
             gral.actualizarUsuario(map);
        } catch (SQLException ex) {
            Logger.getLogger(generalBR.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    public void borrarUsuario(HashMap map) throws JSONException{
        
        try {
             gral.borrarUsuario(map);
        } catch (SQLException ex) {
            Logger.getLogger(generalBR.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }
    
    
}
