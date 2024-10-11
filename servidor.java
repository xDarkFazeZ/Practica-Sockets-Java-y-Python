import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class Servidor {
    static final int PUERTO = 5000;

    public Servidor() {
        try (ServerSocket socketServidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor inicializado.");
            System.out.println("Escuchando en el puerto " + PUERTO);
            System.out.println("Esperando conexiones de clientes...");

            for (int numeroCliente = 1; numeroCliente <= 10; numeroCliente++) {
                try (Socket socketCliente = socketServidor.accept()) {
                    System.out.println("Sirviendo al cliente " + numeroCliente +
                                       " en el puerto de comunicación " + socketCliente.getPort());

                    // Recibir mensaje del cliente como bytes
                    DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
                    byte[] buffer = new byte[1024];
                    int bytesRead = flujoEntrada.read(buffer); // Leer los bytes enviados por el cliente
                    String mensaje = new String(buffer, 0, bytesRead); // Convertir los bytes en un string
                    System.out.println("Mensaje recibido del cliente " + numeroCliente + ": " + mensaje);

                    // Enviar respuesta al cliente
                    DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
                    flujoSalida.writeUTF("Bienvenido, cliente " + numeroCliente + 
                                         ".\nEl puerto de escucha es el número " + socketCliente.getLocalPort() + 
                                         " y el puerto de comunicación bidireccional es " + socketCliente.getPort());

                    System.out.println("Cliente " + numeroCliente + " servido.");
                } catch (IOException e) {
                    System.err.println("Error al comunicarse con el cliente " + numeroCliente + ": " + e.getMessage());
                }
            }

            System.out.println("Demasiados clientes. Solo aceptamos 3 clientes.");

        } catch (IOException e) {
            System.err.println("Error al inicializar el servidor: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
}
