import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

class WesternFrame extends JFrame {
	private static Clip click;
	
	public WesternFrame() {
		super("오늘머먹지?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(new WesternPanel());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setContentPane(scrollPane);

		setSize(450, 600); // 창크기
		setVisible(true); // 창 보이게 하기
		setLocation(400, 100);
		
		loadClickSound("bgm/click.wav");
	}

	class WesternPanel extends JPanel {

		private JLabel FoodType = new JLabel("종류별");
		private JButton Back = new JButton("<");

		String[] Food = { "한식", "중식", "일식", "양식"};
		JComboBox<String> combo = new JComboBox<String>(Food);
		
		private ImageIcon foodimage[] = {new ImageIcon(getClass().getClassLoader().getResource("뽀르께노.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("데이스타.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("아삐에디.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("와이인.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("성북동파스타.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("부부셰프.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("너의냠냠.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("몽마르언덕.jpg"))
										};
		
		private JLabel[] imageLabel = new JLabel[8];
		
		private JLabel nameLabel[] = {new JLabel("뽀르께노 스페니쉬 비스트로"),
									  new JLabel("데이스타"),
									  new JLabel("아삐에디"),
									  new JLabel("와이인"),
									  new JLabel("성북동 파스타"),
									  new JLabel("부부셰프"),
									  new JLabel("너의 냠냠버거"),
									  new JLabel("몽마르 언덕"),
				 					 };

		private JLabel informationLabel[] = {new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 96<br>휴무일 : 일, 월<br>☎ 0507-1446-4562<br>도보 16분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 0507-1424-1996<br>도보 6분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. --<br>휴무일 : 일<br>☎ 0507-1333-5983<br>도보 10분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 83<br>휴무일 : 화<br>☎ 0507-1366-7322<br>도보 13분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 64<br>휴무일 : 일<br>☎ 0507-1328-0990<br>도보 10분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 48<br>휴무일 : X<br>☎ 0507-1411-4931<br>도보 18분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 54<br>휴무일 : 월<br>☎ 070-8731-3001<br>버스 13분 (성북 02)</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 42<br>휴무일 : 둘째주/넷째주 일<br>☎ 02-745-7321<br>도보 8분</body></HTML>"),
										 	};

		WesternPanel() {
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
			Back.setToolTipText("<HTML><body style='font-size:1.3em;font-family:휴먼편지체;'>이전 화면으로 돌아갑니다.</body></HTML>");
			add(Back);

			combo.setFont(new Font("휴먼편지체", Font.BOLD, 20));
			combo.setBounds(300, 20, 80, 30);
			combo.setSelectedIndex(3);
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
			
			setPreferredSize(new Dimension(400, 1280));

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
					else if(selectedFoodType.equals("중식")) {
						new ChineseFrame();
						playClickSound();
						dispose();
					}
					else if(selectedFoodType.equals("일식")) {
						new JapaneseFrame();
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
