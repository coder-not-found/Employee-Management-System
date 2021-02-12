
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import org.hibernate.*;
import org.hibernate.cfg.*;
import java.io.*;
import java.util.*;

class MainFrame extends JFrame{
	Container c;
	JButton btnAdd,btnView,btnUpdate,btnDelete,btnFeedback;
	TextArea taData;

	MainFrame(){
		c=getContentPane();
		c.setLayout(new FlowLayout());

		btnAdd = new JButton("ADD");
		btnView = new JButton("VIEW");
		btnUpdate = new JButton("UPDATE");
		btnDelete = new JButton("DELETE");
		taData = new TextArea(5,30);
		btnFeedback = new JButton("FeedBack");
		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);
		c.add(taData);
		c.add(btnFeedback);

		ActionListener a1 = (ae) -> {
			AddFrame a = new AddFrame();
			dispose();
		};
		btnAdd.addActionListener(a1);

		ActionListener a2 = (ae) -> {
			ViewFrame a = new ViewFrame();
			dispose();
		};
		btnView.addActionListener(a2);

		ActionListener a3 = (ae) -> {
			UpdateFrame a = new UpdateFrame();
			dispose();
		};
		btnUpdate.addActionListener(a3);

		ActionListener a4 = (ae) -> {
			DeleteFrame a = new DeleteFrame();
			dispose();
		};
		btnDelete.addActionListener(a4);

		ActionListener a5 = (ae) -> {
			String data = taData.getText();
			if(data.isEmpty()){
				JOptionPane.showMessageDialog(new JDialog(),"Feedback cannot be empty.","alert",JOptionPane.ERROR_MESSAGE);
				taData.requestFocus();
			}
			else{
				FeedbackFrame a = new FeedbackFrame(data);
				dispose();
			}
			
		};
		btnFeedback.addActionListener(a5);

		setTitle("Student App");
		setSize(300,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String args[]){
		MainFrame m = new MainFrame();
	}
}

class DbHandler {
	public int addStudent(int sid, String sname,int smarks) {
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		int error = 0; 
		Session session = null;
		Transaction t = null;
		try {
			session = factory.openSession();
			System.out.println("add session open");

			t = session.beginTransaction();

			Stu s = new Stu();
			s.setSid(sid);
			s.setSname(sname);
			s.setSmarks(smarks);
			session.save(s);
			t.commit();
			System.out.println("Record inserted");
			JOptionPane.showMessageDialog(new JDialog(),"Record inserted.");
		} catch (Exception e) {
			System.out.println("Issue:" + e);
			t.rollback();
			error = 1;
		} finally {
			session.close();
			System.out.println("add session closed.");
		}
		return error;
	}

	public String viewStudent() {
		try{
			StringBuffer sb = new StringBuffer();
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");

			SessionFactory factory = cfg.buildSessionFactory();

			Session session = null;
			Transaction t = null;
			try {
				session = factory.openSession();
				System.out.println("view session open");

				java.util.List<Stu> e = new ArrayList<>();
				e = session.createQuery("from Stu").list();
				for (Stu m : e) {
					System.out.println(m.getSid() + "  " + m.getSname()+ "  " + m.getSmarks());
					int sid = m.getSid();
					String sname = m.getSname();
					int smarks = m.getSmarks();
					sb.append(sid + "  " + sname + "  " + smarks + "\n");
				}
			} catch (Exception e) {
				System.out.println("Issue:" + e);
			} finally {
				session.close();
				System.out.println("view session close");
			}

			return sb.toString();
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(new JDialog(),"Internet Issue","alert",JOptionPane.ERROR_MESSAGE);
			System.out.println("Issue:"+e);
			return "error";
		}
	}

	public int deleteStudent(int sid){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");
		int check = 0;
		SessionFactory factory = cfg.buildSessionFactory();

		Session session = null;
		Transaction t = null;
		try {
			session = factory.openSession();
			System.out.println("delete session open");
			t=session.beginTransaction();

			Stu s = (Stu)session.get(Stu.class,sid);
			if(s!=null){
				session.delete(s);
				t.commit();
				System.out.println("Record deleted.");
				JOptionPane.showMessageDialog(new JDialog(),"Record deleted.");
				check=1;
			}
			else{ System.out.println("Does Not Exist"); }
		}
		catch(Exception e){
			System.out.println("Issue:"+e);
			t.rollback();
		}

		finally{
		session.close();
		System.out.println("delete session closed");
		}
		return check;
	}

	public int updateStudent(int sid,String sname,int smarks){
		Configuration cfg = new Configuration();
		cfg.configure("hibernate.cfg.xml");

		SessionFactory factory = cfg.buildSessionFactory();
		int check = 0;
		Session session = null;
		Transaction t = null;
		try{
			session = factory.openSession();
			System.out.println("update session open");

			t=session.beginTransaction();
			Stu s = (Stu)session.get(Stu.class,sid);
			if(s!=null){
				s.setSname(sname);
				s.setSmarks(smarks);
				session.save(s);
				t.commit();
				System.out.println("Record Updated");
				JOptionPane.showMessageDialog(new JDialog(),"Record updated.");
				check = 1;
			}
			else { System.out.println("Does not exist."); }
		}
		catch(Exception e){
			System.out.println("Issue:"+e);
			t.rollback();
		}
		finally{
			session.close();
			System.out.println("update session closed");
		}
		return check;
	}
}