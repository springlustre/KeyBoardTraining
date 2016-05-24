package game;

import java.awt.*;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

import model.dataDao;

import java.util.*;
@SuppressWarnings("serial")
public class KillMouse extends JFrame  implements MouseListener,ActionListener,MouseMotionListener
{
	  Timer timer;
	  int grade = 1,success = 0,delay = 2000,total = 0,again;
	  int right=0,sum=0,mark=0;
	  JLabel chuxiancishu,dazhongcishu,gradeLabel,dishu,result,shu;
	  JPanel panel;
	  ImageIcon icon1,icon2,chui1,chui2;
	  Random generator;
	  Image image;
	  Toolkit tk;
	  String shubiao;
	  Cursor mycursor;
	  JLabel lbMark=new JLabel();
	  public KillMouse()
	  {
		  setTitle("打地鼠");
//			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//		  setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		  setBak();         //调用背景方法
		  tk = Toolkit.getDefaultToolkit()   ;  //获取工具箱
		  shu = new JLabel("game/chui1.png");
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

		  dishu = new JLabel(icon1);
		  dishu.setSize(80, 80);
		  dishu.setVisible(false);
		  dishu.addMouseListener(this);
		  dishu.setOpaque(false);
		  chuxiancishu=new JLabel();
		  chuxiancishu.setFont(new Font("幼圆", Font.BOLD, 34));
		  chuxiancishu.setBounds(222,43,100,30);
     	  dazhongcishu=new JLabel();
		  dazhongcishu.setFont(new Font("幼圆", Font.BOLD, 34));
		  dazhongcishu.setBounds(454,42,100,30);
		  gradeLabel=new JLabel();
		  gradeLabel.setFont(new Font("幼圆", Font.BOLD, 34));
		  gradeLabel.setBounds(642,40,100,30);
		  
		  lbMark.setBounds(240,400,200,50);
		  lbMark.setFont(new Font("幼圆",Font.BOLD,31));
		  lbMark.setForeground(Color.WHITE);
		  lbMark.setText("得分:");
		  panel = new JPanel();
		  panel.setLayout(null);
		  panel.setBounds(0,0,854,200);
		  panel.add(chuxiancishu);
		  panel.add(dazhongcishu);
		  panel.add(gradeLabel);
		  panel.add(lbMark);
		  panel.setOpaque(false);           //把JPanel设置为透明 这样就不会遮住后面的背景  这样你就能在JPanel随意加组件了
		  panel.addMouseMotionListener(this);
		  c.add(dishu);
		  c.add(panel);
		 
		  setResizable(false);
		  setSize(new Dimension(854, 480));
		  setVisible(true);
//		  JOptionPane.showMessageDialog(this,"本游戏共4关，地鼠出现20次以内打中地鼠15次则进入下一关。加油!","游戏规则"
//				  ,JOptionPane.CLOSED_OPTION);
		  timer.start();
		  
		  this.addWindowListener(new WindowAdapter() { // 窗口关闭事件监听与实现
				public void windowClosing(WindowEvent evt) {
					int saveChoose = JOptionPane.showConfirmDialog(null,"确定退出吗？","提示",JOptionPane.YES_NO_OPTION);
			  		  if(saveChoose==JOptionPane.YES_OPTION)
			  		  {
			  			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			  			save2File();
//			  			System.exit(0);
				      }else{
				    	  setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				      }
				}			 
			});
	  }
	  
	  
	  public void setBak()
	  {
		  ((JPanel)this.getContentPane()).setOpaque(false);
		  ImageIcon beijing = new ImageIcon(this.getClass()
				  .getClassLoader().getResource("game/dadishubeijing.png"));
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
		  right+=1;
		  mark=right*10*grade-(sum-success)*10*grade;
		  lbMark.setText("得分:"+mark);
		  if(success > 14)
		  {
			  delay -= 300;
			  if(delay < 200)
			  {
				  dishu.setVisible(false);
				  save2File();
				  timer.stop();
				  int a = JOptionPane.showConfirmDialog(this,"您打通关了，要重新来吗？","提示",JOptionPane.YES_NO_OPTION);
		  		  if(a==JOptionPane.YES_OPTION)
		  		  {
		  			  grade = 0;
		  			  delay = 2000;
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
	  		dishu.setLocation(602, 310);
	  	  else if(ran == 2)
	  		dishu.setLocation(375, 310);
	  	  else if(ran == 3)
	  		dishu.setLocation(137, 304);
	  	  else if(ran == 4)
	  		dishu.setLocation(588, 210);
	  	  else if(ran == 5)
	  		dishu.setLocation(375, 210);
	  	  else if(ran == 6)
	  		dishu.setLocation(145, 210);
	  	  else if(ran == 7)
	  		dishu.setLocation(583, 121);
	  	  else if(ran == 8)
	  		dishu.setLocation(376, 116);
	  	  else
	  		dishu.setLocation(168, 119);
	  	  dishu.setVisible(true);
	  	  total += 1;
	  	  sum+=1;
	  	  mark=right*10*grade-(sum-success)*10*grade;
		  lbMark.setText("得分:"+mark);
	  	  if(total > 20)
	  	  {
	  		  dishu.setVisible(false);
	  		  save2File();
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
			  	  delay = 2000;
				  dazhongcishu.setText(""+success);
				  gradeLabel.setText(""+grade);
	  		  }
	  		  else System.exit(0);
	  	  }
	  	  chuxiancishu.setText(""+total);
	  	  timer.start();
	  }


		public void save2File(){
			Long timeStamp=System.currentTimeMillis();
			String saveData=timeStamp+","+right+","+total+","+grade+","+mark+"\n";
			saveData+="\n";
			FileWriter writer = null;  
	        try {     
	            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件     
	            writer = new FileWriter("src/game/result.txt", true);     
	            writer.write(saveData);       
	        } catch (IOException e) {     
	            e.printStackTrace();     
	        } finally {     
	            try {     
	                if(writer != null){  
	                    writer.close();     
	                }  
	            } catch (IOException e) {     
	                e.printStackTrace();     
	            }     
	        } 
	        dataDao.saveGame2DB(timeStamp,right,total,grade,mark);
		}
	  
	  public static void main(String[] args)
	  {
	  	KillMouse frame = new KillMouse();
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
//	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  }
}