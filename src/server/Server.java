package server;

import share.enumUtils.EnumClientRequest;
import share.manager.DataManager;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket server;

    public Server(String ipAddress) throws Exception {
        DataManager.INSTANCE.loadDatas();
        this.server = new ServerSocket(12451);
//        if (ipAddress != null && !ipAddress.isEmpty())
//            this.server = new ServerSocket(0, 1, InetAddress.getByName(ipAddress));
//        else
//            this.server = new ServerSocket(0, 1, InetAddress.getLocalHost());
    }

    public void listen() throws Exception {
        String data;
        Socket client = this.server.accept();
        String clientAddress = client.getInetAddress().getHostAddress();
        System.out.println("\r\nNew connection from " + clientAddress);

        // On écoute la demande du client
        BufferedReader in = new BufferedReader(
                new InputStreamReader(client.getInputStream()));
        // Tant que le client communique
        while ((data = in.readLine()) != null) {
            // On affiche la demande
            System.out.println("\r\nMessage from " + clientAddress + ": " + data);

            // On récupère l'id de la requête
            EnumClientRequest clientRequest = EnumClientRequest.getEnumByID((int) data.charAt(0));
            // On agit en fonction de la requête
            manageRequest(clientRequest, client, data);

        }
    }

    private void manageRequest(EnumClientRequest clientRequest, Socket client, String data) {
        switch (clientRequest) {
            case FIRST_CONNECTION:
                sendAllDatas(client);
                break;
            case CONNECTION:
                checkAllVisites(client);
                break;
            case SEND_UPDATE:
                updateVisites(client, data);
                break;
            case ERROR:
                System.out.println("\r\nInvalid datas from client -> Disconnect");
                break;
        }

    }

    private void updateVisites(Socket client, String data) {
        try {
            String dataIn = data.substring(1);
            // Si on a des données entrantes
            if (dataIn.length() > 0) {
                DataOutputStream dataToSend = new DataOutputStream(client.getOutputStream());
                dataToSend.writeUTF(DataManager.INSTANCE.getAllUsers().toString());
                dataToSend.flush();
            }
            // Sinon Error
            else {
                System.out.println("\r\nInvalid datas from client -> Disconnect");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkAllVisites(Socket client) {

    }

    /**
     * On envoie toutes les données au client
     *
     * @param client socket client
     */
    private void sendAllDatas(Socket client) {
        try {
            DataOutputStream dataToSend = new DataOutputStream(client.getOutputStream());
            dataToSend.writeUTF(DataManager.INSTANCE.getAllUsers().toString());
            dataToSend.flush();
            dataToSend.writeUTF(DataManager.INSTANCE.getAllMagasins().toString());
            dataToSend.flush();
            dataToSend.writeUTF(DataManager.INSTANCE.getAllVisites().toString());
            dataToSend.flush();
            dataToSend.writeByte(-1);
            dataToSend.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public InetAddress getSocketAddress() {
        return this.server.getInetAddress();
    }

    public int getPort() {
        return this.server.getLocalPort();
    }

}