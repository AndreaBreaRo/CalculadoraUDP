import java.io.IOException;
import java.net.*;


public class udpcli {

	public static void main(String[] args) throws IOException {
		
		if (args.length < 3) {
			System.out.println("Escribe las entradas en formato: <IP address> <port> <operation>");
			System.out.println("Escribe las entradas en formato: 1.1.1.1 255 5+3\n");
			return;
		}
		
		DatagramSocket socketUDP = new DatagramSocket();
		
		socketUDP.setSoTimeout(10000);
		
		InetAddress direccionServidor = InetAddress.getByName(args[0]); 
		final int PUERTO_SERVIDOR = Integer.parseInt(args[1]);
		
		String mensaje = args[2];
		
		byte[] buffer = mensaje.getBytes();

		DatagramPacket respuesta = new DatagramPacket(buffer, buffer.length, direccionServidor, PUERTO_SERVIDOR);
		System.out.println("Envio el datagrama");
		socketUDP.send(respuesta);
       
		try {

			byte[] bufferReceived = new byte[1024];
			DatagramPacket peticion = new DatagramPacket(bufferReceived, bufferReceived.length);
			socketUDP.receive(peticion);	
			System.out.println("Recibo mensaje de servidor");
			mensaje = new String(peticion.getData());
			System.out.println("Mensaje recibido: "+ mensaje);
			
		}catch (SocketTimeoutException e) {
            System.out.println("El tiempo de espera ha expirado.");
        }	
			
		socketUDP.close();
		System.out.println("Cierro conexion con servidor");	
						
		
	}
	
}
