/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClientUI;

import java.awt.HeadlessException;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

public class Receive  extends javax.swing.JFrame  implements Runnable {
    private Socket socket;
    private String name;
	private javax.swing.JTextArea chatbox;
	private DataInputStream in = null;

    public Receive(Socket socket, String name, JTextArea chatbox) throws IOException {
        this.socket = socket;
        this.name = name;
        this.chatbox = chatbox;
        this.in = new DataInputStream(this.socket.getInputStream());
    }

    @Override
    public void run() {
        try{
               
               while(true){
                   String sms = in.readUTF();// ðõòi dýÞ liêòu server gýÒi vêÌ 
                   System.out.println(sms);
               }
           }
           catch(Exception e){
               try {
                   in.close();
                   this.socket.close();
               } catch (IOException ex) {
                   System.out.println("Ngãìt kêìt nôìi");
               }
           }
    }
    
}
