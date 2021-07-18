package ua.nure.pzos.dl.laba4.Server;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int SERVER_PORT = 8071;
    private List<BufferedImage> receivedImages = new ArrayList<>(0);
    private List<Long> proceedTime = new ArrayList<>(0);

    public Server() throws IOException {
        System.out.println("Server is started");
        ServerSocket serv = new ServerSocket(SERVER_PORT);
        while (true) {
            Socket sock = serv.accept();
            processRequest(sock);
            sock.close();
        }
    }

    private void processRequest(Socket sock) throws IOException {
        DataInputStream is = new DataInputStream(new BufferedInputStream(sock.getInputStream()));
        int imageCount = 20;
        List<byte[]> oddBytes = new ArrayList<>();
        List<byte[]> evenBytes = new ArrayList<>();
        ImageTransformer odd = new ImageTransformer();
        ImageTransformer even = new ImageTransformer();
        odd.startProcessing();
        even.startProcessing();
        for (int i = 0; i < imageCount; i += 2) {
            byte[] byteArrayOdd = is.readNBytes(230400);
            byte[] byteArrayEven = is.readNBytes(230400);
            oddBytes.add(byteArrayOdd);
            evenBytes.add(byteArrayEven);
        }
        sock.close();
        odd.setByteList(oddBytes, true);
        even.setByteList(evenBytes, false);
        List<BufferedImage> oddImages = odd.getTransformedImages();
        List<BufferedImage> evenImages = even.getTransformedImages();
        List<Long> oddTime = odd.getTimeList();
        List<Long> evenTime = even.getTimeList();
        try {
            odd.leave();
            even.leave();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < imageCount / 2; ++i) {
            receivedImages.add(oddImages.get(i));
            proceedTime.add(oddTime.get(i));
            receivedImages.add(evenImages.get(i));
            proceedTime.add(evenTime.get(i));
        }
        new ServerFrame(receivedImages, proceedTime);
    }

    public static void main(String[] args) {
        try {
            new Server();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
