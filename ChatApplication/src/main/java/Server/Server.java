/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    
    private int port = 15797;
    private String name;
    private static ExecutorService exSer = null;
    public static Map<String, Socket> ListClient = new HashMap<String,Socket>();
    public static Map<String, Socket> ListClientWait = new HashMap<String,Socket>();
    
    private void excute() throws IOException {//ğoòc vaÌ ghi dıŞ liêòu 
        ServerSocket server = new ServerSocket(port);// chaòy song song ghi dıŞ liêòu
//      WriteServer write = new WriteServer();
//      write.start();
        System.out.println("Server is listening ...");
        exSer  = Executors.newFixedThreadPool(100);// sôì lıõòng caìc thread
        while (true) {     // ğõi kêìt noìi       
          Socket socket = server.accept();// ket noi dc vs client
            System.out.println("ĞaŞ kêìt nôÒi"+ socket);
            exSer.execute(new ServerHandler(socket));
            
//         ReadServer read = new ReadServer(socket);
//        read.start();
        }
     
  
    }
    public static void main(String[] args) throws IOException {
       
        Server server = new Server();
        server.excute();
        
    }
}
//      class ReadServer extends Thread{
//      private Socket socket;
//
//    public ReadServer(Socket socket) {
//        this.socket = socket;
//    }
//        @Override
//        public void run() {// lêònh ğıõòc thıòc thi
//           DataInputStream dis = null; 
//           try{
//               dis = new DataInputStream(socket.getInputStream());// ğoòc dıŞ liêòu
//               while(true){
//                   String sms = dis.readUTF();// ğoòc gıÒi ği cho client
//                if(sms.contains("bye")){
//                    Server.list.remove(socket);
//                    System.out.println("Ngãìt kêìt nôìi");
//                    dis.close();
//                    socket.close();
//                    
//                    continue;
//                }
//                   for(Socket items : Server.list)//duyêòt maÒng lâìy ra phâÌn tıÒ trong maÒng 
//                   { 
//                       if(items.getPort() != socket.getPort())// loaòi boÒ gıÒi tin vêÌ client ğaŞ gıÒi
//                       {
//                       DataOutputStream dos = new DataOutputStream(items.getOutputStream());
//                       dos.writeUTF(sms);
//                   }}
//                   System.out.println(sms);
//               }
//           }
//           catch(Exception e){
//               try {
//                   dis.close();
//                   socket.close();
//               } catch (IOException ex) {
//                   System.out.println("Ngãìt kêìt nôìi");
//               }
//           }
//        }
//      
//  }
//  class WriteServer extends Thread{
//
//        @Override
//        public void run() {
//            DataOutputStream dos = null;//ghi kiêÒu dıŞ liêòu
//            Scanner sc = new Scanner(System.in);
//            while (true) {                
//                String sms = sc.nextLine();
//              
//                    try {
//                          for(Socket items : Server.list)//duyêòt maÒng lâìy ra phâÌn tıÒ trong maÒng 
//                   {
//                        dos = new DataOutputStream(items.getOutputStream());
//                        dos.writeUTF(sms);
//                    } }catch (IOException ex) {
//                        Logger.getLogger(WriteServer.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                   
//            }
//            
//        }
//      
//  }
  