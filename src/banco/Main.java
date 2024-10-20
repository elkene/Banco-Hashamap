package banco;

import java.util.Date;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;

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
                        "6. Eliminar Cliente \n" +
                        "7. Eliminar Cuenta \n" +
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
                    eliminarCliente(banco);
                    break;
                case 7:
                    eliminarCuenta(banco);
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
        // Usando streams y lambdas para crear una lista de clientes
        String clientes = banco.getClientes()
                .stream() // Inicia un flujo de clientes
                .map(Cliente::toString) // Convierte cada cliente a su representación en cadena
                .collect(Collectors.joining("\n")); // Une las cadenas en una sola, separadas por saltos de línea

        if (!clientes.isEmpty()) {
            JOptionPane.showMessageDialog(null, clientes);
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
        // Usando streams y lambdas para crear una lista de nombres de clientes
        String clientes = banco.getClientes()
                .stream() // Inicia un flujo de clientes
                .map(Cliente::getNombre) // Extrae el nombre de cada cliente
                .collect(Collectors.joining("\n")); // Une los nombres en una sola cadena

        String nombreCliente = JOptionPane.showInputDialog("Seleccione un cliente:\n" + clientes);
        Cliente cliente = banco.getCliente(nombreCliente);

        if (cliente != null) {
            // Usando streams y lambdas para crear una lista de números de cuentas
            String cuentas = cliente.getCuentas()
                    .stream() // Inicia un flujo de cuentas
                    .map(Cuenta::getNodeCuenta) // Extrae el número de cuenta de cada cuenta
                    .collect(Collectors.joining("\n")); // Une los números de cuenta en una sola cadena

            if (!cuentas.isEmpty()) {
                String nodoCuenta = JOptionPane.showInputDialog("Seleccione el número de cuenta a eliminar:\n" + cuentas);
                cliente.eliminarCuenta(nodoCuenta);
            } else {
                JOptionPane.showMessageDialog(null, "El cliente no tiene cuentas asociadas.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
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
        // Usando streams y lambdas para crear una lista de números de cuentas
        return cliente.getCuentas()
                .stream() // Inicia un flujo de cuentas
                .map(Cuenta::getNodeCuenta) // Extrae el número de cuenta de cada cuenta
                .collect(Collectors.joining("\n")); // Une los números de cuenta en una sola cadena
    }
}
