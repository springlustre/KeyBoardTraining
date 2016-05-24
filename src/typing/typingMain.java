package typing;


import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;





import model.dataDao;

import java.awt.Font;



/**
 * Created by springlustre on 2016/4/9.
 */
public class typingMain extends JFrame implements Runnable {



  JPanel contentPane;//默认面板
  JPanel jPanelMain = new JPanel();//主面板
  JLabel labelTitle=new JLabel(); //标题
  JLabel lbCountDown=new JLabel();//显示倒计时
  JButton btnStart = new JButton();
  JSlider jSlider1 = new JSlider();
  JLabel jLabel1 = new JLabel();
  JButton btnStop = new JButton();
  JLabel jLabel2 = new JLabel();
  int curIndex = 1, rapidity = 80; // curIndex 当前进行的个数, rapidity 游标的位置
  int rightNum = 0, wrongNum = 0;
  int playNumArr[] = {10,10,10,10};     //游戏每关的个数 可以自由添加.列 { 10 ,20 ,30 ,40,50}
  int playCnt = 0;      //记录关数
  int duringTime=10; //切换字母的间隔时间
  boolean answerRight=false; //是否正确作答
  boolean keep=false; //游戏继续进行
  char list[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
    'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
    'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' ,'0'};  //随机出现的数字 可以自由添加
  String key1[]={"1","2","3","4","5","6","7","8","9","0"}; //四排按键
  String key2[]={"Q","W","E","R","T","Y","U","I","O","P"};
  String key3[]={"A","S","D","F","G","H","J","K","L"};
  String key4[]={"Z","X","C","V","B","N","M"};
  HashMap<String ,JLabel> labelMap = new HashMap<String,JLabel>();
  Vector number = new Vector();
  String finish = "true";
  AudioClip Musci_press, Music_fail, Music_success;
//  JLabel labelTitle=new JLabel("a"); //标题
  //局数，次数，对错，原因(1错误 2超时),时间
  Vector data=new Vector();
	int costTime=0;
//	Object[] possibleValues = { "First", "Second", "Third" };   
//	Object selectedValue = JOptionPane.showInputDialog(null,  "Choose one", "Input",    
//	    JOptionPane.INFORMATION_MESSAGE, null, 
//	    possibleValues, possibleValues[0]);
  /**
   * 构造方法，加载声音文件，调用界面初始化函数
   */
  public typingMain(){
    try {
//      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      //-----------------声音文件---------------------
      Musci_press = Applet.newAudioClip(new File("sounds//anjian.wav")
        .toURL());
      Music_fail = Applet.newAudioClip(new File("sounds//shibai.wav")
        .toURL());
      Music_success = Applet.newAudioClip(new File(
        "sounds//chenggong.wav").toURL());
      //---------------------------------------
      voiceMainInit();
    } catch (Exception exception) {
      exception.printStackTrace();
    }
  }


  /**
   * 界面初始化
   * @throws Exception
   */
  private void voiceMainInit() throws Exception {
    contentPane = (JPanel) getContentPane();
    contentPane.setLayout(null);
    setSize(new Dimension(1200, 700));
    setTitle("图像练习");
    labelTitle.setFont(new Font("宋体", Font.PLAIN, 34));
    labelTitle.setBounds(new Rectangle(2, 2, 200, 58));
    labelTitle.setText("键盘操作");

    lbCountDown.setFont(new Font("宋体", Font.PLAIN, 34));
    lbCountDown.setBounds(new Rectangle(300, 2, 200, 58));
    lbCountDown.setText("倒计时:");


    jPanelMain.setBorder(BorderFactory.createEtchedBorder());
    jPanelMain.setBounds(new Rectangle(4, 150, 950, 600));
    jPanelMain.setLayout(null);
//    jPanelMain.setBackground(Color.BLUE);
    for(int i=0;i<list.length;i++){
      JLabel label=new JLabel(String.valueOf(list[i]),JLabel.CENTER);
      String lbName=list[i]+"";
      labelMap.put(lbName,label);
      jPanelMain.add(label);
    }

//    jPanelMain.add(labelMap.get("Q"));
    for(int i=0;i<list.length;i++){
      labelMap.get(String.valueOf(list[i])).setBackground(new Color(48, 47, 55));
      labelMap.get(String.valueOf(list[i])).setOpaque(true);
    }

    /*第一排按键*/
    for(int i=0;i<key1.length;i++){
      labelMap.get(key1[i]).setBounds(90*i+10,100,75,75);
      labelMap.get(key1[i]).setFont(new Font("宋体", Font.PLAIN, 28));
      labelMap.get(key1[i]).setForeground(Color.white);
    }
    /*第二排按键*/
    for(int i=0;i<key2.length;i++){
      labelMap.get(key2[i]).setBounds(90*i+35,190,75,75);
      labelMap.get(key2[i]).setFont(new Font("宋体", Font.PLAIN, 28));
      labelMap.get(key2[i]).setForeground(Color.white);
    }
    /*第三排按键*/
    for(int i=0;i<key3.length;i++){
      labelMap.get(key3[i]).setBounds(90*i+70,280,75,75);
      labelMap.get(key3[i]).setFont(new Font("宋体", Font.PLAIN, 28));
      labelMap.get(key3[i]).setForeground(Color.white);
    }
    /*第四排按键*/
    for(int i=0;i<key4.length;i++){
      labelMap.get(key4[i]).setBounds(90*i+105,370,75,75);
      labelMap.get(key4[i]).setFont(new Font("宋体", Font.PLAIN, 28));
      labelMap.get(key4[i]).setForeground(Color.white);
    }


    btnStart.setBounds(new Rectangle(960, 42, 89, 31));
    btnStart.setText("开始");
    btnStart.addActionListener(new Frame1_btnStart_actionAdapter(this));
//    jSlider1.setBounds(new Rectangle(83, 448, 164, 21));
//    jSlider1.setMaximum(100);
//    jSlider1.setMinimum(1);
//    jSlider1.setValue(50);
    jLabel1.setText("速度");
    jLabel1.setBounds(new Rectangle(35, 451, 39, 18));
    btnStop.setBounds(new Rectangle(1050, 42, 89, 31));
    btnStop.setText("结束");
    btnStop.addActionListener(new Frame1_btnStop_actionAdapter(this));
    jLabel2.setText("第一关:10个");
    jLabel2.setFont(new Font("宋体", Font.PLAIN, 20));
    jLabel2.setBounds(new Rectangle(414, 110, 271, 21));
    contentPane.add(jPanelMain);
    contentPane.add(labelTitle);
    contentPane.add(lbCountDown);
    contentPane.add(btnStop);
    contentPane.add(btnStart);
//    contentPane.add(jSlider1);
    contentPane.add(jLabel1);
    contentPane.add(jLabel2);
    this.addKeyListener(new MyListener());
    btnStart.addKeyListener(new MyListener());
    contentPane.addKeyListener(new MyListener());
    jSlider1.addChangeListener(new ChangeListener() { //滑动游标的位置设置速度
      public void stateChanged(ChangeEvent e) {
        rapidity = jSlider1.getValue();
      }
    });
  }


  public void run() {
		number.clear();
		finish = "true";
		while (curIndex < playNumArr[playCnt]) {
			keep = true; // 游戏继续进行
			int count = duringTime;
			try {
				Thread t = new Thread(new Tthread());
				t.start();
//				curNum.setText(curIndex+"/10");
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

		if (finish.equals("true")) { // 判断是否是自然结束
				playCnt += 1; // 自动加1关
				duringTime-=2;
				if (playCnt <= playNumArr.length) {
					curIndex = 0;
					Thread t = new Thread(this);
					t.start();
				} else {
					playCnt = 0;
					curIndex = 0;
					result2File();
					dataDao.saveTypingData2DB(data);
				}
		} else {
			playCnt = 0;
			curIndex = 0;
			reset();
		}
	}

  /**
   * 开始新的一局
   * @param e
   */
  public void btnStart_actionPerformed(ActionEvent e) {
    if(keep==false) { //只有游戏没开始才能继续执行
      keep = true;
      reset();
      Thread t = new Thread(this);
      t.start();
      playCnt=0;
      curIndex=0;
      duringTime=10;//间隔为10秒初始化
	  rightNum=0;
	  wrongNum=0;
	  data.removeAllElements();
    }
  }

  /**
   * 界面重置
   */
  public void reset(){
    jLabel2.setText("正确:" + 0 + "个,错误:" + 0+ "个");
    lbCountDown.setText("倒计时:"+0);
  }

  /**
   * 结束本局
   * @param e
   */
  public void btnStop_actionPerformed(ActionEvent e) {
    if(keep==true) {
			reset();
			curIndex = playNumArr[playCnt] + 1;
			finish = "flase";
			answerRight = true; // 当前答对,tThread线程结束
			keep = false; // 主线程结束一次循环
			result2File();
			dataDao.saveTypingData2DB(data);
		}
  }

  class Tthread implements Runnable {
    public void run() {
      boolean keep2 = true;
      int Y = 0, X = 0;
      String parameter = list[(int) (Math.random() * list.length)] + ""; //随机字母
      JLabel show = labelMap.get(parameter);
      show.setBackground(Color.cyan);
      show.setForeground(Color.gray);
      show.setFont(new Font("宋体", Font.PLAIN, 33));
      KeyBean bean = new KeyBean();
      bean.setParameter(parameter);
      bean.setShow(show);
      number.add(bean);
      int countDown=duringTime;
      costTime=1;
      while (keep2) {
        lbCountDown.setText("倒计时:"+countDown);
        // ---------------------数字显示--------------------
//        show.setBounds(new Rectangle(X, Y, 33, 33));
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        countDown=countDown-1; //倒计时减一
        costTime=duringTime-countDown;
        if (countDown<0 || answerRight) {  //等待作答时间结束
          keep2 = false;
          answerRight=false;
          for (int i = number.size() - 1; i >= 0; i--) {
            KeyBean bn = ((KeyBean) number.get(i));
            if (parameter.equalsIgnoreCase(bn.getParameter())) {
              wrongNum += 1;
              resetKey(bean.getShow());
              jLabel2.setText("正确:" + rightNum + "个,错误:" + wrongNum
                + "个");
              if(curIndex<playNumArr[playCnt])
              data.add(new int[]{playCnt,curIndex,0,2,duringTime});
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
      if(e.getKeyCode()!=16){
      String uu = e.getKeyChar() + "";
      System.out.println(uu);
      int tempResult=0;
      for (int i = 0; i < number.size(); i++) {
        KeyBean bean = ((KeyBean) number.get(i));
        if (uu.equalsIgnoreCase(bean.getParameter())) {
          tempResult=1;
          rightNum += 1;
          answerRight=true; //当前答对,tThread线程结束
          keep=false; //主线程结束依次循环
          number.removeElementAt(i);
          resetKey(bean.getShow());
          jLabel2.setText("正确:" + rightNum + "个,错误:" + wrongNum + "个");
          data.add(new int[]{playCnt,curIndex,1,0,costTime});
          Music_success.play();
          break;
        }
      }
      if(tempResult==0){
    	  wrongNum += 1;
    	  answerRight=true; //当前答对,tThread线程结束
          keep=false; //主线程结束依次循环
          jLabel2.setText("正确:" + rightNum + "个,错误:" + wrongNum
            + "个");
          data.add(new int[]{playCnt,curIndex,0,1,costTime});
          Music_fail.play();
          for (int i = number.size() - 1; i >= 0; i--) {
            KeyBean bn = ((KeyBean) number.get(i));              
            resetKey(bn.getShow());
            number.removeElementAt(i);
            break;             
          }
      }
      Musci_press.play();
    }
    }
  }

  /**按键回复原状*/
  public void resetKey(JLabel label){
    label.setFont(new Font("宋体", Font.PLAIN, 28));
    label.setForeground(Color.white);
    label.setBackground(new Color(48, 47, 55));
  }

/**
 * 保存到文件
 */
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
          writer = new FileWriter("src/typing/result.txt", true);     
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
   *main 函数
   */
  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception exception) {
      exception.printStackTrace();
    }
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

/**
 * stop键的事件监听
 * @author springlustre
 */
class Frame1_btnStop_actionAdapter implements ActionListener {
  private typingMain adapter;
  Frame1_btnStop_actionAdapter(typingMain adapter) {
    this.adapter = adapter;
  }
  public void actionPerformed(ActionEvent e) {
    adapter.btnStop_actionPerformed(e);
  }
}

/**
 * start键的事件监听
 * @author springlustre
 *
 */
class Frame1_btnStart_actionAdapter implements ActionListener {
  private typingMain adapter;

  Frame1_btnStart_actionAdapter(typingMain adapter) {
    this.adapter = adapter;
  }

  public void actionPerformed(ActionEvent e) {
    adapter.btnStart_actionPerformed(e);
  }
}

class KeyBean {
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

