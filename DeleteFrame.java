import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class DeleteFrame extends JFrame
{
Container c;
JLabel labRno;
JTextField txtRno;
JButton btnDelete, btnBack;

DeleteFrame()
{

c = getContentPane();
c.setLayout(new FlowLayout());

labRno = new JLabel("Enter Rno");
txtRno = new JTextField(30);
btnDelete = new JButton("Delete");
btnBack = new JButton("Back");

c.add(labRno);
c.add(txtRno);
c.add(btnDelete);
c.add(btnBack);

ActionListener a1 = (ae) -> {
MainFrame a = new MainFrame();
dispose();
};
btnBack.addActionListener(a1);


ActionListener a2 = (ae) ->  {
	int rno = Integer.parseInt(txtRno.getText());
	try
	{
		DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentproject", "root", "abc456");
		String sql = "Delete from student where rno=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setInt(1, rno);
		int r = pst.executeUpdate();
		if (r == 1)
		{
			JOptionPane.showMessageDialog(c, "Record Deleted");
		}
		else
		{
			JOptionPane.showMessageDialog(c, "Record does not exists");
		}

	}
	catch(SQLException e)
	{
		JOptionPane.showMessageDialog(c, "issue " + e);
	}

};
btnDelete.addActionListener(a2);

setTitle("Delete Student");
setSize(350, 350);
setLocationRelativeTo(null);
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setVisible(true);



}
}