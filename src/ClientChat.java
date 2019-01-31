import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientChat extends JFrame {

    private static JScrollPane scrollPane;
    private static JTextArea msg_area;
    private static JTextField msg_text;
    private static JButton msg_send;
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
        frame.setTitle("Client");
        msg_area.setEditable(false);
        msg_area.setLineWrap(true);
        scrollPane.setViewportView(msg_area);


        msg_send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                msg_sendActionPerformed(event);
            }
        });

        msg_text.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                msg_textActionPerformed(event);
            }
        });

        panel.add(msg_text);
        panel.add(msg_send);

        frame.getContentPane().add(BorderLayout.SOUTH, panel);
        frame.getContentPane().add(BorderLayout.NORTH, msg_area);


        frame.setVisible(true);
    }

    private static void msg_sendActionPerformed(ActionEvent event) {
        //TODO
        try {
            String msgout = "";
            date = new Date();
            msgout = msg_text.getText();

            dout.writeUTF(msgout);


            msg_area.setText(msg_area.getText() + "\n" + formatForDateNow.format(date) + " Me: " + msgout);
            msg_text.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void msg_textActionPerformed(ActionEvent event) {
        //TODO

    }

    private static void clientConnect() {
        try {
            s = new Socket("127.0.0.1", 1201);

            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            String msgin = "";
            while (!msgin.equals("exit")) {
                date = new Date();
                msgin = din.readUTF();
                msg_area.setText(msg_area.getText().trim() + "\n" + formatForDateNow.format(date) + " Server:" + msgin);
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        initComp(frame);
        clientConnect();
    }
}
