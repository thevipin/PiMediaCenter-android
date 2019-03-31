package mediacenter.pi.vipin.pimediacenter.player.remoter;


import mediacenter.pi.vipin.pimediacenter.network.client.DataCodex;

public abstract class PlayerRemoteControllerEvents {
	abstract public void onPlay(DataCodex code);
	abstract public void onPause();
	abstract public void onStop();
	abstract public void onConnected();
	abstract public void onWriteToOutputcmd(char cmd);
	abstract public void onResume();
	abstract public void onDecreaseSpeed();
	abstract public void onIncreaseSpeed();
	abstract public void onRewind();
	abstract public void onFastForward();
	abstract public void onShowSubtitles();
	abstract public void onHideSubtitles();
	abstract public void onSendInfo();
	abstract public void onSeek10Back();
	abstract public void onSeek10For();
}
