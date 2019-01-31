import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerChat extends JFrame {
    private static JScrollPane scrollPane;
    private static JTextArea msg_area;
    private static JTextField msg_text;
    private static JButton msg_send;
    static ServerSocket ss;
    static Socket s;
    static DataInputStream din;
    static DataOutputStream dout;
    static Date date;
    static SimpleDateFormat formatForDateNow = new SimpleDateFormat("hh:mm:ss");


    private static void initComp(JFrame frame) {
        scrollPane = new JScrollPane();
        msg_area = new JTextArea(28, 40);
        msg_text = new JTextField(40);
        msg_send = new JButton("send");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setResizable(false);
        frame.setTitle("Server");
        msg_area.setEditable(false);
        msg_area.setLineWrap(true);
        scrollPane.setViewportView(msg_area);


        msg_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                msg_sendActionPerformed(event);
            }
        });

        msg_text.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {
                msg_textActionPerformed(keyEvent);
            }

            @Override
            public void keyPressed(KeyEvent keyEvent) {

            }

            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        panel.add(msg_text);
        panel.add(msg_send);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, msg_area);


        frame.setVisible(true);
    }

    private static void sendMsg() {
        try {

            String msgout = "";
            msgout = msg_text.getText().trim();
            dout.writeUTF(msgout);
            date = new Date();

            msg_area.setText(msg_area.getText().trim() + "\n" + formatForDateNow.format(date) + " Me: " + msgout);
            msg_text.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void msg_sendActionPerformed(ActionEvent event) {
        //TODO
        sendMsg();
    }

    private static void msg_textActionPerformed(KeyEvent event) {
        //TODO
        if (event.getKeyChar() == '\n') {
            sendMsg();
        }
    }

    private static void serverConnect() {
        String msgin = "";

        try {

            ss = new ServerSocket(1201); //server starts at 1201 port
            s = ss.accept();

            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());

            while (!msgin.equals("exit")) {
                date = new Date();
                msgin = din.readUTF();
                msg_area.setText(msg_area.getText().trim() + "\n" + formatForDateNow.format(date) + " Client: " + msgin);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        initComp(frame);
        serverConnect();
    }
}

