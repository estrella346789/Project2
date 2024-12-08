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

class ChineseFrame extends JFrame {
	private static Clip click;
	
	public ChineseFrame() {
		super("오늘머먹지?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(new ChinesePanel());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setContentPane(scrollPane);

		setSize(450, 600); // 창크기
		setVisible(true); // 창 보이게 하기
		setLocation(400, 100);
		
		loadClickSound("bgm/click.wav");
	}

	class ChinesePanel extends JPanel {

		private JLabel FoodType = new JLabel("종류별");
		private JButton Back = new JButton("<");

		String[] Food = { "한식", "중식", "일식", "양식"};
		JComboBox<String> combo = new JComboBox<String>(Food);
		
		private ImageIcon foodimage[] = {new ImageIcon(getClass().getClassLoader().getResource("삼선.jpg")),
				 						 new ImageIcon(getClass().getClassLoader().getResource("공푸.jpg")),
				 						 new ImageIcon(getClass().getClassLoader().getResource("창창.jpg")),
				 						 new ImageIcon(getClass().getClassLoader().getResource("한성대양꼬치.jpg")),
				 						 new ImageIcon(getClass().getClassLoader().getResource("옛날중국집.jpg"))
										};
		
		private JLabel[] imageLabel = new JLabel[5];
		
		private JLabel nameLabel[] = {new JLabel("삼선"),
									  new JLabel("공푸"),
									  new JLabel("창창"),
									  new JLabel("한성대 양꼬치"),
									  new JLabel("옛날 중국집"),
									 };
		
		private JLabel informationLabel[] = {new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : 월<br>☎ 0507-1358-7847<br>도보 13분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 55<br>휴무일 : 일, 첫째주/셋째주 월<br>☎ 0507-1314-4683<br>도보 19분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 0507-1370-3548<br>도보 15분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 31<br>휴무일 : --<br>☎ 02-911-9000<br>도보 15분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 39<br>휴무일 : 월<br>☎ 02-764-0094<br>버스 11분 (성북 02)</body></HTML>"),
											};

		ChinesePanel() {
			setLayout(null);
			setBackground(Color.WHITE);

			FoodType.setSize(120, 40);
			FoodType.setLocation(60, 15);
			FoodType.setForeground(Color.BLACK);
			FoodType.setFont(new Font("휴먼편지체", Font.BOLD, 30));
			add(FoodType);

			Back.setBackground(Color.WHITE);
			Back.setFont(new Font("휴먼편지체", Font.BOLD, 30));
			Back.setBounds(5, 5, 50, 50);
			Back.setToolTipText("<HTML><body style='font-size:1.5em;font-family:휴먼편지체;'>이전 화면으로 돌아갑니다.</body></HTML>");
			add(Back);

			combo.setFont(new Font("휴먼편지체", Font.BOLD, 20));
			combo.setBounds(300, 20, 80, 30);
			combo.setSelectedIndex(1);
			add(combo);
			
			for(int i=0;i<imageLabel.length;i++) {
				imageLabel[i] = new JLabel();
				imageLabel[i].setIcon(foodimage[i]);
				imageLabel[i].setSize(130,130);
				imageLabel[i].setLocation(20,80+150*i);
				add(imageLabel[i]);
			}
			
			for(int k=0; k<nameLabel.length; k++) {
				nameLabel[k].setSize(500,100);
				nameLabel[k].setLocation(170,45+150*k);
				nameLabel[k].setForeground(Color.RED);
				nameLabel[k].setFont(new Font("휴먼편지체",Font.BOLD,23));
				add(nameLabel[k]);
			}
			
			for(int j=0; j<informationLabel.length; j++) {
				informationLabel[j].setSize(300,150);
				informationLabel[j].setLocation(170,85+150*j);
				add(informationLabel[j]);
			}
			
			setPreferredSize(new Dimension(400, 830));

			Back.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new FoodTypeChoose();
					//setVisible(false);
					playClickSound();
					dispose();
				}
			});
			
			combo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JComboBox jcb=(JComboBox)e.getSource();
					String selectedFoodType = (String) jcb.getSelectedItem();
					
					if(selectedFoodType.equals("한식")) {
						new KoreanFrame();
						playClickSound();
						dispose();
					}
					else if(selectedFoodType.equals("일식")) {
						new JapaneseFrame();
						playClickSound();
						dispose();
					}
					else if(selectedFoodType.equals("양식")) {
						new WesternFrame();
						playClickSound();
						dispose();
					}
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