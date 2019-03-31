package mediacenter.pi.vipin.pimediacenter.network;

import mediacenter.pi.vipin.pimediacenter.network.client.DataCodex;

/**
 * Created by Vipin on 15-03-2017.
 */

public class KeyStrokes {
    MainClient client;
    public KeyStrokes()
    {
        client = new MainClient("KeyStrokes", new MainClientInterface() {
            @Override
            public void DataReciever(DataCodex code) {
                System.out.print(code.getCodex());
            }
        });
    }
    public void KeyUp()
    {
        sent("keyup");
    }
    public void KeyDown()
    {
        sent("keydown");
    }
    public void KeyLeft()
    {
        sent("keyleft");
    }
    public void KeyRight()
    {
        sent("keyright");
    }
    public void KeyEnter()
    {
        sent("keyenter");
    }
    public void KeyBack()
    {
        sent("keyback");
    }
    public void MouseRight()
    {
        sent("mouseright");
    }
    public void Text(String text)
    {
        DataCodex code = new DataCodex("KeyStrokes");
        code.put("call", "text");
        code.put("text", text);
        client.sentData(code);
    }
    public void Shutdown()
    {
        sent("shutdown");
    }
    void sent(String call)
    {
        DataCodex code = new DataCodex("KeyStrokes");
        code.put("call", call);
        client.sentData(code);
    }
}
