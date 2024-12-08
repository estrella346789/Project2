import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.Random;

public class RandomMenu extends JFrame{
	private static Clip click;
	
	public RandomMenu() {
		super("오늘머먹지?"); //창이름
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new RandomMenuPanel());
		setSize(450,600); //창크기
		setVisible(true); //창 보이게 하기
		setLocation(400,100);
		
		loadClickSound("bgm/click.wav");
	}
	class RandomMenuPanel extends JPanel{		
		private JLabel Today = new JLabel("오늘의");
		private JLabel Menu = new JLabel ("메뉴는");
		
		private ImageIcon HomeIcon = new ImageIcon(getClass().getClassLoader().getResource("HomeIcon.png"));
		private JButton Home = new JButton("",HomeIcon);
		
		//사각형
		public void paint(Graphics Rectangle) {
			Graphics2D RectangleStroke = (Graphics2D) Rectangle;
			super.paint(Rectangle);
			RectangleStroke.setStroke(new BasicStroke(6));
			RectangleStroke.setColor(Color.BLACK);
			RectangleStroke.drawRect(70,200,300,150);
		}
		
		Random random = new Random();
		
		String[] FoodList = {"손두부", "소고기", "브런치", "삼계탕", "함박스테이크", "떡볶이", "김밥", "사케동", "우육면", "<HTML><body>김치나베<br>돈카츠</body></HTML>",
							 "칼국수", "곱창", "파스타", "오꼬노미야끼", "해물짬뽕", "치킨", "설렁탕", "돼지고기", "양꼬치",
							 "스테이크", "문어", "꿀대구", "꼬치", "스시", "차돌짬뽕", "피자", "만두전골", "짜장면", "카레", "수제버거"};
		String RandomFood;
		
		private volatile boolean running = true;
        private int timerCount = 0;
        private final int MAX_TIMER_COUNT = 15;
        
		
		private JLabel FoodLabel;
		
		private ImageIcon foodimage[] = {new ImageIcon (getClass().getClassLoader().getResource("손두부.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("우물집.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("데이스타.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("이조삼계탕.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("다바타식당.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("삼선꼬마김밥.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("삼선꼬마김밥.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("스타동.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("창창.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("김나돈.jpg")),
									 	 new ImageIcon (getClass().getClassLoader().getResource("운봉손칼국수.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("홍추곱창카페.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("성북동파스타.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("시노다야.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("삼선.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("맛닭꼬.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("100년 설렁탕.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("방목.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("한성대양꼬치.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("와이인.jpg")),										 
										 new ImageIcon (getClass().getClassLoader().getResource("뽀르께노.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("뽀르께노.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("야끼토끼.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("스시현.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("공푸.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("부부셰프.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("고석환손만두전골.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("옛날중국집.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("카레.jpg")),
										 new ImageIcon (getClass().getClassLoader().getResource("너의냠냠.jpg"))
										};

		private JLabel[] imageLabel = new JLabel[30];

		private JLabel nameLabel[] = {new JLabel("67년 전통 손두부"),
									  new JLabel("우물집"),
									  new JLabel("데이스타"),
									  new JLabel("이조 삼계탕"),
									  new JLabel("다바타식당서울"),
									  new JLabel("삼선꼬마김밥"),
									  new JLabel("삼선꼬마김밥"),
									  new JLabel("스타동 한성대점"),
									  new JLabel("창창"),
									  new JLabel("김나돈"),
									  new JLabel("운봉손칼국수"),
									  new JLabel("홍추곱창카페"),
									  new JLabel("성북동 파스타"),
									  new JLabel("시노다야 이자카야"),
									  new JLabel("삼선"),
									  new JLabel("맛닭꼬 한성대입구역점"),
									  new JLabel("100년 설렁탕 본점"),
									  new JLabel("방목"),
									  new JLabel("한성대 양꼬치"),
									  new JLabel("와이인"),
									  new JLabel("뽀르께노 스페니쉬 비스트로"),
									  new JLabel("뽀르께노 스페니쉬 비스트로"),
									  new JLabel("야끼토끼"),
									  new JLabel("스시현"),
									  new JLabel("공푸"),
									  new JLabel("부부셰프"),
									  new JLabel("고석환손만두전골"),
									  new JLabel("옛날 중국집"),
									  new JLabel("카레"),
									  new JLabel("너의 냠냠버거")
									 };

		private JLabel informationLabel[] = {new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 02-745-1151<br>도보 7분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 0507-1318-7500<br>도보 13분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 0507-1424-1996<br>도보 6분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 83<br>휴무일 : X<br>☎ 0507-1352-5340<br>도보 8분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 68<br>휴무일 : 일<br>☎ 010-3768-6475<br>도보 7분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 85<br>휴무일 : X<br>☎ 02-742-6544<br>도보 8분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 85<br>휴무일 : X<br>☎ 02-742-6544<br>도보 8분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 44<br>휴무일 : X<br>☎ 02-743-7707<br>도보 8분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 0507-1370-3548<br>도보 15분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 4<br>휴무일 : X<br>☎ 0507-1384-0758<br>도보 9분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 49<br>휴무일 : 일<br>☎ 02-953-5155<br>도보 9분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 52<br>휴무일 : X<br>☎ 02-6449-9288<br>도보 11분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 64<br>휴무일 : 일<br>☎ 0507-1328-0990<br>도보 10분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 85<br>휴무일 : --<br>☎ 02-928-9292<br>도보 12분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : 월<br>☎ 0507-1358-7847<br>도보 13분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 02-745-2292<br>도보 12분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 34<br>휴무일 : X<br>☎ 02-742-5588<br>도보 12분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 61<br>휴무일 : X<br>☎ 010-5322-1562<br>도보 15분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 31<br>휴무일 : --<br>☎ 02-911-9000<br>도보 15분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 83<br>휴무일 : 화<br>☎ 0507-1366-7322<br>도보 13분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 96<br>휴무일 : 일, 월<br>☎ 0507-1446-4562<br>도보 16분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 96<br>휴무일 : 일, 월<br>☎ 0507-1446-4562<br>도보 16분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 02-6925-6259<br>도보 14분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 44<br>휴무일 : 일<br>☎ 02-744-2257<br>도보 13분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 55<br>휴무일 : 일, 첫째주/셋째주 월<br>☎ 0507-1314-4683<br>도보 19분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 48<br>휴무일 : X<br>☎ 0507-1411-4931<br>도보 18분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 37<br>휴무일 : 월<br>☎ 02-744-1990<br>도보 18분</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 39<br>휴무일 : 월<br>☎ 02-764-0094<br>버스 11분 (성북 02)</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 72<br>휴무일 : X<br>☎ --<br>버스 14분 (성북 02)</body></HTML>"),
											 new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 54<br>휴무일 : 월<br>☎ 070-8731-3001<br>버스 13분 (성북 02)</body></HTML>"),
											};
		
		public RandomMenuPanel() {
			setLayout(null);
			setBackground(Color.WHITE);
			
			Today.setSize(360,120);
			Today.setLocation(130,20);
			Today.setForeground(Color.BLACK);
			Today.setFont(new Font("휴먼편지체",Font.BOLD,75));
			add(Today);
			
			Menu.setSize(360,120);
			Menu.setLocation(130,95);
			Menu.setForeground(Color.BLACK);
			Menu.setFont(new Font("휴먼편지체",Font.BOLD,75));
			add(Menu);
			
			Home.setBackground(Color.WHITE);
	        Home.setBounds(5,5, 50, 50);
	        Home.setToolTipText("<HTML><body style='font-size:1.5em;font-family:휴먼편지체;'>초기 화면으로 돌아갑니다.</body></HTML>");
	        add(Home);
	        
	        RandomFood = FoodList[random.nextInt(30)];
	        FoodLabel = new JLabel(RandomFood);
	        FoodLabel.setSize(300,150);
	        FoodLabel.setLocation(70,200);
	        FoodLabel.setForeground(Color.red);
	        FoodLabel.setFont(new Font("휴먼편지체",Font.BOLD,50));
	        FoodLabel.setHorizontalAlignment(JLabel.CENTER);
	        add(FoodLabel);
	        
	        
	        
	        Home.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		running = false;
	        		new InitialScreen();
	        		//setVisible(false);
	        		playClickSound();
	        		dispose();
	        	}
	        });
			
	        class FoodThread extends Thread {
                Random randomThread = new Random();
                private int selectedIndex;

                public void run() {
                	playBackgroundMusic("bgm/slot machine.wav");
                	
                    while (running) {
                        try {
                            SwingUtilities.invokeAndWait(() -> {
                            	selectedIndex = randomThread.nextInt(30);
                                FoodLabel.setText(FoodList[selectedIndex]);
                            });

                            Thread.sleep(100);
                            timerCount++;

                            if (timerCount >= MAX_TIMER_COUNT) {
                                running = false;
                                Home.setEnabled(true);
                                if (timerCount == MAX_TIMER_COUNT ) { 
                                    ShowInfo(selectedIndex);
                                }
                            }
                        } catch (InterruptedException | java.lang.reflect.InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
	        
	        

            Thread foodThread = new FoodThread();
            foodThread.start();
		
		}
		private void ShowInfo(int selectedIndex) {
            if (imageLabel[selectedIndex] != null) {
                remove(imageLabel[selectedIndex]);
            }

            imageLabel[selectedIndex] = new JLabel();
            imageLabel[selectedIndex].setIcon(foodimage[selectedIndex]);
            imageLabel[selectedIndex].setSize(130, 130);
            imageLabel[selectedIndex].setLocation(20, 400);
            add(imageLabel[selectedIndex]);
            
            nameLabel[selectedIndex].setSize(500,100);
			nameLabel[selectedIndex].setLocation(170,365);
			nameLabel[selectedIndex].setForeground(Color.RED);
			nameLabel[selectedIndex].setFont(new Font("휴먼편지체",Font.BOLD,23));
			add(nameLabel[selectedIndex]);
            
            informationLabel[selectedIndex].setSize(300,150);
			informationLabel[selectedIndex].setLocation(170,405);
			add(informationLabel[selectedIndex]);

            // 변경 사항을 패널에 적용하기 위해 다시 그립니다.
            repaint();
        }
		
		private void playBackgroundMusic(String pathName) {
            try {
                Clip bgm = AudioSystem.getClip();
                File audioFile = new File(pathName);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                bgm.open(audioStream);
                bgm.start(); // 백그라운드 음악 재생 시작
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }
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
