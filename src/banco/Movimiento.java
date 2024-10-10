package banco;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
// Definición de la clase abstracta Movimiento. Una clase abstracta no se puede instanciar directamente y 
//puede contener métodos abstractos que deben ser implementados por las subclases.
public abstract class Movimiento {
    // Variable de instancia privada para almacenar una referencia única para el movimiento.
    private String referencia;
    
    // Variable de instancia privada para almacenar el monto del movimiento.
    private double monto;
    
    // Variable de instancia privada para almacenar la fecha del movimiento.
    private Date fecha;

    // Un mapa para almacenar metadatos adicionales sobre el movimiento.
    private Map<String, String> metadatos = new HashMap<>();
    
    // Método público para obtener el valor de la referencia.
    public String getReferencia() {
        return referencia;
    }

    // Método público para establecer un valor a la referencia.
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    // Método público para obtener el valor del monto.
    public double getMonto() {
        return monto;
    }

    // Método público para establecer un valor al monto.
    public void setMonto(double monto) {
        this.monto = monto;
    }

    // Método público para obtener la fecha del movimiento.
    public Date getFecha() {
        return fecha;
    }

    // Método público para establecer una fecha al movimiento.
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    // Método público para agregar un metadato al movimiento.
    public void agregarMetadato(String clave, String valor) {
        metadatos.put(clave, valor); // Agrega un par clave-valor al mapa de metadatos
    }

    // Método público para obtener el valor de un metadato.
    public String getMetadato(String clave) {
        return metadatos.get(clave); // Obtiene el valor del metadato dado una clave
    }

    // Método público para eliminar un metadato.
    public void eliminarMetadato(String clave) {
        metadatos.remove(clave); // Elimina un metadato dado una clave
    }

    // Método público para obtener todos los metadatos.
    public Map<String, String> obtenerMetadatos() {
        return metadatos; // Devuelve el mapa completo de metadatos
    }

    // Método abstracto que debe ser implementado por las subclases.
    public abstract String toString();
}
