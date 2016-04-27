package image;

import java.awt.*;

import javax.swing.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

public class imageMain extends JFrame implements Runnable {
	static int WIDTH=1200;
    static int HEIGHT=700;

	JPanel contentPane;//默认面板
	JPanel imgPane = new JPanel(); //显示图像面板
	JPanel titlePane = new JPanel(); //标题面板
	JPanel introPane = new JPanel(); //介绍信息面板

	JLabel lbTitle=new JLabel();//标题标签
	JLabel lbName=new JLabel();//名称标签
	JLabel lbIntro=new JLabel();//说明标签
	JLabel lbIntro2=new JLabel();//说明标签
	JLabel lbResult=new JLabel();//操作结果标签
	JLabel lbNowTime=new JLabel();//显示当前进行时间的标签

	JButton btnStart=new JButton();//开始按钮
	JButton btnStop=new JButton();//结束按钮
	JButton btnRestart=new JButton();//重新开始按钮

	Vector blocks = new Vector(); //存储方块♦
//	HashMap result = new HashMap();
//	result.put(1,2);
    HashMap<String ,Integer> result = new HashMap<String,Integer>();
    int timeCnt=0;
	boolean stop=false;

	/**
	 * 界面初始化
	 * @throws Exception
	 */
	public void init() throws Exception{
	 result.put("total", 0);
	 result.put("right", 0);
	 result.put("wrong",0);
	 contentPane = (JPanel) getContentPane();
     contentPane.setLayout(null);
     contentPane.setBackground(Color.WHITE);
     setSize(new Dimension(WIDTH, HEIGHT));
     setTitle("语音训练");
     /******************标题面板****************************/
     titlePane.setBounds(0, 2, WIDTH, (int) (HEIGHT * 0.15));
     titlePane.setBackground(Color.blue);
     titlePane.setLayout(null);
     titlePane.setBorder(BorderFactory.createEtchedBorder());
     lbTitle.setText("图像训练");
     lbTitle.setBounds(new Rectangle(2, 30, 400, 40));
     lbTitle.setFont(new Font("宋体", Font.PLAIN, 34));
     titlePane.add(lbTitle);
	 lbResult.setBounds(160, 60, 400, 40);
	 lbResult.setFont(new Font("宋体", Font.PLAIN, 25));
	 titlePane.add(lbResult);
		lbNowTime.setBounds(600, 60, 200, 40);
		lbNowTime.setFont(new Font("宋体", Font.PLAIN, 25));
		titlePane.add(lbNowTime);


     /******************图像面板****************************/
     imgPane.setBounds(new Rectangle(10, (int) (HEIGHT * 0.2), (int) (WIDTH * 0.65), (int) (HEIGHT * 0.8)));
	 imgPane.setBackground(Color.cyan);
     imgPane.setLayout(null);

     /******************说明面板****************************/
     introPane.setBounds(new Rectangle((int) (WIDTH * 0.7), (int) (HEIGHT * 0.2), (int) (WIDTH * 0.3), (int) (HEIGHT * 0.7)));
     introPane.setBackground(Color.green);
     introPane.setLayout(null);
     lbIntro.setBounds(new Rectangle(20, 30, 400, 200));
     lbIntro.setFont(new Font("宋体", Font.PLAIN, 36));
     lbIntro.setText("<html>"
			 + "(请用鼠标左键单击<br>"
			 + "蓝色方格,右键单击<br>"
			 + "橙色方格,左键双击<br>"
			 + "绿色方格)<br>"
			 + "</html>");
     introPane.add(lbIntro);
     lbIntro2.setBounds(new Rectangle(20, 280, 400, 50));
     lbIntro2.setFont(new Font("宋体", Font.PLAIN, 33));
     lbIntro2.setText("精准定位及运动功能");
     introPane.add(lbIntro2);

     btnStart.setBounds(new Rectangle(20, 400, 110, 40));
	 btnStart.setText("开始");
	 btnStart.addActionListener(new btnStartActionAdapter(this));
	 introPane.add(btnStart);
		btnRestart.setBounds(new Rectangle(20, 400, 110, 40));
		btnRestart.setText("重新开始");
		btnRestart.setVisible(false);
		btnRestart.addActionListener(new btnRestartActionAdapter(this));
		introPane.add(btnRestart);

     btnStop.setBounds(new Rectangle(180, 400, 110, 40));
     btnStop.setText("结束");
		btnStop.addActionListener(new btnStopActionAdapter(this));
		introPane.add(btnStop);


     contentPane.add(titlePane);
     contentPane.add(imgPane);
     contentPane.add(introPane);
	}

	/**
	 * 为方块♦绑定鼠标事件
	 */
	class MyMouseListener extends MouseAdapter {
		public boolean clickFlag;//记录是否已经执行过鼠标双击事件
		public int clickNum = 0; //判断是否执行双击事件

		public void mouseClicked(MouseEvent e) {
			final MouseEvent mouse=e;
			this.clickFlag = false;
			if (e.getButton() == e.BUTTON1) { //鼠标左键
				if (clickNum == 1) {//如果鼠标在规定的时间内已经被单击过一次，则说明这次是双击了，执行双击事件
					JButton bn = ((JButton) e.getSource());
					int total=result.get("total");
					result.put("total", total + 1);
					if(bn.getBackground()==Color.GREEN){
						int right=result.get("right");
						result.put("right", right + 1);
						bn.setVisible(false);
						removeBtnFromVector(bn);
					}else{
						int wrong=result.get("wrong");
						result.put("wrong", wrong + 1);
					}
					updateResult();
					clickFlag = true;
					clickNum = 0;
					return;
				}

				java.util.Timer timer = new java.util.Timer();//定义一个定时器

				timer.schedule(new java.util.TimerTask() {
					int n = 0;//记录定时器执行的次数

					@Override
					public void run() {
						// TODO Auto-generated method stub
						if (clickFlag) {//如果双击事件已经执行完毕，取消单击事件
							clickNum = 0;
							n = 0;
							this.cancel();
							return;
						}
						if (n == 1) {//如果规定的时间内未执行鼠标双击事件则执行鼠标单击事件
							JButton bn = ((JButton) mouse.getSource());
							int total=result.get("total");
							result.put("total", total + 1);
							if(bn.getBackground()==Color.BLUE) {
								bn.setVisible(false);
								removeBtnFromVector(bn);
								int right=result.get("right");
								result.put("right", right + 1);
							}else{
								int wrong=result.get("wrong");
								result.put("wrong", wrong + 1);
							}
							updateResult();
							clickFlag = true;
							clickNum = 0;
							n = 0;
							this.cancel();
							return;
						}
						n++;
						clickNum++;
					}
				}, new java.util.Date(), 200);
			}else if(e.getButton()==e.BUTTON3)
			{
				JButton bn = ((JButton) e.getSource());
				int total=result.get("total");
				result.put("total", total + 1);
				if(bn.getBackground()==Color.ORANGE) {
					bn.setVisible(false);
					removeBtnFromVector(bn);
					int right=result.get("right");
					result.put("right", right + 1);
				}else{
					int wrong=result.get("wrong");
					result.put("wrong", wrong + 1);
				}
				updateResult();
			}
		}
	}



	/**
	 *从vector中去除jbutton
	 */
	public void removeBtnFromVector(JButton btn){
		blocks.remove(btn);
		if(blocks.isEmpty()){
			nextPlay();
		}
	}

	/**
	 * 构造方法
	 */
	public imageMain(){
//	  setDefaultCloseOperation(EXIT_ON_CLOSE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  try {
		init();
	   } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	}


	/**
	 * 添加图像方块♦
	 * @param p
	 */
	public void addImgBlock(JPanel p,int n){
		int[][] location=toolUtil.getRandomLocation(p.getWidth(),p.getHeight(),60,40,n);//获取坐标
		for(int i=0;i<n;i++) {
			JButton btnBlock = new JButton(i+"");
			System.out.println(i+" "+location[i][0]+" "+location[i][1]);
			blocks.add(btnBlock);
			btnBlock.addMouseListener(new MyMouseListener());
			btnBlock.setBounds(location[i][0], location[i][1],60,50);
			if(location[i][2]==0){
				btnBlock.setBackground(Color.BLUE);
			}else if(location[i][2]==1){
				btnBlock.setBackground(Color.ORANGE);
			}else{
				btnBlock.setBackground(Color.GREEN);
			}
			imgPane.add(btnBlock);
		}
		SwingUtilities.updateComponentTreeUI(this);
	}


	public void updateResult(){
		int total=result.get("total");
		int right=result.get("right");
		int wrong=result.get("wrong");
		lbResult.setText("总共:"+total+" "+"正确:"+right+" "+"错误:"+wrong);
	}


	@Override
	public void run() {
		Thread t = new Thread(new displayThread());
		t.start();
	}

	/**
	 * 显示方块的线程
	 */
	class displayThread implements Runnable {
		public void run() {
			addImgBlock(imgPane, 10);
		}
	}

	/**设置Timer 1000ms实现一次动作 实际是一个线程
	 */
	class timeCountThread implements Runnable{
		public void run(){
			while(!stop) {
				int t=timeCnt;
				lbNowTime.setText("已进行:" + t + "s");
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					System.out.println(e);
				}
				timeCnt++;
			}
		}
	}


	/**
	 * 开始
	 */
	public void btnStart() {
		timeCnt=0;
		stop=false;
		Thread t = new Thread(this);
		t.start();
		Thread timeThread=new Thread(new timeCountThread());
		timeThread.start();
		btnStart.setVisible(false);
		btnRestart.setVisible(true);
	}

	/**
	 * 结束
	 */
	public void btnStop(){
		for(int i=0;i<blocks.size();i++){
			JButton btn=(JButton)blocks.get(i);
			btn.setVisible(false);
		}
		stop=true;
		btnStart.setVisible(true);
		btnRestart.setVisible(false);
	}

	/**
	 * 重新开始
	 */
	public void btnRestart(){
		for(int i=0;i<blocks.size();i++){
			JButton btn=(JButton)blocks.get(i);
			btn.setVisible(false);
		}
		timeCnt=0;
		Thread t = new Thread(this);
		t.start();
	}


	/**
	 * 新的一轮
	 */
	public void nextPlay(){
		Thread t = new Thread(this);
		t.start();
	}


	public static void main(String[] args){
//		try {
//		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//		} catch (Exception exception) {
//			exception.printStackTrace();
//		}
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


/**
 * start键的事件监听
 * @author springlustre
 *
 */
class btnStartActionAdapter implements ActionListener {
	private imageMain adapter;
	btnStartActionAdapter(imageMain adapter) {
		this.adapter = adapter;
	}
	public void actionPerformed(ActionEvent e) {
		adapter.btnStart();
	}
}

/**
 * stop键的事件监听
 * @author springlustre
 *
 */
class btnStopActionAdapter implements ActionListener {
	private imageMain adapter;
	btnStopActionAdapter(imageMain adapter) {
		this.adapter = adapter;
	}
	public void actionPerformed(ActionEvent e) {
		adapter.btnStop();
	}
}

/**
 * restart按钮的事件监听
 */
class btnRestartActionAdapter implements ActionListener{
	private imageMain adapter;
	btnRestartActionAdapter(imageMain adapter){
		this.adapter=adapter;
	}
	public void actionPerformed(ActionEvent e) {
		adapter.btnRestart();
	}
}