package voice;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class voiceMain extends JFrame implements Runnable {
	JPanel contentPane;// 默认面板
	JPanel jPanelMain = new JPanel();// 主面板
	JPanel panelResult=new JPanel();
	JLabel labelTitle = new JLabel(); // 标题
	JLabel lbCountDown = new JLabel();// 显示倒计时
	JButton btnStart = new JButton();
	JSlider jSlider1 = new JSlider();
	JLabel jLabel1 = new JLabel();
	JButton btnStop = new JButton();
	JButton btnShow = new JButton();
	JLabel jLabel2 = new JLabel();
	JLabel lbPlayImg = new JLabel();
	JLabel playResult = new JLabel();
	JLabel lbInput = new JLabel();//显示输入
	JLabel input1=new JLabel(""); //输入1
	JLabel input2=new JLabel(); //输入1
	JLabel input3=new JLabel(); //输入1
	JLabel input4=new JLabel(); //输入1
	JLabel lbResult=new JLabel(); //操作结果
	Vector vectorInput=new Vector();
	
	ImageIcon playIcon = new ImageIcon();
	
	JLabel curPlay=new JLabel();//当前局数
	JLabel curNum =new JLabel();//当前个数
	
	int curIndex = 1, rapidity = 80; // curIndex 当前进行的个数, rapidity 游标的位置
	int rightNum = 0, wrongNum = 0;
	int playNumArr[] = { 10, 10, 10 }; // 游戏每关的个数 可以自由添加.列 { 10 ,20 ,30 ,40,50}
	int playCnt = 0; // 记录关数
	int duringTime = 10; // 切换字母的间隔时间
	boolean answerRight = false; // 是否正确作答
	boolean keep = false; // 游戏继续进行
	char list[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' }; // 随机出现的数字
	
	Vector number = new Vector();
	String finish = "true";
	AudioClip Musci_press, Music_fail, Music_success;

	/**存储操作数据*/
	//局数，次数，对错，原因(1错误 2超时)，操作次数,时间
	Vector data=new Vector();
	int costTime=0;
	/**
	 * 构造方法，加载声音文件，调用界面初始化函数
	 */
	public voiceMain() {
		try {
			// setDefaultCloseOperation(EXIT_ON_CLOSE);
			setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			// -----------------声音文件---------------------
			Musci_press = Applet.newAudioClip(new File("sounds//anjian.wav")
					.toURL());
			Music_fail = Applet.newAudioClip(new File("sounds//shibai.wav")
					.toURL());
			Music_success = Applet.newAudioClip(new File(
					"sounds//chenggong.wav").toURL());
			// ---------------------------------------
			voiceMainInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 界面初始化
	 * 
	 * @throws Exception
	 */
	private void voiceMainInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(1100, 650));
		setTitle("语音训练");
		labelTitle.setFont(new Font("宋体", Font.PLAIN, 34));
		labelTitle.setBounds(new Rectangle(2, 2, 200, 58));
		labelTitle.setText("语音训练");


		playResult.setFont(new Font("宋体", Font.PLAIN, 34));
		playResult.setBounds(new Rectangle(600, 2, 400, 58));

		jPanelMain.setBounds(new Rectangle(20, 55, 500, 410));
		jPanelMain.setLayout(null);
		lbPlayImg.setBounds(new Rectangle(20, 5, 400, 400));
		playIcon = // new ImageIcon(new ImageIcon("playicon.jpg").getImage()
					// .getScaledInstance(500, 470, Image.SCALE_DEFAULT));
		new ImageIcon(this.getClass().getClassLoader()
				.getResource("voice/yuyinbofang1.png"));
		lbPlayImg.setIcon(playIcon);
		jPanelMain.add(lbPlayImg);

		jSlider1.setBounds(new Rectangle(83, 448, 164, 21));
		jSlider1.setMaximum(100);
		jSlider1.setMinimum(1);
		jSlider1.setValue(50);
		jLabel1.setText("速度");
		jLabel1.setBounds(new Rectangle(35, 451, 39, 18));
		contentPane.add(jPanelMain);
		contentPane.add(labelTitle);
		contentPane.add(playResult);
		// contentPane.add(jSlider1);
		// contentPane.add(jLabel1);
		/*****当前个数和关卡数 倒计时*****/
		JPanel panelBtn=new JPanel();
		panelBtn.setBounds(20, 480, 530, 200);
		panelBtn.setLayout(null);
//		panelBtn.setBackground(Color.blue);
		curPlay.setFont(new Font("宋体", Font.PLAIN, 28));
		curPlay.setBounds(new Rectangle(29, 35, 100, 58));
		JLabel curPlayTitle = new JLabel();// 把背景图片显示在一个标签里面
		curPlayTitle.setFont(new Font("宋体", Font.PLAIN, 30));
        curPlayTitle.setBounds(new Rectangle(10, 10, 140, 30));
        curPlayTitle.setText("当前局数");
        //正在进行的个数
        curNum.setFont(new Font("宋体", Font.PLAIN, 28));
        curNum.setBounds(new Rectangle(198, 35, 100, 58));
		JLabel curNumTitle = new JLabel();
		curNumTitle.setFont(new Font("宋体", Font.PLAIN, 30));
		curNumTitle.setBounds(new Rectangle(170, 10, 140, 30));
		curNumTitle.setText("正在进行");
		//剩余时间
		lbCountDown.setFont(new Font("宋体", Font.PLAIN, 28));
		lbCountDown.setBounds(new Rectangle(400, 35, 100, 58));
		lbCountDown.setText("");
		JLabel countDownTitle = new JLabel();
		countDownTitle.setFont(new Font("宋体", Font.PLAIN, 30));
		countDownTitle.setBounds(new Rectangle(340, 10, 180, 30));
		countDownTitle.setText("当前剩余时间");
		
		panelBtn.add(curNum);
		panelBtn.add(curNumTitle);
		panelBtn.add(curPlay);
		panelBtn.add(curPlayTitle);
		panelBtn.add(lbCountDown);
		panelBtn.add(countDownTitle);
		contentPane.add(panelBtn);
		
		/** 开始 结束 等按钮**/
		JPanel panelControl=new JPanel();
		panelControl.setBounds(620, 480, 450, 200);
		panelControl.setLayout(null);
//		panelControl.setBackground(Color.blue);
		btnStart.setBounds(new Rectangle(10, 20, 89, 31));
		btnStart.setText("开始");
		btnStart.addActionListener(new Frame1_btnStart_actionAdapter(this));
		btnStop.setBounds(new Rectangle(150, 20, 89, 31));
		btnStop.setText("结束");
		btnStop.addActionListener(new Frame1_btnStop_actionAdapter(this));
		
		btnShow.setBounds(new Rectangle(290, 20, 100, 31));
		btnShow.setText("数据分析");
		btnShow.addActionListener(new Frame1_btnStop_actionAdapter(this));
		
		panelControl.add(btnStart);
		panelControl.add(btnStop);
		panelControl.add(btnShow);
		contentPane.add(panelControl);
		
		/***********结果显示部分***********/
		panelResult.setLayout(null);
		panelResult.setBounds(new Rectangle(550, 60, 530, 400));
//		panelResult.setBackground(Color.cyan);
		JLabel curPlayIcon=new JLabel(new ImageIcon(this.getClass().getClassLoader()
				.getResource("voice/curPlayIcon.png")));
		curPlayIcon.setBounds(new Rectangle(10, 0,540, 240));
		panelResult.add(curPlayIcon);
		JLabel inputIcon=new JLabel(new ImageIcon(this.getClass().getClassLoader()
				.getResource("voice/nindecaozuo.png")));
		inputIcon.setBounds(new Rectangle(10, 0,390, 280));
		panelResult.add(inputIcon);
		lbInput.setFont(new Font("宋体", Font.PLAIN, 50));
		lbInput.setBounds(new Rectangle(330, 0,390, 280));
		panelResult.add(lbInput);
		//历史输入
		input1.setFont(new Font("宋体", Font.PLAIN, 50));
		input1.setBounds(new Rectangle(380, 180,130, 50));
		input1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
//		input1.setBackground(Color.ORANGE);
		input1.setOpaque(true);
		input1.setHorizontalAlignment(JLabel.CENTER);
		input2.setFont(new Font("宋体", Font.PLAIN, 50));
		input2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		input2.setBounds(new Rectangle(380, 230,130, 50));
//		input2.setBackground(Color.ORANGE);
		input2.setOpaque(true);
		input2.setHorizontalAlignment(JLabel.CENTER);
		input3.setFont(new Font("宋体", Font.PLAIN, 50));
		input3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		input3.setBounds(new Rectangle(380, 280,130, 50));
//		input3.setBackground(Color.ORANGE);
		input3.setOpaque(true);
		input3.setHorizontalAlignment(JLabel.CENTER);
		input4.setFont(new Font("宋体", Font.PLAIN, 50));
		input4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		input4.setBounds(new Rectangle(380, 330,130, 50));
//		input4.setBackground(Color.ORANGE);
		input4.setOpaque(true);
		input4.setHorizontalAlignment(JLabel.CENTER);
		//当前操作结果
		lbResult.setBounds(new Rectangle(150,200,100,100));
		lbResult.setFont(new Font("宋体", Font.PLAIN, 40));
		lbResult.setOpaque(true);
		//当前正确错误数
		jLabel2.setFont(new Font("宋体", Font.PLAIN, 30));
		jLabel2.setBounds(new Rectangle(70,350,300,50));
		panelResult.add(input1);
		panelResult.add(input2);
		panelResult.add(input3);
		panelResult.add(input4);
		panelResult.add(lbResult);
		panelResult.add(jLabel2);
		
		
		contentPane.add(panelResult);
		
		
		this.addKeyListener(new MyListener());
		btnStart.addKeyListener(new MyListener());
		jSlider1.addKeyListener(new MyListener());
		jSlider1.addChangeListener(new ChangeListener() { // 滑动游标的位置设置速度
			public void stateChanged(ChangeEvent e) {
				rapidity = jSlider1.getValue();
			}
		});
		
		this.addWindowListener(new WindowAdapter() { // 窗口关闭事件监听与实现
			public void windowClosing(WindowEvent evt) {
				int saveChoose = JOptionPane.showConfirmDialog(contentPane,
						"确定退出?", "提示",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (saveChoose == JOptionPane.YES_OPTION) {
					curIndex = playNumArr[playCnt] + 1;
					finish = "flase";
					answerRight = true; // 当前答对,tThread线程结束
					keep = false; // 主线程结束一次循环
//					System.exit(0);
				} else if (saveChoose == JOptionPane.NO_OPTION) {
					curIndex = playNumArr[playCnt] + 1;
					finish = "flase";
					answerRight = true; // 当前答对,tThread线程结束
					keep = false; // 主线程结束一次循环
//					System.exit(0);
				} else if (saveChoose == JOptionPane.CANCEL_OPTION) {
					// 问题出现在这，我想取消时关闭对话框而不关闭主窗口，这改写什么呢？
				}
				else
					System.exit(0);// 退出
			}
		});
	}

	public void run() {
		number.clear();
//		rightNum = 0;
//		wrongNum = 0;
		finish = "true";

		while (curIndex < playNumArr[playCnt]) {
			keep = true; // 游戏继续进行
			int count = duringTime;
			try {
				Thread t = new Thread(new Tthread());
				t.start();
				curNum.setText(curIndex+"/10");
				curIndex += 1;
				Thread.sleep(100); // 生产下组停顿时间
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (keep) {
				count -= 1;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (count < 0)
					keep = false;
			}
		}

		while (true) { // 等待最后一个字符消失
			if (number.size() == 0) {
				break;
			}
		}

//		if (rightNum == 0) { // 为了以后相除..如果全部正确或者错误就会出现错误. 所以..
//			rightNum = 1;
//		}
//		if (wrongNum == 0) {
//			wrongNum = 1;
//		}

		if (finish.equals("true")) { // 判断是否是自然结束
//			if (rightNum / wrongNum >= 2) {
				// JOptionPane.showMessageDialog(null, "恭喜你过关了");
				playResult.setText("进入下一关！");
				playCnt += 1; // 自动加1关
				curPlay.setText("第"+(playCnt+1)+"局");
				duringTime-=2;
				if (playCnt <= playNumArr.length) {
//					if (rapidity > 10) { // 当速度大于10的时候在-5提加速度.怕速度太快
//						rapidity -= 5; // 速度自动减10毫秒
//						jSlider1.setValue(rapidity); // 选择位置
//					}
					curIndex = 0;
					Thread t = new Thread(this);
					t.start();
				} else {
					// JOptionPane.showMessageDialog(null, "牛B...你通关了..");
					playResult.setText("测试结束");
					playCnt = 0;
					curIndex = 0;
					result2File();
				}
//			} 
//			else {
//				// JOptionPane.showMessageDialog(null, "请再接再励");
//				playResult.setText("请再接再厉");
//				playCnt = 0;
//				curIndex = 0;
//			}
		} else {
			playCnt = 0;
			curIndex = 0;
			reset();
		}
	}

	/**
	 * 开始新的一局
	 * 
	 * @param e
	 */
	public void btnStart_actionPerformed(ActionEvent e) {
		if (keep == false) { // 只有游戏没开始才能继续执行
			keep = true;
			reset();
			Thread t = new Thread(this);
			t.start();
		duringTime=10;//间隔为10秒初始化
		rightNum=0;
		wrongNum=0;
		playIcon = // new ImageIcon(new ImageIcon("playicon.jpg").getImage()
					// .getScaledInstance(500, 470, Image.SCALE_DEFAULT));
		new ImageIcon(this.getClass().getClassLoader()
				.getResource("voice/yuyinbofang.gif"));
		lbPlayImg.setIcon(playIcon);
        curNum.setText("1/10");
		curPlay.setText("第1局");
		data.removeAllElements();
		}
	}

	/**
	 * 界面重置
	 */
	public void reset() {
		jLabel2.setText("正确:" + 0 + "个,错误:" + 0 + "个");
		lbCountDown.setText(0+"秒");
		curNum.setText("");
		curPlay.setText("");
		lbResult.setText("");
		lbInput.setText("");
		lbCountDown.setText("");
		
	}

	/**
	 * 结束本局
	 * 
	 * @param e
	 */
	public void btnStop_actionPerformed(ActionEvent e) {
		if (keep == true) {
			curIndex = playNumArr[playCnt] + 1;
			finish = "flase";
			answerRight = true; // 当前答对,tThread线程结束
			keep = false; // 主线程结束一次循环
			reset();
			result2File();//保存到文件
		playIcon = // new ImageIcon(new ImageIcon("playicon.jpg").getImage()
					// .getScaledInstance(500, 470, Image.SCALE_DEFAULT));
		new ImageIcon(this.getClass().getClassLoader()
				.getResource("voice/yuyinbofang1.png"));
		lbPlayImg.setIcon(playIcon);
		}
	}

	class Tthread implements Runnable {
		public void run() {
			boolean keep = true;
			int X = 330, Y =50;
			JLabel show = new JLabel();
			show.setFont(new Font("宋体", Font.PLAIN, 50));
			panelResult.add(show);
			String parameter = list[(int) (Math.random() * list.length)] + "";
			Bean bean = new Bean();
			bean.setParameter(parameter);
			bean.setShow(show);
			number.add(bean);
			show.setText(parameter);
			clearHistoryInput(); //清楚历史输入显示
			int countDown = duringTime;
			costTime=1;
			while (keep) {
				lbCountDown.setText(countDown+"秒");
				// ---------------------数字显示--------------------
				show.setBounds(new Rectangle(X, Y, 45, 45));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDown = countDown - 1; // 倒计时减一
				costTime=duringTime-countDown;
				if (countDown < 0 || answerRight) { // 等待作答时间结束
					keep = false;
					answerRight = false;
					for (int i = number.size() - 1; i >= 0; i--) {
						Bean bn = ((Bean) number.get(i));
						if (parameter.equalsIgnoreCase(bn.getParameter())) {
							wrongNum += 1;
							bn.getShow().setVisible(false);
							jLabel2.setText("正确:" + rightNum + "个,错误:"
									+ wrongNum + "个");
							data.add(new int[]{playCnt,curIndex,0,2,vectorInput.size(),duringTime});
							number.removeElementAt(i);
							Music_fail.play();
							break;
						}
					}
				}
			}

		}
	}

	class MyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			String uu = e.getKeyChar() + "";
			playResult.setText("");
			if(vectorInput.size()<4){
			vectorInput.add(uu);//保存历史输入
			}
			lbInput.setText(uu); //显示输入	
			saveHistoryInput();
			for (int i = 0; i < number.size(); i++) {
				Bean bean = ((Bean) number.get(i));
				if (uu.equalsIgnoreCase(bean.getParameter())) {
					rightNum += 1;
					answerRight = true; // 当前答对,tThread线程结束
					keep = false; // 主线程结束依次循环
					number.removeElementAt(i);
					bean.getShow().setVisible(false);
					lbResult.setText("正确");
					jLabel2.setText("正确:" + rightNum + "个,错误:" + wrongNum + "个");
					Music_success.play();
					//保存数据
					data.add(new int[]{playCnt,curIndex,1,0,vectorInput.size(),costTime});
					vectorInput.removeAllElements();
					break;
				}else{
					if(vectorInput.size()>=4){
						wrongNum+=1;
						lbResult.setText("错误");
						answerRight = true; // 当前答对,tThread线程结束
						keep = false; // 主线程结束依次循环
						number.removeElementAt(i);
						bean.getShow().setVisible(false);
						vectorInput.removeAllElements();
						jLabel2.setText("正确:" + rightNum + "个,错误:" + wrongNum + "个");
						//保存数据
						data.add(new int[]{playCnt,curIndex,0,1,4,duringTime});
					}
				}
			}		
			
			Musci_press.play();
		}
	}
	
	public void saveHistoryInput(){
//		System.out.println(vectorInput.size());
		ArrayList al=new ArrayList();
		al.add(input1);
		al.add(input2);
		al.add(input3);
		al.add(input4);
		for(int i=vectorInput.size()-1;i>=0;i--){
			JLabel a =(JLabel)al.get(vectorInput.size()-i-1);
			a.setText((String) vectorInput.get(i));
		}
	}
	
	public void clearHistoryInput(){
		input1.setText("");
		input2.setText("");
		input3.setText("");
		input4.setText("");
	}

	public void result2File(){
		String saveData=System.currentTimeMillis()+"\n";
		for(int i=0;i<data.size();i++){
			int a[]=(int[])data.get(i);
			for(int k=0;k<a.length;k++){
				saveData+=a[k]+",";
			}
			saveData+="\n";
		}
		saveData+="\n";
		FileWriter writer = null;  
        try {     
            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件     
            writer = new FileWriter("src/voice/result.txt", true);     
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
	}
	
	/**
	 * main 函数
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
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

/**
 * stop键的事件监听
 * 
 * @author springlustre
 */
class Frame1_btnStop_actionAdapter implements ActionListener {
	private voiceMain adapter;

	Frame1_btnStop_actionAdapter(voiceMain adapter) {
		this.adapter = adapter;
	}

	public void actionPerformed(ActionEvent e) {
		adapter.btnStop_actionPerformed(e);
	}
}

/**
 * start键的事件监听
 * 
 * @author springlustre
 *
 */
class Frame1_btnStart_actionAdapter implements ActionListener {
	private voiceMain adapter;

	Frame1_btnStart_actionAdapter(voiceMain adapter) {
		this.adapter = adapter;
	}

	public void actionPerformed(ActionEvent e) {
		adapter.btnStart_actionPerformed(e);
	}
}

class Bean {
	String parameter = null;

	JLabel show = null;

	public JLabel getShow() {
		return show;
	}

	public void setShow(JLabel show) {
		this.show = show;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
}
