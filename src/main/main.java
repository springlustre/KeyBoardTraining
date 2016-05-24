package main;

import game.KillMouse;
import image.imageMain;
import typing.typingMain;
import voice.voiceMain;

import javax.swing.*;

import analyse.Analyse;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by springlustre on 2016/4/18.
 */
public class main extends JFrame{
  JPanel contentPane;//默认面板
  JPanel jPanelMain = new JPanel();//主面板
  JButton btn1=new JButton("语音练习");
  JButton btn2=new JButton("图像练习");
  JButton btn3=new JButton("按键练习");
  JButton btn4=new JButton("休闲游戏");
  JButton btn5=new JButton("数据分析");
  public main(){
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
    setSize(new Dimension(900,450));
    btn1.setBounds(20, 80, 200, 50);
    btn1.setFont(new Font("宋体", Font.PLAIN, 28));
    btn2.setBounds(320, 80, 200, 50);
    btn2.setFont(new Font("宋体", Font.PLAIN, 28));
    btn3.setBounds(20, 260, 200, 50);
    btn3.setFont(new Font("宋体", Font.PLAIN, 28));
    btn4.setBounds(320, 260, 200, 50);
    btn4.setFont(new Font("宋体", Font.PLAIN, 28));
    btn5.setBounds(550, 260, 200, 50);
    btn5.setFont(new Font("宋体", Font.PLAIN, 28));
    contentPane.add(btn1);
    contentPane.add(btn2);
    contentPane.add(btn3);
    contentPane.add(btn4);
    contentPane.add(btn5);

    btn1.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          voiceMain frame = new voiceMain();
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
          imageMain frame = new imageMain();
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
          typingMain frame = new typingMain();
          
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
        }
      }
    );
    
    btn5.addActionListener(
    	      new ActionListener() {
    	        public void actionPerformed(ActionEvent e) {
    	        	Analyse frame = new Analyse();
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
 
  }

}
