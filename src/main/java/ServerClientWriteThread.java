import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by Amit Kumar on 16-05-2017.
 */
public class ServerClientWriteThread implements Runnable
{
    private Socket clientSocket;
    private OutputStream outputStream;
    private DataOutputStream dataOutputStream;
    private String message;

    public ServerClientWriteThread(Socket socket)
    {
        try
        {
            this.clientSocket = socket;
            outputStream = socket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);

        }catch (Exception e){e.printStackTrace();}
    }

    public void run()
    {
        //while (true)
        {

        }
    }

    public void writeUserList()
    {
        try
        {
            //Gets the current list of users.
            JSONObject userListCopy = Server.getUserList();
            dataOutputStream.writeUTF(userListCopy.toString());

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
