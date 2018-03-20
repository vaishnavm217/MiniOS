/* 
Author : Kushal Borkar
Alias : Lashuk1729
IIIT SriCity
*/

package osmini;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class Terminal {

	public JInternalFrame frame;
	public JTextPane console;
	public JTextField input;
	public JScrollPane scrollpane;
	public String dir;
	public StyledDocument document;
	
	/**
	 * Launch the application.
	 */
	public class ti extends Thread
	{
		public void run()
		{
			while(true)
			{
				//lblNewLabel_1.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
				frame.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						frame.moveToFront();
					}
				});
				
			}
		}	
	}
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() {
			public void run()
			{
				try
				{
					Terminal window = new Terminal();
					window.frame.setVisible(true);
					//while()
					window.frame.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							window.frame.moveToFront();
						}
					});
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
	 */
	public Terminal() 
	{
		dir=System.getProperty("user.dir");
		//txtedit g=ew txtedit();
		
		
		
		frame = new JInternalFrame("Terminal",true,true,true,true);
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.toFront();
			}
		});
		frame.setResizable(true);
		frame.setIconifiable(true);
		frame.setClosable(true); 
		frame.setTitle("Terminal");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setClosable(true);
		console = new JTextPane();
		console.setEditable(false);
		console.setFont(new Font("Deja Vu Sans Mono", Font.BOLD, 16));
		console.setOpaque(false);
		
		document = console.getStyledDocument();
		input = new JTextField();
		input.setEditable(true);
		input.setFont(new Font("Monofur", Font.PLAIN, 16));
		input.setForeground(Color.WHITE);
		input.setCaretColor(Color.WHITE);
		input.setOpaque(false);
		
		input.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						String txt = input.getText();
						if(txt.length() > 1)
						{
							doCommand(txt);
							scrollBottom();
							input.setText("");
						}
					}
				}
		);
		
		input.addKeyListener(
				new KeyListener()
				{
					public void keyPressed(KeyEvent e) {}
					
					public void keyReleased(KeyEvent e) {}
					
					public void keyTyped(KeyEvent e) {}
				}
		);
		
		scrollpane = new JScrollPane(console);
		scrollpane.setBorder(null);
		scrollpane.setOpaque(false);
		scrollpane.getViewport().setOpaque(false);
		
		frame.add(input, BorderLayout.SOUTH);
		frame.add(scrollpane, BorderLayout.CENTER);
		
		frame.getContentPane().setBackground(new Color(50, 50, 50));
		
		frame.setBounds(100, 100, 978, 569);
		//frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
	}

	public void scrollTop()
	{
		console.setCaretPosition(0);
	}
	
	public void scrollBottom()
	{
		console.setCaretPosition(console.getDocument().getLength());
	}
	
	public void print(String S)
	{
		print(S, new Color(255,255,255));
	}
	
	public void print(String S, Color c)
	{
		Style style = console.addStyle("Style", null);
		StyleConstants.setForeground(style, c);
		try
		{
			document.insertString(document.getLength(), S, style);
		}
		catch (Exception E) {}
	}
	
	public void println(String S)
	{
		println(S, new Color(255,255,255));
	}
	
	public void println(String S, Color C)
	{
		print(S + "\n", C);
	}

	public void clear()
	{
		try
		{
			document.remove(0, document.getLength());
		}
		catch(Exception E) {}
	}
	
	public void doCommand(String txt)
	{
		String commands = txt;
		try
		{
			if(commands.equalsIgnoreCase("clr"))
			{
				clear();
			}
			else if(commands.contains("cd "))
			{
				String h=commands.split(" ")[1];
				System.out.println(h);
				if(h.contentEquals(".."))
					dir=dir.substring(0,dir.lastIndexOf("\\"));
				else
				{
					File [] f=new File(dir).listFiles();
					for(int i=0;i<f.length;i++)
					{
						if(h.contentEquals(f[i].getName()))
						{	System.out.println("YEAH!!");
							dir=f[i].getAbsolutePath();
							break;
						}
					}
				}
			}
			else
			{	
				String [] c,q;
				commands="cmd /c "+commands;
				c=commands.split(" ");
				final Process p = Runtime.getRuntime().exec(c,null,new File(dir));

				new Thread(new Runnable() {
				    public void run() {
				     BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
				     String line = null; 

				     try {
				        while ((line = input.readLine()) != null)
				            println(line);
				     } catch (IOException e) {
				            e.printStackTrace();
				     }
				    }
				}).start();

				p.waitFor();
				//println("Error -> This command doesn't exist..." , new Color(255, 55, 55));
			}
		}
		catch(Exception E)
		{
			println("Error -> " + E.getMessage(), new Color(255, 155, 155));
		}
		
	}
	
}
