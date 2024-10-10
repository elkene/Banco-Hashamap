package banco;

import java.util.Collection;
import java.util.HashMap;
import javax.swing.JOptionPane;

// Definición de la clase Cliente.
public class Cliente {
    // Usamos un HashMap para almacenar las cuentas del cliente.
    // La clave es el número de cuenta (nodoCuenta), y el valor es el objeto Cuenta correspondiente.
    private final HashMap<String, Cuenta> cuentasMap = new HashMap<>(); 
    
    // Variables de instancia privadas para almacenar la información personal del cliente.
    private String nombre;
    private String curp;
    private String celular;
    
    // Método público para agregar una cuenta al HashMap del cliente.
    // En lugar de agregar la cuenta a una lista, la agregamos al HashMap usando el nodo de cuenta como clave.
    public void agregarCuenta(Cuenta cuenta) {
        cuentasMap.put(cuenta.getNodeCuenta(), cuenta);  // Agrega la cuenta al HashMap.
    }

    // Método público para obtener una cuenta por su número de cuenta (nodoCuenta).
    // Usamos el método `get` del HashMap, que busca la cuenta directamente con la clave (nodoCuenta).
    public Cuenta getCuenta(String nodoCuenta) {
        return cuentasMap.get(nodoCuenta);  // Devuelve la cuenta o null si no existe en el HashMap.
    }

    // Método público para eliminar una cuenta por su número de cuenta (nodoCuenta).
    public void eliminarCuenta(String nodoCuenta) {
        if (cuentasMap.containsKey(nodoCuenta)) {  // Verificamos si el HashMap contiene la cuenta.
            cuentasMap.remove(nodoCuenta);  // Eliminamos la cuenta del HashMap.
            JOptionPane.showMessageDialog(null, "Cuenta eliminada con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
        }
    }

    // Método público para obtener el número de cuentas asociadas al cliente.
    // El tamaño del HashMap nos indica cuántas cuentas hay.
    public int getNumeroCuentas() {
        return cuentasMap.size();
    }

    // Método público para obtener el nombre del cliente.
    public String getNombre() {
        return nombre;
    }

    // Método público para establecer un valor al nombre del cliente.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método público para obtener la CURP del cliente.
    public String getCurp() {
        return curp;
    }

    // Método público para establecer un valor a la CURP del cliente.
    public void setCurp(String curp) {
        this.curp = curp;
    }

    // Método público para obtener el número de celular del cliente.
    public String getCelular() {
        return celular;
    }

    // Método público para establecer un valor al número de celular del cliente.
    public void setCelular(String celular) {
        this.celular = celular;
    }
  public Collection<Cuenta> getCuentas() {
        return cuentasMap.values(); // Devuelve las cuentas como una colección.
    }
    // Método público para proporcionar una representación en cadena del cliente y sus cuentas asociadas.
    // Recorremos el HashMap de cuentas para construir la cadena de texto con la información de las cuentas.
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(nombre).append(" | CURP: ").append(curp).append(" | Celular: ").append(celular).append("\n");
        
        // Iteramos sobre las cuentas almacenadas en el HashMap para agregarlas a la cadena.
        for (Cuenta cuenta : cuentasMap.values()) {
            sb.append(cuenta.toString()).append("\n");
        }
        return sb.toString();
    }
}