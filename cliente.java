import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yeli_
 */
public class cliente {

    public static void main(String[] args) {

        

        final String HOST = "192.168.217.204";
        final int PUERTO = 5000;
        DataInputStream in;
        DataOutputStream out;

        try {
            Socket sc = new Socket(HOST, PUERTO);
            
            in =new DataInputStream (sc.getInputStream());
            out =new DataOutputStream(sc.getOutputStream());
            
            out.writeUTF("Hola mundo desde el cliente en la laptop de Iram");
            
            String mensaje = in.readUTF();
                    
            System.out.println(mensaje);
            
            sc.close();
            
        } catch (IOException ex) {
            Logger.getLogger(cliente.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

}
