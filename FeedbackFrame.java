import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class FeedbackFrame extends JFrame {
    Container c;
    TextArea taFeedData;
    JButton btnFeedOk;

    FeedbackFrame(String data) {
        c = getContentPane();
        c.setLayout(new FlowLayout());
        taFeedData = new TextArea(10, 30);
        btnFeedOk = new JButton("OK");
        c.add(taFeedData);
        c.add(btnFeedOk);

        
        taFeedData.setText(data);

        ActionListener a1 = (ae) -> {
            MainFrame a = new MainFrame();
            dispose();
        };
        btnFeedOk.addActionListener(a1);

        setTitle("Feedback");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}