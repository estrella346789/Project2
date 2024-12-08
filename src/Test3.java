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

public class Test3 extends JFrame{
	private static Clip click;
	
	 static String answerTest3;
	public Test3() { 
		super("오늘머먹지?"); //창이름
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new Test3Panel());
		setSize(450,600); //창크기
		setVisible(true); //창 보이게 하기
		setLocation(400,100);
		
		loadClickSound("bgm/click.wav");
	}
	
	class Test3Panel extends JPanel{
		private JLabel N3 = new JLabel();
		private JButton Next = new JButton("다음>");
		private JButton Back = new JButton("<");
		
		JRadioButton hurry = new JRadioButton("많이! 당장 밥 먹고 싶어요!");
        JRadioButton slow = new JRadioButton("조금 늦어져도 괜찮아요~");
        ButtonGroup buttonGroup = new ButtonGroup();
 
	    
		public Test3Panel(){
			setLayout(null);
			setBackground(Color.WHITE);
			
			Font font1 = new Font("휴먼편지체",Font.BOLD,20);
			
			//설명문
			N3.setText("<HTML><body style='text-align:left;'>Q3. 배가 얼마나 고픈가요?<br>.<br><br></body></HTML>");
			N3.setSize(300,300);
			N3.setLocation(80,80);
			N3.setForeground(Color.BLACK);
			N3.setFont(new Font("휴먼편지체",Font.BOLD,30));
			add(N3);
			
			// 라디오 버튼 생성
			hurry.setBounds(80, 250, 350, 40);
            slow.setBounds(80, 290, 300, 40);
            hurry.setBackground(Color.WHITE);
            slow.setBackground(Color.WHITE);
            hurry.setFont(new Font("휴먼편지체",Font.BOLD,25));
            slow.setFont(new Font("휴먼편지체",Font.BOLD,25));


            buttonGroup.add(hurry);
            buttonGroup.add(slow);

            add(hurry);
            add(slow);
			
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
	                answerTest3 = hurry.isSelected() ? "많이! 당장 밥 먹고 싶어요!" : "조금 늦어져도 괜찮아요~";
	                new Test4();
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

