import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Sean on 2/18/2016.
 */
public class ServerReadRunnable implements Runnable {

    //vars
    BufferedReader bufferedReader;
    JTextArea textBox;

    public ServerReadRunnable (BufferedReader b, JTextArea j){

        bufferedReader = b;
        textBox = j;

    }

    @Override
    public void run() {

        System.out.println("Server Read thread running...");

        String receiveMessage;

        while (true){

            try {

                if((receiveMessage = bufferedReader.readLine()) != null)
                {

                    //client has read a socket code, need check what code it is
                    if(receiveMessage.equals(SocketSignals.BATTLESHIP_SIGNAL_CHAT)){

                        System.out.println("message is inconming");

                        //now know that a chat message is coming
                        try {
                            if((receiveMessage = bufferedReader.readLine()) != null)
                            {
                                //now recieved the message, need print it to screen
                                textBox.append(receiveMessage + '\n');
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }else{

                        System.out.println("Read something but its not a message, recieveMessage: " + receiveMessage + " signal: " + SocketSignals.BATTLESHIP_SIGNAL_CHAT);
                    }

                    // textBox.append(receiveMessage + '\n');
                    //System.out.println("The server says: " + receiveMessage);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//close run()
}