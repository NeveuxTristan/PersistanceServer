import server.Server;
import share.dataObject.Magasin;
import share.dataObject.User;
import share.dataObject.Visite;
import share.manager.DataManager;

public class Main {

    public static void main(String[] args) throws Exception {
        testServer();
    }

    private static void testServer() throws Exception {
        Server app = new Server(null);
        System.out.println("\r\nRunning Server: " +
                "Host=" + app.getSocketAddress().getHostAddress() +
                " Port=" + app.getPort());

        app.listen();
    }

    private static void testLoadJson() {
        DataManager.INSTANCE.loadDatas();
        for (Magasin m : DataManager.INSTANCE.getAllMagasins()
        ) {
            System.out.println(m.toString());
        }
        for (Visite v : DataManager.INSTANCE.getAllVisites()
        ) {
            System.out.println(v.toString());
        }
        for (User u : DataManager.INSTANCE.getAllUsers()
        ) {
            System.out.println(u.toString());
        }
    }

}
