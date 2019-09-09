/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import bl.generalBR;
import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author LEON
 */
@Path("generic")
public class WsGeneral {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of wsGeneral
     */
    public WsGeneral() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {

        return "Hola";

    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("insertarUsuario/{nombre}/{apellidos}/{fNacimiento}")
    public String insertarUsuario(
            @PathParam("nombre") String nombre,
            @PathParam("apellidos") String apellidos,
            @PathParam("fNacimiento") String fNacimiento) {

        HashMap map = new HashMap();
        map.put("opcion", 1);
        map.put("nombre", nombre);
        map.put("apellidos", apellidos);
        map.put("fNacimiento", fNacimiento);

        try {

            generalBR gralBR = new generalBR();
            gralBR.insertarUsuario(map);

            return "1";

        } catch (Exception e) {

            return "-1";

        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultarUsuarios")
    public String consultarUsuarios() {

        HashMap map = new HashMap();
        map.put("opcion", 1);
        try {

            generalBR gralBR = new generalBR();

            return gralBR.consultarUsuarios(map);

        } catch (Exception e) {
            return "-1";
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("actualizarUsuarios/{id}/{nombre}/{apellidos}/{fNacimiento}")
    public String actualizarUsuarios(@PathParam("id") String idUsuario,
            @PathParam("nombre") String nombre,
            @PathParam("apellidos") String apellidos,
            @PathParam("fNacimiento") String fNacimiento
    ) {

        HashMap map = new HashMap();
     
        try {
            
            map.put("idUsuario", idUsuario);
            map.put("nombre", nombre);
            map.put("apellidos", apellidos);
            map.put("fNacimiento", fNacimiento);
            
            
            generalBR gralBR = new generalBR();            
            gralBR.actualizarUsuario(map);
            return "1";
        } catch (Exception e) {
            return "-1";
        }

    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("borrarUsuario/{id}")
    public String borrarUsuario(@PathParam("id") String idUsuario) {

        HashMap map = new HashMap();
        try {
            map.put("idUsuario", idUsuario);
            generalBR gralBR = new generalBR();
            
            gralBR.borrarUsuario(map);
            
            return "1";

        } catch (Exception e) {
            return "-1";
        }

    }

}
