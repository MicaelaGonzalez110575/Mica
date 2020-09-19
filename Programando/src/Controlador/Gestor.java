
package Controlador;
import Modelo.Producto;
import Modelo.Venta;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Gestor {
    
    String CON= "jdbc:sqlserver://localhost;databaseName=Producto";
    String USER = "PabloR";
    String PASS = "Pablo123";
    Connection con = null;
    
   
    private void abrirConexion(){
        try{
            con = DriverManager.getConnection(CON,USER,PASS);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    private void cerrarConexion(){
        try{
            if (con!=null && !con.isClosed()){
                con.close();
            } 
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void agregarProducto(Producto p){
        
        try{
       abrirConexion();
       PreparedStatement pst = con.prepareStatement("Insert into Productos (nombre,precio) values (?,?)");
       pst.setString(1,p.getNombre());
       pst.setDouble(2, p.getPrecio());
               
       pst.executeUpdate();
       pst.close();
       
       JOptionPane.showMessageDialog(null,"Se guardó con exito!");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error!");
        }
        finally{
            cerrarConexion();
        }
       
    }
    
    public void agregarVenta(Venta v){
        try{
       abrirConexion();
       PreparedStatement pst = con.prepareStatement("Insert into Ventas (cliente,cantidad,idProducto) values (?,?,?)");
       pst.setString(1,v.getCliente());
       pst.setInt(2,v.getCantidad());
       pst.setInt(3,v.getProducto().getCodigo());
               
       pst.executeUpdate();
       pst.close();
       
       JOptionPane.showMessageDialog(null,"Se guardó con exito!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cerrarConexion();
        }
       
    }
    
  
    public ArrayList<Producto> mostrarProductos(){

    ArrayList<Producto> lista = new ArrayList<>();
        
        try{
       abrirConexion();
       Statement st = con.createStatement();
       ResultSet rt = st.executeQuery("Select * from Productos");
       
       while(rt.next()){
       double precio = rt.getDouble("precio");
       int codigo = rt.getInt("codigo");
       String nombre = rt.getString("nombre");
       
       Producto p = new Producto();
       p.setCodigo(codigo);
       p.setNombre(nombre);
       p.setPrecio(precio);
       
       lista.add(p);
       }
       st.close();
       
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        finally{
            cerrarConexion();
        }
       return lista;
    }
    
    
    public ArrayList<Venta> mostrarVentas(){

    ArrayList<Venta> lista = new ArrayList<>();
        
        try{
       abrirConexion();
       Statement st = con.createStatement();
       ResultSet rt = st.executeQuery("Select * from Ventas v join Productos p on v.idProducto=p.codigo");
       
       while(rt.next()){
       int id = rt.getInt("id");
       String cliente = rt.getString("cliente");
       double precio = rt.getDouble("precio");
       int cantidad = rt.getInt ("cantidad");
       int codigo = rt.getInt("codigo");
       String nombre = rt.getString("nombre");
       
       Producto p = new Producto();
       p.setCodigo(codigo);
       p.setNombre(nombre);
       p.setPrecio(precio);
       
       Venta v = new Venta();
       v.setId(id);
       v.setCliente(cliente);
       v.setCantidad(cantidad);
       v.setProducto(p);
       
       lista.add(v);
       }
       st.close();
       
        }
        catch(Exception e){
            e.printStackTrace();
            
        }
        finally{
            cerrarConexion();
        }
       return lista;
    }
      

     public void editarProducto(Producto p){
        
        try{
       abrirConexion();
       PreparedStatement pst = con.prepareStatement("Update Productos set nombre=?, precio=?");
       pst.setString(1,p.getNombre());
       pst.setDouble(2, p.getPrecio());
               
       pst.executeUpdate();
       pst.close();
       
       JOptionPane.showMessageDialog(null,"Se guardaron los cambios ok!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cerrarConexion();
        }
       
    }
public void borrarProducto(Producto p){
        
        try{
       abrirConexion();
       PreparedStatement pst = con.prepareStatement("DELETE FROM Productos WHERE codigo=?");
       pst.setInt(1,p.getCodigo());
             
       int row = pst.executeUpdate();
       
       System.out.println(row);
       
       pst.close();
       
       JOptionPane.showMessageDialog(null,"Se eliminó el Producto!");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            cerrarConexion();
        }
       
    }



}