import org.json.JSONObject;
import sun.net.ConnectionResetException;

import javax.xml.crypto.Data;
import java.io.DataInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.net.SocketException;

/**
 * Created by Amit Kumar on 16-05-2017.
 */
public class ServerClientReadThread implements Runnable
{
    private Socket socket;
    private InputStream inputStream;
    private DataInputStream dataInputStream;

    public ServerClientReadThread(Socket clientSocket)
    {
        this.socket = clientSocket;
    }

   // @Override
    public void run()
    {
        //The outer catch block is for catching the socket exception which is thrown when a user disconnects. This then still needs the while loop to continue.
        try
        {
            while (true)
            {
                try
                {
                    System.out.println(dataInputStream.readUTF());
                }
                catch (NullPointerException e){}
            }
        }catch (SocketException e)
        {
            System.out.println("The user has left.");
            closeStreams();
        }
        catch (Exception e){e.printStackTrace();}
    }

    public String readLoginData()
    {
        String jsonLoginData;
        JSONObject loginJsonObject;
        try
        {
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);
            jsonLoginData = dataInputStream.readUTF();
            loginJsonObject = new JSONObject(jsonLoginData);
            if(loginJsonObject.getString("type").equals("LOGIN"))
                return loginJsonObject.toString();

        }catch (Exception e){e.printStackTrace();}
        return "null";
    }

    private void closeStreams()
    {
        try
        {
            inputStream.close();
            dataInputStream.close();
        }catch (Exception e){e.printStackTrace();}
    }
}
