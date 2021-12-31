/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatRoomTCP;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    
    private int port;
    public static ArrayList<Socket> list;

    public Server(int port) {
        this.port = port;
    }
    private void excute() throws IOException {//�o�c va� ghi d�� li��u 
        ServerSocket server = new ServerSocket(port);// cha�y song song ghi d�� li��u
        WriteServer write = new WriteServer();
        write.start();
        System.out.println("Server is listrning ...");
        while (true) {     // ��i k��t no�i       
          Socket socket = server.accept();// ket noi dc vs client
            System.out.println("�a� k��t n��i"+ socket);
            Server.list.add(socket);//day socket vo mang
            ReadServer read = new ReadServer(socket);
          read.start();
        }
  
    }
    public static void main(String[] args) throws IOException {
        Server.list = new ArrayList<>();
        Server server = new Server(15797);
        server.excute();
     
        
    }
}
      class ReadServer extends Thread{
      private Socket socket;

    public ReadServer(Socket socket) {
        this.socket = socket;
    }

        
     

        
        @Override
        public void run() {// l��nh ����c th��c thi
           DataInputStream dis = null; 
           try{
               dis = new DataInputStream(socket.getInputStream());// �o�c d�� li��u
               while(true){
                   String sms = dis.readUTF();// �o�c g��i �i cho client
                if(sms.contains("bye")){
                    Server.list.remove(socket);
                    System.out.println("Ng��t k��t n��i");
                    dis.close();
                    socket.close();
                    
                    continue;
                }
                   for(Socket items : Server.list)//duy��t ma�ng l��y ra ph��n t�� trong ma�ng 
                   { 
                       if(items.getPort() != socket.getPort())// loa�i bo� g��i tin v�� client �a� g��i
                       {
                       DataOutputStream dos = new DataOutputStream(items.getOutputStream());
                       dos.writeUTF(sms);
                   }}
                   System.out.println(sms);
               }
           }
           catch(Exception e){
               try {
                   dis.close();
                   socket.close();
               } catch (IOException ex) {
                   System.out.println("Ng��t k��t n��i");
               }
           }
        }
      
  }
  class WriteServer extends Thread{


   

        @Override
        public void run() {
            DataOutputStream dos = null;//ghi ki��u d�� li��u
            Scanner sc = new Scanner(System.in);
            while (true) {                
                String sms = sc.nextLine();
              
                    try {
                          for(Socket items : Server.list)//duy��t ma�ng l��y ra ph��n t�� trong ma�ng 
                   {
                        dos = new DataOutputStream(items.getOutputStream());
                        dos.writeUTF(sms);
                    } }catch (IOException ex) {
                        Logger.getLogger(WriteServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
            }
            
        }
      
  }
  