/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica3.pkg3_vuelo_appjava;

import java.util.Scanner;

/**
 *
 * @author Micky
 */
public class Practica33_Vuelo_AppJava {
    private static Scanner texto;
    private static Scanner opcion;
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       int id_vuelo;
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
                 System.out.println("Introduce el número de vuelo valido:");
                  String aux_vuelo = texto.nextLine();
                    if (!aux_vuelo.isEmpty() && aux_vuelo!=null && isNumeric(aux_vuelo)){
                        id_vuelo = Integer.parseInt(aux_vuelo);
                          System.out.println("Introduce la fecha valida: <aaaammdd>");  
                          String aux_fecha = texto.nextLine();
                          int valor;
                          
                          if(!aux_vuelo.isEmpty() && aux_vuelo!=null && isNumeric(aux_vuelo)){
                              fecha = Integer.parseInt(aux_fecha);
                              valor = consultaLibres(id_vuelo, fecha);
                              if(valor != -1 ){
                                  System.out.println("Hay "+valor+" plazas libres");
                                  
                              }
                              else  System.out.println("No hay plazas libres para este vuelo / fecha.");
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
                 System.out.println("Introduce el número de vuelo valido:");
                  String aux_vuelo = texto.nextLine();
                    if (!aux_vuelo.isEmpty() && aux_vuelo!=null && isNumeric(aux_vuelo)){
                        id_vuelo = Integer.parseInt(aux_vuelo);
                          System.out.println("Introduce la fecha valida: <aaaammdd>");  
                          String aux_fecha = texto.nextLine();
                          int valor;
                          
                          if(!aux_vuelo.isEmpty() && aux_vuelo!=null && isNumeric(aux_vuelo)){
                              fecha = Integer.parseInt(aux_fecha);
                              valor = reservaPlaza(id_vuelo, fecha);
                              if(valor != -1 ){
                                  System.out.println("Has reservado plaza!!Ahora hay "+valor + " plazas ocupadas.");
                                  
                              }
                              else  System.out.println("No se ha podido reservar habitación en ese vuelo/ fecha.");
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
                System.exit(0);
                break;
            default:
                 System.out.println("Número incorrecto.");  
                break;
            
        }
       }while(var!= 0);
    
    }

    private static int consultaLibres(int idVuelo, int fecha) {
        practica3.pkg3_vuelo_appjava.WSVuelo_Service service = new practica3.pkg3_vuelo_appjava.WSVuelo_Service();
        practica3.pkg3_vuelo_appjava.WSVuelo port = service.getWSVueloPort();
        return port.consultaLibres(idVuelo, fecha);
    }

    private static int reservaPlaza(int idVuelo, int fecha) {
        practica3.pkg3_vuelo_appjava.WSVuelo_Service service = new practica3.pkg3_vuelo_appjava.WSVuelo_Service();
        practica3.pkg3_vuelo_appjava.WSVuelo port = service.getWSVueloPort();
        return port.reservaPlaza(idVuelo, fecha);
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
