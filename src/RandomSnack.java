import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.Random;

public class RandomSnack extends JFrame{
	private static Clip click;
	
	public RandomSnack() {
		super("오늘머먹지?"); //창이름
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new RandomSnackPanel());
		setSize(450,600); //창크기
		setVisible(true); //창 보이게 하기
		setLocation(400,100);
		
		loadClickSound("bgm/click.wav");
	}
	class RandomSnackPanel extends JPanel{		
		private JLabel Today = new JLabel("오늘의");
		private JLabel Snack = new JLabel ("간식은");
		
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
		
		String[] SnackList = {"젤리", "초콜릿", "과자", "아이스크림", "케이크", "어묵", "사탕", "과일", "쿠키", "단밭빵", "크림빵",
							  "초코케이크", "피자빵", "크로아상", "소금빵", "휘낭시에", "스콘", "마카롱", "탕후루", "몽블랑", "슈크림",
							  "붕어빵", "델리만쥬", "호두과자", "식빵", "에그타르트", "과일타르트", "츄러스", "모찌", "떡", "베이글",
							  "치아바타", "바게트", "맘모스", "꽈배기", "찹쌀도너츠", "도넛", "타코야끼", "핫도그", "샌드위치", "모카번",
							  "앙버터", "프레첼", "머랭쿠키", "마들렌", "호빵", "와플", "마늘빵", "러스크", "카나페", "머핀", "카스테라",
							  "허니브레드", "계란빵", "땅콩과자", "국화빵", "호떡", "고로케", "소보로", "통밀빵", "롤케잌", "모닝빵", 
							  "팬케이크", "수플레"};
		String RandomFood;
		
		private volatile boolean running = true;
        private int timerCount = 0;
        private final int MAX_TIMER_COUNT = 15; 
        
		
		private JLabel FoodLabel;
		private JButton Retry = new JButton("다시 뽑기!");
		
		public RandomSnackPanel() {
			setLayout(null);
			setBackground(Color.WHITE);
			
			Today.setSize(360,120);
			Today.setLocation(130,20);
			Today.setForeground(Color.BLACK);
			Today.setFont(new Font("휴먼편지체",Font.BOLD,75));
			add(Today);
			
			Snack.setSize(360,120);
			Snack.setLocation(130,95);
			Snack.setForeground(Color.BLACK);
			Snack.setFont(new Font("휴먼편지체",Font.BOLD,75));
			add(Snack);
			
			Home.setBackground(Color.WHITE);
	        Home.setBounds(5,5, 50, 50);
	        Home.setToolTipText("<HTML><body style='font-size:1.5em;font-family:휴먼편지체;'>초기 화면으로 돌아갑니다.</body></HTML>");
	        add(Home);
	        
	        RandomFood = SnackList[random.nextInt(64)];
	        FoodLabel = new JLabel(RandomFood);
	        FoodLabel.setSize(300,150);
	        FoodLabel.setLocation(70,200);
	        FoodLabel.setForeground(Color.red);
	        FoodLabel.setFont(new Font("휴먼편지체",Font.BOLD,50));
	        FoodLabel.setHorizontalAlignment(JLabel.CENTER);
	        add(FoodLabel);
	        
	        Retry.setBackground(Color.PINK);
		    Retry.setFont(new Font("휴먼편지체",Font.BOLD,30));
		    Retry.setBounds(125, 420, 180, 80);
		    add(Retry);
	        
	        Home.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		running = false;
	        		new InitialScreen();
	        		//setVisible(false);
	        		playClickSound();
	        		dispose();
	        	}
	        });
	        
	        Retry.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		running = false;
	        		new RandomSnack();
	        		//setVisible(false);
	        		playClickSound();
	        		dispose();
	        	}
	        });
			
	        class FoodThread extends Thread {
                Random randomThread = new Random();

                public void run() {
                	playBackgroundMusic("bgm/slot machine.wav");
                	
                    while (running) {
                        try {
                            SwingUtilities.invokeAndWait(() -> {
                                FoodLabel.setText(SnackList[randomThread.nextInt(64)]);
                            });

                            Thread.sleep(100);
                            timerCount++;

                            if (timerCount >= MAX_TIMER_COUNT) {
                                running = false;
                                Home.setEnabled(true);
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
