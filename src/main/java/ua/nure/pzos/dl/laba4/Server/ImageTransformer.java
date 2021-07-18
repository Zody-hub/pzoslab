package ua.nure.pzos.dl.laba4.Server;

import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ImageTransformer extends Thread {
    private final Lock lock;
    private final Condition condition;
    private long time;
    private List<Long> timeList = new ArrayList<>();
    List<byte[]> byteList = new ArrayList<>();
    List<BufferedImage> res = new ArrayList<>();
    boolean isOdd;

    public void startProcessing() {
        this.start();
    }

    ImageTransformer() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
    }

    @Override
    public synchronized void run() {
        try {
            wait();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
        transformList();
    }

    public void transformList() {
        int i;
        if (isOdd) {
            i = 0;
        } else {
            i = 1;
        }
        for (byte[] mas : byteList
        ) {
            res.add(transform(mas, i));
            timeList.add(getTime());
            i += 2;
        }
    }

    public BufferedImage transform(byte[] byteArray, int index) {
        long m = System.currentTimeMillis();
        int count1 = 0;
        byte temp1 = 0;

        for (int j = 0; j < byteArray.length; j++) {
            if (count1 == 0) {
                temp1 = byteArray[j];
                count1++;
            } else if (count1 == 1) {
                count1++;
            } else if (count1 == 2) {
                byteArray[j - 2] = byteArray[j];
                byteArray[j] = temp1;
                count1 = 0;
            }
        }
        BufferedImage image = new BufferedImage(320, 240, BufferedImage.TYPE_3BYTE_BGR);
        image.getWritableTile(0, 0).setDataElements(0, 0, 320, 240, byteArray);
        ColorConvertOp op =
                new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
        op.filter(image, image);
        BufferedImage res = createResizedCopy(image, 40, 30, index + 1);
        time = System.currentTimeMillis() - m;
        return res;
    }

    public BufferedImage createResizedCopy(Image originalImage,
                                           int scaledWidth, int scaledHeight, int index) {
        int imageType = BufferedImage.TYPE_INT_RGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        Font font = new Font("Arial", Font.PLAIN, 20);
        g.setFont(font);
        g.setPaint(Color.RED);
        String text = String.valueOf(index);
        TextLayout textLayout = new TextLayout(text, g.getFont(),
                g.getFontRenderContext());
        double textHeight = textLayout.getBounds().getHeight();
        double textWidth = textLayout.getBounds().getWidth();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.drawString(text, scaledWidth / 2 - (int) textWidth / 2,
                scaledHeight / 2 + (int) textHeight / 2);
        g.dispose();
        return scaledBI;
    }

    public long getTime() {
        return time;
    }

    public List<Long> getTimeList() {
        return timeList;
    }

    public synchronized void setByteList(List<byte[]> byteList, boolean isOdd) {
        this.byteList = byteList;
        this.isOdd = isOdd;
        notify();
    }

    public void leave() throws InterruptedException {
        lock.lock();
        try {
            condition.signal();
        } finally {
            lock.unlock();
        }
        join();
    }

    public List<BufferedImage> getTransformedImages() {
        return res;
    }
}
