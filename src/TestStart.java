import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class TestStart extends JFrame{
	private static Clip click;
	
	public TestStart() { 
		super("오늘머먹지?"); //창이름
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new TestStartPanel());
		setSize(450,600); //창크기
		setVisible(true); //창 보이게 하기
		setLocation(400,100);
		
		loadClickSound("bgm/click.wav");
	}
	
	class TestStartPanel extends JPanel{
		private JLabel ExplainTest = new JLabel();
		private JButton Start = new JButton("테스트 시작하기");
		private JButton Back = new JButton("<");
		
		
		public TestStartPanel(){
			setLayout(null);
			setBackground(Color.WHITE);
			
			Font font1 = new Font("휴먼편지체",Font.BOLD,20);
			
			//설명문
			ExplainTest.setText("<HTML><body style='text-align:center;'>보기를 보고 고른 답을 종합적으로<br>분석하여 메뉴를 추천합니다.<br><br>'테스트 시작하기' 버튼을 <br>누르면 테스트를 시작합니다.</body></HTML>");
			ExplainTest.setSize(300,300);
			ExplainTest.setLocation(100,60);
			ExplainTest.setForeground(Color.BLACK);
			ExplainTest.setFont(new Font("휴먼편지체",Font.BOLD,20));
			add(ExplainTest);
			
			//테스트 시작하기 버튼
			Start.setBackground(Color.PINK);
		    Start.setFont(font1);
		    Start.setBounds(125, 300, 180, 80);
		    add(Start);
			
		    Back.setBackground(Color.WHITE);
	        Back.setFont(new Font("휴먼편지체",Font.BOLD,30));
	        Back.setBounds(5,5, 50, 50);
	        Back.setToolTipText("<HTML><body style='font-size:1.5em;font-family:휴먼편지체;'>이전 화면으로 돌아갑니다.</body></HTML>");
	        add(Back);
	        
	        Back.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new MenuRecommend();
	        		//setVisible(false);
	        		playClickSound();
	        		dispose();
	        	}
	        });
	        
	        Start.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new Test1();
	        		//setVisible(false);
	        		playClickSound();
	        		dispose();
	        	}
	        });
		}
		
	}
	
	private void loadClickSound(String pathName) {
		try {
			click = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
			click.open(audioStream);
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
	}
	private void playClickSound() {
        if (click != null) {
            click.setFramePosition(0);
            click.start();
        }
    }
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
