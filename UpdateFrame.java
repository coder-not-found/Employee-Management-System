import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

class UpdateFrame extends JFrame{
	Container c;
	JLabel lblUpdateRno,lblUpdateName,lblUpdateMarks;
	JTextField txtUpdateRno,txtUpdateName,txtUpdateMarks;
	JButton btnUpdateSubmit,btnUpdateBack;

	UpdateFrame(){
		c=getContentPane();
		c.setLayout(new FlowLayout());
		lblUpdateRno = new JLabel("Enter rno.to update");
		txtUpdateRno = new JTextField(10);
		lblUpdateName = new JLabel("Enter updated name");
		txtUpdateName = new JTextField(10);
		lblUpdateMarks = new JLabel("Enter updated marks");
		txtUpdateMarks = new JTextField(10);
		btnUpdateSubmit = new JButton("SUBMIT");
		btnUpdateBack = new JButton("BACK");
		c.add(lblUpdateRno);
		c.add(txtUpdateRno);
		c.add(lblUpdateName);
		c.add(txtUpdateName);
		c.add(lblUpdateMarks);
		c.add(txtUpdateMarks);
		c.add(btnUpdateSubmit);
		c.add(btnUpdateBack);

		ActionListener a1 = (ae) -> {
			MainFrame a = new MainFrame();
			dispose();
		};
		btnUpdateBack.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			try{
				int sid = Integer.parseInt(txtUpdateRno.getText());
				String sname = txtUpdateName.getText();
				String smarksorg = txtUpdateMarks.getText();
				int smarks = Integer.parseInt(smarksorg);
				if(sname.isEmpty()){
					JOptionPane.showMessageDialog(c,"Name cannot be empty.","alert",JOptionPane.ERROR_MESSAGE);
					txtUpdateName.requestFocus();
				}
				else if(sname.matches("^[a-zA-Z]*$") && smarksorg.matches("(^[0-9]|^[1-9][0-9]|^100)$")){
					int val = new DbHandler().updateStudent(sid,sname,smarks);
					if(val == 0){
						JOptionPane.showMessageDialog(c,"Student Does Not Exist","alert",JOptionPane.ERROR_MESSAGE);
						txtUpdateName.setText("");
						txtUpdateRno.setText("");
						txtUpdateMarks.setText("");
						txtUpdateRno.requestFocus();
					}
				}
				else if(!smarksorg.matches("(^[0-9]|^[1-9][0-9]|^100)$")){
					JOptionPane.showMessageDialog(new JDialog(),"Marks between 0-100","alert",JOptionPane.ERROR_MESSAGE);
					txtUpdateMarks.setText("");
					txtUpdateMarks.requestFocus();
				}
				else{
					JOptionPane.showMessageDialog(new JDialog(),"Name only has alphabets,fool.","alert",JOptionPane.ERROR_MESSAGE);
					txtUpdateName.setText("");
					txtUpdateName.requestFocus();
				}
			}
			catch(Exception e){
				JOptionPane.showMessageDialog(new JDialog(),"Cannot be empty,use only Integers.","alert",JOptionPane.ERROR_MESSAGE);
				txtUpdateName.setText("");
				txtUpdateRno.setText("");
				txtUpdateMarks.setText("");
				txtUpdateRno.requestFocus();
			}
		};
		btnUpdateSubmit.addActionListener(a2);

		setTitle("Update Student");
		setSize(300,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}