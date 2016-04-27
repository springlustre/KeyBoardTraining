package typing;
import javax.swing.*;
import java.awt.*;
import java.lang.Thread;
import java.awt.event.*;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Collections;
import java.util.Random;

class Boom extends JLabel{
	MainFrame BoomFrame;
	JLabel Kill;
	
	BoomT KillStart;
	int x;
	int y;
	public Boom(MainFrame th,JLabel Kh)
	{
		super("asgsa");
		x=Kh.getX();
		y=Kh.getY();
		this.BoomFrame=th;
		this.Kill=Kh;
		this.setBounds(x,this.getHeight(),100,100);
		this.setFont(new Font("SansSerif", 0,23));
		BoomFrame.getContentPane().add(this,null);
	//Download:CodeFans.net
	//	this.setForeground(Color.WHITE);
	//	this.setIcon(new ImageIcon("GIF/Boom.GIF"));
		KillStart=new BoomT(this);
	}
	class BoomT extends Thread{
		Boom BT;
		int y;
		public BoomT(Boom one)
		{
			BT=one;
			y=BT.getHeight();
			this.start();
		}
		public void run()
		{
			while(true)
			{
				y=y-12;
				BT.Kill.setBounds(100,y,100,100);
				if(y<0)
				{
					this.stop();
				}
			    try
				{
					this.sleep(400);
				}
				catch(Exception e)
				{
					e.getMessage();
				}
			}
		}// end run
	}//end inner 
}

class MyJLabel extends JLabel{
	MainFrame MyLFrame;
	char keyP;
    Jt one;
//  static Object Obj = new Object();
  class Jt extends Thread{
    JLabel Jtj;
    int speed;
    int x,y;
    int xun;
    int killTop;
    boolean Goin;
    JLabel Kill;
    public Jt(JLabel JJ)
    {
      Jtj=JJ;
      Jtj.setFont(new Font("SansSerif", 0, 1));
      Jtj.setForeground(Color.WHITE);
      x=(int)(Math.random()*500);
      y=0;
      xun=1;
      
      Goin=true;
    }
    public void run()
    {
//    	synchronized(Obj){
         speed=(int)(MyLFrame.speed);
        while(xun>0)
        { 
           if(Goin==false)
           {
           	
           	killTop=killTop-12;	
           	Kill.setBounds(Jtj.getX()+12,killTop,Jtj.getWidth(),Jtj.getHeight());
           	synchronized(this)
           	{
           		if(Kill.getY()<(Jtj.getY()+30))
	           	{
	           		
	           	   Kill.setVisible(false);
	           	   Jtj.setVisible(false);
	           	   this.stop();
	           	}
           	}
           	
          // 	System.out.println(Kill.getY()+"   "+Jtj.getY());
           }
           y=y+4;
          Jtj.setBounds(x,y, 100, 100);
           synchronized(this){
           	if(Goin==true)
              {
          
           
               if(MyLFrame.qufen==false)
               { 
                  if(Jtj.getText().equals(String.valueOf(MyLFrame.yeschar)))
		            {
		            	
		            //	Jtj.setVisible(false);
		            	MyLFrame.yeschar='0';
		            //	new Boom(MyLFrame,Jtj);
		                Kill=new JLabel();
		                killTop=MyLFrame.getHeight();
		                Kill.setIcon(new ImageIcon("GIF/Boom.gif"));
		                Kill.setForeground(Color.WHITE);
		                MyLFrame.getContentPane().add(Kill,null);
		             //   MyLFrame.getContentPane().add(new JLabel("gasgd"),null);
		                Goin=false;
		            	//this.stop();
		            }
	           }
	           else
	           {
	            	if(Jtj.getText().equalsIgnoreCase(String.valueOf(MyLFrame.yeschar)))
		            {
		            	Goin=false;
		            //	Jtj.setVisible(false);
		            	MyLFrame.yeschar='0';
		            // 	 MyLFrame.getContentPane().add(new JLabel().setBounds(100,100,100,100),null);
		            	Kill=new JLabel("aa");
		                killTop=MyLFrame.getHeight();
		                Kill.setIcon(new ImageIcon("GIF/Boom.gif"));
		                Kill.setForeground(Color.WHITE);
		                MyLFrame.getContentPane().add(Kill,null);
		             //	new Boom(MyLFrame,Jtj);
		              // 	this.stop();
		            }
	           }
            }
	      
         	 if(y>500)
		        {
		          Jtj.setVisible(false);
		          xun=0;
		          MyLFrame.bad=MyLFrame.bad+1;
		          MyLFrame.badJ.setText("你漏掉了： "+String.valueOf(MyLFrame.bad));
		         this.stop();
		        }
		        try
		        {
		          sleep(speed);
		        }
		        catch(Exception e)
		        {
		          e.toString();
		        }
        }
      }
    }
  }
 // } //synchronized end

  
  public MyJLabel(char name,MainFrame This)
  {
    super(String.valueOf(name));
    one = new Jt(this);
    MyLFrame=This;
  }
}
//-------------------------------------------------------------------------------
class MainFrame extends JFrame {
  int bad,count,speed,start;
  boolean qufen;
  char yeschar;
  JTextField badJ = new JTextField();
  threads two = new threads(this);
  Vector onon = new Vector();
  Enumeration e;

  //==================================================================
  public class threads extends Thread{
    MainFrame tt;
    Icon thIcon;
    threads(MainFrame zz)
    {
      tt=zz;
      this.setDaemon(false);
    }
    public void run()
    {
      while(true)
      {

         if(e.hasMoreElements())
         {
           MyJLabel x =(MyJLabel)e.nextElement();
           tt.getContentPane().add(x);
           x.setVisible(true);
           if(qufen==true)
           {
           	thIcon = new ImageIcon("GIF/"+ x.getText() +".GIF");
           }
           else
           {
         	if(((int)x.getText().charAt(0))>94)
         	{
         	  thIcon = new ImageIcon("GIF/"+ x.getText() +"_s.GIF");
         	}
         	else
         	{
         	   thIcon = new ImageIcon("GIF/"+ x.getText() +".GIF");
         	}
         
           }
           x.setIcon(thIcon);
//           Font f = new Font();
     //      f.deriveFont(22);
//           x.setFont(f);
           x.one.start();
         }
         else
         {
         	
         }
        try
        {
          this.sleep(tt.start);
        }
        catch(Exception e)
        {
          e.toString();
        }
      } //end while
    }  //end run()
  } //end inner
   //---------------------- go ---------------------------------00
  public MainFrame(int star,int cou,int spee,boolean big) {
  	this.getContentPane().setBackground(Color.WHITE);
     start = star;
     count=cou;
     speed= spee;
     bad = 0;
     yeschar=1;
     qufen = big;
     char ss;
     int ff;
    System.out.println(this.getFont());
     for(int x=1;x<this.count+1;x++)
     {
        
        ff=(int)(Math.random()*25);
        if(Math.random()*10>5)
        {
            ss=(char)(ff+97);	
        }
        else
        {
        	ss=(char)(ff+65);
        }
       onon.addElement(new MyJLabel(ss,this));
       
     }
      
   //   Collections.sort(onon);
      e = onon.elements();
      two.start();
    try {

      jbInit();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    this.setSize(800,600);
    this.setVisible(true);
    this.setSize(800,600);
  }
	  void jbInit() throws Exception {
	    this.getContentPane().setLayout(null);
	    this.getContentPane().add(badJ,null);
	    this.addKeyListener(new MainFrame_this_keyAdapter(this));
	    badJ.setFocusable(false);
	    badJ.setBounds(0,0,100,30);
	  }
  
  void this_keyPressed(KeyEvent e) {
      this.yeschar=e.getKeyChar();
 }
 
}

//===========================StartGUI==============================
class startFrame extends JFrame {
  int iStart,iCount,iSpeed;
  JTextField txtStart = new JTextField();
  JTextField txtCount = new JTextField();
  JLabel jLabel1 = new JLabel();
  JLabel jLabel2 = new JLabel();
  JLabel jLabel3 = new JLabel();
  JTextField txtSpeed = new JTextField();
  JButton cmdOk = new JButton();
  JRadioButton oneR = new JRadioButton("不分大小写");
  JRadioButton twoR = new JRadioButton("分大小写");
  ButtonGroup select = new ButtonGroup();
  JButton about = new JButton("About");
  

  public startFrame() {
    try {
      jbInit();
     
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }
  private void jbInit() throws Exception {

  	  select.add(oneR);
      select.add(twoR);
    cmdOk.setBounds(new Rectangle(119, 114, 104, 28));
    cmdOk.setText("开始");
    about.setBounds(new Rectangle(223, 114, 104, 28));
    
    about.addMouseListener(new startFrame_about_mouseAdapter(this));
    cmdOk.addMouseListener(new startFrame_cmdOk_mouseAdapter(this));
    txtSpeed.setText("30");
    txtSpeed.setBounds(new Rectangle(86, 73, 149, 23));
    
    jLabel3.setFont(new Font("SansSerif", 0, 15));
    jLabel3.setForeground(Color.red);
    jLabel3.setText("下落速度");
    jLabel3.setBounds(new Rectangle(8, 74, 99, 24));
    jLabel2.setFont(new Font("SansSerif", 0, 15));
    jLabel2.setForeground(Color.red);
    jLabel2.setText("字母个数");
    jLabel2.setBounds(new Rectangle(8, 36, 99, 24));
    jLabel1.setFont(new Font("SansSerif", 0, 15));
    jLabel1.setForeground(Color.red);
    jLabel1.setDebugGraphicsOptions(0);
    jLabel1.setText("出现速度");
    jLabel1.setBounds(new Rectangle(8, 6, 99, 24));
    txtStart.setSelectionStart(0);
    txtStart.setText("1000");
    txtStart.setBounds(new Rectangle(86, 5, 149, 23));
    this.getContentPane().setLayout(null);
    this.getContentPane().setBackground(UIManager.getColor("CheckBoxMenuItem.background"));
    this.setSize(new Dimension(336, 339));
    txtCount.setText("30");
    txtCount.setBounds(new Rectangle(86, 36, 149, 23));
    this.getContentPane().add(txtStart, null);
    this.getContentPane().add(txtCount, null);
    this.getContentPane().add(txtSpeed, null);
    this.getContentPane().add(jLabel1, null);
    this.getContentPane().add(jLabel2, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(cmdOk, null);
    this.getContentPane().add(about,null);
    oneR.setBounds(new Rectangle(7, 105, 110, 25));
    twoR.setBounds(new Rectangle(7, 131, 108, 24));
    txtStart.addKeyListener(new startFrame_txtStart_keyAdapter(this));
    txtCount.addKeyListener(new startFrame_txtCount_keyAdapter(this));
   // txtSpeed.addKeyListener(new startFrame_txt_keyAdapter());
    this.getContentPane().add(oneR, null);
    this.getContentPane().add(twoR, null);
    oneR.setSelected(true);
      	UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
  	SwingUtilities.updateComponentTreeUI(this);
  }


  void cmdOk_mouseClicked(MouseEvent e) {
      if(txtStart.getText().equals("")||txtCount.getText().equals("")||txtSpeed.getText().equals(""))
      {
      	System.out.println(iStart);
      	JOptionPane.showConfirmDialog(this,"请填写所有消息！","提示",JOptionPane.YES_OPTION,JOptionPane.ERROR_MESSAGE);
        return;
      }
      else
      {
          new MainFrame(Integer.parseInt(txtStart.getText()),Integer.parseInt(txtCount.getText()),Integer.parseInt(txtSpeed.getText()),oneR.isSelected());

      }
  }
//==================================================
public static void main(String[] args) {
		  	startFrame onoG = new startFrame();
		  	onoG.setVisible(true);
		  	onoG.setSize(400,200);
		  }
//==================================================
void txtStartChange(KeyEvent e)
{
	if(e.getKeyCode()==e.VK_BACK_SPACE)
	{
		
	}
	
}
void txtCountChange(KeyEvent e)
{
  System.out.println();
}
void aboutClicked(MouseEvent e)
{
	new JOptionPane().showMessageDialog(this,"S1T30 QQ57209367,大家可以和我讨论哦！","我的小东西",JOptionPane.INFORMATION_MESSAGE);
}
}

// ==============================Event==================================
class startFrame_cmdOk_mouseAdapter extends MouseAdapter {
  startFrame adaptee;

  startFrame_cmdOk_mouseAdapter(startFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void mouseClicked(MouseEvent e) {
    adaptee.cmdOk_mouseClicked(e);

  }
}
class MainFrame_this_keyAdapter extends KeyAdapter {
  MainFrame adaptee;

  MainFrame_this_keyAdapter(MainFrame adaptee) {
    this.adaptee = adaptee;
  }
  public void keyPressed(KeyEvent e) {
    adaptee.this_keyPressed(e);
  }
 }
class startFrame_txtStart_keyAdapter extends KeyAdapter{
      startFrame adaptee;
      startFrame_txtStart_keyAdapter(startFrame add)
      {
      	this.adaptee=add;
      }
      public void keyPressed(KeyEvent e)
      {
      	adaptee.txtStartChange(e);
      }
}
class startFrame_txtCount_keyAdapter extends KeyAdapter{
      startFrame adaptee;
      startFrame_txtCount_keyAdapter(startFrame add)
      {
      	this.adaptee=add;
      }
      public void keyPressed(KeyEvent e)
      {
      	 	System.out.println("asdfsad");
      }
}
class startFrame_about_mouseAdapter extends MouseAdapter{
	startFrame adap;
	startFrame_about_mouseAdapter(startFrame add){
		this.adap=add;
	}
	public void mouseClicked(MouseEvent e)
	{
		adap.aboutClicked(e);
	}
}