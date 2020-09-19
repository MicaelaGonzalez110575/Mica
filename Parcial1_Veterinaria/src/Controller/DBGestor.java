package Controller;

import Modelo.Cliente;
import Modelo.Especie;
import Modelo.Mascota;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class DBGestor {
    
    String CON= "jdbc:sqlserver://localhost;databaseName=Veterinaria";
    String USER = "PabloR";
    String PASS = "Pablo2020";
    Connection con = null;
    
   private void abrirConecion(){
       try{
       con= DriverManager.getConnection(CON,USER,PASS);
   }
       catch(SQLException exc){
           exc.printStackTrace();
       }
   }
   private void cerrarConexion(){
       try {
           if(con!= null && !con.isClosed()){
               con.close();
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   
   public void agregarMascota(Mascota m){
       try{
       abrirConecion();
       PreparedStatement pst = con.prepareStatement("Insert into Mascotas (nombre,idCliente,idEspecie,nacimiento) values (?,?,?,?)");
       pst.setString(1, m.getNombre());
       pst.setInt(2, m.getCliente().getIdCliente());
       pst.setInt(3, m.getEspecie().getIdEspecie());
       pst.setInt(4, m.getNac());
       
      
       pst.executeUpdate();
       pst.close();
           JOptionPane.showMessageDialog(null, "Se guardo la mascota correctamente");
   }
    catch(Exception exc){
        //JOptionPane.showMessageDialog(null, "Error al guardar");
        exc.printStackTrace();
    }   
    finally{
           cerrarConexion();
       }
   }
   public void agregarCliente(Cliente c){
       try{
       abrirConecion();
       PreparedStatement pst = con.prepareStatement("Insert into Clientes (idCliente,nombre,telefono) values (?,?,?)");
       pst.setString(2, c.getNombre());
       pst.setString(3, c.getTelefono());
       pst.setInt(1, c.getIdCliente());
       
       pst.executeUpdate();
       
           JOptionPane.showMessageDialog(null, "Se guardo el Cliente correctamente!");
           pst.close();
   }
    catch(Exception exc){
        //JOptionPane.showMessageDialog(null, "Error al guardar");
        exc.printStackTrace();
    }   
    finally{
           cerrarConexion();
       }
   }
    public void agregarEspecie(Especie e){
       try{
       abrirConecion();
       PreparedStatement pst = con.prepareStatement("Insert into Especies (idEspecie,denominacion) values (?,?)");
       pst.setInt(1, e.getIdEspecie());
       pst.setString(2, e.getDenominacion());
      
       
       
       pst.executeUpdate();
       
           JOptionPane.showMessageDialog(null, "Se guardo la Especie correctamente!");
           pst.close();
   }
    catch(Exception exc){
        //JOptionPane.showMessageDialog(null, "Error al guardar");
        exc.printStackTrace();
    }   
    finally{
           cerrarConexion();
       }
   }
   public ArrayList<Mascota> obtenerMascota(){
       ArrayList<Mascota> lista = new ArrayList<Mascota>();
       try{
           abrirConecion();
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("Select m.nombre 'Mascota',c.Nombre 'Dueño',c.Telefono, e.Denominacion 'Especie',m.Nacimiento from Mascotas m join Clientes c on m.idCliente=c.idCliente join Especies e on e.idEspecie=m.idEspecie order by c.Nombre");
           
           while (rs.next()){
               //int idEspecie= rs.getInt("IdEspecie");
               String denominacion = rs.getString("Especie");
               Especie e = new Especie();
              //e.setIdEspecie(idEspecie);
               e.setDenominacion(denominacion);
               
               //int idCliente= rs.getInt("idCliente");
               String nombreC = rs.getString("Dueño");
              String telefono= rs.getString("telefono");
               Cliente c = new Cliente();
               //c.setIdCliente(idCliente);
               c.setNombre(nombreC);
               c.setTelefono(telefono);
               
               String mascota= rs.getString("Mascota");
               int nac = rs.getInt("nacimiento");
               Mascota m = new Mascota();
               m.setNombre(mascota);
               m.setCliente(c);
               m.setEspecie(e);
               m.setNac(nac);
               
               lista.add(m);
           }
           rs.close();
       }
       catch(Exception e){
           e.printStackTrace();
       }
       finally{
           cerrarConexion();
       }
       return lista;
   }
   
   public ArrayList<Cliente> obtenerCliente(){
       ArrayList<Cliente> lista = new ArrayList<Cliente>();
       try{
           abrirConecion();
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("Select * from Clientes");
           
           while (rs.next()){
               int idCliente = rs.getInt("idCliente");
               String nombre = rs.getString("nombre");
              String telefono= rs.getString("telefono");
              
               Cliente c = new Cliente();
               c.setIdCliente(idCliente);
               c.setNombre(nombre);
               c.setTelefono(telefono);
               
               lista.add(c);
           }
           rs.close();
       }
       catch(Exception e){
           e.printStackTrace();
       }
       finally{
           cerrarConexion();
       }
       return lista;
   }
   
   public ArrayList<Especie> obtenerEspecie(){
       ArrayList<Especie> lista = new ArrayList<Especie>();
       try{
           abrirConecion();
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("Select * from Especies");
           
           while (rs.next()){
               int idEspecie = rs.getInt("idEspecie");
               String denominacion = rs.getString("denominacion");
              
              
               Especie e = new Especie();
               e.setIdEspecie(idEspecie);
               e.setDenominacion(denominacion);
               
               
               lista.add(e);
           }
           rs.close();
       }
       catch(Exception e){
           e.printStackTrace();
       }
       finally{
           cerrarConexion();
       }
       return lista;
   }
   
   
   
   public ArrayList<Mascota> obtenerPerros(){
       ArrayList<Mascota> lista = new ArrayList<Mascota>();
       try{
           abrirConecion();
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("Select * from Mascotas m join Especies e on e.idEspecie=m.idEspecie where e.denominacion like  'perro%'");
           
           while (rs.next()){
               String nombre= rs.getString("nombre");
               String denominacion = rs.getString("denominacion");
               Especie e = new Especie();
               e.setDenominacion(denominacion);
               
              Mascota m = new Mascota();   
               m.setNombre(nombre);
               m.setEspecie(e);
               lista.add(m);
           }
           rs.close();
       }
       catch(Exception e){
           e.printStackTrace();
       }
       finally{
           cerrarConexion();
       }
       return lista;
   }
   
   
   public int calcularPromedioEdades(){
       int promedio=0;
       try{
           abrirConecion();
           Statement st = con.createStatement();
           ResultSet rs = st.executeQuery("select AVG(year(getdate())-nacimiento) as 'Promedio' from Mascotas");
           
           if (rs.next()){
               promedio = rs.getInt("Promedio");
               
           }
           rs.close();
       }
       catch(Exception e){
           e.printStackTrace();
       }
       finally{
           cerrarConexion();
       }
       return promedio;
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
}
