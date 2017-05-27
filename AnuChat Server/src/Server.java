import org.json.JSONObject;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by Amit Kumar on 15-05-2017.
 */

public class Server
{

    private ServerSocket serverSocket;
    private static ArrayList<ServerClient> serverClients;
    private static JSONObject userList;
    private final int PORT = 8080;
    private static int userCount = 0;

    Server()
    {
        try
        {
            serverSocket = new ServerSocket(PORT);
            serverClients = new ArrayList<>();
            userList = new JSONObject();

            while (true)
            {
                System.out.println("Server waiting....");
                Socket socket = serverSocket.accept();          //This method blocks until a client connects
                serverClients.add(new ServerClient(socket));
                System.out.println(userList.toString());
            }
        }catch (Exception e){e.printStackTrace();}
    }

    public static void main(String args[])
    {
        Server server = new Server();
    }

    public static JSONObject getUserList()
    {
        return userList;
    }

    public static void addNewUserToJson(String name)
    {
        try
        {
            userList.put("user" + ++userCount, new JSONObject().put("name", name));

        }catch (Exception e){e.printStackTrace();}

    }
}
