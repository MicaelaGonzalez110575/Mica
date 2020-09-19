package Modelo;


public class Mascota {

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int IdMascota) {
        this.idMascota = IdMascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Especie getEspecie() {
        return especie;
    }

    public void setEspecie(Especie especie) {
        this.especie = especie;
    }

    public int getNac() {
        return nac;
    }

    public void setNac(int nac) {
        this.nac = nac;
    }
    private int idMascota;
private String nombre; 
private Cliente cliente;
private Especie especie;
private int nac;

@Override
public String toString(){
    return "Nombre: "+ nombre + " - Dueño: " + cliente.getNombre() + " - Especie: " + especie.getDenominacion() +
            " Año de Nacimiento: " + nac;
}
}
