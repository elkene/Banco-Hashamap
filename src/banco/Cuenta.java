package banco;

import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class Cuenta {
    // Variables de instancia privadas
    private String nodeCuenta;
    private double saldo = 0.0;
    private String tipoDeCuenta;
    
    // HashMap para almacenar el historial de movimientos
    private HashMap<Integer, Movimiento> historialMovimientos = new HashMap<>();
    private int contadorMovimientos = 0; // Contador para asignar claves únicas a cada movimiento.

    // Método para obtener el número de cuenta
    public String getNodeCuenta() {
        return nodeCuenta;
    }

    // Método para establecer el número de cuenta
    public void setNodeCuenta(String nodeCuenta) {
        this.nodeCuenta = nodeCuenta;
    }

    // Método para obtener el saldo de la cuenta
    public double getSaldo() {
        return saldo;
    }

    // Método para establecer el saldo de la cuenta
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    // Método para obtener el tipo de cuenta
    public String getTipodeCuenta() {
        return tipoDeCuenta;
    }

    // Método para establecer el tipo de cuenta
    public void setTipodeCuenta(String tipoDeCuenta) {
        this.tipoDeCuenta = tipoDeCuenta;
    }

    // Método para agregar un movimiento al historial y actualizar el saldo usando lambda
    public void agregarMovimiento(Movimiento movimiento) {
        historialMovimientos.put(contadorMovimientos++, movimiento); // Agrega el movimiento al HashMap.
        saldo -= Optional.ofNullable(movimiento)
                         .map(Movimiento::getMonto)
                         .orElse(0.0);  // Actualiza el saldo basado en el monto del movimiento.
    }

    // Método para mostrar el historial de movimientos usando lambda y stream
    public void mostrarHistorialMovimientos() {
        String movimientosString = historialMovimientos.values()
                                                       .stream()
                                                       .map(Movimiento::toString)
                                                       .collect(Collectors.joining("\n"));

        if (!movimientosString.isEmpty()) {
            JOptionPane.showMessageDialog(null, movimientosString);
        } else {
            JOptionPane.showMessageDialog(null, "No hay movimientos aún");
        }
    }

    // Método para proporcionar una representación en cadena de la cuenta
    public String toString() {
        return String.format("Cuenta: %s | Tipo: %s | Saldo: %.2f", nodeCuenta, tipoDeCuenta, saldo);
    }
}
