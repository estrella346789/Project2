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

class JapaneseFrame extends JFrame {
	private static Clip click;
	
	public JapaneseFrame() {
		super("오늘머먹지?");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(new JapanesePanel());
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		setContentPane(scrollPane);

		setSize(450, 600); // 창크기
		setVisible(true); // 창 보이게 하기
		setLocation(400, 100);
		
		loadClickSound("bgm/click.wav");
	}

	class JapanesePanel extends JPanel {

		private JLabel FoodType = new JLabel("종류별");
		private JButton Back = new JButton("<");

		String[] Food = { "한식", "중식", "일식", "양식"};
		JComboBox<String> combo = new JComboBox<String>(Food);
		
		private ImageIcon foodimage[] = {new ImageIcon(getClass().getClassLoader().getResource("다바타식당.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("스타동.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("김나돈.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("시노다야.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("야끼토끼.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("스시산.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("스시현.jpg")),
										 new ImageIcon(getClass().getClassLoader().getResource("카레.jpg"))
				                        };
		
		private JLabel[] imageLabel = new JLabel[8];
		
		private JLabel nameLabel[] = {new JLabel("다바타식당서울"),
									  new JLabel("스타동 한성대점"),
									  new JLabel("김나돈"),
									  new JLabel("시노다야 이자카야"),
									  new JLabel("야끼토끼"),
									  new JLabel("스시산"),
									  new JLabel("스시현"),
									  new JLabel("카레"),
				  					 };

		private JLabel informationLabel[] = {new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 68<br>휴무일 : 일<br>☎ 010-3768-6475<br>도보 7분</body></HTML>"),
									 		 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 44<br>휴무일 : X<br>☎ 02-743-7707<br>도보 8분</body></HTML>"),
									 		 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 4<br>휴무일 : X<br>☎ 0507-1384-0758<br>도보 9분</body></HTML>"),
									 		 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 85<br>휴무일 : --<br>☎ 02-928-9292<br>도보 12분</body></HTML>"),
									 		 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. --<br>휴무일 : X<br>☎ 02-6925-6259<br>도보 14분</body></HTML>"),
									 		 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 26<br>휴무일 : X<br>☎ 02-745-3730<br>도보 20분</body></HTML>"),
									 		 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 44<br>휴무일 : 일<br>☎ 02-744-2257<br>도보 13분</body></HTML>"),
									 		 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 72<br>휴무일 : X<br>☎ --<br>버스 14분 (성북 02)</body></HTML>"),
											};

		JapanesePanel() {
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
			combo.setSelectedIndex(2);
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
