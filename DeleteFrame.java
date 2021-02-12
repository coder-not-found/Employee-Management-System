import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class DeleteFrame extends JFrame{
	Container c;
	JLabel lblDeleteRno;
	JTextField txtDeleteRno;
	JButton btnDeleteSubmit,btnDeleteBack;

	DeleteFrame(){
		c=getContentPane();
		c.setLayout(new FlowLayout());
		lblDeleteRno = new JLabel("Enter rno. to delete");
		txtDeleteRno = new JTextField(10);
		btnDeleteSubmit = new JButton("SUBMIT");
		btnDeleteBack = new JButton("BACK");
		c.add(lblDeleteRno);
		c.add(txtDeleteRno);
		c.add(btnDeleteSubmit);
		c.add(btnDeleteBack);

		ActionListener a1 = (ae) -> {
			MainFrame a = new MainFrame();
			dispose();
		};
		btnDeleteBack.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			try{
				String sidorg = txtDeleteRno.getText();
				int sid = Integer.parseInt(sidorg);
				if(sidorg.matches("^[0-9]*$")){
					int val  = new DbHandler().deleteStudent(sid);
					if(val == 0){
						JOptionPane.showMessageDialog(c,"Student Does Not Exist","alert",JOptionPane.ERROR_MESSAGE);
						txtDeleteRno.setText("");
						txtDeleteRno.requestFocus();
					}
				}
				else{
					JOptionPane.showMessageDialog(new JDialog(),"Only numerics.","alert",JOptionPane.ERROR_MESSAGE);
					txtDeleteRno.setText("");
					txtDeleteRno.requestFocus();
				}
			}
			catch(NumberFormatException e){
				JOptionPane.showMessageDialog(new JDialog(),"Cannot be empty,use only Integer.","alert",JOptionPane.ERROR_MESSAGE);
				txtDeleteRno.setText("");
				txtDeleteRno.requestFocus();
			}
		};
		btnDeleteSubmit.addActionListener(a2);

		setTitle("Delete Student");
		setSize(250,250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}