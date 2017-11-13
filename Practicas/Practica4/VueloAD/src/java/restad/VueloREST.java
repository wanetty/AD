/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Papilomavirus
 */
@Path("vueloREST")
public class VueloREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VueloREST
     */
    public VueloREST() {
    }

    /**
     * Retrieves representation of an instance of restad.VueloREST
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getHtml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of VueloREST
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content) {
    }
        /**
 * Método POST para reservar una plaza dados un id y
 * una fecha
 * @param id_vuelo
 * @param fecha
 * @return
 */
 @Path("reserva_vuelo")
 @POST
 @Consumes("application/x-www-form-urlencoded")
 @Produces("text/html")
 public String reserva_vuelo (@FormParam("id_vuelo") String id_vuelo, @FormParam("fecha") String fecha) {
         Connection connection = null; 
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\Practica3.2\\DBpractica3.db");
            Statement st = connection.createStatement();
            st.setQueryTimeout(30);
             String wSql = "Select num_plazas_ocupadas,num_plazas_max from vuelo_fecha where id_vuelo = "+ id_vuelo +" and fecha = "+ fecha;
            ResultSet rs = st.executeQuery(wSql);
            int ocupadas, max;
            if(rs.next()){
                ocupadas = rs.getInt(1);
                max = rs.getInt(2);
                if(max - ocupadas > 0){
                ocupadas = ocupadas + 1;
                 st.executeUpdate("Update vuelo_fecha Set num_plazas_ocupadas ='" + ocupadas + "' where  id_vuelo ='" + id_vuelo + "'");
                return Integer.toString(ocupadas) + " plazas ocupadas.";
                }else{
                    return "-1";
                }
            }else{
                return "-1";
            }
           
        
    } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(VueloREST.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(VueloREST.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        }
         return "-1";
 }
        /* Método POST para consultar plazas de un vuelo segun id
        * una fecha
     *
     * @param id_vuelo
     * @param fecha
     * @return
     */

@Path("consulta_libres")
 @POST
 @Consumes("application/x-www-form-urlencoded")
 @Produces("text/html")
 public String consulta_libres (@FormParam("id_vuelo") String id_vuelo, @FormParam("fecha") String fecha) { 
 
    Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\Practica3.2\\DBpractica3.db");
            Statement st = connection.createStatement();
            st.setQueryTimeout(30);
           String wSql = "Select num_plazas_ocupadas,num_plazas_max from vuelo_fecha where id_vuelo = "+ id_vuelo +" and fecha = "+ fecha;
            ResultSet rs = st.executeQuery(wSql);
            int ocupadas, max;
            if(rs.next()){
                ocupadas = rs.getInt(1);
                max = rs.getInt(2);
                 return Integer.toString(max-ocupadas) + " plazas libres.";
            }else{
                return "-1";
            }
           
        
    } catch (SQLException | ClassNotFoundException ex) {
             return "-1";
            //Logger.getLogger(WSHotel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                 return "-1";
                //Logger.getLogger(WSHotel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        }
    }
}
