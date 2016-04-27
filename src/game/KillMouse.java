package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import java.util.*;
@SuppressWarnings("serial")
public class KillMouse extends JFrame  implements MouseListener,ActionListener,MouseMotionListener
{
	  Timer timer;
	  int grade = 1,success = 0,delay = 1000,total = 0,again;
	  JLabel chuxiancishu,dazhongcishu,gradeLabel,dishu,result,shu;
	  JPanel panel;
	  ImageIcon icon1,icon2,chui1,chui2;
	  Random generator;
	  Image image;
	  Toolkit tk;
	  String shubiao;
	  Cursor mycursor;
	  public KillMouse()
	  {
		  setTitle("打地鼠");
//			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		  setBak();         //调用背景方法
		  tk = Toolkit.getDefaultToolkit()   ;  //获取工具箱

		  shu = new JLabel("game/chui1.png");
//		  image = tk.getImage(shu.getText());
			image = tk.getImage(this.getClass().getClassLoader().getResource("game/chui1.png"));
		  mycursor = tk.createCustomCursor(image,new Point(10,10),"xxx");
		  setCursor(mycursor);
		  timer = new Timer(delay,this);
		  generator = new Random();
		  Container c = getContentPane();
		  chui1 = new ImageIcon(this.getClass().getClassLoader().getResource("game/chui1.png"));
		  chui2 = new ImageIcon(this.getClass().getClassLoader().getResource("game/chui2.png"));
		  icon1 = new ImageIcon(this.getClass().getClassLoader().getResource("game/dishu.png"));
		  icon2 = new ImageIcon(this.getClass().getClassLoader().getResource("game/datou.png"));
//			chui1 = new ImageIcon("image/chui1.png");
//			chui2 = new ImageIcon("image/chui2.png");
//			icon1 = new ImageIcon("image/dishu.png");
//			icon2 = new ImageIcon("image/datou.png");
		  dishu = new JLabel(icon1);
		  dishu.setSize(80, 80);
		  dishu.setVisible(false);
		  dishu.addMouseListener(this);
		  dishu.setOpaque(false);
//		  chuxiancishu = new JLabel("0",new ImageIcon("image/chuxiancishu.png"),SwingConstants.CENTER);
			chuxiancishu=new JLabel();
			chuxiancishu.setText("出现次数");
		  chuxiancishu.setFont(new Font("幼圆", Font.BOLD, 16));
		  chuxiancishu.setSize(146, 40);
//		  dazhongcishu = new JLabel("0",new ImageIcon("image/dazhongcishu.png"),SwingConstants.CENTER);
			dazhongcishu=new JLabel();
			dazhongcishu.setText("击中次数");
		  dazhongcishu.setFont(new Font("幼圆", Font.BOLD, 16));
		  dazhongcishu.setSize(146, 40);
//		  gradeLabel = new JLabel("0",new ImageIcon("image/dangqiandengji.png"),SwingConstants.CENTER);
			gradeLabel=new JLabel();
			gradeLabel.setText("等级");
		  gradeLabel.setFont(new Font("幼圆", Font.BOLD, 16));
		  gradeLabel.setSize(146, 40);
		  panel = new JPanel();
		  panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		  panel.setPreferredSize(new Dimension(438, 375));
		  panel.add(chuxiancishu);
		  panel.add(dazhongcishu);
		  panel.add(gradeLabel);
		  panel.setOpaque(false);           //把JPanel设置为透明 这样就不会遮住后面的背景  这样你就能在JPanel随意加组件了
		  panel.addMouseMotionListener(this);
		  c.add(dishu);
		  c.add(panel);
		  setResizable(false);
		  setSize(438, 375);
		  setVisible(true);
//		  JOptionPane.showMessageDialog(this,"本游戏共4关，地鼠出现20次以内打中地鼠15次则进入下一关。加油!","游戏规则"
//				  ,JOptionPane.CLOSED_OPTION);
		  timer.start();
	  }
	  public void setBak()
	  {
		  ((JPanel)this.getContentPane()).setOpaque(false);
		  ImageIcon beijing = new ImageIcon();//new ImageIcon("image/beijing.jpg");
		  JLabel background = new JLabel(beijing);
		  this.getLayeredPane().add(background, new Integer(Integer.MIN_VALUE)); 
		  background.setBounds(0, 0, beijing.getIconWidth(), beijing.getIconHeight()); 
	  }
	  public void mouseMoved(MouseEvent e)
	  {
	  }
	  public void mouseDragged(MouseEvent e) {

		}
	  public void mousePressed(MouseEvent e)
	  {
		  shu.setText("game/chui2.png");
		  image = tk.getImage(this.getClass().getClassLoader().getResource("game/chui2.png"));
		  mycursor = tk.createCustomCursor(image,new Point(10,10),"xxx"); 
		  setCursor(mycursor);
		  dishu.setIcon(icon2);
		  dishu.setVisible(true);
		  success += 1;
		  if(success > 14)
		  {
			  delay -= 300;
			  if(delay < 200)
			  {
				  dishu.setVisible(false);
				  timer.stop();
				  int a = JOptionPane.showConfirmDialog(this,"您打通关了，要重新来吗？","提示",JOptionPane.YES_NO_OPTION);
		  		  if(a==JOptionPane.YES_OPTION)
		  		  {
		  			  grade = 0;
		  			  delay = 1000;
		  		  }
		  		  else System.exit(0);
			  }
			  grade += 1;
			  total = 0;
			  success = 0;
			  timer.stop();
			  dishu.setVisible(false);
			  JOptionPane.showMessageDialog(this,"进入第" +grade+ "关  加油!","提示",JOptionPane.CLOSED_OPTION);
			  shu = new JLabel("game/chui1.png");
			  image = tk.getImage(this.getClass().getClassLoader().getResource("game/chui1.png"));
			  mycursor = tk.createCustomCursor(image,new Point(10,10),"xxx"); 
			  setCursor(mycursor);
			  dishu.setIcon(icon1);
		  }
		  chuxiancishu.setText(""+total);
		  dazhongcishu.setText(""+success);
		  gradeLabel.setText(""+grade);
		  timer.start();
	  }
	  public void mouseReleased(MouseEvent e)
	  {
		  shu.setText("game/chui1.png");
		  image = tk.getImage(this.getClass().getClassLoader().getResource("game/chui1.png"));
		  mycursor = tk.createCustomCursor(image,new Point(10,10),"xxx"); 
		  setCursor(mycursor);
		  dishu.setVisible(false);
		  dishu.setIcon(icon1);
	  }
	  public void mouseEntered(MouseEvent e) {}
	  public void mouseExited(MouseEvent e) {}
	  public void mouseClicked(MouseEvent e) {}
	  public void actionPerformed(ActionEvent e)
	  {
	  	  int ran = generator.nextInt(9);
	  	  if(ran == 1)
	  		dishu.setLocation(322, 204);
	  	  else if(ran == 2)
	  		dishu.setLocation(185, 204);
	  	  else if(ran == 3)
	  		dishu.setLocation(48, 203);
	  	  else if(ran == 4)
	  		dishu.setLocation(298, 133);
	  	  else if(ran == 5)
	  		dishu.setLocation(162, 133);
	  	  else if(ran == 6)
	  		dishu.setLocation(22, 133);
	  	  else if(ran == 7)
	  		dishu.setLocation(311, 63);
	  	  else if(ran == 8)
	  		dishu.setLocation(186, 63);
	  	  else
	  		dishu.setLocation(56, 63);
	  	  dishu.setVisible(true);
	  	  total += 1;
	  	  if(total > 20)
	  	  {
	  		  dishu.setVisible(false);
			  timer.stop();
	  		  int again = JOptionPane.showConfirmDialog(this,"很遗憾，你输了，要再来一次吗？","提示",JOptionPane.YES_NO_OPTION);
	  		  if(again==JOptionPane.YES_OPTION)
	  		  {
	  			  shu = new JLabel("game/chui1.png");
				  image = tk.getImage(this.getClass().getClassLoader().getResource("game/chui1.png"));
				  mycursor = tk.createCustomCursor(image,new Point(10,10),"xxx"); 
				  setCursor(mycursor);
	  			  grade = 1;
			  	  success = 0;
			  	  total = 0;
			  	  delay = 1000;
				  dazhongcishu.setText(""+success);
				  gradeLabel.setText(""+grade);
	  		  }
	  		  else System.exit(0);
	  	  }
	  	  chuxiancishu.setText(""+total);
	  	  timer.start();
	  }
	  public static void main(String[] args)
	  {
	  	  KillMouse killmouse = new KillMouse();
	  	  killmouse.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
}