package ua.nure.pzos.dl.laba3;

import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.Math;


public class DesktopUserInterface extends JPanel {
    static int n = 0;
    static int R = 100;
    static int X = 250, Y = 220;
    Color color = Color.BLUE;

    public DesktopUserInterface() {
        JButton button1 = new JButton("Выбрать цвет");
        JButton button2 = new JButton("Задать количесвто углов");
        JTextField jtf = new JTextField(15);
        JPanel jp = new JPanel();
        jp.setSize(500, 50);
        JFrame jf = new JFrame("User interface");
        jf.setSize(500, 500);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jp.add(jtf, BorderLayout.WEST);
        jp.add(button2, BorderLayout.SOUTH);
        jp.add(button1, BorderLayout.SOUTH);
        jf.add(jp, BorderLayout.NORTH);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int tmp = Integer.parseInt(jtf.getText());
                    if (tmp <= 2) {
                        JOptionPane.showMessageDialog(jf, "Вы ввели меньше 3 углов");
                    } else {
                        n = tmp;
                    }
                    jf.repaint();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(jf, "Вы не ввели целое число");
                }
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame colorFrame = new JFrame();
                colorFrame.setSize(500, 600);
                colorFrame.setDefaultCloseOperation(colorFrame.DISPOSE_ON_CLOSE);
                colorFrame.setLocationRelativeTo(jf);
                colorFrame.setLayout(new BorderLayout());
                JColorChooser jcc = new JColorChooser();
                JButton button3 = new JButton("Сменить цвет");
                colorFrame.add(button3, BorderLayout.SOUTH);
                button3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        color = jcc.getColor();
                        jf.repaint();
                    }
                });
                colorFrame.add(jcc, BorderLayout.CENTER);
                colorFrame.setVisible(true);
            }
        });
        jf.getContentPane().add(this);
        jf.setVisible(true);
        jf.repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        Polygon p = new Polygon();
        for (int i = 0; i < n; i++)
            p.addPoint((int) (X + R * Math.cos(i * 2 * Math.PI / n)),
                    (int) (Y + R * Math.sin(i * 2 * Math.PI / n)));
        g.drawPolygon(p);
        g.fillPolygon(p);
    }

    public static void main(String[] args) {
        DesktopUserInterface NY = new DesktopUserInterface();
        NY.repaint();
    }
}
