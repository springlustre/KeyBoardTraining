package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
 






import java.sql.SQLException;

import javax.swing.*;

import model.user;
 
public class register implements ActionListener {
	/**
	 * @author wangchunze
	 */
	private int WIDTH=900;
	private int HEIGHT=600;
	JFrame jf;
	JTextField jtf;
	JPasswordField jpf, jpf2;
	BackgroundPanel bgp;  
	public register() {
		jf = new JFrame("注册");
		jf.setLayout(null);
		jf.setIconImage(new ImageIcon(getClass().
				getClassLoader().getResource("main/zhucebeijing.png")).getImage()); 
		jf.add(new JPanel());
		bgp=new BackgroundPanel(new ImageIcon(getClass().
				getClassLoader().getResource("main/zhucebeijing.png")).getImage());
        bgp.setBounds(0,0,WIDTH,HEIGHT);  
		jtf = new JTextField(18);
		jtf.setPreferredSize(new Dimension(280, 30));
		jtf.setFont(new Font("宋体", Font.PLAIN, 22));
		JPanel jp1 = new JPanel();
		jp1.setBounds(340, 184, 300, 90);
		jp1.setOpaque(false);
		jp1.add(jtf);
		jf.add(jp1);
 
		jpf = new JPasswordField(18);
		jpf.setPreferredSize(new Dimension(280, 30));
		jpf.setFont(new Font("宋体", Font.PLAIN, 22));
		JPanel jp2 = new JPanel();
		jp2.setBounds(340, 242, 300, 90);
		jp2.setOpaque(false);
		jp2.add(jpf);
		jf.add(jp2);
 
		jpf2 = new JPasswordField(18);
		jpf2.setPreferredSize(new Dimension(280, 30));
		jpf2.setFont(new Font("宋体", Font.PLAIN, 22));
		JPanel jp3 = new JPanel();
		jp3.setBounds(340, 298, 300, 90);
		jp3.setOpaque(false);
		jp3.add(jpf2);
		jf.add(jp3);
 
		JPanel jp4 = new JPanel();
		jp4.setBounds(300, 350, 300, 90);
		jp4.setOpaque(false);
		JButton jb1 = new JButton("确认注册");
		jb1.addActionListener(this);
		JButton jb2 = new JButton("取消");
		jb2.addActionListener(this);
		JButton jb3 = new JButton("返回登录");
		jb3.addActionListener(this);
		jp4.add(jb1);
		jp4.add(jb2);
		jp4.add(jb3);
		jf.add(jp4);

		jf.add(bgp);
		jf.setSize(WIDTH, HEIGHT);
		jf.setResizable(false);
		jf.setVisible(true);

		// 设置显示位置
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = jf.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		jf.setLocation((screenSize.width - frameSize.width) / 2,
				(screenSize.height - frameSize.height) / 2);
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
				String password =new String(jpf.getPassword());
				String password2=new String(jpf2.getPassword());
				String name = jtf.getText();
				if(!password.equals(password2)){
					JOptionPane.showConfirmDialog(
							jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
							"两次密码不一样!\n请重新输入密码！", "密码不一样",
							JOptionPane.CLOSED_OPTION);
					jpf.setText(null);
					jpf2.setText(null);
					jpf.requestFocus();// 光标回来
				}else{
					int registerResult = 0;
					try {
						registerResult = user.register(name,password);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(registerResult==1){
						int a = JOptionPane.showConfirmDialog(jf, "注册成功！\n"
								+ "用户名 ： " + jtf.getText() + "\n密码 ： "
								+ new String(jpf.getPassword())
								+ "\n点确定转入登录界面", "注册结果",
								JOptionPane.OK_CANCEL_OPTION);
						if (a == 0) {
							jf.dispose();
							new login();
						}
					}else if(registerResult==0){
						JOptionPane.showConfirmDialog(
								jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
								"注册失败!\n请稍后再试！", "错误",
								JOptionPane.CLOSED_OPTION);
						jtf.setText(null);
						jpf.setText(null);
						jpf2.setText(null);
						jtf.requestFocus();// 光标回来
					}else if(registerResult==2){
						JOptionPane.showConfirmDialog(
								jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
								"用户名已经被注册!\n请换一个用户名重新注册！", "错误",
								JOptionPane.CLOSED_OPTION);
						jtf.setText(null);
						jpf.setText(null);
						jpf2.setText(null);
						jtf.requestFocus();// 光标回来
					}
				}			
			}
		}else if(comm.equals("返回登录")){
			jf.dispose();
			new login();
		}else{
			System.exit(0);
		}
	}
}
