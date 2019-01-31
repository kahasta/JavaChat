import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chats extends JFrame {

    private static JScrollPane scrollPane;
    private static TextArea msg_area;
    private static TextField msg_text;
    private static JButton msg_send;


    private static void initComp(JFrame frame) {
        scrollPane = new JScrollPane();
        msg_area = new TextArea(24, 40);
        msg_text = new TextField(60);
        msg_send = new JButton("send");
        JPanel panel = new JPanel();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setResizable(false);
        msg_area.setEditable(false);
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
    }

    private static void msg_textActionPerformed(ActionEvent event) {
        //TODO
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        initComp(frame);
    }
}
