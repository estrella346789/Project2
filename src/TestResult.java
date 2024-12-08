import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.Random;
import javax.imageio.ImageIO;

public class TestResult extends JFrame{
   private static Clip click;
   
   public TestResult() {
      super("오늘머먹지?"); //창이름
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      setContentPane(new TestResultPanel());
      setSize(450,600); //창크기
      setVisible(true); //창 보이게 하기
      setLocation(400,100);
      
      loadClickSound("bgm/click.wav");
   }
   class TestResultPanel extends JPanel{      
      private JLabel Today = new JLabel("오늘의");
      private JLabel Menu = new JLabel ("메뉴는");
      private JLabel RecommendedMenuImageLabel = new JLabel();
      private ImageIcon HomeIcon = new ImageIcon(getClass().getClassLoader().getResource("HomeIcon.png"));
      private JButton Home = new JButton("",HomeIcon);
      
      private ImageIcon foodimage[] = {new ImageIcon(getClass().getClassLoader().getResource("이조삼계탕.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("운봉손칼국수.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("와이인.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("고석환손만두전골.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("너의냠냠.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("뽀르께노.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("스타동.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("옛날중국집.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("홍추곱창카페.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("카레.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("성북동파스타.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("한성대양꼬치.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("손두부.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("공푸.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("방목.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("100년 설렁탕.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("삼선.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("우물집.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("삼선꼬마김밥.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("창창.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("맛닭꼬.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("스시현.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("부부셰프.jpg")),
				 					   new ImageIcon(getClass().getClassLoader().getResource("야끼토끼.jpg")),
									  };
      
      private JLabel[] imageLabel = new JLabel[24];
      
      private JLabel nameLabel[] = {new JLabel("이조 삼계탕"),
    		  						new JLabel("운봉손칼국수"),
    		  						new JLabel("와이인"),
    		  						new JLabel("고석환손만두전골"),
    		  						new JLabel("너의 냠냠버거"),
    		  						new JLabel("뽀르께노 스페니쉬 비스트로"),
    		  						new JLabel("스타동 한성대점"),
    		  						new JLabel("옛날 중국집"),
    		  						new JLabel("홍추곱창카페"),
    		  						new JLabel("카레"),
    		  						new JLabel("성북동 파스타"),
    		  						new JLabel("한성대 양꼬치"),
    		  						new JLabel("67년 전통 손두부"),
    		  						new JLabel("공푸"),
    		  						new JLabel("방목"),
    		  						new JLabel("100년 설렁탕 본점"),
    		  						new JLabel("삼선"),
    		  						new JLabel("우물집"),
    		  						new JLabel("삼선꼬마김밥"),
    		  						new JLabel("창창"),
    		  						new JLabel("맛닭꼬 한성대입구역점"),
    		  						new JLabel("스시현"),
    		  						new JLabel("부부셰프"),
    		  						new JLabel("야끼토끼")
      							   };
      
      private JLabel informationLabel[] = {new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 83<br>휴무일 : X<br>☎ 0507-1352-5340<br>도보 8분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 49<br>휴무일 : 일<br>☎ 02-953-5155<br>도보 9분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 83<br>휴무일 : 화<br>☎ 0507-1366-7322<br>도보 13분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 37<br>휴무일 : 월<br>☎ 02-744-1990<br>도보 18분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 54<br>휴무일 : 월<br>☎ 0507-8731-3001<br>버스 13분 (성북 02)</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 96<br>휴무일 : 일, 월<br>☎ 0507-1446-4562<br>도보 16분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 44<br>휴무일 : X<br>☎ 02-743-7707<br>도보 8분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 39<br>휴무일 : 월<br>☎ 02-764-0094<br>버스 11분 (성북 02)</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 52<br>휴무일 : X<br>☎ 02-6449-9288<br>도보 11분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 72<br>휴무일 : X<br>☎ --<br>버스 14분 (성북 02)</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 64<br>휴무일 : 일<br>☎ 0507-1328-0990<br>도보 10분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 31<br>휴무일 : --<br>☎ 02-911-9000<br>도보 15분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 02-745-1151<br>도보 7분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 55<br>휴무일 : 일, 첫째주/셋째주 월<br>☎ 0507-1314-4683<br>도보 19분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 61<br>휴무일 : X<br>☎ 010-5322-1562<br>도보 15분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 34<br>휴무일 : X<br>☎ 02-742-5588<br>도보 12분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : 월<br>☎ 0507-1358-7847<br>도보 13분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 0507-1318-7500<br>도보 13분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 85<br>휴무일 : X<br>☎ 02-742-6544<br>도보 8분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 0507-1370-3548<br>도보 15분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 02-745-2292<br>도보 12분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 44<br>휴무일 : 일<br>☎ 02-744-2257<br>도보 13분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ 4. 48<br>휴무일 : X<br>☎ 0507-1411-4931<br>도보 18분</body></HTML>"),
    		  							   new JLabel("<HTML><body style='font-size:1.7em;font-family:휴먼편지체;'>★ --<br>휴무일 : X<br>☎ 02-6925-6259<br>도보 14분</body></HTML>"),					   
				};
      
      //사각형
      public void paint(Graphics Rectangle) {
         Graphics2D RectangleStroke = (Graphics2D) Rectangle;
         super.paint(Rectangle);
         RectangleStroke.setStroke(new BasicStroke(6));
         RectangleStroke.setColor(Color.BLACK);
         RectangleStroke.drawRect(70,200,300,150);
      }
      
      private JLabel FoodLabel;
      
      public TestResultPanel() {
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
         
         // 추천 메뉴 이미지
         String imagePath = getRecommendedImagePath();
         try {
             Image recommendedMenuImage = ImageIO.read(new File(imagePath));
             RecommendedMenuImageLabel.setIcon(new ImageIcon(recommendedMenuImage));
         } catch (IOException e) {
             e.printStackTrace();
         }
         
         RecommendedMenuImageLabel.setBounds(20, 310, 300, 300);
         add(RecommendedMenuImageLabel);
         
         Home.setBackground(Color.WHITE);
           Home.setBounds(5,5, 50, 50);
           Home.setToolTipText("<HTML><body style='font-size:1.5em;font-family:휴먼편지체;'>초기 화면으로 돌아갑니다.</body></HTML>");
           add(Home);
           
           FoodLabel = new JLabel(getRecommendedMenu());
           FoodLabel.setSize(300,150);
           FoodLabel.setLocation(70,200);
           FoodLabel.setForeground(Color.red);
           FoodLabel.setFont(new Font("휴먼편지체",Font.BOLD,50));
           FoodLabel.setHorizontalAlignment(JLabel.CENTER);
           add(FoodLabel);
           
           Home.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                 new InitialScreen();
                 //setVisible(false);
                 playClickSound();
                 dispose();
              }
           });
      }
      private String getRecommendedImagePath() {
            String recommendedMenu = getRecommendedMenu().trim(); // 추천 메뉴
            String imagePath = "";

            switch (recommendedMenu) {
                case "삼계탕":
                    ShowInfo(0);
                    break;
                case "칼국수":
                    ShowInfo(1);
                    break;
                case "스테이크":
                    ShowInfo(2);
                    break;
                case "만두전골":
                    ShowInfo(3);
                    break;
                case "수제버거":
                    ShowInfo(4);
                    break;
                case "문어":
                    ShowInfo(5);
                    break;
                case "사케동":
                    ShowInfo(6);
                    break;
                case "짜장면":
                    ShowInfo(7);
                    break;
                case "곱창":
                    ShowInfo(8);
                    break;
                case "카레":
                    ShowInfo(9);
                    break;
                case "파스타":
                    ShowInfo(10);
                    break;
                case "양꼬치":
                    ShowInfo(11);
                    break;
                case "손두부찌개":
                    ShowInfo(12);
                    break;
                case "차돌짬뽕":
                    ShowInfo(13);
                    break;
                case "돼지고기":
                    ShowInfo(14);
                    break;
                case "설렁탕":
                    ShowInfo(15);
                    break;
                case "해물짬뽕":
                    ShowInfo(16);
                    break;
                case "소고기":
                    ShowInfo(17);
                    break;
                case "김밥":
                    ShowInfo(18);
                    break;
                case "우육면":
                    ShowInfo(19);
                    break;
                case "치킨":
                    ShowInfo(20);
                    break;
                case "초밥":
                    ShowInfo(21);
                    break;
                case "피자":
                    ShowInfo(22);
                    break;
                case "꼬치":
                    ShowInfo(23);
                    break;
                    
                default:
                    // 기본적으로 사용할 이미지 파일 경로 (없으면 빈 문자열로 설정)
                    
            }

            return imagePath;
        }
      private String getRecommendedMenu() {
            // 각 테스트에서 선택된 답변에 따라 추천 메뉴 반환
            String result = "";

            // Test1
            if ("좋다".equals(Test1.answerTest1)) {
                result += "good-";
            } else {
                result += "bad-";
            }

            // Test2
            if ("춥다".equals(Test2.answerTest2)) {
                result += "cold-";
            } else {
                result += "hot-";
            }

            // Test3
            if ("많이! 당장 밥 먹고 싶어요!".equals(Test3.answerTest3)) {
                result += "hurry-";
            } else {
                result += "slow-";
            }

            // Test4
            if ("밥".equals(Test4.answerTest4)) {
                result += "rice";
            } else if ("면, 빵".equals(Test4.answerTest4)) {
                result += "mbb";
            } else if ("고기".equals(Test4.answerTest4)) {
                result += "meat";
            }

            // 각 테스트에서 선택된 답변에 따라 메뉴 반환
            switch (result) {
                case "good-cold-hurry-rice":
                    return "삼계탕";
                case "good-cold-hurry-mbb":
                    return "칼국수";
                case "good-cold-hurry-meat":
                    return "스테이크";
                case "good-cold-slow-rice":
                    return "만두전골";
                case "good-cold-slow-mbb":
                    return "수제버거";
                case "good-cold-slow-meat":
                    return "문어";
                case "good-hot-hurry-rice":
                    return "사케동";
                case "good-hot-hurry-mbb":
                    return "짜장면";
                case "good-hot-hurry-meat":
                    return "곱창";
                case "good-hot-slow-rice":
                    return "카레";
                case "good-hot-slow-mbb":
                    return "파스타";
                case "good-hot-slow-meat":
                    return "양꼬치";
                case "bad-cold-hurry-rice":
                    return "손두부찌개";
                case "bad-cold-hurry-mbb":
                    return "차돌짬뽕";
                case "bad-cold-hurry-meat":
                    return "돼지고기";
                case "bad-cold-slow-rice":
                    return "설렁탕";
                case "bad-cold-slow-mbb":
                    return "해물짬뽕";
                case "bad-cold-slow-meat":
                    return "소고기";
                case "bad-hot-hurry-rice":
                    return "김밥";
                case "bad-hot-hurry-mbb":
                    return "우육면";
                case "bad-hot-hurry-meat":
                    return "치킨";
                case "bad-hot-slow-rice":
                    return "초밥";
                case "bad-hot-slow-mbb":
                    return "피자";
                case "bad-hot-slow-meat":
                    return "꼬치";
                default:
                    return "추천할 메뉴가 없습니다.";
                   
            }
        }
      private void ShowInfo(int index) {
    	    imageLabel[index] = new JLabel();
			imageLabel[index].setIcon(foodimage[index]);
			imageLabel[index].setSize(130,130);
			imageLabel[index].setLocation(20,400);
			add(imageLabel[index]);
			
          	nameLabel[index].setSize(500,100);
			nameLabel[index].setLocation(170,365);
			nameLabel[index].setForeground(Color.RED);
			nameLabel[index].setFont(new Font("휴먼편지체",Font.BOLD,23));
			add(nameLabel[index]);
          
          informationLabel[index].setSize(300,150);
			informationLabel[index].setLocation(170,405);
			add(informationLabel[index]);
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