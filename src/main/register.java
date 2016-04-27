package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
 
import javax.swing.*;
 
public class register implements ActionListener {
	/**
	 * @author Laycher {@link http://laycher.cn}
	 */
	JFrame jf;
	JTextField jtf;
	JPasswordField jpf, jpf2;
 
	public register() {
		jf = new JFrame("注册");
		jf.setLayout(new GridLayout(6, 1));
		jf.add(new JPanel());
		JLabel jl1 = new JLabel(" 用  户  名：");
		jtf = new JTextField(12);
		JPanel jp1 = new JPanel();
		jp1.add(jl1);
		jp1.add(jtf);
		jf.add(jp1);
 
		JLabel jl2 = new JLabel(" 密        码：");
		jpf = new JPasswordField(12);
		JPanel jp2 = new JPanel();
		jp2.add(jl2);
		jp2.add(jpf);
		jf.add(jp2);
 
		JLabel jl3 = new JLabel("确认密码：");
		jpf2 = new JPasswordField(12);
		JPanel jp3 = new JPanel();
		jp3.add(jl3);
		jp3.add(jpf2);
		jf.add(jp3);
 
		JPanel jp4 = new JPanel();
		JButton jb1 = new JButton("确认注册");
		jb1.addActionListener(this);
		JButton jb2 = new JButton("取消");
		jb2.addActionListener(this);
		jp4.add(jb1);
		jp4.add(jb2);
		jf.add(jp4);
 
		jf.setResizable(false);
		jf.setVisible(true);
		jf.setSize(300, 240);
		jf.setLocation(300, 200);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
 
	public static void main(String[] args) {
		new register();
	}
 
	@Override
	public void actionPerformed(ActionEvent e) {
		String comm = e.getActionCommand();
		if (comm.equals("确认注册")) {
			// jf.dispose();
			if ("".equals(jtf.getText())
					|| "".equals(new String(jpf.getPassword()))
					|| jpf.getPassword() == null
					|| "".equals(new String(jpf2.getPassword()))
					|| jpf2.getPassword() == null) {
				final JFrame jf = new JFrame("错误");
				JLabel jl = new JLabel("用户名 或者 密码 不能为空！");
				JPanel jp1 = new JPanel();
				JPanel jp2 = new JPanel();
				jp1.add(jl);
				jf.add(jp1);
				JButton jb = new JButton("确定");
				jb.addActionListener(new ActionListener() {
 
					@Override
					public void actionPerformed(ActionEvent e) {
						jf.dispose();
					}
 
				});
				jp2.add(jb);
				jf.add(jp2);
				jf.setLayout(new GridLayout(2, 1));
				jf.setResizable(false);
				jf.setVisible(true);
				jf.pack();
				jf.setLocation(400, 300);
			} else {
				String s = jtf.getText() + "&&" + new String(jpf.getPassword())
						+ "\r\n";// '\r'+'\n'也可以，"\r"+"\n"也可以
				String name = jtf.getText() + "&&";
				File file = new File("reg.txt");
				try {
					file.createNewFile(); // 当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件。
				} catch (IOException e2) {
					e2.printStackTrace();
				}
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
					if (i == -1) { // 如果文档中没有，要注册到文件中
						if (new String(jpf.getPassword()).equals(new String(
								jpf2.getPassword()))) {
							try {
								FileOutputStream fos = new FileOutputStream(
										file, true);
								// true就是追加，false就是替换。
								fos.write(s.getBytes());
								fos.close();
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							int a = JOptionPane.showConfirmDialog(jf, "注册成功！\n"
									+ "用户名 ： " + jtf.getText() + "\n密码 ： "
									+ new String(jpf.getPassword())
									+ "\n点确定转入登录界面", "注册结果",
									JOptionPane.OK_CANCEL_OPTION);
							if (a == 0) {
								jf.dispose();
								new login();
							}
						} else {
							JOptionPane.showConfirmDialog(
									jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
									"两次密码不一样!\n请重新输入密码！", "密码不一样",
									JOptionPane.CLOSED_OPTION);
							jpf.setText(null);
							jpf2.setText(null);
							jpf.requestFocus();// 光标回来
						}
					} else {
 
						JOptionPane.showConfirmDialog(
								jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
								"用户名已经被注册!\n请换一个用户名重新注册！", "错误",
								JOptionPane.CLOSED_OPTION);
						jtf.setText(null);
						jpf.setText(null);
						jpf2.setText(null);
						jtf.requestFocus();// 光标回来
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