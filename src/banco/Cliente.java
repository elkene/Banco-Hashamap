package banco;

import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class Cliente {
    // Usamos un HashMap para almacenar las cuentas del cliente.
    private final HashMap<String, Cuenta> cuentasMap = new HashMap<>();
    
    // Variables de instancia privadas para almacenar la información personal del cliente.
    private String nombre;
    private String curp;
    private String celular;
    
    // Método para agregar una cuenta al HashMap del cliente.
    public void agregarCuenta(Cuenta cuenta) {
        cuentasMap.put(cuenta.getNodeCuenta(), cuenta);  // Agrega la cuenta al HashMap.
    }

    // Método para obtener una cuenta por su número de cuenta usando lambda y stream.
    public Cuenta getCuenta(String nodoCuenta) {
        return cuentasMap.entrySet()
                         .stream()
                         .filter(entry -> entry.getKey().equals(nodoCuenta))
                         .map(Map.Entry::getValue)
                         .findFirst()
                         .orElse(null);  // Devuelve la cuenta o null si no existe en el HashMap.
    }

    // Método para eliminar una cuenta por su número de cuenta usando lambda y stream.
    public void eliminarCuenta(String nodoCuenta) {
        Optional.ofNullable(cuentasMap.remove(nodoCuenta))
                .ifPresentOrElse(
                    c -> JOptionPane.showMessageDialog(null, "Cuenta eliminada con éxito."),
                    () -> JOptionPane.showMessageDialog(null, "Cuenta no encontrada.")
                );
    }

    // Método para obtener el número de cuentas asociadas al cliente usando stream.
    public int getNumeroCuentas() {
        return (int) cuentasMap.values()
                               .stream()
                               .count();  // El tamaño del HashMap.
    }

    // Método para obtener el nombre del cliente.
    public String getNombre() {
        return nombre;
    }

    // Método para establecer el nombre del cliente.
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Método para obtener la CURP del cliente.
    public String getCurp() {
        return curp;
    }

    // Método para establecer la CURP del cliente.
    public void setCurp(String curp) {
        this.curp = curp;
    }

    // Método para obtener el número de celular del cliente.
    public String getCelular() {
        return celular;
    }

    // Método para establecer el número de celular del cliente.
    public void setCelular(String celular) {
        this.celular = celular;
    }

    // Método para obtener todas las cuentas del cliente usando stream.
    public Collection<Cuenta> getCuentas() {
        return cuentasMap.values()
                         .stream()
                         .collect(Collectors.toList());  // Devuelve las cuentas como una colección.
    }

    // Método para generar una representación en cadena del cliente y sus cuentas usando stream.
    public String toString() {
        String cuentasString = cuentasMap.values()
                                         .stream()
                                         .map(Cuenta::toString)
                                         .collect(Collectors.joining("\n"));
        
        return String.format("Cliente: %s | CURP: %s | Celular: %s\n%s", nombre, curp, celular, cuentasString);
    }
}
