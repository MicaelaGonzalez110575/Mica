
package Modelo;

public class Especie {

    public int getIdEspecie() {
        return idEspecie;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }
    private int idEspecie;
 private String denominacion;
 
 @Override
 public String toString(){
     return denominacion;
 }
}
