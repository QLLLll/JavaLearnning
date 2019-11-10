package com.ll;

import java.sql.*;

public class ConnectionDemo1 {

	public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";

	public static final String DBURL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";

	public static final String DBUSER = "root";

	public static final String DBPWD = "1234";

	private static Connection conn = null;

	public static void main(String[] args) {

		try {

			Class.forName(DBDRIVER);
			System.out.println("OK");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		conn = null;

		try {
			conn = DriverManager.getConnection(DBURL, DBUSER, DBPWD);

			System.out.println("connection ok");
			// add(4,"mm",0);
			//update(4, "mmmmm");
			delete(1);
			select();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public static void select() {
		String sql = "select * from tbl_1";// 查询usrInfo表中的信息

		try {
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = (ResultSet) stmt.executeQuery(sql);// 得到的是结果的集合
			System.out.println("--------------------------------");
			System.out.println("ID" + "\t" + "姓名" + "\t" + "性别");
			System.out.println("--------------------------------");
			while (rs.next()) {
				String name = rs.getString("tbl_name");
				int id = rs.getInt("id");
				int gender = rs.getByte("tbl_sex");
				System.out.println(id + "\t" + name + "\t" + gender);
			}
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void add(int id, String name, int gender) {

		String sql = "insert into tbl_1(id,tbl_name,tbl_sex) values(?,?,?)";// 向usrInfo表中插入数据
		// String sql="insert into usrInfo(age,gender,username)
		// values('"+age+"','"+gender+"','"+name+"')";
		try {
			PreparedStatement preStmt = (PreparedStatement) conn.prepareStatement(sql);
			preStmt.setInt(1, id);
			preStmt.setString(2, name);
			preStmt.setInt(3, gender);// 和上面的注释的一块组成另外一种插入方法
			preStmt.executeUpdate();
			System.out.println("插入数据成功！");
			preStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void update(int id, String name) {

		String sql = "Update tbl_1  set tbl_name=? where id=?";

		try {

			PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(sql);

			stmt.setString(1, name);
			stmt.setInt(2, id);

			stmt.executeUpdate();

			stmt.close();

		} catch (SQLException e) {

			e.printStackTrace();

		}

	}

	
	public static void delete(int id) {
		
		//String sql="delete from tbl_1 where id="+id;
		String sql="delete from tbl_1 where id=?";
		PreparedStatement prpaStatm=null;
		try {
			prpaStatm = conn.prepareStatement(sql);
			prpaStatm.setInt(1, id);
			//prpaStatm.setString(1, id);
			
			prpaStatm.executeUpdate(sql);
			
			prpaStatm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
