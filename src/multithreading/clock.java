package multithreading;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class clock extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField time;
	private JLabel label;
	private JButton open;
	private JLabel timeZone;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DateFormat df = new SimpleDateFormat("HH:mm:ss");
					clock frame = new clock(df);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public clock(DateFormat df) {
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		setBounds(100, 100, 323, 142);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		label = new JLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(127, 10, 172, 49);
		contentPane.add(label);
		
		time = new JTextField();
		time.setBounds(10, 68, 198, 35);
		contentPane.add(time);
		time.setColumns(10);
		
		open = new JButton("OPEN");
		open.setBounds(214, 69, 85, 35);
		contentPane.add(open);
		
		timeZone = new JLabel("");
		timeZone.setBounds(0, 10, 117, 48);
		contentPane.add(timeZone);
		Start(null);
		open.addActionListener(this);
		this.timeZone.setText("GMT+07:00");
	
	}
	
	 public void Start(String a) {
	        new Thread(()->{
	            while (true) {
	                updateTime(a);
	                try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
	            }
	        }).start();
	       
	    }

	    private void updateTime(String hour) {
	    	SimpleDateFormat dateFormat;
	    	if (hour != null)
	    		{
	    		Date currentDate = new Date();
	    		dateFormat = new SimpleDateFormat("HH:mm:ss");
	    		TimeZone timeZone = null;
	    		if(Integer.parseInt(hour) > 0)
	    			 timeZone = TimeZone.getTimeZone("GMT+"+hour+":00");
	    		dateFormat.setTimeZone(timeZone);
	    		String formattedTime = dateFormat.format(currentDate);
	    		label.setText(formattedTime);
	    		this.timeZone.setText(timeZone.getID().toString());
	    	
	    		}
	    	else
	    		{
	    		dateFormat = new SimpleDateFormat("HH:mm:ss");
	    		label.setText(dateFormat.format(new Date()));
	    		}
	    }

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("OPEN"))
			{
				clock a = new clock(null);
				a.Start(time.getText());
				a.setVisible(true);
			}
			
		}

}
