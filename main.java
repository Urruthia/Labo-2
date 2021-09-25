/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 *
 * @author pedro
 */
public class main {
    private void EscrituraSecuencialTamano(String Nombre, byte Edad, int Telefono)
    {
        try {
            RandomAccessFile archivo = new RandomAccessFile("SecuenciaTamano.bin","w");
            archivo.seek(archivo.length());
            archivo.writeBytes(Nombre);
            archivo.writeByte(Edad);
            archivo.writeInt(Telefono);
            archivo.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No encontre el archivo");
        } catch (IOException ex) {
               System.out.println("Problemas con el archivo");
        } 
    }
    private void EscrituraSencuencialBinaria(String Nombre, byte Edad, int Telefono)
    {  
        try {
            RandomAccessFile archivo = new RandomAccessFile("Secuenciabinaria.bin","w");
            archivo.seek(archivo.length());
            archivo.writeUTF(Nombre);
            archivo.writeByte(Edad);
            archivo.writeInt(Telefono);
            archivo.writeByte('/');
            archivo.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No encontre el archivo");
        } catch (IOException ex) {
          System.out.println("Problemas con el archivo");
        } 
    }
    private void LecturaSecuenciaTamano()
    {
        try {
            RandomAccessFile archivo = new RandomAccessFile("SecuenciaTamano.bin","r");
             byte Edad;
            byte NombreTamano[] = new byte[30];
            int Telefono;
            while(archivo.read(NombreTamano) != -1){
                Edad = archivo.readByte();
                Telefono = archivo.readInt();
                
                System.out.println("Nombre: " + new String(NombreTamano));
                 System.out.println("Edad: " + Telefono);
                System.out.println("Telefono: " + Edad);
               
            }

            archivo.close();
        } catch (FileNotFoundException ex) {
           System.out.println("No encontre el archivo");
        } catch (IOException ex) {
      System.out.println("Problemas con el archivo");
        }
        
    }
    private void LecturaSecuenciaBinaria()
    {
        try {
            RandomAccessFile archivo = new RandomAccessFile("Secuenciabinaria.bin","r");
            
                 boolean Salida=false;
            byte[] separador = new byte[1];
            String Nombre;
            int Telefono;
            byte Edad;

            do {
                try
                {
              Nombre = archivo.readUTF();
              Edad = archivo.readByte();
              Telefono = archivo.readInt();
              archivo.read(separador);
                System.out.println( "Nombre: " + new String(Nombre));
                System.out.println("Edad: " + Edad);
                System.out.println("Telefono: " + Telefono);
                
                } catch (IOException ex) {
                   Salida = true;
                   }
            } while (Salida==false);
            
                
            
            archivo.close();
        } catch (FileNotFoundException ex) {
            System.out.println("No encontre el archivo");
        } catch (IOException ex) {
             System.out.println("Problemas con el archivo");
        }
        
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
     Scanner scanner = new Scanner(System.in);
    int op, Telefono;
    boolean entrada=true;
    String nombre,nombreAux;
    
    main main=new main();
        char[] Nombre = new char[30];
        byte Edad;
        
        for(int i=0;i < 30;i++){
           Nombre[i] = '0';
        }
        do{
      
            System.out.println("1)Ingresar datos");
            System.out.println("2)Ver datos de la agenda");
            System.out.println("3)Salir");
            op = scanner.nextInt();
            
            
            switch(op)
            {
                case 1:
            
                System.out.println("Ingrese el nombre ");
                scanner.nextLine();
              nombre = scanner.nextLine();
                  char[] NombreAux = new char[nombre.length()];
                for(int i=0;i<nombre.length();i++){
                    Nombre[i] = nombre.charAt(i);
                    NombreAux[i] = nombre.charAt(i);
                }
                nombreAux = String.valueOf(NombreAux);
                nombre = String.valueOf(Nombre);
                System.out.println("Ingrese la edad ");
             Edad = scanner.nextByte();
                System.out.println("Ingrese el Telefono");
                        Telefono = scanner.nextInt();
               main.EscrituraSencuencialBinaria(nombreAux, Edad, Telefono);
                main.EscrituraSecuencialTamano(nombre, Edad, Telefono);
            
                    break;
                    
                case 2:
                System.out.println("Lectura Por secuecian con separador Binario ");
   main.LecturaSecuenciaTamano();
                System.out.println("Lectura Con separador de tamaño");
   main.LecturaSecuenciaBinaria();
                    break;
                case 3:
                    entrada=false;
                    break;
                default:
                             System.out.println("Opcion no valida");
                            break;
            }       
    
        }while(entrada);
    }
}
