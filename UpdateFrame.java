import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class UpdateFrame extends JFrame
{
Container c;
JLabel labRno, labName, labM1, labM2, labM3;
JTextField txtRno, txtName, txtM1, txtM2, txtM3;

JButton btnUpdate, btnBack;

UpdateFrame()
{
c = getContentPane();
c.setLayout(new FlowLayout());

labRno = new JLabel("Enter Rno");
txtRno = new JTextField(30);
labName = new JLabel("Enter name");
txtName = new JTextField(30);
labM1 = new JLabel("Enter marks 1");
txtM1 = new JTextField(30);
labM2 = new JLabel("Enter marks 2");
txtM2 = new JTextField(30);
labM3 = new JLabel("Enter marks 3");
txtM3 = new JTextField(30);
btnUpdate = new JButton("Update");
btnBack = new JButton("Back");


c.add(labRno);
c.add(txtRno);
c.add(labName);
c.add(txtName);
c.add(labM1);
c.add(txtM1);
c.add(labM2);
c.add(txtM2);
c.add(labM3);
c.add(txtM3);
c.add(btnUpdate);
c.add(btnBack);

ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);

ActionListener a2 = (ae) -> {
	int rno = Integer.parseInt(txtRno.getText());
	String name = txtName.getText();
	int m1 = Integer.parseInt(txtM1.getText());
	int m2 = Integer.parseInt(txtM2.getText());
	int m3 = Integer.parseInt(txtM3.getText());
	try 
	{
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentproject", "root", "abc456");
		String sql = "UPDATE student SET m1=?, m2=?, m3=?, name=? WHERE rno=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, rno);
		pst.setString(2, name);
		pst.setInt(3, m1);
		pst.setInt(4, m2);
		pst.setInt(5, m3);
		int r = pst.executeUpdate();
		if (r == 1)
		{
			JOptionPane.showMessageDialog(c, "Record updated ");
		}
		else
		{
			JOptionPane.showMessageDialog(c, "Record does not exists ");
		}
	}
	catch(SQLException e)
	{
		JOptionPane.showMessageDialog(c, "issue" + e);
	}

};
btnUpdate.addActionListener(a2);

setTitle("Add Student");
setSize(350, 350);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);

}
}