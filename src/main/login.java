package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;

import javax.swing.*;
 
public class login implements ActionListener {
	JFrame jf;
	JTextField jtf;
	JPasswordField jpf;
 
	public login() {
		jf = new JFrame("登录");
		jf.setLayout(new GridLayout(5, 1));
		jf.add(new JPanel());
		JLabel jl1 = new JLabel("用户名：");
		jtf = new JTextField(12);
		JPanel jp1 = new JPanel();
		jp1.add(jl1);
		jp1.add(jtf);
		jf.add(jp1);
 
		JLabel jl2 = new JLabel("  密  码： ");
		jpf = new JPasswordField(12);
		JPanel jp2 = new JPanel();
		jp2.add(jl2);
		jp2.add(jpf);
		jf.add(jp2);
 
		JPanel jp3 = new JPanel();
		JButton jb1 = new JButton("我要注册");
		jb1.addActionListener(this);
		JButton jb2 = new JButton("登录");
		jb2.addActionListener(this);
		JButton jb3 = new JButton("取消");
		jb3.addActionListener(this);
		jp3.add(jb1);
		jp3.add(jb2);
		jp3.add(jb3);
		jf.add(jp3);
 
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setSize(300, 200);
		jf.setLocation(300, 200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
	public static void main(String[] args) {
		new login();
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		String comm = e.getActionCommand();
		if (comm.equals("我要注册")) { // 已经能够确认了的字符串放在equals的后面
			jf.dispose();
			new register();
		} else if (comm.equals("登录")) {
			if ("".equals(jtf.getText())
					|| "".equals(new String(jpf.getPassword()))
					|| jpf.getPassword() == null) {
				JOptionPane.showConfirmDialog(
						jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
						"用户名 或者 密码 不能为空！!\n请重新输入！", "错误",
						JOptionPane.DEFAULT_OPTION);
				jtf.setText(null);
				jpf.setText(null);
				jtf.requestFocus();// 光标回来
			} else {
				String s = jtf.getText() + "&&" + new String(jpf.getPassword());
				String name = jtf.getText() + "&&";
				File file = new File("reg.txt");
				try { // 读文件的注册信息
					FileInputStream fis = new FileInputStream(file);
					String s1 = "";
					byte[] b = new byte[1024];
					while (true) {
						int i = fis.read(b);
						if (i == -1)
							break;
						s1 = s1 + new String(b, 0, i);
					}
					fis.close();// 关闭流
					int i = s1.indexOf(name);
					int j = s1.indexOf(s);
					if (i == -1) { // 如果文档中没有，说明没有此用户名
						JOptionPane.showConfirmDialog(
								jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
								"没有此用户名!\n请重新 登录 或者 注册！", "错误",
								JOptionPane.ERROR_MESSAGE);// 此处有两个选项，还未解决问题
						jtf.setText(null);
						jpf.setText(null);
						jtf.requestFocus();// 光标回来
					} else {
						if (j == -1) {
							JOptionPane.showConfirmDialog(
									jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
									"密码错误!\n请重新输入密码！", "错误",
									JOptionPane.DEFAULT_OPTION);
							jpf.setText(null);
							jpf.requestFocus();// 光标回来
						} else {
						    main frame = new main();
						    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
						    Dimension frameSize = frame.getSize();
						    if (frameSize.height > screenSize.height) {
						      frameSize.height = screenSize.height;
						    }
						    if (frameSize.width > screenSize.width) {
						      frameSize.width = screenSize.width;
						    }
						    frame.setLocation((screenSize.width - frameSize.width) / 2,
						      (screenSize.height - frameSize.height) / 2);
						    frame.setVisible(true);
						    jf.dispose();
//							JOptionPane.showConfirmDialog(
//									jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
//									"登录成功！\n" + "用户名 ： " + jtf.getText()
//											+ "\n密码 ： "
//											+ new String(jpf.getPassword()),
//									"登录结果", JOptionPane.DEFAULT_OPTION);
						}
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		} else if (comm.equals("取消")) {
			System.exit(0);
		}
 
	}
 
}