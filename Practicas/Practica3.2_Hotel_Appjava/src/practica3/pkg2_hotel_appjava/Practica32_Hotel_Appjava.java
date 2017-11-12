/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3.pkg2_hotel_appjava;

import java.util.Scanner;

/**
 *
 * @author Papilomavirus
 */
public class Practica32_Hotel_Appjava {

    /**
     * 
     * @param args the command line arguments
     */
    private static Scanner opcion;
    private static Scanner texto;
    public static void main(String[] args) {
       int id_hotel;
       int fecha;
       int var;
       boolean correcto = false;
       texto = new Scanner(System.in);
       opcion = new Scanner(System.in);
       
       do{
           System.out.println("Selecciona la operación a realizar:");
       System.out.println("1.Consultar habitaciónes libres.");
       System.out.println("2.Reservar habitación.");
       System.out.println("9.Salir.");
       var = opcion.nextInt();
        switch(var){
            case 1:
                correcto = false;
                
                while (!correcto){
                    System.out.println("Introduce el número de hotel valido:");
                    String aux_hotel = texto.nextLine();
                    if (!aux_hotel.isEmpty() && aux_hotel != null && isNumeric(aux_hotel)){
                          System.out.println("Introduce la fecha valida: <aaaammdd>");  
                          String aux_fecha = texto.nextLine();
                          int valor;
                          if(!aux_fecha.isEmpty() && aux_fecha != null && isNumeric(aux_fecha)){
                              id_hotel = Integer.parseInt(aux_hotel);
                              fecha = Integer.parseInt(aux_fecha);
                              valor = consultaLibres(id_hotel, fecha);
                              if(valor != -1 ){
                                  System.out.println("Hay "+valor+" habitaciones libres");
                                  
                              }
                              else  System.out.println("No hay habitaciones libres para este hotel / fecha.");
                              correcto = true;
                          }else {
                               System.out.println("Formato fecha incorrecto");  
                          }
                    }else {
                         System.out.println("Formato id incorrecto");  
                    }
                }
                break;
            case 2:
                correcto = false;
                while (!correcto){
                    System.out.println("Introduce el número de hotel valido:");
                    String aux_hotel = texto.nextLine();
                    if (!aux_hotel.isEmpty() && aux_hotel != null && isNumeric(aux_hotel)){
                          System.out.println("Introduce la fecha valida: <aaaammdd>");  
                          String aux_fecha = texto.nextLine();
                          int valor;
                          if(!aux_fecha.isEmpty() && aux_fecha != null && isNumeric(aux_fecha)){
                              id_hotel = Integer.parseInt(aux_hotel);
                              fecha = Integer.parseInt(aux_fecha);
                              valor = reservaHabitacion(id_hotel, fecha);
                              if(valor != -1 ){
                                  System.out.println("Has reservado plaza!!Ahora hay "+valor + " habitaciones ocupadas.");
                                  
                              }
                              else  System.out.println("No se ha podido reservar habitación en ese hotel/ fecha.");
                              correcto = true;
                          }else {
                               System.out.println("Formato fecha incorrecto");  
                          }
                    }else {
                         System.out.println("Formato id incorrecto");  
                    }
                }
                break;
            case 9:
                break;
            default:
                 System.out.println("Número incorrecto.");  
                break;
            
        }
       }while(var!= 0);
    }
       

    private static int consultaLibres(int idHotel, int fecha) {
        wshotel.WSHotel_Service service = new wshotel.WSHotel_Service();
        wshotel.WSHotel port = service.getWSHotelPort();
        return port.consultaLibres(idHotel, fecha);
    }

    private static int reservaHabitacion(int idHotel, int fecha) {
        wshotel.WSHotel_Service service = new wshotel.WSHotel_Service();
        wshotel.WSHotel port = service.getWSHotelPort();
        return port.reservaHabitacion(idHotel, fecha);
    }
  private static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
}
