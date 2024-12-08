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

class KoreanFrame extends JFrame {
	private static Clip click;
	public KoreanFrame() {
		super("오늘머먹지?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(new KoreanPanel());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setContentPane(scrollPane);

		setSize(450, 600); // 창크기
		setVisible(true); // 창 보이게 하기
		setLocation(400, 100);
		
		loadClickSound("bgm/click.wav");
	}

	class KoreanPanel extends JPanel {

		private JLabel FoodType = new JLabel("종류별");
		private JButton Back = new JButton("<");

		String[] Food = { "한식", "중식", "일식", "양식"};
		JComboBox<String> combo = new JComboBox<String>(Food);
		
		private ImageIcon foodimage[] = {new ImageIcon(getClass().getClassLoader().getResource("이조삼계탕.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("손두부.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("방목.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("운봉손칼국수.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("홍추곱창카페.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("맛닭꼬.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("종로곱창.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("100년 설렁탕.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("삼선꼬마김밥.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("우물집.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("고석환손만두전골.jpg"))
										};
		
		private JLabel[] imageLabel = new JLabel[11];
		
		private JLabel nameLabel[] = {new JLabel("이조 삼계탕"),
									  new JLabel("67년 전통 손두부"),
									  new JLabel("방목"),
									  new JLabel("운봉손칼국수"),
									  new JLabel("홍추곱창카페"),
									  new JLabel("맛닭꼬 한성대입구역점"),
									  new JLabel("종로곱창"),
									  new JLabel("100년 설렁탕 본점"),
									  new JLabel("삼선꼬마김밥"),
									  new JLabel("우물집"),
									  new JLabel("고석환손만두전골")
									 };
		
		private JLabel informationLabel[] = {new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 83<br>휴무일 : X<br>☎ 0507-1352-5340<br>도보 8분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 02-745-1151<br>도보 7분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 61<br>휴무일 : X<br>☎ 010-5322-1562<br>도보 15분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 49<br>휴무일 : 일<br>☎ 02-953-5155<br>도보 9분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 52<br>휴무일 : X<br>☎ 02-6449-9288<br>도보 11분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 02-745-2292<br>도보 12분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 47<br>휴무일 : 일<br>☎ 02-744-0642<br>도보 14분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 34<br>휴무일 : X<br>☎ 02-742-5588<br>도보 12분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 85<br>휴무일 : X<br>☎ 02-742-6544<br>도보 8분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 0507-1318-7500<br>도보 13분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 37<br>휴무일 : 월<br>☎ 02-744-1990<br>도보 18분</body></HTML>"),
											};
		
		

		KoreanPanel() {
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
			combo.setSelectedIndex(0);
			add(combo);
			
			for(int i=0; i<imageLabel.length; i++) {
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
			
			
			setPreferredSize(new Dimension(400, 1730));

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
					
					if(selectedFoodType.equals("중식")) {
						new ChineseFrame();
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
