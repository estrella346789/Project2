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

public class Test4 extends JFrame{
	private static Clip click;
	
	static String answerTest4;
	public Test4() { 
		super("오늘머먹지?"); //창이름
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new Test4Panel());
		setSize(450,600); //창크기
		setVisible(true); //창 보이게 하기
		setLocation(400,100);
		
		loadClickSound("bgm/click.wav");
	}
	
	class Test4Panel extends JPanel{
		private JLabel N4 = new JLabel();
		private JButton ShowResult = new JButton("결과보기");
		private JButton Back = new JButton("<");
		
		JRadioButton rice = new JRadioButton("밥");
        JRadioButton mbb = new JRadioButton("면, 빵");
        JRadioButton meat = new JRadioButton("고기");
        ButtonGroup buttonGroup = new ButtonGroup(); 
	    
		public Test4Panel(){
			setLayout(null);
			setBackground(Color.WHITE);
			
			Font font1 = new Font("휴먼편지체",Font.BOLD,20);
			
			//설명문
			N4.setText("<HTML><body style='text-align:left;'>Q4. 뭐가 땡겨요?<br>.<br><br></body></HTML>");
			N4.setSize(300,300);
			N4.setLocation(80,80);
			N4.setForeground(Color.BLACK);
			N4.setFont(new Font("휴먼편지체",Font.BOLD,30));
			add(N4);
			
			// 라디오 버튼 생성
			rice.setBounds(80, 250, 200, 40);
			mbb.setBounds(80, 290, 200, 40);
			meat.setBounds(80, 330, 200, 40);
            rice.setBackground(Color.WHITE);
            mbb.setBackground(Color.WHITE);
            meat.setBackground(Color.WHITE);
            rice.setFont(new Font("휴먼편지체",Font.BOLD,25));
            mbb.setFont(new Font("휴먼편지체",Font.BOLD,25));
            meat.setFont(new Font("휴먼편지체",Font.BOLD,25));

			
			buttonGroup.add(rice);
			buttonGroup.add(mbb);
			buttonGroup.add(meat);
			
			add(rice);
			add(mbb);
			add(meat);

			
			//테스트 시작하기 버튼
			ShowResult.setBackground(Color.PINK);
			ShowResult.setFont(font1);
			ShowResult.setBounds(300, 500, 120, 50);
		    add(ShowResult);
			
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
	        ShowResult.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (rice.isSelected()) {
	                    answerTest4 = "밥";
	                } else if (mbb.isSelected()) {
	                    answerTest4 = "면, 빵";
	                } else if (meat.isSelected()) {
	                    answerTest4 = "고기";
	                }
	                new loading();
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

