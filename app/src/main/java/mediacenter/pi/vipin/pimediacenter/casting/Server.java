package mediacenter.pi.vipin.pimediacenter.casting;

import android.content.Context;
import android.net.rtp.RtpStream;
import android.widget.Toast;

import java.io.File;
import java.net.DatagramSocket;
import java.net.SocketException;


/**
 * Created by Vipin on 19-03-2017.
 */

public class Server {

    Context contextAll;

    public Server(Context context)
    {
        contextAll=context;
    }
    public String StartCasting(String FileSrc)
    {
        File file=new File(FileSrc);
        if(!file.exists())
        {
            Toast.makeText(contextAll,"File Not Found",Toast.LENGTH_LONG).show();
            return FileSrc;
        }
        return null;

    }

}
