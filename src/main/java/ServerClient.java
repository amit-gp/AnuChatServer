import org.json.JSONObject;
import java.net.Socket;

/**
 * Created by Amit Kumar on 15-05-2017.
 */
public class ServerClient
{

    private Socket socket;
    private ServerClientWriteThread writeThread;
    private ServerClientReadThread readThread;
    private String loginName;
    private String password;

    ServerClient(Socket clientSocket)
    {
        this.socket = clientSocket;
        writeThread = new ServerClientWriteThread(socket);
        readThread = new ServerClientReadThread(socket);
        JSONObject loginObject;
        try
        {
            //Receiving the initial login data .
            loginObject = new JSONObject(readThread.readLoginData());
            loginName = loginObject.getString("loginName");
            password = loginObject.getString("loginPassword");
            System.out.println("User " + loginName + " has connected with password: " + password);
            //Adding the new user to json List of users to be sent to the clients.
            //Sending the list of users online when logged in.
            writeThread.writeUserList();

        }catch (Exception e){e.printStackTrace();}

        new Thread(writeThread).start();
        new Thread(readThread).start();

    }
}
