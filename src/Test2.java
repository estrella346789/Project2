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

public class Test2 extends JFrame{
	private static Clip click;
	
	static String answerTest2;
	
	public Test2() { 
		super("오늘머먹지?"); //창이름
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new Test2Panel());
		setSize(450,600); //창크기
		setVisible(true); //창 보이게 하기
		setLocation(400,100);
		
		loadClickSound("bgm/click.wav");
	}
	
	class Test2Panel extends JPanel{
		private JLabel N2 = new JLabel();
		private JButton Next = new JButton("다음>");
		private JButton Back = new JButton("<");
		
		JRadioButton cold = new JRadioButton("춥다");
        JRadioButton hot = new JRadioButton("덥다");
        ButtonGroup buttonGroup = new ButtonGroup();
 
	    
		public Test2Panel(){
			setLayout(null);
			setBackground(Color.WHITE);
			
			Font font1 = new Font("휴먼편지체",Font.BOLD,20);
			
			//설명문
			N2.setText("<HTML><body style='text-align:left;'>Q2.날씨가 어떤가요? <br>.<br><br></body></HTML>");
			N2.setSize(300,300);
			N2.setLocation(80,80);
			N2.setForeground(Color.BLACK);
			N2.setFont(new Font("휴먼편지체",Font.BOLD,30));
			add(N2);
			
			// 라디오 버튼 생성
            cold.setBounds(80, 250, 100, 40);
            hot.setBounds(80, 290, 100, 40);
            cold.setBackground(Color.WHITE);
            hot.setBackground(Color.WHITE);
            cold.setFont(new Font("휴먼편지체",Font.BOLD,25));
            hot.setFont(new Font("휴먼편지체",Font.BOLD,25));


            buttonGroup.add(cold);
            buttonGroup.add(hot);

            add(cold);
            add(hot);
			
			//테스트 시작하기 버튼
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
	                answerTest2 = cold.isSelected() ? "춥다" : "덥다";
	                new Test3();
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

	}

}

