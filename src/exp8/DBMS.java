package exp8;

import java.awt.DisplayMode;
import java.sql.*;

/**
 * Created by ZNing on 16/6/27.
 * 1. 在数据库 在数据库 SQL Server或 mysql中建立 中建立 student表，包含编号（自增长）、 姓名、
 * 电话 、email 、家庭住址、邮政编码。然后写  GUI程序实现对学生的浏览、添加、删除、更新功能。
 *
 * 这是数据库中间件
 */

public class DBMS
{
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	public DBMS()
	{
		connect();
	}
	public void connect()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/zning?user=zning1994&password=zning1994");

		} catch (Exception e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public void getTableNow()
	{

		String strsql = "select * from student";
		try
		{
			pstmt = conn.prepareStatement(strsql);
			rs = pstmt.executeQuery();
		} catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

	}
	public ResultSet getresultset()//取得结果集
	{
		return rs;
	}
	public void InsertRow(String name,String tel,String email,String address,String postcode)
	{
		String strsql = "insert into student(姓名,电话,email,家庭住址,邮政编码) values(?,?,?,?,?)";
		try
		{
			pstmt=conn.prepareStatement(strsql);

			pstmt.setString(1,name);
			if(tel.equals("")==false)
				pstmt.setString(2,tel);
			else
				pstmt.setNull(2, Types.VARCHAR);
			if(email.equals("")==false)
				pstmt.setString(3,email);
			else
				pstmt.setNull(3, Types.VARCHAR);
			if(address.equals("")==false)
				pstmt.setString(4,address);
			else
				pstmt.setNull(4, Types.VARCHAR);
			if(postcode.equals("")==false)
				pstmt.setString(5,postcode);
			else
				pstmt.setNull(5, Types.VARCHAR);
			pstmt.executeUpdate();

			getTableNow();
		} catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public void DeleteRow(int RowID)
	{
		String strsql= "delete from student where 编号=? ";
		try
		{
			pstmt=conn.prepareStatement(strsql);
			pstmt.setInt(1, RowID);
			pstmt.executeUpdate();

			getTableNow();
		} catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public void disconnect()
	{
		try
		{
			if(rs!=null)
				rs.close();
			if(pstmt!=null)
				pstmt.close();
			if(conn!=null)
				conn.close();

		} catch (SQLException e)
		{
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
}
