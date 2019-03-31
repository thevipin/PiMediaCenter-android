package mediacenter.pi.vipin.pimediacenter.network.client;


import android.content.Context;
import android.content.SharedPreferences;

import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.Socket;

import mediacenter.pi.vipin.pimediacenter.MainActivity;

/**
 * Created by Vipin on 15-03-2017.
 */

public class Client {
    private Socket socketClient;
    //private NetworkStream networkStream;
    private String IpAddress;
    private int PortAddress;
    private ClientInterface events;

    private Thread Threading= new Thread(){
        @Override
        public void run() {
            super.run();
            try
            {
                while (true)
                {
                    SharedPreferences preferance = MainActivity.getThis().getSharedPreferences("Network", Context.MODE_PRIVATE);

                    socketClient=new Socket(preferance.getString("ServerIpAddress","192.168.1.122"),preferance.getInt("ServerPortAddress",8082));
                    socketClient.setKeepAlive(true);
                    if(!socketClient.isConnected())
                    {
                        //socketClient.connect(SocketAddress);
                        //Debug.
                    }
                    events.onConnectionSuccess();
                    //Application..Exit += Current_Exit;
                    if (socketClient.isConnected())
                    {
                        InputStreamReader networkStream = new InputStreamReader(socketClient.getInputStream());
                        BufferedReader reader = new BufferedReader(networkStream);
                        while (socketClient.isConnected())
                        {
                            if ( reader.ready())
                            {

                                DataCodex codex = new DataCodex();
                                String data = reader.readLine();;
                                codex.putCodex(data);
                                events.onDataRecieved(codex);

                            }
                        }
                    }
                }
            }
            catch(Exception e)
            {
                //Debug.Print("\n----*** Vipin ***---\nError :\n" + e.StackTrace);
                try { events.onConnFailed(e); } catch(Exception e1) { }
            }
        }
    };


    public Client(ClientInterface clientInterface)
    {

        events=clientInterface;
        Threading.start();

    }
    private void Current_Exit()
    {
        DisconnectAll();
    }

    public void sent(DataCodex code)
    {
        try
        {
            if (Threading.isAlive() == false)
            {
                try
                {
                    Threading.start();
                    Thread.sleep(100);
                }
                catch(Exception e) { }
            }
            if (socketClient == null)
                return;
            if (socketClient.isConnected())
            {
                String data = code.getCodex();
                OutputStreamWriter writer=new OutputStreamWriter(socketClient.getOutputStream());
                //code.
                BufferedWriter Bwritter=new BufferedWriter(writer);
                Bwritter.write(data+"\n");
                Bwritter.flush();
            }
            else
            {
                Toast.makeText(null,"No Connection",Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e) {e.printStackTrace(); }
    }
    public void DisconnectAll()
    {
        try {
            socketClient.close();
            Threading.stop();
        } catch (Exception e) {
           // e.printStackTrace();
        }
    }

}
