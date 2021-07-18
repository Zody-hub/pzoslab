package ua.nure.pzos.dl.laba4.Server;


import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ServerFrame extends JFrame {
    private List<BufferedImage> imageList;
    private List<Long> proceedTime = new ArrayList<>(0);
    private final int imageCount = 20;


    public ServerFrame(List<BufferedImage> imageList, List<Long> proceedTime) {
        super("Server frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.proceedTime = proceedTime;
        this.imageList = imageList;
        writeToXML(proceedTime);
        int imageCount = 20;
        for (int i = 0; i < imageCount; i++) {
            setIconImages(imageList);
        }
        addKeyListener(new KeyWork());
        setSize(40 * 7, 30 * 6);
        setVisible(true);
    }

    public void paint(Graphics g) {
        int x = 40;
        int y = 40;
        for (int i = 0; i < imageCount; ++i) {
            g.drawImage(imageList.get(i), x, y, this);
            x += 40;
            if (i % 5 == 4 && i != 0) {
                x = 40;
                y += 30;
            }
        }
    }

    private class KeyWork extends KeyAdapter {
        public void keyPressed(KeyEvent k) {
            if (k.isAltDown() && k.getKeyCode() == KeyEvent.VK_U) {
                JOptionPane.showMessageDialog(null, "XML cleared");
                clearXML();
            }
            if (k.isControlDown() && k.getKeyCode() == KeyEvent.VK_X) {
                JFrame jf = new JFrame("Time output");
                jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                JTextArea jTextField = new JTextArea();
                jTextField.setText(timeToString(proceedTime));
                jf.add(jTextField);
                jf.setSize(800, 600);
                jf.setVisible(true);
            }
        }
    }

    public void writeToXML(List<Long> times) {
        int i = 1;
        Document doc = new Document();
        doc.setRootElement(new Element("Images"));
        for (Long time :
                times) {
            Element timeElem = new Element("Image");
            timeElem.setAttribute("NO", String.valueOf(i));
            timeElem.addContent(new Element("time").setText(String.valueOf(time)));
            doc.getRootElement().addContent(timeElem);
            ++i;
        }
        XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());
        try {
            xmlWriter.output(doc, new FileOutputStream("src/main/resources/output.xml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void clearXML() {
        Document doc = new Document();
        doc.setRootElement(new Element("Images"));
        XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());
        try {
            xmlWriter.output(doc, new FileOutputStream("src/main/resources/output.xml"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public String timeToString(List<Long> time) {
        int i = 1;
        StringBuilder res = new StringBuilder();
        for (Long t :
                time) {
            res.append("Image NO ");
            res.append(i);
            res.append(" required time: ");
            res.append(t);
            res.append("\n");
            ++i;
        }
        return res.toString();
    }
}
