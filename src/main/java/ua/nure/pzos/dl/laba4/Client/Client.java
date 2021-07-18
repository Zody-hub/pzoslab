package ua.nure.pzos.dl.laba4.Client;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;

public class Client {
    private final static int SERVER_PORT = 8071;
    private List<BufferedImage> imageList;
    private ClientFrame clientFrame;

    public Client() {
        clientFrame = new ClientFrame();
        imageList = clientFrame.getImageList();
    }

    public byte[] extractBytes(BufferedImage image) {
        WritableRaster raster = image.getRaster();
        DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

        return (data.getData());
    }

    private void sendImages() throws IOException {
        int imageCount = 20;
        while (imageList.size() != imageCount) {
            imageList = clientFrame.getImageList();
        }
        Socket s = new Socket(InetAddress.getLocalHost(), SERVER_PORT);

        DataOutputStream ds = new DataOutputStream(new BufferedOutputStream(s.getOutputStream()));

        for (int i = 0; i < imageCount; i++) {
            ds.write(extractBytes(imageList.get(i)));
        }
        System.out.println("sending to server");
        ds.flush();
    }

    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.sendImages();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
