import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;

public class InitialScreen extends JFrame{ //초기화면 클래스
	private static Clip clip;  // 정적(static) 변수로 변경
    private static boolean isMusicOn = true;
    
    private static Clip click;
    
	public InitialScreen() { 
		super("오늘머먹지?"); //창이름
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new InitialPanel());
		setSize(450,600); //창크기
		setVisible(true); //창 보이게 하기
		setLocation(400,100);	
		
		loadBackgroundMusic("bgm/bgm.wav");
		loadClickSound("bgm/click.wav");
        playBackgroundMusic();
	}
	
	class InitialPanel extends JPanel{ //초기화면 jpanel
		//타이틀
		private JLabel oh = new JLabel("오"); //'오 label
		private JLabel neul = new JLabel("늘"); //늘 label
		private JLabel what = new JLabel("머"); //머' label
		private JLabel eat = new JLabel("먹지?"); //먹지? label
		
		//버튼
		private JButton button1 = new JButton("거리순");
		private JButton button2 = new JButton("종류별");
		private JButton button3 = new JButton("별점순");
		private JButton button4 = new JButton();
		private JButton button5 = new JButton("간식");
		
		//private ImageIcon MusicOnIcon = new ImageIcon("Icon_image/Music_on.png");
		private ImageIcon MusicOnIcon = new ImageIcon(getClass().getClassLoader().getResource("Music_on.png"));
		//private ImageIcon MusicOffIcon = new ImageIcon("Icon_image/Music_off.png");
		private ImageIcon MusicOffIcon = new ImageIcon(getClass().getClassLoader().getResource("Music_off.png"));

		private JButton Music = new JButton("",MusicOnIcon);
		
		public InitialPanel() {
			setLayout(null);
			setBackground(Color.WHITE);
			
			//오
			oh.setSize(120,120);
			oh.setLocation(20,20);
			oh.setForeground(Color.RED);
			oh.setFont(new Font("휴먼편지체",Font.BOLD,120));
			add(oh);
			
			//늘
			neul.setSize(40,40);
			neul.setLocation(110,60);
			neul.setForeground(Color.BLACK);
			neul.setFont(new Font("휴먼편지체",Font.BOLD,40));
			add(neul);
			
			//머
			what.setSize(120,120);
			what.setLocation(190,20);
			what.setForeground(Color.RED);
			what.setFont(new Font("휴먼편지체",Font.BOLD,120));
			add(what);
			
			//먹지?
			eat.setSize(150,40);
			eat.setLocation(330,60);
			eat.setForeground(Color.BLACK);
			eat.setFont(new Font("휴먼편지체",Font.BOLD,40));
			add(eat);
			
			Font font1 = new Font("휴먼편지체",Font.BOLD,20);
	        Font font2 = new Font("휴먼편지체",Font.BOLD,12);
	         
	        //거리순 버튼
	        button1.setVisible(true);
	        button1.setBackground(Color.PINK);
	        button1.setFont(font1);
	        button1.setBounds(50,200, 130, 50);
	        add(button1);
	        
	        //종류별 버튼
	        button2.setVisible(true);
	        button2.setBackground(Color.PINK);
	        button2.setFont(font1);
	        button2.setBounds(50,300, 130, 50);
	        add(button2);
	        
	        //별점순 버튼
	        button3.setVisible(true);
	        button3.setBackground(Color.PINK);
	        button3.setFont(font1);
	        button3.setBounds(50,400, 130, 50);
	        add(button3);
	        
	        //결정이 어렵다면 이걸 눌러봐~ 버튼
	        button4.setText("<HTML><body style='text-align:center;'>결정이 어렵다면 <br>이걸 눌러봐~</body></HTML>");
	        button4.setVisible(true);
	        button4.setBackground(Color.PINK);
	        button4.setFont(font2);
	        button4.setBounds(250,230, 130, 100);
	        add(button4);
	        
	        //간식 버튼
	        button5.setVisible(true);
	        button5.setBackground(Color.PINK);
	        button5.setFont(font1);
	        button5.setBounds(250,380, 130, 50);
	        add(button5);
	        
	        //배경음악 버튼
	        Music.setBackground(Color.WHITE);
	        Music.setBounds(370, 500, 52, 52);
	        add(Music);
			
	        button1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new Distance();
	        		//setVisible(false);
	        		 playClickSound();
	        		dispose();
	        	}
	        });
	        
	        button2.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new FoodTypeChoose();
	        		//setVisible(false);
	        		 playClickSound();
	        		dispose();
	        	}
	        });
	        
	        
	        button3.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new StarRating();
	        		//setVisible(false);
	        		 playClickSound();
	        		dispose();
	        	}
	        });
	        
	        button4.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new MenuRecommend();
	        		//setVisible(false);
	        		 playClickSound();
	        		dispose();
	        	}
	        });
	        
	        button5.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        		new RandomSnack();
	        		//setVisible(false);
	        		 playClickSound();
	        		dispose();
	        	}
	        });
	        
	        Music.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                if (isMusicOn) {
	                    // 현재 음악이 켜져 있다면 MusicOffIcon으로 전환
	                    Music.setIcon(MusicOffIcon);
	                    stopBackgroundMusic();
	                    playClickSound();
	                } 
	                else {
	                    // 현재 음악이 꺼져 있다면 MusicOnIcon으로 전환
	                    Music.setIcon(MusicOnIcon);
	                    playBackgroundMusic();
	                    playClickSound();
	                }
	                isMusicOn = !isMusicOn;
	            }
	        });
		}
	}
	private void loadBackgroundMusic(String pathName) {
        try {
        	if (clip == null) {
        		clip = AudioSystem.getClip();//비어있는 오디오 클립 만들기
        		File audioFile = new File(pathName); //오디오 파일의 경로명
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile); //오디오 파일로부터 
                clip.open(audioStream); //재생할 오디오 스트림 열기
                clip.loop(Clip.LOOP_CONTINUOUSLY); // 배경음악을 반복 재생합니다.
                
                if(!isMusicOn) {
                	stopBackgroundMusic();
                }
        	}
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
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

    private void playBackgroundMusic() {
        if (clip != null && !clip.isRunning()) {
            clip.start();
        }
    }

    private void stopBackgroundMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
    
    private void playClickSound() {
        if (click != null) {
            click.setFramePosition(0);
            click.start();
        }
    }

	public static void main(String[] args) {
		InitialScreen IS = new InitialScreen();
	}
}
