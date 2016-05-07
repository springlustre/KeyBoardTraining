package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user {
	
	public static String login(String username,String password) throws SQLException, FileNotFoundException{
		dbConn h = new dbConn();
	    h.connSQL();
	    String s="select * from user where email='"+username+"' or mobile='"+username+
	    		"'"+"or name='"+username+"'";
	    ResultSet rs = h.selectSQL(s);
//	    h.deconnSQL();
	    int flag=0;
	    String name="";
	    String info="";
	    while(rs.next()){
	    	String pwd = rs.getString("password");
	    	if(pwd.equals(password)){
	    		flag=1;
	    		name= rs.getString("name");
	    		info=rs.getString("id")+"\u0001"
	    				+rs.getString("name")+"\u0001"
	    				+rs.getString("mobile")+"\u0001"
	    				+rs.getString("email")+"\u0001"
	    				+rs.getString("password")+"\u0001"
	    				+rs.getString("sex")+"\u0001"
	    				+rs.getString("age")+"\u0001"
	    				+rs.getString("address")+"\u0001";
	    	}
	    	break;
	    }
	    h.deconnSQL();
	    if(flag==0){
	    	return null;
	    }else{
	    	File file = new File("user.txt");
			try {
				file.createNewFile(); // 当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件。
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			PrintWriter output=new PrintWriter(file);
			output.println(info);
			output.close();
	    	return name;
	    }
	}
	
	/**1:成功
	 * 2：失败
	 * 0：已被注册
	 * */
	public static int register(String username,String password) throws SQLException, FileNotFoundException{
		dbConn h = new dbConn();
	    h.connSQL();
	    String s="select * from user where email='"+username+"' or mobile='"+username+
	    		"'"+"or name='"+username+"'";
	    ResultSet rs = h.selectSQL(s);
	    int flag=0;
	    String name="";
	    String info="";
	    while(rs.next()){
	    	if(Integer.parseInt(rs.getString("id"))>0)
	    		flag=1;
	    }
		if (flag == 0) {
			String insert = "insert into user(name,password) values('"
					+ username + "','" + password + "')";
			if (h.insertSQL(insert) == true) {
				h.deconnSQL();
				return 1;
			}else{
				return 0;
			}
		} else {
			return 2;
	    }
	}
	
	
	public static void main(String[] args) throws SQLException, FileNotFoundException {
		// TODO Auto-generated method stub
//		System.out.println(login("王春泽","mmaassdd"));
		System.out.println(register("13520358713","aaa"));
	}

}
