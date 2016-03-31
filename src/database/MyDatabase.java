package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

import datamodel.NewsItemForClient;
import utils.DateGetter;

/**
 * 删除表DROP TABLE IF EXISTS tbl_name;
 * 删除表中的记录 delete from newsDetail where NO=2016001;
 * 创建表：stmt.executeUpdate("create table IF NOT EXISTS newsDetail(NO char(80),path text,primary key(NO))");
 * 插入数据：stmt.executeUpdate("insert into newsDetail(NO,path) values('2016001','/Users/bym/xmldoc/2016001.xml')");
 * 插入数据：stmt.executeUpdate("insert into newsDetail(NO,path) values('2016002','/Users/bym/xmldoc/2016002.xml')");
 * 查询记录：select path from newsDetail where NO = '2016001'
 * 清空表数据：truncate table newsDetail;
 * @author bym
 *
 * create table if not existis newsItem();
 * create table IF NOT EXISTS newsDetail(id integer primary key,NO char(80),path text);
 * create table if not exists newsItem(id integer primary key auto_increment,title text,newsDetailLink text,picLink text,pubDate text,original text,)
 * 设置主键自增长：alter table newsItem modify id integer auto_increment ;
 * 获取表的最后一天数据：select * from newsDetail order by id desc limit 1;
 * 插入新闻列表：create table if not exists newsItem(id integer auto_increment,title text,newsDetailLink text,picLink text,pubDate bigInt(20) primary key,original text,time timestamp default CURRENT_TIMESTAMP, detailNo text);
 * create table if not exists newsItem(id integer auto_increment,title text,newsDetailLink text,picLink text,pubDate bigInt(20),original text,time timestamp default CURRENT_TIMESTAMP, detailNo text,primary key(id,pubDate));
 * insert into newsDetail(NO,path) values('2016001','/Users/bym/xmldoc/2016001.xml')
 * 
 * 查询某id的pubdate最大值：select max(pubDate) from newsItem where id = '50';
 */



public class MyDatabase {
	
	
	private static Connection conn = null;
	private static Statement stmt = null;
	private static int result;
	
	//连接数据库
	private static void initConnection() {
		
        // MySQL的JDBC URL编写方式：jdbc:mysql://主机名称：连接端口/数据库的名称?参数=值
        // 避免中文乱码要指定useUnicode和characterEncoding
        // 执行数据库操作之前要在数据库管理系统上创建一个数据库，名字自己定，
        // 下面语句之前就要先创建javademo数据库
        String url = "jdbc:mysql://localhost:3306/javademo?" //直接连接到某个数据库
                + "user=root&password=871028&useUnicode=true&characterEncoding=UTF8";
        String url1 = "jdbc:mysql://localhost/mysql?"
                + "user=root&password=871028&useUnicode=true&characterEncoding=UTF8&useSSL=false";
        try {
        	Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("成功加载MySQL驱动程序");
    		conn = DriverManager.getConnection(url1);
    		System.out.println("成功连接数据库");
    		stmt  = conn.createStatement();
    		String usehappy = "use happy";
			result = stmt.executeUpdate(usehappy);
			System.out.println("use happy");
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
	}
	
	public static long getMaxPubdate(int category){
		initConnection();
		long maxPubdate = 0;
		try {
			if(result != -1) {
				stmt.executeUpdate("create table if not exists newsItem(id integer primary key auto_increment, "
						+ "title text,newsDetailLink text,picLink text,pubDate bigInt(20), original text,time timestamp default CURRENT_TIMESTAMP, detailNo text)");
				String sql = "select max(pubDate) from newsItem where category = '" + category + "'";//select max(pubDate) from newsItem where id = '50';
				ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
				while (rs.next()) {
					maxPubdate = rs.getLong(1);
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
		return maxPubdate;
	}
	
	public static void insertNewsItems(List<NewsItemForClient> list){
		initConnection();
		try {
			if(result != -1) {
				stmt.executeUpdate("create table if not exists newsItem(id integer primary key auto_increment, "
						+ "title text,newsDetailLink text,picLink text,pubDate bigInt(20), original text,time timestamp default CURRENT_TIMESTAMP, detailNo text)");
				for(NewsItemForClient item : list) {
					stmt.executeUpdate("insert into newsItem(title,newsDetailLink,picLink,pubDate,original,detailNo,category)"    
							+ "values('" + item.getTitle() + "','" + item.getNewsDetailLink() + "','" + item.getPicLink() + "','" + DateGetter.parsePubdateToLong(item.getPubDate()) + "','" + item.getOriginal() + "','" + item.getDetailNo() + "','" + item.getCategory() +"')");
					System.out.println("成功保存一条记录");
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
	
	
	
	
	/**
	 * 将新闻详情的xml文件路径保存到数据库中
	 * @param map
	 */
	public static void insertIntoDatabase(Map<String, String> map) {
		initConnection();
		try {
			if(result != -1) {
				stmt.executeUpdate("create table IF NOT EXISTS newsDetail(id integer primary key,NO char(80),path text)");
				Set<String> set = map.keySet();
				for(String str : set) {
					try {
						stmt.executeUpdate("insert into newsDetail(NO,path) values('" + str + "','" + map.get(str) + "')");
					} catch (SQLException e) {
						e.printStackTrace();
					}
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
	
	
	public static String queryFromDatabase(String number) {
		String xmlPath = null;
		initConnection();
        try {
        	
        	try {
					
				if(result != -1) {
					stmt.executeUpdate("create table IF NOT EXISTS newsDetail(id integer primary key,NO char(80),path text)");
					String sql = "select path from newsDetail where NO = '" + number + "'";
					ResultSet rs = stmt.executeQuery(sql);// executeQuery会返回结果的集合，否则返回空值
					while (rs.next()) {
						xmlPath = rs.getString(1);
						System.out.println(xmlPath);// 如果返回的是int类型可以用getInt()
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
        	
        } catch(Exception e) {
        	e.printStackTrace();
        } finally {
        	try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        
        return xmlPath;

	}
	
	private static void createDatabase(String databaseName) {
		
		initConnection();
		//判断数据库是否被创建过，没有创建再创建
		String check = "SELECT * FROM information_schema.SCHEMATA where SCHEMA_NAME=" + "'" + databaseName + "'";
		String sql = "CREATE DATABASE " + databaseName;
		try {
			//boolean result = stmt.execute(check);
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
	
	public static void main(String[] args){
//		System.out.println(getMaxPubdate());
	}

}








