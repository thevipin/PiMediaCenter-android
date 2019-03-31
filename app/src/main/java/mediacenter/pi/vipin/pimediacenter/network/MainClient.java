package mediacenter.pi.vipin.pimediacenter.network;

import java.util.ArrayList;
import java.util.List;

import mediacenter.pi.vipin.pimediacenter.network.client.Client;
import mediacenter.pi.vipin.pimediacenter.network.client.ClientInterface;
import mediacenter.pi.vipin.pimediacenter.network.client.DataCodex;

/**
 * Created by Vipin on 15-03-2017.
 */

public class MainClient {
    private static ArrayList<String> ListIdentifier = new ArrayList<String>();
    //public MainClientInterface events;
    private static ArrayList<MainClientInterface> ListDataReciever= new ArrayList<MainClientInterface>();
    private static Client client = new Client(new ClientInterface(){
        public void onDataRecieved(DataCodex code) {
            ListDataReciever.get(ListIdentifier.indexOf(code.getIdentifier())).DataReciever(code);
        }

        @Override
        public void onConnectionSuccess() {

        }

        @Override
        public void onConnFailed(Exception e) {

        }
    });


    String identfier;
    MainClientInterface dataRecievered;

    public MainClient(String Identfier, MainClientInterface DataReciever)
    {
        identfier = Identfier;
        dataRecievered = DataReciever;
        ListIdentifier.add(identfier);
        ListDataReciever.add(dataRecievered);
    }
    public static void Kill()
    {
        client.DisconnectAll();
    }

    @Override
    protected void finalize() throws Throwable {
        ListIdentifier.remove(identfier);
        ListDataReciever.remove(dataRecievered);
        client.DisconnectAll();
        super.finalize();

    }

    public void sentData(DataCodex code)
    {
        code.setIdentifier(identfier);
        client.sent(code);
    }
}
