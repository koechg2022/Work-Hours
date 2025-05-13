import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import Structs.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


public class UserInterface {

    private boolean active;
    private JFrame frame;
    private JButton start;
    private JButton stop;
    // private JButton store;
    private JTextField text;
    // private String directory;

    public UserInterface(int oneD, String title, String startButton, String stopButton, String startHint, String stopHint){
        this.frame = new JFrame(title);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setBackground(new ColorUIResource(0.4f, 0.2f, 0.4f));
        oneD = (oneD == -1) ? 300 : oneD;
        this.frame.setSize(2 * oneD, oneD);
        this.start = new JButton(startButton);
        this.start.setToolTipText(startHint);
        this.stop = new JButton(stopButton);
        this.stop.setToolTipText(stopHint);
        int horizonalPos = 25;
        int verticalPos = 150;
        this.start.setBounds(horizonalPos, verticalPos, oneD / 5 + startButton.length(), oneD / 20);
        this.stop.setBounds(this.frame.getWidth() - stopButton.length() - oneD / 5 - horizonalPos, verticalPos, oneD / 5 + stopButton.length(), oneD / 20);
        this.stop.setEnabled(false);
        this.text = new JTextField("TIMER");
        this.text.setEditable(false);
        this.text.setBackground(new ColorUIResource(Color.lightGray));
        this.text.setBounds( 0, 0, this.frame.getWidth(), oneD / 3);
        this.text.setHorizontalAlignment(JTextField.CENTER);
        this.frame.getContentPane().add(this.start);
        this.frame.getContentPane().add(this.stop);
        this.frame.getContentPane().add(this.text);
        this.frame.setLayout(null);
        this.frame.setVisible(true);
    }

    void click(String time){
        if (this.active || time.equals("")){
            this.start.setEnabled(true);
            this.stop.setEnabled(false);
        }
        else{
            this.start.setEnabled(false);
            this.stop.setEnabled(true);
        }
        this.setText(time);
        this.active = !this.active;
        this.frame.setVisible(true);
    }

    void setText(String t){
        this.text.setText(t);
    }

    public JFrame getFrame(){
        return this.frame;
    }

    public static void main(String args[]){
        String startHint = "Push to punch in"; String stopHint = "Push to punch out";
        UserInterface gui = new UserInterface(
            400,
            "My time punching system", 
            "--Start--", "--Stop--", 
            startHint, 
            stopHint);

        
        gui.start.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                app ap = new app();
                if (!gui.active){
                    try {
                        ap.runCode();
                        gui.click("Starting Time : " + Integer.toString(ap.getStart().getHour()) + " : " + Integer.toString(ap.getStart().getMinute()) + " : " + Integer.toString(ap.getStart().getSeconds()));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                ap = null;
            }
            
        });

        gui.stop.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                app ap = new app();
                if(gui.active){
                    try {
                        ap.runCode();
                        String time = Integer.toString(ap.getStart().getHour()) + " : "+ Integer.toString(ap.getStart().getMinute()) + " : " + Integer.toString(ap.getStart().getSeconds());
                        time = time + "-->"+ Integer.toString(ap.getStop().getHour()) + " : " + Integer.toString(ap.getStop().getMinute()) + " : " + Integer.toString(ap.getStop().getSeconds());
                        time = time + "\t \nTotal Time: " + ap.getTimeDifference();
                        gui.click(time);
                    }
                    catch (IOException e1){
                        e1.printStackTrace();
                    }
                }
                File tempCheck = new File(ap.getTempDirectory());
                if (tempCheck.exists()){
                    tempCheck.delete();
                }
                ap = null;
            }
        });
    }
}
