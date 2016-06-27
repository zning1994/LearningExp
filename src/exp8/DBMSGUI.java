package exp8;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.*;

/**
 * Created by ZNing on 16/6/27.
 * 1. 在数据库 在数据库 SQL Server或 mysql中建立 中建立 student表，包含编号（自增长）、 姓名、
 * 电话 、email 、家庭住址、邮政编码。然后写  GUI程序实现对学生的浏览、添加、删除、更新功能。
 *
 * 这是GUI运行端
 */

public class DBMSGUI
{
	public static void main(String[] args)
	{
		DBFrame frame =new DBFrame();

	}
}
class DBFrame extends JFrame
{
	DBMS dbms;
	JTable table;
	DefaultTableModel model;
	JButton del;
	JButton ins;
	JTextField txtName;
	JTextField txtTel;
	JTextField txtEmail;
	JTextField txtAdress;
	JTextField txtPostcode;
	private static final int DEFAULT_WIDTH = 400;
	private static final int DEFAULT_HEIGHT =300;
	public DBFrame()
	{
		setTitle("实验1：JDBC DBMS实现   By: ZNing");
		dbms =new DBMS();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(400,DEFAULT_HEIGHT));
		settableModel();//设置表样式
		table.setAutoCreateRowSorter(true);
		JScrollPane jsp =new JScrollPane(table);
		jsp.setPreferredSize((new Dimension(400, 0)));
		add(jsp,BorderLayout.CENTER);
		JPanel BottomPanel=new JPanel();
		BottomPanel.setLayout(new GridLayout(2, 1));
		add(BottomPanel,BorderLayout.SOUTH);
		JPanel ButtonPanel =new JPanel();
		ButtonPanel.setLayout(new GridLayout(1, 2));

		ins=new JButton("添加");
		del=new JButton("删除");
		del.addActionListener(new DelListener());
		ins.addActionListener(new InsListener());
		ButtonPanel.add(ins);
		ButtonPanel.add(del);

		BottomPanel.add(ButtonPanel);



		JPanel InputPanel =new JPanel();
		InputPanel.setLayout(new GridLayout(2, 5));
		InputPanel.add(new Label("姓名"));
		InputPanel.add(new Label("电话"));
		InputPanel.add(new Label("Email"));
		InputPanel.add(new Label("家庭住址"));
		InputPanel.add(new Label("邮政编码"));

		InputPanel.add(txtName=new JTextField());
		InputPanel.add(txtTel=new JTextField());
		InputPanel.add(txtEmail=new JTextField());
		InputPanel.add(txtAdress=new JTextField());
		InputPanel.add(txtPostcode=new JTextField());

		BottomPanel.add(InputPanel);
		addEventListener();
		pack();


		setVisible(true);
		display();
	}
	public void addEventListener()
	{
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent event)
			{
				dbms.disconnect();
			}
		});
	}
	public void settableModel()
	{ if (table == null) {
		table = new JTable();}
		String[] columns = {"编号", "姓名", "电话", "email", "家庭住址", "邮政编码"};
		int[] columnWidth = { 30, 10, 30, 40, 50, 20 };
		model = new DefaultTableModel(columns, 0);
		table.setModel(model);// 设置表格数据模型
		TableColumnModel columnModel = table.getColumnModel();// 获取列模型
		int count = columnModel.getColumnCount();// 获取列数量
		for (int i = 0; i < count; i++) {// 遍历列
			TableColumn column = columnModel.getColumn(i);// 获取列对象
			column.setPreferredWidth(columnWidth[i]);// 以数组元素设置列的宽度
		}
	}
	public void display()
	{
		dbms.getTableNow();
		int   rowcount   =   model.getRowCount()-1;
		if(rowcount>=0)
			for(int i=rowcount;i>=0;i--)
			{
				model.removeRow(i);
			}
		ResultSet rs;
		rs=dbms.getresultset();
		try
		{
			while(rs.next())
			{
				int id =rs.getInt(1);
				String name=rs.getString(2);
				String tel= rs.getString(3);
				String email =rs.getString(4);
				String address = rs.getString(5);
				String postcode =rs.getString(6);
				Vector vRow =new Vector();
				vRow.add(Integer.valueOf(id));
				vRow.add(name);
				vRow.add(tel);
				vRow.add(email);
				vRow.add(address);
				vRow.add(postcode);
				model.addRow(vRow);
			}
		} catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	class DelListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int RowNow=table.getSelectedRow();
			String  ID=model.getValueAt(RowNow, 0).toString();
			dbms.DeleteRow(Integer.valueOf(ID));
			model.removeRow(RowNow);
			display();
		}
	}
	class InsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			dbms.InsertRow(txtName.getText(), txtTel.getText(), txtEmail.getText()
					, txtAdress.getText(), txtPostcode.getText());
			display();
		}
	}
}