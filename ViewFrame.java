import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewFrame extends JFrame {
    Container c;
    TextArea taViewData;
    JButton btnViewBack;

    ViewFrame() {
        c = getContentPane();
        c.setLayout(new FlowLayout());
        taViewData = new TextArea(10, 30);
        btnViewBack = new JButton("BACK");
        c.add(taViewData);
        c.add(btnViewBack);

        String data = new DbHandler().viewStudent();
        taViewData.setText(data);

        ActionListener a1 = (ae) -> {
            MainFrame a = new MainFrame();
            dispose();
        };
        btnViewBack.addActionListener(a1);

        setTitle("View Student");
        setSize(300, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}