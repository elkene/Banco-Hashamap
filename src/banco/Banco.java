package banco;
import java.util.*;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

public class Banco {
    // Variable de instancia privada para almacenar un mapa de clientes.
    private final Map<String, Cliente> mapaDeClientes = new HashMap<>();

    // Método para agregar un cliente al banco.
    public void agregarCliente(Cliente cliente) {
        mapaDeClientes.put(cliente.getNombre(), cliente);  // El nombre del cliente es la clave
    }

    // Método para obtener un cliente por su nombre usando lambda y stream.
    public Cliente getCliente(String nombre) {
        return mapaDeClientes.entrySet()
                             .stream()
                             .filter(entry -> entry.getKey().equals(nombre))
                             .map(Map.Entry::getValue)
                             .findFirst()
                             .orElse(null);  // Devuelve el cliente asociado con el nombre o null si no existe.
    }

    // Método para eliminar un cliente por nombre usando lambda y stream.
    public void eliminarCliente(String nombre) {
        Cliente clienteEliminado = Optional.ofNullable(mapaDeClientes.remove(nombre))
                                           .orElse(null);  // Elimina el cliente asociado con el nombre o null si no existe

        Optional.ofNullable(clienteEliminado)
                .ifPresentOrElse(
                    c -> JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito."),
                    () -> JOptionPane.showMessageDialog(null, "Cliente no encontrado.")
                );
    }

    // Método para obtener el número de clientes registrados usando stream.
    public int getNumeroClientes() {
        return (int) mapaDeClientes.values()
                                   .stream()
                                   .count();  // El tamaño del mapa se obtiene directamente con un stream
    }
    
    // Método para obtener todos los clientes del mapa usando stream.
    public Collection<Cliente> getClientes() {
        return mapaDeClientes.values()
                             .stream()
                             .collect(Collectors.toList());  // Devuelve la colección de clientes usando stream.
    }
}
