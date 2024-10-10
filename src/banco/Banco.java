package banco;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

// Clase Banco
public class Banco {
    // Variable de instancia privada para almacenar un mapa de clientes.
    private final Map<String, Cliente> mapaDeClientes = new HashMap<>();

    // Método para agregar un cliente al banco.
    public void agregarCliente(Cliente cliente) {
        mapaDeClientes.put(cliente.getNombre(), cliente);  // El nombre del cliente es la clave
    }

    // Método para obtener un cliente por su nombre.
    public Cliente getCliente(String nombre) {
        return mapaDeClientes.get(nombre); // Devuelve el cliente asociado con el nombre o null si no existe.
    }

    // Método para eliminar un cliente por nombre.
    public void eliminarCliente(String nombre) {
        Cliente clienteEliminado = mapaDeClientes.remove(nombre); // Elimina el cliente asociado con el nombre
        
        if (clienteEliminado != null) {
            JOptionPane.showMessageDialog(null, "Cliente eliminado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
        }
    }

    // Método para obtener el número de clientes registrados.
    public int getNumeroClientes() {
        return mapaDeClientes.size();  // El tamaño del mapa se obtiene directamente
    }
    
    // Método para obtener todos los clientes del mapa
    public Collection<Cliente> getClientes() {
        return mapaDeClientes.values();  // Devuelve la colección de clientes.
    }
}