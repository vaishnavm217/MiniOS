package osmini;

import java.awt.*;
import java.io.*;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;


public class txtedit {

	public static int c;
	public String name,path;
	private JInternalFrame frame;
	//private JMenuBar menuBar;
	JScrollPane scrollPane;
	JMenuBar menuBar;
	JEditorPane editorPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		txtedit window=new txtedit(System.getProperty("user.dir"));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window.frame.setVisible(true);
					window.frame.setTitle(window.name);
					window.frame.setBounds(0, 0, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
					window.scrollPane.setBounds(0, 20, window.frame.getWidth(), window.frame.getHeight()-42);
					window.menuBar.setBounds(0,0,window.frame.getWidth(),21);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public JInternalFrame ret_frame()
	{
		return frame;
	}

	/**
	 * Create the application.
	 */
	public txtedit(String p) {
		try{
		name=p.substring(p.lastIndexOf("\\")+1);
		}catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("AAYYYYY");
		}
		path=p;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void save()
	{
		String con=editorPane.getText();
		FileWriter F;
		
		try{
		if(path.contains(".txt"))
			F=new FileWriter(path);
		else
		{
			name="Newtxt"+c+".txt";
			path+='\\'+name;
			F=new FileWriter(path);
			frame.setTitle(name);
		}
		F.write(con);
		F.close();
		}catch(IOException e){}
		System.out.println(path);
	System.out.println(con);
	}
	private void initialize() {
		frame = new JInternalFrame();
		frame.setBounds(0, 0, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width, java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setClosable(true);
		frame.getContentPane().setLayout(null);
		frame.setResizable(true);;
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 649, 21);
		frame.getContentPane().add(menuBar);
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_1 = new JMenu("New");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				save();
				c++;
				name="Newtxt"+c+".txt";
				path=System.getProperty("user.dir")+"\\"+name;
				
				frame.setTitle(name);
				System.out.println(path);
				editorPane.setText("");
			}
		});
		mnNewMenu_1.setHorizontalAlignment(SwingConstants.LEFT);
		mnNewMenu.add(mnNewMenu_1);
		
		JMenu mnNewMenu_2 = new JMenu("Open");
		mnNewMenu.add(mnNewMenu_2);
		
		JMenu mnNewMenu_3 = new JMenu("Save");
		mnNewMenu_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				save();
			}
		});
		mnNewMenu.add(mnNewMenu_3);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 20, frame.getWidth(), frame.getHeight()-60);
		frame.getContentPane().add(scrollPane);
		
		editorPane = new JEditorPane();
		scrollPane.setViewportView(editorPane);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
