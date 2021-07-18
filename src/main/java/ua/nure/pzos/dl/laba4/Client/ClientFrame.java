package ua.nure.pzos.dl.laba4.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientFrame extends JFrame {
    private final JFileChooser fileChooser;
    private List<BufferedImage> imageList = new ArrayList<>(0);
    private File file;
    private final int imageCount = 20;

    public ClientFrame() {
        super("ClientFrame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JButton readFiles = new JButton("Read files");
        fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("JPEG only", "jpg");
        fileChooser.setFileFilter(filter);
        readFiles.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                while (imageList.size() != imageCount) {
                    fileChooser.setDialogTitle("Choose directory");
                    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                    int result = fileChooser.showOpenDialog(ClientFrame.this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        file = fileChooser.getSelectedFile();
                        try {
                            imageList.add(ImageIO.read(file));
                        } catch (IOException ioException) {
                            System.out.println(ioException.getMessage());
                        }
                    }
                }
            }
        });
        add(readFiles, BorderLayout.CENTER);
        setSize(320, 240);
        setVisible(true);
    }

    public List<BufferedImage> getImageList() {
        return Collections.unmodifiableList(imageList);
    }
}
