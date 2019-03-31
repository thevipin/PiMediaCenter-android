package mediacenter.pi.vipin.pimediacenter.network.client;

/**
 * Created by Vipin on 15-03-2017.
 */

public abstract class ClientInterface {
    abstract public void onDataRecieved(DataCodex code);
    abstract public void onConnectionSuccess();
    abstract public void onConnFailed(Exception e);

}
