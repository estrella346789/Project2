import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class MenuRecommend extends JFrame {
	private static Clip click;
	
	public MenuRecommend() {
		super("오늘머먹지?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new MenuRecommendPanel());
		setSize(450,600); //창크기
		setVisible(true); //창 보이게 하기
		setLocation(400,100);
		
		loadClickSound("bgm/click.wav");
	}
	
	class MenuRecommendPanel extends JPanel{
		private JLabel MenuRecommend = new JLabel("메뉴추천");
		private JButton Random = new JButton("랜덤"); //랜덤 버튼
		private JButton Test = new JButton("심리테스트"); //심리테스트 버튼
		private JButton Back = new JButton("<");
		
		public MenuRecommendPanel(){
			setLayout(null);
			setBackground(Color.WHITE);
			
			MenuRecommend.setSize(360,120);
			MenuRecommend.setLocation(100,20);
			MenuRecommend.setForeground(Color.BLACK);
			MenuRecommend.setFont(new Font("휴먼편지체",Font.BOLD,75));
			add(MenuRecommend);
			
			Font font1 = new Font("휴먼편지체",Font.BOLD,25);
			
			//랜덤 버튼
			Random.setBackground(Color.PINK);
	        Random.setFont(font1);
	        Random.setBounds(125, 170, 180, 80);
	        add(Random);
	        
	        //심리테스트 버튼
	        Test.setBackground(Color.PINK);
	        Test.setFont(font1);
	        Test.setBounds(125,320, 180, 80);
	        add(Test);
	        
	        Back.setBackground(Color.WHITE);
	        Back.setFont(new Font("휴먼편지체",Font.BOLD,30));
	        Back.setBounds(5,5, 50, 50);
	        Back.setToolTipText("<HTML><body style='font-size:1.5em;font-family:휴먼편지체;'>이전 화면으로 돌아갑니다.</body></HTML>");
	        add(Back);
	        
	        //랜덤 버튼 누르면 RandomMenu 창으로 전환 이벤트
	        Random.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new RandomMenu();
	        		//setVisible(false);
	        		playClickSound();
	        		dispose();
	        	}
	        });
	        
	        //테스트 버튼 누르면 TestStart 창으로 전환 이벤트
	        Test.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new TestStart();
	        		//setVisible(false);
	        		playClickSound();
	        		dispose();
	        	}
	        });
	        
	        Back.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new InitialScreen();
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

