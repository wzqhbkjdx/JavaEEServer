package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

public class MyDatabase {
	private static Connection conn = null;
	private static Statement stmt = null;
	
	//连接数据库
	private static void initConnection() {
		
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://localhost:3306/javademo?" //直接连接到某个数据库
                + "user=root&password=871028&useUnicode=true&characterEncoding=UTF8";
        String url1 = "jdbc:mysql://localhost/mysql?"
                + "user=root&password=871028&useUnicode=true&characterEncoding=UTF8";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("成功加载MySQL驱动程序");
    		conn = DriverManager.getConnection(url1);
    		System.out.println("成功连接数据库");
    		stmt  = conn.createStatement();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
	}
	
	
	private static void createDatabase(String databaseName) {
		
		initConnection();
		//判断数据库是否被创建过，没有创建再创建
		String check = "SELECT * FROM information_schema.SCHEMATA where SCHEMA_NAME=" + "'" + databaseName + "'";
		String sql = "CREATE DATABASE " + databaseName;
		try {
//			boolean result = stmt.execute(check);
			
				stmt.executeUpdate(sql);
				System.out.println("创建数据库" + databaseName + "成功");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void insertIntoDatabase(Map<String, String> map) {
		initConnection();
		try {
			
			Set<String> set = map.keySet();
			for(String str : set) {
				try {
					stmt.executeUpdate("insert into newsDetail(NO,path) values('" + str + "','/Users/bym/xmldoc/2016001.xml')");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public static void queryFromDatabase(String talbeName) {
		
        
        try {
        	
			String usehappy = "use happy";
			int result = stmt.executeUpdate(usehappy);
			System.out.println("use happy");
			//删除表DROP TABLE IF EXISTS tbl_name;
			//删除表中的记录 delete from newsDetail where NO=2016001;
//			stmt.executeUpdate("create table IF NOT EXISTS newsDetail(NO char(80),path text,primary key(NO))");
//			stmt.executeUpdate("insert into newsDetail(NO,path) values('2016001','/Users/bym/xmldoc/2016001.xml')");
//			stmt.executeUpdate("insert into newsDetail(NO,path) values('2016002','/Users/bym/xmldoc/2016002.xml')");
//			System.out.println("成功添加记录");
			//select path from newsDetail where NO = '2016001'
			if(result != -1) {
				String sql = "select path from newsDetail where NO = '2016001'";
				ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
				while (rs.next()) {
                  System.out.println(rs.getString(1));// 入如果返回的是int类型可以用getInt()
				}
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        

	}
	
	public static void main(String[] args){
		createDatabase("testhahahaha");
	}

}
