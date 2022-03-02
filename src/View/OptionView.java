package src.View;


import src.CalenderModel;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

public class OptionView extends JPanel implements ActionListener, PropertyChangeListener {
    CalenderModel m;

    public OptionView(CalenderModel m) {
        this.setBackground(Color.BLACK);
        this.m = m;
        JLabel label = new JLabel("Settings", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.PLAIN, 30));

        JPanel p = new JPanel(new GridLayout(5, 1, 10, 10));

        JButton button1 = new JButton("Appearance");
        JButton button3 = new JButton("Save");
        button3.addActionListener(this);
        JButton button4 = new JButton("Load");
        button4.addActionListener(this);

        p.add(label);
        p.add(button1);
        p.add(button3);
        p.add(button4);
        this.add(p);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if (cmd.equals("Save")) {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "DAT files", "dat");
            fc.setFileFilter(filter);
            int returnVal = fc.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                try {
                    String filename = file.getCanonicalPath();
                    if (!file.getName().endsWith(".dat")) {
                        file = new File(filename+".dat");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                m.save(file.getAbsolutePath());
            }
        } else if (cmd.equals("Load")) {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "DAT files", "dat");
            fc.setFileFilter(filter);
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                m.load(file.getAbsolutePath());
            }
        }
    }
}