/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatRoomTCP;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client{
    private InetAddress host;
    private int port;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

  
    private void excute() throws IOException{//?o?c va? ghi d?? li??u 
        Scanner sc= new Scanner(System.in);
        System.out.println("Nh??p va?o t?n ba?n ");
        String name =sc.nextLine();
      
        Socket client = new Socket(host,port);
        ReadClient read = new ReadClient(client);
        read.start();
        WriteClient write = new WriteClient(client,name);
        write.start();
    }
    public static void main(String[] args) throws  IOException {
        Client client = new Client(InetAddress.getLocalHost(),15797);
        client.excute();
    }
}
  class ReadClient extends Thread{
      private Socket client;

        public ReadClient(Socket client) {
            this.client = client;
        }       

        @Override
        public void run() {// l??nh ????c th??c thi
           DataInputStream dis = null; 
           try{
               dis = new DataInputStream(client.getInputStream());// ?o?c d?? li??u
               while(true){
                   String sms = dis.readUTF();// ???i d?? li??u server g??i v?? 
                   System.out.println(sms);
               }
           }
           catch(Exception e){
               try {
                   dis.close();
                   client.close();
               } catch (IOException ex) {
                   System.out.println("Ng??t k??t n??i");
               }
           }
        }
      
  }
  class WriteClient extends Thread{
      private Socket client;
      private String name;

        public WriteClient(Socket client,String name) {
            this.client = client;
            this.name = name;
        }

        @Override
        public void run() {
            DataOutputStream dos = null;//ghi ki??u d?? li??u
            Scanner sc = null;
            try {
                dos = new DataOutputStream(client.getOutputStream());
                sc = new Scanner(System.in);
                while (true) {                    
                    String sms = sc.nextLine();
                    dos.writeUTF(name + ":" +sms);
                }
            }     catch(Exception e){
               try {
                   dos.close();
               } catch (IOException ex) {
                   System.out.println("Ng??t k??t n??i");
               }
           }
            }
            
        }
      
  
