package banco;


// Definición de la clase Transaccion que extiende (hereda de) la clase Movimiento.
public class Transaccion extends Movimiento {
    // Variable de instancia privada para almacenar el tipo de transacción (por ejemplo, Depósito o Retiro).
    private String tipo; // Depósito o Retiro

    // Método público para obtener el valor del tipo de transacción.
    public String getTipo() {
        return tipo;
    }

    // Método público para establecer un valor al tipo de transacción.
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Método sobrescrito de la clase Object para proporcionar una representación en cadena de la transacción.
    
    public String toString() {
        // Retorna una cadena que incluye el tipo de transacción, la referencia, el monto y la fecha.
        return "Transacción: " + tipo + " | Referencia: " + getReferencia() + " | Monto: " + getMonto() + " | Fecha: " + getFecha();
    }
}
