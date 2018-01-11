package A10515003;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class TimerText extends JFrame implements ActionListener {
	private JLabel jlTimer=new JLabel();
	private Timer timer;
	
	public TimerText() {
		// TODO Auto-generated constructor stub
		setSize(100,100);
		add(jlTimer);
		//设置Timer定时器并启动
		timer=new Timer(500,this);
		timer.start();
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=new Date();
		jlTimer.setText(format.format(date));
	}
}
