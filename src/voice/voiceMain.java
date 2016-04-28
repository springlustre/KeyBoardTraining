package voice;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
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
	JLabel labelTitle = new JLabel(); // 标题
	JLabel lbCountDown = new JLabel();// 显示倒计时
	JButton btnStart = new JButton();
	JSlider jSlider1 = new JSlider();
	JLabel jLabel1 = new JLabel();
	JButton btnStop = new JButton();
	JLabel jLabel2 = new JLabel();
	JLabel lbPlayImg = new JLabel();
	JLabel playResult = new JLabel();
	ImageIcon playIcon = new ImageIcon();
	int curIndex = 1, rapidity = 80; // curIndex 当前进行的个数, rapidity 游标的位置
	int rightNum = 0, wrongNum = 0;
	int playNumArr[] = { 10, 20, 30 }; // 游戏每关的个数 可以自由添加.列 { 10 ,20 ,30 ,40,50}
	int playCnt = 0; // 记录关数
	int duringTime = 10; // 切换字母的间隔时间
	boolean answerRight = false; // 是否正确作答
	boolean keep = false; // 游戏继续进行
	char list[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
			'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' }; // 随机出现的数字
																// 可以自由添加
	Vector number = new Vector();
	String finish = "true";
	AudioClip Musci_press, Music_fail, Music_success;

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

		lbCountDown.setFont(new Font("宋体", Font.PLAIN, 34));
		lbCountDown.setBounds(new Rectangle(300, 2, 200, 58));
		lbCountDown.setText("倒计时:");

		playResult.setFont(new Font("宋体", Font.PLAIN, 34));
		playResult.setBounds(new Rectangle(600, 2, 400, 58));

		jPanelMain.setBorder(BorderFactory.createEtchedBorder());
		jPanelMain.setBounds(new Rectangle(4, 55, 573, 450));
		jPanelMain.setLayout(null);
		lbPlayImg.setBounds(new Rectangle(20, 20, 400, 400));
		playIcon = // new ImageIcon(new ImageIcon("playicon.jpg").getImage()
					// .getScaledInstance(500, 470, Image.SCALE_DEFAULT));
		new ImageIcon(this.getClass().getClassLoader()
				.getResource("voice/playicon.jpg"));
		lbPlayImg.setIcon(playIcon);
		jPanelMain.add(lbPlayImg);

		btnStart.setBounds(new Rectangle(277, 542, 89, 31));
		btnStart.setText("开始");
		btnStart.addActionListener(new Frame1_btnStart_actionAdapter(this));
		jSlider1.setBounds(new Rectangle(83, 448, 164, 21));
		jSlider1.setMaximum(100);
		jSlider1.setMinimum(1);
		jSlider1.setValue(50);
		jLabel1.setText("速度");
		jLabel1.setBounds(new Rectangle(35, 451, 39, 18));
		btnStop.setBounds(new Rectangle(408, 542, 89, 31));
		btnStop.setText("结束");
		btnStop.addActionListener(new Frame1_btnStop_actionAdapter(this));
		jLabel2.setText("第一关:100个");
		jLabel2.setBounds(new Rectangle(414, 473, 171, 21));
		contentPane.add(jPanelMain);
		contentPane.add(labelTitle);
		contentPane.add(lbCountDown);
		contentPane.add(btnStop);
		contentPane.add(btnStart);
		contentPane.add(playResult);
		// contentPane.add(jSlider1);
		// contentPane.add(jLabel1);
		contentPane.add(jLabel2);
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
		rightNum = 0;
		wrongNum = 0;
		finish = "true";

		while (curIndex < playNumArr[playCnt]) {
			keep = true; // 游戏继续进行
			int count = duringTime;
			try {
				Thread t = new Thread(new Tthread());
				t.start();
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

		if (rightNum == 0) { // 为了以后相除..如果全部正确或者错误就会出现错误. 所以..
			rightNum = 1;
		}
		if (wrongNum == 0) {
			wrongNum = 1;
		}

		if (finish.equals("true")) { // 判断是否是自然结束
			if (rightNum / wrongNum >= 2) {
				// JOptionPane.showMessageDialog(null, "恭喜你过关了");
				playResult.setText("恭喜你过关了！");

				playCnt += 1; // 自动加1关
				if (playCnt < playNumArr.length) {
					if (rapidity > 10) { // 当速度大于10的时候在-5提加速度.怕速度太快
						rapidity -= 5; // 速度自动减10毫秒
						jSlider1.setValue(rapidity); // 选择位置
					}
					Thread t = new Thread(this);
					t.start();
				} else {
					// JOptionPane.showMessageDialog(null, "牛B...你通关了..");
					playResult.setText("请再接再厉");
					playCnt = 0;
					curIndex = 0;
				}
			} else {
				// JOptionPane.showMessageDialog(null, "请再接再励");
				playResult.setText("请再接再厉");
				playCnt = 0;
				curIndex = 0;
			}
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
		}
	}

	/**
	 * 界面重置
	 */
	public void reset() {
		jLabel2.setText("正确:" + 0 + "个,错误:" + 0 + "个");
		lbCountDown.setText("倒计时:" + 0);
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
		}
	}

	class Tthread implements Runnable {
		public void run() {
			boolean keep = true;
			int Y = 0, X = 0;
			JLabel show = new JLabel();
			show.setFont(new Font("宋体", Font.PLAIN, 33));
			jPanelMain.add(show);
			X = 10;// + (int) (Math.random() * 400);
			String parameter = list[(int) (Math.random() * list.length)] + "";
			Bean bean = new Bean();
			bean.setParameter(parameter);
			bean.setShow(show);
			number.add(bean);
			show.setText(parameter);
			int countDown = duringTime;
			while (keep) {
				lbCountDown.setText("倒计时:" + countDown);
				// ---------------------数字显示--------------------
				show.setBounds(new Rectangle(X, Y, 33, 33));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				countDown = countDown - 1; // 倒计时减一

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
			for (int i = 0; i < number.size(); i++) {
				Bean bean = ((Bean) number.get(i));
				if (uu.equalsIgnoreCase(bean.getParameter())) {
					rightNum += 1;
					answerRight = true; // 当前答对,tThread线程结束
					keep = false; // 主线程结束依次循环
					number.removeElementAt(i);
					bean.getShow().setVisible(false);
					jLabel2.setText("正确:" + rightNum + "个,错误:" + wrongNum + "个");
					Music_success.play();
					break;
				}
			}
			Musci_press.play();
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
