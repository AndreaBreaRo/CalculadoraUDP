import java.net.*;

public class udpser {
	
	public static void main(String[] args) throws Exception {
		
		if(args.length != 2){
			System.out.println("El número de argumentos de entrada no es correcto:");
			System.out.println("udpser port_numer secreto");
			return;
		}
		
		//Creacion del socket del datagrama
		int port_numer = Integer.parseInt(args[0]);
		DatagramSocket socketUDP = new DatagramSocket(port_numer);
		
		byte[] buffer = new byte[1024];
		byte[] bufferSend = new byte[1024];
			
		System.out.println("Iniciado servidor UDP:");
			
		while(true){
			
			System.out.println("Esperando conexion con cliente...");
			
			//Recibe
			DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);	
			socketUDP.receive(peticion);
			
			System.out.println("Recibida informacion del cliente");
				
			String mensaje = new String(peticion.getData());
			
			InetAddress direccion = peticion.getAddress();
			int puertoCliente = peticion.getPort();
			
			
			//CALCULADORA
			
			String[] tokens = null;
			int result = 0;
			if(mensaje.contains("+") == true) {
				tokens = mensaje.split("\\+");
				int op1 = Integer.parseInt(tokens[0]);
				//elimina el retorno de carro
				int op2 = Integer.parseInt(tokens[1].trim().replaceAll("\\r", ""));
				result = (op1 + op2);
				System.out.println("La operacion es: " +op1 + "+" + op2 + "=" + result);
				
			}else if(mensaje.contains("-") == true) {
				tokens = mensaje.split("\\-");
				int op1 = Integer.parseInt(tokens[0]);
				//elimina el retorno de carro
				int op2 = Integer.parseInt(tokens[1].trim().replaceAll("\\r", ""));
				result = (op1 - op2);
				System.out.println("La operacion es: " +op1 + "-" + op2 + "=" + result);
				
			}else if(mensaje.contains("*") == true) {
				tokens = mensaje.split("\\*");
				int op1 = Integer.parseInt(tokens[0]);
				//elimina el retorno de carro
				int op2 = Integer.parseInt(tokens[1].trim().replaceAll("\\r", ""));
				result = (op1 * op2);
				System.out.println("La operacion es: " +op1 + "*" + op2 + "=" + result);
				
			}else if(mensaje.contains("/") == true) {
				tokens = mensaje.split("\\/");
				int op1 = Integer.parseInt(tokens[0]);
				//elimina el retorno de carro
				int op2 = Integer.parseInt(tokens[1].trim().replaceAll("\\r", ""));
				result = (op1 / op2);
				System.out.println("La operacion es: " +op1 + "/" + op2 + "=" + result);
				
			}else{
			  System.out.println("No hay ningun singno de operacion");
			  return;
			}
			
			//Numero secreto
			int secreto = Integer.parseInt(args[1]);
			System.out.println("El numero aleatorio es: " + secreto);
			result += secreto;
			System.out.println("La suma de resultado y número secreto es: " + result);
            
			//Envio
			bufferSend = String.valueOf(result).getBytes();
			
			
			DatagramPacket respuesta = new DatagramPacket(bufferSend, bufferSend.length, direccion, puertoCliente);
			System.out.println("Envio informacion al cliente");
			socketUDP.send(respuesta);
			
		}
              	
	}
        
     
}
