import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class Test1 extends JFrame{
	private static Clip click;
	
	static String answerTest1; // 정적 변수로 선택된 답변을 저장
	public Test1() { 
		super("오늘머먹지?"); //창이름
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new Test1Panel());
		setSize(450,600); //창크기
		setVisible(true); //창 보이게 하기
		setLocation(400,100);
		
		loadClickSound("bgm/click.wav");
	}
	
	class Test1Panel extends JPanel{
		private JLabel N1 = new JLabel();
		private JButton Next = new JButton("다음>");
		private JButton Back = new JButton("<");
		
		JRadioButton good = new JRadioButton("좋다");
        JRadioButton bad = new JRadioButton("별로다");
        ButtonGroup buttonGroup = new ButtonGroup();

        
		public Test1Panel(){
			setLayout(null);
			setBackground(Color.WHITE);
			
			Font font1 = new Font("휴먼편지체",Font.BOLD,20);
			
			//설명문
			N1.setText("<HTML><body style='text-align:left;'>Q1.지금 기분이 어떠세요? <br>.<br><br></body></HTML>");
			N1.setSize(300,300);
			N1.setLocation(80,80);
			N1.setForeground(Color.BLACK);
			N1.setFont(new Font("휴먼편지체",Font.BOLD,30));
			add(N1);
		
			// 라디오 버튼 생성
            good.setBounds(80, 250, 100, 40);
            bad.setBounds(80, 290, 100, 40);
            good.setBackground(Color.WHITE);
            bad.setBackground(Color.WHITE);
            good.setFont(new Font("휴먼편지체",Font.BOLD,25));
            bad.setFont(new Font("휴먼편지체",Font.BOLD,25));

            buttonGroup.add(good);
            buttonGroup.add(bad);

            add(good);
            add(bad);
			
			//다음 버튼
			Next.setBackground(Color.PINK);
			Next.setFont(font1);
			Next.setBounds(300, 500, 120, 50);
		    add(Next);
			
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
	        Next.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                answerTest1 = good.isSelected() ? "좋다" : "별로다";
	                new Test2();
	                playClickSound();
	                //setVisible(false);
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
		new Test1();
	}

}

