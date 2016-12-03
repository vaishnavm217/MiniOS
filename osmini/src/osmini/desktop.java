package osmini;
import osmini.scheduling;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import osmini.Terminal;
import osmini.Text_Editor;
import osmini.Terminal;
import java.awt.BorderLayout;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

import java.awt.Font;
import javax.swing.UIManager;

public class desktop {
	public JLabel lblNewLabel_1;
	private static JDesktopPane frame;
	/**
	 * @wbp.nonvisual location=621,209
	 */
	/**
	 * Launch the application.
	 */
	public class ti extends Thread
	{
		public void run()
		{
			while(true)
			{
				lblNewLabel_1.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
			}
		}	
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
		desktop window = new desktop();
		window.frame.setVisible(true);
		
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 * @return 
	 */
	public desktop() {
		initialize();
		ti t=new ti();
		t.start();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDesktopPane();
		frame.setBackground(Color.WHITE);
		JFrame f = new JFrame();
		f.getContentPane().setLayout(new BorderLayout());
		f.getContentPane().add(frame, BorderLayout.CENTER);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);
		
		frame.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		scheduling s=new scheduling(frame);
		s.start();
		
		lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(1214, 0, 148, 41);
		frame.add(lblNewLabel_1);
		JLabel lblTerminal = new JLabel("Terminal");
		lblTerminal.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblTerminal.setIcon(null);
		lblTerminal.addMouseListener(new MouseAdapter() {
		
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton()==MouseEvent.BUTTON1)
				{
					Terminal t=new Terminal();
					t.frame.setVisible(true);
					s.q.add(t.frame);
					try {
						t.frame.setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			}
		});
		lblTerminal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTerminal.setBounds(1252, 72, 100, 100);
		frame.add(lblTerminal);
		
		JLabel lblFilemanager = new JLabel("Filemanager");
		lblFilemanager.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblFilemanager.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilemanager.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getButton()==MouseEvent.BUTTON1)
				{
					System.out.println("HII");
					FileSystem t=new FileSystem(frame);
					t.frame.setVisible(true);
					s.q.add(t.frame);
					try {
						t.frame.setSelected(true);
					} catch (PropertyVetoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
			}
		});
		lblFilemanager.setBounds(1252, 183, 100, 100);
		frame.add(lblFilemanager);
		
		JLabel lblNewLabel = new JLabel("Text Editor");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getButton()==MouseEvent.BUTTON1)
				{
					Text_Editor t = null;
					try {
						t = new Text_Editor();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					t.frame.setVisible(true);
					s.q.add(t.frame);
					try {
						t.frame.setSelected(true);
					} catch (PropertyVetoException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}					
				}
			}
		});
		lblNewLabel.setBounds(1252, 297, 100, 100);
		frame.add(lblNewLabel);
		
		//k.setVisible(false);
	}
}
