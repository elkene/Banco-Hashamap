package banco;
// Definición de la clase Transferencia que extiende (hereda de) la clase Movimiento.
public class Transferencia extends Movimiento {
    // Variable de instancia privada para almacenar el destino de la transferencia.
    private String destino;

    // Método público para obtener el valor del destino.
    public String getDestino() {
        return destino;
    }

    // Método público para establecer un valor al destino.
    public void setDestino(String destino) {
        this.destino = destino;
    }

    // Método sobrescrito de la clase Object para proporcionar una representación en cadena de la transferencia.
    public String toString() {
        // Retorna una cadena que incluye el destino de la transferencia, la referencia, el monto y la fecha.
        return "Transferencia a: " + destino + " | Referencia: " + getReferencia() + " | Monto: " + getMonto() + " | Fecha: " + getFecha();
    }
}
