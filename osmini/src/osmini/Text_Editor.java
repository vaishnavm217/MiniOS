/* 
Author : Kushal Borkar
Alias : Lashuk1729
IIIT SriCity
*/
package osmini;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;

public class Text_Editor extends JFrame{

	File currentEditingFile;
	public JInternalFrame frame;
	private JPanel contentPane;
	TextArea textArea = new TextArea();
	String Filename;
	String str;
	int start, end, pos;
	/**
	 * Launch the application.
	 */
	public class ti extends Thread
	{
		public void run()
		{
			while(true)
			{
				frame.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						frame.moveToFront();
					}
				});
				
			}
		}	
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					Text_Editor frame = new Text_Editor();
					frame.frame.setTitle(frame.Filename);
					frame.frame.setVisible(true);
					frame.frame.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							frame.frame.moveToFront();
						}
					});
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 */
	public Text_Editor(String p) throws IOException
	{
		currentEditingFile=new File(p);
		Filename=currentEditingFile.getName();
		initialize();
		
	}
	public Text_Editor() throws IOException {
		currentEditingFile=null;
		Filename="New Text";
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 */
	private void initialize() throws IOException {
		frame=new JInternalFrame(Filename,true,true,true,true);
		frame.setBounds(100, 100, 988, 554);		
		if(currentEditingFile!=null)
		{
			FileInputStream fis = new FileInputStream(currentEditingFile);
			byte[] data = new byte[(int) currentEditingFile.length()];
			fis.read(data);
			fis.close();
			String str = new String(data, "UTF-8");
			textArea.setText(str);

		}
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNewFile = new JMenuItem("New File");
		//mntmNewFile.setIcon(new ImageIcon("C:\\Users\\Kushal Borkar\\workspace\\OS_Terminal\\create-new-file-icons-49843.png"));
		mntmNewFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(Filename == null)
				{
					frame.setTitle("New File *");
				}
			}
		});
		mnFile.add(mntmNewFile);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open File");
		//mntmOpenFile.setIcon(new ImageIcon("C:\\Users\\Kushal Borkar\\workspace\\OS_Terminal\\icon_open.png"));
		mntmOpenFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0)
			{
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(true);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files", "txt");
				chooser.addChoosableFileFilter(filter);
				chooser.setCurrentDirectory(new File("C:\\Users\\mural\\workspace\\osmini\\Root"));
				int res=chooser.showOpenDialog(frame);
				if(res==JFileChooser.APPROVE_OPTION)
				{
					currentEditingFile = chooser.getSelectedFile();
					System.out.println("File Chosen. File name = "+ chooser.getSelectedFile().getName());
					try
					{
						Scanner scn = new Scanner(new FileInputStream(currentEditingFile));
						String buffer = "";
						while(scn.hasNext())
						{
							buffer+= scn.nextLine();
						}
						textArea.setText(buffer);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("No File Selected");
				}
			}
		});
		mnFile.add(mntmOpenFile);
		
		JMenuItem mntmSaveFile = new JMenuItem("Save File");
		mntmSaveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(Filename == null)
				{
					FileDialog dialog1 = new FileDialog(Text_Editor.this,"Save As...",FileDialog.SAVE);
					dialog1.setVisible(true);
					String s7 = dialog1.getDirectory();
					String s8 = dialog1.getFile();
					String s9 = s7 + s8 +".txt";
					String s6 = textArea.getText();
					int len1 = s6.length();
					byte buf[] = s6.getBytes();
					File f1 = new File(s9);
					FileOutputStream fobj1 = null;
					try
					{
						fobj1 = new FileOutputStream(f1);
					}
					catch(Exception e1)
					{
						e1.printStackTrace();
					}
					for(int k = 0; k < len1; k++)
					{
						try
						{
							fobj1.write(buf[k]);
						}
						catch(IOException e1)
						{
							e1.printStackTrace();
						}
					}
					try
					{
						fobj1.close();
					}
					catch(IOException e1)
					{
						e1.printStackTrace();
					}
					
					frame.setTitle(dialog1.getFile());
				}
				else
				{
					FileDialog dialog1 = new FileDialog(Text_Editor.this,"Save As...",FileDialog.SAVE);
					dialog1.setVisible(true);
					String s7 = dialog1.getDirectory();
					String s8 = Text_Editor.this.getTitle();
					String s9 = s7 + s8 +".txt";
					String s6 = textArea.getText();
					int len1 = s6.length();
					byte buf[] = s6.getBytes();
					File f1 = new File(s9);
					FileOutputStream fobj1 = null;
					try
					{
						fobj1 = new FileOutputStream(f1);
					}
					catch(Exception e1)
					{
						e1.printStackTrace();
					}
					for(int k = 0; k < len1; k++)
					{
						try
						{
							fobj1.write(buf[k]);
						}
						catch(IOException e1)
						{
							e1.printStackTrace();
						}
					}
					try
					{
						fobj1.close();
					}
					catch(IOException e1)
					{
						e1.printStackTrace();
					}
					Text_Editor.this.setTitle(dialog1.getFile());
				}
			}
		});
		mnFile.add(mntmSaveFile);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As...");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				FileDialog dialog1 = new FileDialog(Text_Editor.this,"Save As...",FileDialog.SAVE);
				dialog1.setVisible(true);
				String s7 = dialog1.getDirectory();
				String s8 = dialog1.getFile();
				String s9 = s7 + s8 +".txt";
				String s6 = textArea.getText();
				int len1 = s6.length();
				byte buf[] = s6.getBytes();
				File f1 = new File(s9);
				FileOutputStream fobj1 = null;
				try
				{
					fobj1 = new FileOutputStream(f1);
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
				for(int k = 0; k < len1; k++)
				{
					try
					{
						fobj1.write(buf[k]);
					}
					catch(IOException e1)
					{
						e1.printStackTrace();
					}
				}
				try
				{
					fobj1.close();
				}
				catch(IOException e1)
				{
					e1.printStackTrace();
				}
				
				Text_Editor.this.setTitle(dialog1.getFile());
			}
		});
		mnFile.add(mntmSaveAs);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		mnFile.add(mntmExit);
		
		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmCut = new JMenuItem("Cut");
		mntmCut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				str = textArea.getSelectedText();
				start = textArea.getSelectionStart();
				end =  textArea.getSelectionEnd();
				textArea.replaceRange("", start, end);
			}
		});
		
		JMenuItem mntmCopy = new JMenuItem("Copy");
		mntmCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				textArea.getSelectedText();
			}
		});
		JMenuItem mntmPaste = new JMenuItem("Paste");
		mntmPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				pos = textArea.getCaretPosition();
				textArea.insert(str, pos);
			}
		});
		frame.getContentPane().add(textArea, BorderLayout.CENTER);
	}

}
