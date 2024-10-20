package banco;
import java.util.Date;
import javax.swing.JOptionPane;


// Definición de la clase Main. Contiene el punto de entrada principal para la aplicación.
public class Main {
    public static void main(String[] args) {
        Main programa = new Main();
        programa.iniciarPrograma();
    }

    public void iniciarPrograma() {
        Cliente clienteEjemplo = new Cliente();
        clienteEjemplo.setNombre("Juan Perez");
        clienteEjemplo.setCurp("JUAP800101HDFRLL00");
        clienteEjemplo.setCelular("5551234567");

        Cuenta cuentaEjemplo = new Cuenta();
        cuentaEjemplo.setNodeCuenta("12345");
        cuentaEjemplo.setTipodeCuenta("Ahorro");
        cuentaEjemplo.setSaldo(5000);

        Cliente clienteEjemplo2 = new Cliente();
        clienteEjemplo2.setNombre("Kenneth Armenta");
        clienteEjemplo2.setCurp("AEIK030618HSLRBNA9");
        clienteEjemplo2.setCelular("6731148022");

        Cuenta cuentaEjemplo2 = new Cuenta();
        cuentaEjemplo2.setNodeCuenta("1426");
        cuentaEjemplo2.setTipodeCuenta("Ahorro");
        cuentaEjemplo2.setSaldo(100000);

        clienteEjemplo.agregarCuenta(cuentaEjemplo);
        clienteEjemplo2.agregarCuenta(cuentaEjemplo2);

        Banco banco = new Banco();
        banco.agregarCliente(clienteEjemplo);
        banco.agregarCliente(clienteEjemplo2);

        menu(banco);
    }

    private void menu(Banco banco) {
        int opcion = 0;
        while (opcion != 8) { 
            try {
                opcion = Integer.parseInt(JOptionPane.showInputDialog("MENU \n" +
                        "1. Crear Cliente \n" +
                        "2. Crear Cuenta para Cliente \n" +
                        "3. Hacer Movimiento \n" +
                        "4. Mostrar Clientes \n" +
                        "5. Mostrar Historial de Movimientos \n" +
                        "6. Eliminar Cliente \n" + // Nueva opción
                        "7. Eliminar Cuenta \n" +   // Nueva opción
                        "8. Salir \n" +
                        "Ingrese una opción:"));
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Por favor ingrese un número válido.");
                continue;
            }
    
            switch (opcion) {
                case 1:
                    crearCliente(banco);
                    break;
                case 2:
                    crearCuenta(banco);
                    break;
                case 3:
                    hacerMovimiento(banco);
                    break;
                case 4:
                    mostrarClientes(banco);
                    break;
                case 5:
                    mostrarHistorialMovimientos(banco);
                    break;
                case 6:
                    eliminarCliente(banco); // Método para eliminar cliente
                    break;
                case 7:
                    eliminarCuenta(banco); // Método para eliminar cuenta
                    break;
                case 8:
                    JOptionPane.showMessageDialog(null, "Saliendo del programa...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese una opción válida...");
                    break;
            }
        }
    }
    
    private void crearCliente(Banco banco) {
        try {
            Cliente cliente = new Cliente();
            cliente.setNombre(JOptionPane.showInputDialog("Ingrese el nombre del cliente:"));
            cliente.setCurp(JOptionPane.showInputDialog("Ingrese el CURP del cliente:"));
            cliente.setCelular(JOptionPane.showInputDialog("Ingrese el número de celular del cliente:"));
            banco.agregarCliente(cliente);
            JOptionPane.showMessageDialog(null, "Cliente creado con éxito");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear el cliente: " + e.getMessage());
        }
    }
    
    private void crearCuenta(Banco banco) {
        try {
            String clientes = obtenerListaClientes(banco);
            String nombreCliente = JOptionPane.showInputDialog("Seleccione un cliente para agregar una cuenta:\n" + clientes);
    
            Cliente cliente = banco.getCliente(nombreCliente);
    
            if (cliente != null) {
                Cuenta cuenta = new Cuenta();
                cuenta.setNodeCuenta(JOptionPane.showInputDialog("Ingrese el número de cuenta:"));
                cuenta.setTipodeCuenta(JOptionPane.showInputDialog("Ingrese el tipo de cuenta (Ahorro/Corriente):"));
                cliente.agregarCuenta(cuenta);
                JOptionPane.showMessageDialog(null, "Cuenta creada con éxito");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al crear la cuenta: " + e.getMessage());
        }
    }
    
    private void mostrarHistorialMovimientos(Banco banco) {
        try {
            String clientes = obtenerListaClientes(banco);
            String nombreCliente = JOptionPane.showInputDialog("Seleccione un cliente:\n" + clientes);
    
            Cliente cliente = banco.getCliente(nombreCliente);
    
            if (cliente != null) {
                String cuentas = obtenerListaCuentas(cliente);
                String nodoCuenta = JOptionPane.showInputDialog("Seleccione una cuenta:\n" + cuentas);
                Cuenta cuenta = cliente.getCuenta(nodoCuenta);
    
                if (cuenta != null) {
                    cuenta.mostrarHistorialMovimientos();
                } else {
                    JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar historial: " + e.getMessage());
        }
    }
    
    private void hacerMovimiento(Banco banco) {
        try {
            String clientes = obtenerListaClientes(banco);
            String nombreCliente = JOptionPane.showInputDialog("Seleccione un cliente:\n" + clientes);
            Cliente cliente = banco.getCliente(nombreCliente);
    
            if (cliente != null) {
                String cuentas = obtenerListaCuentas(cliente);
                String nodoCuenta = JOptionPane.showInputDialog("Seleccione una cuenta:\n" + cuentas);
                Cuenta cuenta = cliente.getCuenta(nodoCuenta);
    
                if (cuenta != null) {
                    int tipoMovimiento = Integer.parseInt(JOptionPane.showInputDialog("Seleccione tipo de movimiento: \n" +
                            "1. Transferencia \n" +
                            "2. Transacción \n" +
                            "3. Pago"));
    
                    Movimiento movimiento = null;
                    switch (tipoMovimiento) {
                        case 1:
                            Transferencia transferencia = new Transferencia();
                            transferencia.setDestino(JOptionPane.showInputDialog("Ingrese cuenta destino:"));
                            movimiento = transferencia;
                            break;
                        case 2:
                            Transaccion transaccion = new Transaccion();
                            transaccion.setTipo(JOptionPane.showInputDialog("Ingrese el tipo de transacción (Depósito/Retiro):"));
                            movimiento = transaccion;
                            break;
                        case 3:
                            Pago pago = new Pago();
                            pago.setServicios(JOptionPane.showInputDialog("Ingrese el servicio a pagar:"));
                            movimiento = pago;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opción inválida.");
                            return;
                    }
    
                    movimiento.setReferencia(JOptionPane.showInputDialog("Ingrese la referencia:"));
                    movimiento.setFecha(new Date());
                    movimiento.setMonto(Double.parseDouble(JOptionPane.showInputDialog("Ingrese el monto:")));
                    cuenta.agregarMovimiento(movimiento);
                    JOptionPane.showMessageDialog(null, "Movimiento realizado con éxito.");
                } else {
                    JOptionPane.showMessageDialog(null, "Cuenta no encontrada.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Por favor, ingrese un número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al realizar movimiento: " + e.getMessage());
        }
    }

    private void mostrarClientes(Banco banco) {
        StringBuilder sb = new StringBuilder();
        for (Cliente cliente : banco.getClientes()) {
            sb.append(cliente.toString()).append("\n");
        }
    
        if (sb.length() > 0) {
            JOptionPane.showMessageDialog(null, sb.toString());
        } else {
            JOptionPane.showMessageDialog(null, "No hay clientes registrados.");
        }
    }

    private void eliminarCliente(Banco banco) {
        String clientes = obtenerListaClientes(banco);

        String nombreCliente = JOptionPane.showInputDialog("Seleccione un cliente a eliminar:\n" + clientes);
        banco.eliminarCliente(nombreCliente);
    }

    private void eliminarCuenta(Banco banco) {
        StringBuilder listaClientes = new StringBuilder();
        for (Cliente cliente : banco.getClientes()) { // Suponiendo que el Banco tiene un método getClientes()
            listaClientes.append(cliente.getNombre()).append("\n"); // Añade el nombre de cada cliente
        }
    
        String nombreCliente = JOptionPane.showInputDialog("Seleccione un cliente:\n" + listaClientes.toString());
        Cliente cliente = banco.getCliente(nombreCliente); // Busca al cliente seleccionado
    
        if (cliente != null) {
            // Obtiene la lista de cuentas del cliente
            StringBuilder cuentas = new StringBuilder();
            for (Cuenta cuenta : cliente.getCuentas()) {
                cuentas.append(cuenta.getNodeCuenta()).append("\n"); // Construye la lista de cuentas
            }
    
            // Si hay cuentas, se le pide al usuario que elija una para eliminar
            if (cuentas.length() > 0) {
                String nodoCuenta = JOptionPane.showInputDialog("Seleccione el número de cuenta a eliminar:\n" + cuentas.toString());
                cliente.eliminarCuenta(nodoCuenta); // Elimina la cuenta
            } else {
                JOptionPane.showMessageDialog(null, "El cliente no tiene cuentas asociadas.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado."); // Cliente no existe
        }
    }
    private String obtenerListaClientes(Banco banco) {
        StringBuilder clientes = new StringBuilder();
        for (Cliente cliente : banco.getClientes()) {
            clientes.append(cliente.getNombre()).append("\n");
        }
        return clientes.toString();
    }
    
    // Método para obtener la lista de cuentas de un cliente
    private String obtenerListaCuentas(Cliente cliente) {
        StringBuilder cuentas = new StringBuilder();
        for (Cuenta cuenta : cliente.getCuentas()) {
            cuentas.append(cuenta.getNodeCuenta()).append("\n");
        }
        return cuentas.toString();
    }
}