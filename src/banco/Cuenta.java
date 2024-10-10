package banco;

// Definición de la clase Cuenta.
import java.util.HashMap;
import javax.swing.JOptionPane;

public class Cuenta {
    // Variable de instancia privada para almacenar el número de cuenta.
    private String nodeCuenta;
    
    // Variable de instancia privada para almacenar el saldo de la cuenta.
    private double saldo = 0.0;
    
    // Variable de instancia privada para almacenar el tipo de cuenta.
    private String tipoDeCuenta;
    
    // Usamos un HashMap para almacenar el historial de movimientos.
    // La clave será un identificador único del movimiento (puede ser un número o ID de transacción).
    private HashMap<Integer, Movimiento> historialMovimientos = new HashMap<>();
    private int contadorMovimientos = 0; // Contador para asignar claves únicas a cada movimiento.
    
    // Método público para obtener el valor del número de cuenta.
    public String getNodeCuenta() {
        return nodeCuenta;
    }

    // Método público para establecer un valor al número de cuenta.
    public void setNodeCuenta(String nodeCuenta) {
        this.nodeCuenta = nodeCuenta;
    }

    // Método público para obtener el saldo de la cuenta.
    public double getSaldo() {
        return saldo;
    }

    // Método público para establecer un valor al saldo de la cuenta.
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método público para obtener el tipo de cuenta.
    public String getTipodeCuenta() {
        return tipoDeCuenta;
    }

    // Método público para establecer un valor al tipo de cuenta.
    public void setTipodeCuenta(String tipoDeCuenta) {
        this.tipoDeCuenta = tipoDeCuenta;
    }

    // Método público para agregar un movimiento al historial. Actualiza el saldo.
    public void agregarMovimiento(Movimiento movimiento) {
        historialMovimientos.put(contadorMovimientos++, movimiento); // Agrega el movimiento al HashMap.
        saldo -= movimiento.getMonto(); // Actualiza el saldo basado en el monto del movimiento.
    }

    // Método público para mostrar el historial de movimientos.
    public void mostrarHistorialMovimientos() {
        StringBuilder sb = new StringBuilder();
    
        // Iteramos sobre los movimientos almacenados en el HashMap.
        historialMovimientos.values().forEach(movimiento -> sb.append(movimiento.toString()).append("\n"));
    
        if (!historialMovimientos.isEmpty()) {
            JOptionPane.showMessageDialog(null, sb.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No hay movimientos aún");
        }
    }
    
    // Método público para proporcionar una representación en cadena de la cuenta.
    public String toString() {
        return "Cuenta: " + nodeCuenta + " | Tipo: " + tipoDeCuenta + " | Saldo: " + saldo;
    }
}