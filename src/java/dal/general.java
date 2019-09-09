/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import bdcon.executeSPreturn;
import bdcon.executeSPvoid;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author LEON
 */
public class general {
    
    private executeSPvoid execVoid = new executeSPvoid();
    private executeSPreturn execConsulta = new executeSPreturn();
    
    public void insertarUsuario(HashMap map) throws SQLException{
        
            execVoid.exec("sp_usuario_i(?,?,?,?)", map);
            execVoid.getCon().close();
    }
    
    public String consultarUsuarios(HashMap map) throws SQLException, JSONException{
        
        ResultSet rs =execConsulta.exec("sp_usuario_s(?)", map);
        
        
        JSONArray jsona = new JSONArray();
        while(rs.next()){
            JSONObject jsono = new JSONObject();
            jsono.put("idUsuario",rs.getString("idUsuario"));
            jsono.put("nombre",rs.getString("nombre"));
            jsono.put("apellidos",rs.getString("apellidos"));
            jsono.put("fechaNacimiento",rs.getString("fechaNacimiento"));
            jsona.put(jsono);
        }
        execConsulta.getCon().close();
        return jsona.toString();
        
    }
    
    public void actualizarUsuario(HashMap map) throws SQLException{
        
            execVoid.exec("sp_usuario_u(?,?,?,?)", map);
            execVoid.getCon().close();
    }
    
    public void borrarUsuario(HashMap map) throws SQLException{
        
            execVoid.exec("sp_usuario_d(?)", map);
            execVoid.getCon().close();
    }
    
}
