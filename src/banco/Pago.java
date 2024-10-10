package banco;

// Definición de la clase Pago que extiende (hereda de) la clase Movimiento.
public class Pago extends Movimiento {
    // Variable de instancia privada para almacenar la descripción de los servicios pagados.
    private String servicios;

    // Método público para obtener el valor de los servicios.
    public String getServicios() {
        return servicios;
    }

    // Método público para establecer un valor a los servicios.
    public void setServicios(String servicios) {
        this.servicios = servicios;
    }

    // Método sobrescrito de la clase Object para proporcionar una representación en cadena del pago.
    public String toString() {
        // Retorna una cadena que incluye la descripción de los servicios, la referencia, el monto y la fecha.
        return "Pago de: " + servicios + " | Referencia: " + getReferencia() + " | Monto: " + getMonto() + " | Fecha: " + getFecha();
    }
}
