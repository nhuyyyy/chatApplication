/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClientUI;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ADMIN
 */
public class ClientConnected implements Runnable{
    private Socket socket;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private String name;

    public ClientConnected(Socket socket, String name) throws IOException {
        this.socket = socket;
        this.name = name;
         this.out = new DataOutputStream(this.socket.getOutputStream());
        this.in = new DataInputStream(this.socket.getInputStream());
    }
    
    
    public Socket createConnect() throws IOException {
        ServerSocket svSocket = new ServerSocket(0);
        out.writeUTF(svSocket.getLocalSocketAddress().toString());
        System.out.println("Ä?ang chá»? client káº¿t ná»‘i");
        return svSocket.accept();
    }
    public Socket connect(String address) throws Exception{
        System.out.println(address);
        StringTokenizer st = new StringTokenizer(address,"/");
        st.nextToken();
        st = new StringTokenizer(st.nextToken(),":");
        String hot = st.nextToken();
        int port = Integer.parseInt(st.nextToken());
        System.out.println(hot + port);
        return socket = new Socket(hot, port);
    }
    @Override
    public void run() {
        try {
            while (true) {
                String read = in.readUTF();
                StringTokenizer st = new StringTokenizer(read, ";");
                Boolean waiter = (st.nextToken().equalsIgnoreCase("A")) ? true : false;
                String name = st.nextToken();
                System.out.println(name);
                int dialogButton = JOptionPane.YES_NO_OPTION;
                int dialogResult = JOptionPane.showConfirmDialog(null, "Would You Like to chat with " + name + " ?", "Find match", dialogButton);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    out.writeBoolean(true);
                    if (waiter) {
                        ChattingGUI chat = new ChattingGUI(createConnect(), name, this.name);
                        chat.setVisible(true);
                    } else {
                        String address = in.readUTF();
                        if (!address.equalsIgnoreCase("reject")) {
                            ChattingGUI chat = new ChattingGUI(connect(address), name, this.name);
                            chat.setVisible(true);
                        } else {
                            //thong bao tu choi ghep doi
                        }
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
