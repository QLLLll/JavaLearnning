package com.ll;
import java.sql.*;
import java.io.*;


public class NovelOperation {

	public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";

	public static final String DBURL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC";

	public static final String DBUSER = "root";

	public static final String DBPWD = "1234";

	private static Connection conn = null;
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
		
		Class.forName(DBDRIVER);
		
		/*MyNovel myNovel=new MyNovel();
		
		File f=new File("H:\\迅雷下载\\Novel\\我的合成天赋txt");
		

		InputStream is=new FileInputStream(f);
		myNovel.setAuthor("朱可夫");
		myNovel.setNovelName(f.getName());
		myNovel.setPath(f.getPath());
		myNovel.setIs(is);
		myNovel.setNovelType(1);
		

		if(insert(myNovel)) {
			
			System.out.println("OK");
		}else {
			System.out.println("fail");
		}
		
		myNovel.getIs().close();*/
		
		MyNovel novel=getNovel(1);
		
		System.out.println(novel.getNovelName()+"\n"+novel.getAuthor()+"\n"+
		novel.getNovelType()+"\n"+novel.getPath()+"\n"+novel.getIs().available());
		
	}
	
	
	public static boolean insert(MyNovel novel) throws SQLException {
		
		if(novel==null||novel.getIs()==null) {
			
			return false;
			
		}
		
		if(conn!=null&&!conn.isClosed())
			conn.close();
		
		conn=DriverManager.getConnection(DBURL,DBUSER,DBPWD);
		
		String sql="insert into tbl_Novel(NovelName,NovelType,Author,NovelPath,NovelContent)values(?,?,?,?,?)";
		
		
		PreparedStatement prpStat=conn.prepareStatement(sql);
		
	    prpStat.setString(1,novel.getNovelName());
	    prpStat.setInt(2, novel.getNovelType());
	    prpStat.setString(3, novel.getAuthor());
	    prpStat.setString(4, novel.getPath());
	    prpStat.setBinaryStream(5, novel.getIs());
	    
		return prpStat.executeLargeUpdate()>=1;
		
		
	}

	public static MyNovel getNovel(int id) throws SQLException {
		
		MyNovel novel=new MyNovel();
		
		if(conn!=null&&!conn.isClosed())
			conn.close();
		
		conn=DriverManager.getConnection(DBURL,DBUSER,DBPWD);
		
		String sql="select NovelID,NovelName,Author,NovelType,NovelPath,NovelContent from tbl_Novel where NovelID=?";
		
		
		PreparedStatement prpStat=conn.prepareStatement(sql);
		
		prpStat.setInt(1, id);
		
		ResultSet rs=prpStat.executeQuery();
		rs.next();
		 novel.setNovelName(rs.getString(2));
		 novel.setAuthor(rs.getString(3));
		 novel.setNovelType(rs.getInt(4));
		 novel.setIs(rs.getBinaryStream(6));
		 novel.setPath(rs.getString(5));
		
		return novel;
		
	}
	
}
