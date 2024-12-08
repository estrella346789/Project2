import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import javax.sound.sampled.*;
import javax.swing.border.Border;

public class loading extends JFrame{
	public loading() { 
		super("오늘머먹지?"); //창이름
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setContentPane(new loadingPanel());
		setSize(450,600); //창크기
		setVisible(true); //창 보이게 하기
		setLocation(400,100);
	}
	
	class loadingPanel extends JPanel {
        JProgressBar progressBar = new JProgressBar();
        
        private JLabel analyze = new JLabel("테스트 분석중");

        public loadingPanel() {
        	setLayout(null);
			setBackground(Color.WHITE);
			
			analyze.setSize(140,20);
			analyze.setLocation(170,250);
			analyze.setForeground(Color.BLACK);
			analyze.setFont(new Font("휴먼편지체",Font.BOLD,20));
			add(analyze);
			
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            progressBar.setBounds(125, 280, 200, 30);
            progressBar.setBackground(Color.WHITE);
            progressBar.setForeground(Color.PINK);
            add(progressBar);

            Timer timer = new Timer(50, new ActionListener() {
                int progress = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    progress += 2;
                    progressBar.setValue(progress);

                    if (progress >= 100) {
                        ((Timer) e.getSource()).stop();
                        
                        new TestResult();
                        dispose();
                    }
                }
            });

            timer.start();
        }
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
