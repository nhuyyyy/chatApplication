/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ClientUI;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{
    private InetAddress host;
    private int port=15797;

//    public Client(InetAddress host, int port) {
//        this.host = host;
//        this.port = port;
//    }

  
//    private void excute() throws IOException{//ðoòc vaÌ ghi dýÞ liêòu 
//        Scanner sc= new Scanner(System.in);
//        System.out.println("Nhâòp vaÌo tên baòn ");
//        String name =sc.nextLine();
//      
//        Socket client = new Socket(host,port);
//        ReadClient read = new ReadClient(client);
//        read.start();
//        WriteClient write = new WriteClient(client,name);
//        write.start();
//    }
    public static void main(String[] args) throws  IOException {
        Socket clientsocket = new Socket(InetAddress.getLocalHost(),15797);
        System.out.println(clientsocket);
        LoginClientGUI lg = new LoginClientGUI(clientsocket);
        lg.setVisible(true);
       
    }
}
//  class ReadClient extends Thread{
//      private Socket client;
//
//        public ReadClient(Socket client) {
//            this.client = client;
//        }       
//
//        @Override
//        public void run() {// lêònh ðýõòc thýòc thi
//           DataInputStream dis = null; 
//          
//        }
//      
//  }
//  class WriteClient extends Thread{
//      private Socket client;
//      private String name;
//
//        public WriteClient(Socket client,String name) {
//            this.client = client;
//            this.name = name;
//        }
//
//        @Override
//        public void run() {
//            DataOutputStream dos = null;//ghi kiêÒu dýÞ liêòu
//            Scanner sc = null;
//            try {
//                dos = new DataOutputStream(client.getOutputStream());
//                sc = new Scanner(System.in);
//                while (true) {                    
//                    String sms = sc.nextLine();
//                    dos.writeUTF(name + ":" +sms);
//                }
//            }     catch(Exception e){
//               try {
//                   dos.close();
//               } catch (IOException ex) {
//                   System.out.println("Ngãìt kêìt nôìi");
//               }
//           }
//            }
//            
//        }
      
  
