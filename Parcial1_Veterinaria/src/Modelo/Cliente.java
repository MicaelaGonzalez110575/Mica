
package Modelo;


public class Cliente {

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.idCliente = IdCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
private int idCliente;
private String nombre;
private String Telefono;

@Override
public String toString(){
    return nombre + " - Tel: " + Telefono;
}
}
