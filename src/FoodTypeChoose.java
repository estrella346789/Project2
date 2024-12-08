import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

public class FoodTypeChoose extends JFrame {
	private static Clip click;
	
	public FoodTypeChoose() {
		super("오늘머먹지?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setContentPane(new FTCPanel());
		setSize(450, 600); // 창크기
		setVisible(true); // 창 보이게 하기
		setLocation(400, 100);
		
		loadClickSound("bgm/click.wav");
	}

	class FTCPanel extends JPanel {
		public JButton Korean = new JButton("한식"); // 한식
		public JButton Chinese = new JButton("중식"); // 중식
		public JButton Japanese = new JButton("일식"); // 일식
		public JButton Western = new JButton("양식"); // 양식
		private JButton Back = new JButton("<");

		public FTCPanel() {
			setLayout(null);
			setBackground(Color.WHITE);

			Font font1 = new Font("휴먼편지체", Font.BOLD, 40);

			Korean.setBackground(Color.PINK);
			Korean.setFont(font1);
			Korean.setBounds(125, 60, 180, 80);
			add(Korean);

			Chinese.setBackground(Color.PINK);
			Chinese.setFont(font1);
			Chinese.setBounds(125, 180, 180, 80);
			add(Chinese);

			Japanese.setBackground(Color.PINK);
			Japanese.setFont(font1);
			Japanese.setBounds(125, 300, 180, 80);
			add(Japanese);

			Western.setBackground(Color.PINK);
			Western.setFont(font1);
			Western.setBounds(125, 420, 180, 80);
			add(Western);

			Back.setBackground(Color.WHITE);
			Back.setFont(new Font("휴먼편지체", Font.BOLD, 30));
			Back.setBounds(5, 5, 50, 50);
			Back.setToolTipText("<HTML><body style='font-size:1.5em;font-family:휴먼편지체;'>이전 화면으로 돌아갑니다.</body></HTML>");
			add(Back);

			Korean.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new KoreanFrame();
					//setVisible(false);
					playClickSound();
					dispose();
				}
			});

			Chinese.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new ChineseFrame();
					//setVisible(false);
					playClickSound();
					dispose();
				}
			});

			Japanese.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new JapaneseFrame();
					//setVisible(false);
					playClickSound();
					dispose();
				}
			});

			Western.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new WesternFrame();
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
