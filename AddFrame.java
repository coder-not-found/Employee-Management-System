import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class AddFrame extends JFrame{
Container c;
JLabel lblAddRno,lblAddName,lblAddMarks;
JTextField txtAddRno,txtAddName,txtAddMarks;
JButton btnAddSave,btnAddBack;


AddFrame(){
c=getContentPane();
c.setLayout(new FlowLayout());
lblAddRno = new JLabel("Enter rno.");
lblAddName = new JLabel("Enter name");
lblAddMarks = new JLabel("Enter marks");
txtAddRno = new JTextField(10);
txtAddName = new JTextField(10);
txtAddMarks = new JTextField(10);
btnAddSave = new JButton("SAVE");
btnAddBack = new JButton("BACK");
c.add(lblAddRno);
c.add(txtAddRno);
c.add(lblAddName);
c.add(txtAddName);
c.add(lblAddMarks);
c.add(txtAddMarks);
c.add(btnAddSave);
c.add(btnAddBack);

ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnAddBack.addActionListener(a1);

ActionListener a2 = (ae) -> {
	try{	
		String sidorg = txtAddRno.getText();
		int sid = Integer.parseInt(sidorg);
		String sname = txtAddName.getText();
		String smarksorg = txtAddMarks.getText();
		int smarks = Integer.parseInt(smarksorg);
		if(sname.isEmpty()){
			JOptionPane.showMessageDialog(c,"Name cannot be empty.","alert",JOptionPane.ERROR_MESSAGE);
			txtAddName.requestFocus();
		}
		else if(sname.matches("^[a-zA-Z]*$") && smarksorg.matches("(^[0-9]|^[1-9][0-9]|^100)$")){
			System.out.println(sid+" "+sname+" "+smarks);
			int val = new DbHandler().addStudent(sid,sname,smarks);
			if(val != 0){
				JOptionPane.showMessageDialog(c,"Rno already exists,Try again.","alert",JOptionPane.ERROR_MESSAGE);
				txtAddName.setText("");
				txtAddRno.setText("");
				txtAddRno.requestFocus();
			}
		}
		else if(!smarksorg.matches("(^[0-9]|^[1-9][0-9]|^100)$")){
			JOptionPane.showMessageDialog(new JDialog(),"Marks between 0-100","alert",JOptionPane.ERROR_MESSAGE);
			txtAddMarks.setText("");
			txtAddMarks.requestFocus();
		}
		else{
			JOptionPane.showMessageDialog(new JDialog(),"Name has only alphabets.","alert",JOptionPane.ERROR_MESSAGE);
			txtAddName.setText("");
			txtAddName.requestFocus();
		}
	}
	catch(Exception e){
		JOptionPane.showMessageDialog(new JDialog(),"Cannot be empty,Integers only.","alert",JOptionPane.ERROR_MESSAGE);
		txtAddRno.setText("");
		txtAddRno.requestFocus();
	}
};
btnAddSave.addActionListener(a2);


setTitle("Add Student");
setSize(250,250);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);
}
}