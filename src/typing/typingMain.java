package typing;

import game.KillMouse;
import image.imageMain;
import typing.typingMain;
import voice.voiceDetail;

import javax.swing.*;

import analyse.Analyse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by springlustre on 2016/4/18.
 */
public class typingMain extends JFrame{
  JPanel contentPane;//默认面板
  JPanel jPanelMain = new JPanel();//主面板
  JButton btn1=new JButton("入门级别");
  JButton btn2=new JButton("简单级别");
  JButton btn3=new JButton("中等级别");
  JButton btn4=new JButton("困难级别");
  JLabel lb=new JLabel("请选择图像训练难度");
  BackgroundPanel bgp;
  public typingMain(){
    try {
      init();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }

  public void init() throws Exception{
	  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    contentPane = (JPanel) getContentPane();
    contentPane.setLayout(null);
    setSize(new Dimension(800,400));
    setTitle("图像训练II");
    btn1.setBounds(20, 130, 170, 80);
    btn1.setFont(new Font("宋体", Font.PLAIN, 28));
    btn2.setBounds(210, 130, 170, 80);
    btn2.setFont(new Font("宋体", Font.PLAIN, 28));
    btn3.setBounds(400, 130, 170, 80);
    btn3.setFont(new Font("宋体", Font.PLAIN, 28));
    btn4.setBounds(590, 130, 170, 80);
    btn4.setFont(new Font("宋体", Font.PLAIN, 28));
    lb.setFont(new Font("宋体", Font.PLAIN, 43));
    lb.setBounds(200,20,500,50);
    
    bgp=new BackgroundPanel(new ImageIcon(getClass().
			getClassLoader().getResource("typing/typingBg.png")).getImage());
    bgp.setBounds(0,0,800,400);
    
    contentPane.add(btn1);
    contentPane.add(btn2);
    contentPane.add(btn3);
    contentPane.add(btn4);
    contentPane.add(lb);
    contentPane.add(bgp);

    btn1.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          typingDetail frame = new typingDetail(10,15);
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
        }
      }
    );

    btn2.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          typingDetail frame = new typingDetail(8,15);
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
        }
      }
    );

    btn3.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          typingDetail frame = new typingDetail(6,20);
          
//          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        }
      }
    );

    btn4.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        	typingDetail frame = new typingDetail(3,20);
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
        }
      }
    );

  }

  static public void main(String[] args){
    typingMain frame = new typingMain();
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
 
  }

}


class BackgroundPanel extends JPanel  
{  
    Image im;  
    public BackgroundPanel(Image im)  
    {  
        this.im=im;  
        this.setOpaque(true);  
    }  
    //Draw the back ground.  
    public void paintComponent(Graphics g)  
    {  
        super.paintComponents(g);  
        g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);  
          
    }  
}  