/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WSHotel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Papilomavirus
 */
@WebService(serviceName = "WSHotel")
public class WSHotel {

    /**
     * Web service operation
     * @param id_hotel
     * @param fecha
     * @return 
     */
    @WebMethod(operationName = "consulta_libres")
    public int consulta_libres(@WebParam(name = "id_hotel") int id_hotel, @WebParam(name = "fecha") int fecha) {
          Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            // create a database connection
            if (id_hotel <= 0  || fecha <= 0)return -1;
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\Practica3.2\\DBpractica3.db");
            Statement st = connection.createStatement();
            st.setQueryTimeout(30);
            String wSql = "Select num_hab_ocupadas,num_hab_max from hotel_fecha where id_hotel = "+ id_hotel +" and fecha = "+ fecha;
            ResultSet rs = st.executeQuery(wSql);
            int ocupadas, max;
            if(rs.next()){
                ocupadas = rs.getInt(1);
                max = rs.getInt(2);
                 return max-ocupadas;
            }else{
                return -1;
            }
           
        
    } catch (SQLException | ClassNotFoundException ex) {
            return -1;
            //Logger.getLogger(WSHotel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                return -1;
                //Logger.getLogger(WSHotel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        }
    }

    /**
     * Web service operation
     * @param id_hotel
     * @param fecha
     * @return 
     */
    @WebMethod(operationName = "reserva_habitacion")
    public int reserva_habitacion(@WebParam(name = "id_hotel") int id_hotel, @WebParam(name = "fecha") int fecha) {
           Connection connection = null; 
        try {
            Class.forName("org.sqlite.JDBC");
            if (id_hotel <= 0  || fecha <= 0)return -1;
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\WORKSPACE\\Universidad\\AD\\Practicas\\Practica3.2\\DBpractica3.db");
            Statement st = connection.createStatement();
            st.setQueryTimeout(30);
            String wSql = "Select num_hab_ocupadas,num_hab_max from hotel_fecha where id_hotel = "+ id_hotel +" and fecha = "+ fecha;
            ResultSet rs = st.executeQuery(wSql);
            int ocupadas, max;
            if(rs.next()){
                ocupadas = rs.getInt(1);
                max = rs.getInt(2);
                if(max - ocupadas > 0) ocupadas = ocupadas + 1;
                st.executeUpdate("Update hotel_fecha Set num_hab_ocupadas ='" + ocupadas + "' where  id_hotel ='" + id_hotel + "'");
                return ocupadas;
            }else{
                return -1;
            }
           
        
    } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(WSHotel.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(WSHotel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
           
        }
        return -1;
    }
}

