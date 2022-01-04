/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerHandler implements Runnable{

    private String name;
    private Socket socket;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public ServerHandler(Socket socket) {

        this.socket = socket;
        try {
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }
	public void run() {
        try {
            // TODO Auto-generated method stub

            checkClientName();
        } catch (IOException ex) {
            Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
    public void checkClientName() throws IOException {
       name = in.readUTF();
		Boolean con = false;
		
		while(Server.ListClient.containsKey(name)||Server.ListClientWait.containsKey(name)) {
			
			System.out.println("...............");
			out.writeBoolean(false);
			name = in.readUTF();
			System.out.println(name);
			if (name.equalsIgnoreCase("bye")) {
				System.out.println("Disconnect");
				socket.close();
				return;
			}
		}
		System.out.println(name+ socket.getPort()) ;
		Server.ListClient.put(name, socket);
		out.writeBoolean(true);
    }
    public String Accepted(Socket socket) throws IOException {
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		dos.writeUTF("A;"+name);
		if (dis.readBoolean()) {
			return dis.readUTF();
		}else {
			return null;
		}
		
		
	}
	public void ConnectwithC2C() throws IOException {
            boolean flag =false;
		for (Map.Entry<String, Socket> entry : Server.ListClientWait.entrySet()) {
            
            out.writeUTF("B;" + entry.getKey());
            boolean result = in.readBoolean();
            System.out.println(result);
            if (result) {
            	String accept = Accepted(entry.getValue());
            	if (accept!=null) {
            		flag = true;
            		out.writeUTF(accept);
            		break;
            	}else {
            		out.writeUTF("refuse !!");// týÌ chôìi 
            	}
            }
        }
		if (!flag) {
			System.out.println("Waiting...");
			Server.ListClientWait.put(name, socket);
			Server.ListClient.remove(name);
		}else {
			System.out.println("Connected ...");
		}
	}
    
}
