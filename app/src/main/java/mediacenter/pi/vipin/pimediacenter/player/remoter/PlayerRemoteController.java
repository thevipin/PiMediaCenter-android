package mediacenter.pi.vipin.pimediacenter.player.remoter;

import java.io.IOException;
import java.net.UnknownHostException;

import mediacenter.pi.vipin.pimediacenter.network.MainClient;
import mediacenter.pi.vipin.pimediacenter.network.MainClientInterface;
import mediacenter.pi.vipin.pimediacenter.network.client.DataCodex;

public class PlayerRemoteController {
	PlayerRemoteControllerEvents events;
	String lastCall;
	MainClient client;

	MainClientInterface clientRequest = new MainClientInterface()
	{
		public void DataReciever(DataCodex code) {
			// TODO Auto-generated method stub
			lastCall=code.get("call").toLowerCase();
			switch(lastCall)
			{
			case "connect":
				events.onConnected();
				break;
			case "play":
				events.onPlay(code);
				break;
			case "pause":
				events.onPause();
				break;
			case "resume":
				events.onResume();
				break;
			case "stop":
				events.onStop();
				break;
			case "volumeup":
				events.onWriteToOutputcmd('+');
				break;
			case "volumedown":
				events.onWriteToOutputcmd('-');
				break;
			case "decreasespeed":
				events.onDecreaseSpeed();
				break;
			case "increasespeed":
				events.onIncreaseSpeed();
				break;
			case "rewind":
				events.onRewind();
				break;
			case "fastforward": 
				events.onFastForward();
				break;
			case "showsubtitles":
				events.onShowSubtitles();
				break;
			case"hidesubtitles":
				events.onHideSubtitles();
				break;
			case "sendinfo":
				events.onSendInfo();
				break;
			case "directwrite":
				char ch=code.get("char").charAt(0);
				if(ch!='q')
					events.onWriteToOutputcmd(ch);
				break;
			case "seek10back":
				events.onSeek10Back();
				break;
			case "seek10for":
				events.onSeek10For();
				break;
			}
		}
	};


	public PlayerRemoteController() throws UnknownHostException, IOException
	{		
		//MainServer.server.addClientRequestHandler("PlayerRemoteController", clientRequest);
		client=new MainClient("PlayerRemoteController", clientRequest);
	}
	public void addEvents(PlayerRemoteControllerEvents doing)
	{
		events=doing;
	}
	public void sentInfo(String name,Boolean IsPause ) throws IOException
	{
		name=name.replaceAll(" ","");
		DataCodex code=new DataCodex("PlayerRemoteController");
		code.put("call", "sentinfo");
		code.put("name",name);
		code.put("IsPause", ((IsPause)?("true"):("false")));
		System.out.println("SEND INFO : "+name);
		client.sentData(code);
	}
	
	
	//===================================================
	private void sentResponse(DataCodex code) throws IOException
	{
		if(lastCall!=code.get("call"))
		{
			client.sentData(code);
		}
		else
		{
			lastCall="**";
		}
	}
	public void Play(DataCodex code) throws IOException
	{
		code.setIdentifier("PlayerRemoteController");
		sentResponse(code);
	}
	
	public void Stop() throws IOException
	{
		DataCodex code=new DataCodex("PlayerRemoteController");
		code.put("call", "stop");
		sentResponse(code);
	}
	public void Pause() throws IOException
	{
		DataCodex code=new DataCodex("PlayerRemoteController");
		code.put("call", "Pause");
		sentResponse(code);
	}
	
	public void Resume() throws IOException
	{
		DataCodex code=new DataCodex("PlayerRemoteController");
		code.put("call", "Resume");
		sentResponse(code);
	}
	public void DecreaseSpeed() throws IOException
	{
		DataCodex code=new DataCodex("PlayerRemoteController");
		code.put("call", "DecreaseSpeed");
		sentResponse(code);
	}
	public void IncreaseSpeed() throws IOException
	{
		DataCodex code=new DataCodex("PlayerRemoteController");
		code.put("call", "IncreaseSpeed");
		sentResponse(code);
	}
	public void Rewind() throws IOException
	{
		DataCodex code=new DataCodex("PlayerRemoteController");
		code.put("call", "Rewind");
		sentResponse(code);
	}
	public void FastForward() throws IOException
	{
		DataCodex code=new DataCodex("PlayerRemoteController");
		code.put("call", "FastForward");
		sentResponse(code);
	}
	public void ShowSubtitles() throws IOException
	{
		DataCodex code=new DataCodex("PlayerRemoteController");
		code.put("call", "ShowSubtitles");
		sentResponse(code);
	}
	public void HideSubtitles() throws IOException
	{
		DataCodex code=new DataCodex("PlayerRemoteController");
		code.put("call", "HideSubtitles");
		sentResponse(code);
	}
	public void DirectWrite(String value) throws IOException {
		DataCodex code = new DataCodex("PlayerRemoteController");
		code.put("call", "directwrite");
		code.put("char", value);
		sentResponse(code);
	}
	public void Seek10Back() throws IOException {
		DataCodex code = new DataCodex("PlayerRemoteController");
		code.put("call", "seek10back");
		sentResponse(code);
	}
	public void Seek10For() throws IOException {
		DataCodex code = new DataCodex("PlayerRemoteController");
		code.put("call", "seek10for");
		sentResponse(code);
	}
}
