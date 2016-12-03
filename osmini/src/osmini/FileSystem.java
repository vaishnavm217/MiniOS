package osmini;
import java.io.*;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
//import javax.swing.event.TreeModelEvent;
//import javax.swing.event.TreeModelListener;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import javax.swing.tree.*;

//import fSys.FileSystem.TListener;
import java.awt.event.MouseAdapter;
import osmini.Text_Editor;
import osmini.fcfs;
public class FileSystem {
	public static fcfs obj;
	public static Object os;
	static int x=0;
	public static class MyTreeCellRenderer extends DefaultTreeCellRenderer {
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 8698185110415183136L;

		@Override
		public Component getTreeCellRendererComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4,
				int arg5, boolean arg6) {
			super.getTreeCellRendererComponent(arg0, arg1, arg2, arg3, arg4, arg5, arg6);
			if(arg1 instanceof DefaultMutableTreeNode)
			{
				item a=(item)((DefaultMutableTreeNode) arg1).getUserObject();
				if(a.id==false)
				{
					setIcon(UIManager.getIcon("FileView.fileIcon"));
				}
				else
				{
					setIcon(UIManager.getIcon("FileView.directoryIcon"));
				}
			}
			// TODO Auto-generated method stub
			return this;
		}

	}

	JInternalFrame frame;
	JScrollPane scrollPane;
	public DefaultTreeModel tmodel;
	public JTree tree;
	public class item
	{
		String name;
		boolean id;
		String path;
		public item(boolean i,String n,String p){
			id=i;
			name=n;
			path=p;
		}
		public String toString()
		{
			return name;
		}
		public boolean check()
		{
			return id;
		}
	}
	public void walk( String path,DefaultMutableTreeNode node ) { 

        File y = new File( path ); 
        File[] list = y.listFiles(); 
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        for ( File f : list ) { 
            if ( f.isDirectory() ) {
            	item i=new item(true,f.getName(),f.getAbsolutePath());
            	DefaultMutableTreeNode n=new DefaultMutableTreeNode(i);
            	model.insertNodeInto(n, node, node.getChildCount());
                walk( f.getAbsolutePath(),n); 
            } 
            else {
            	item i=new item(false,f.getName(),f.getAbsolutePath());
            	DefaultMutableTreeNode n=new DefaultMutableTreeNode(i);
            	model.insertNodeInto(n, node, node.getChildCount()); 
            } 
        } 
    }
	
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileSystem window = new FileSystem(os);
					window.frame.setVisible(true);
					window.frame.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							window.frame.moveToFront();
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FileSystem() {
		os=null;
		tmodel =new DefaultTreeModel(new DefaultMutableTreeNode(new item(true,"Root",System.getProperty("user.dir"))));
		tree = new JTree(tmodel);
		walk(System.getProperty("user.dir"),(DefaultMutableTreeNode)tmodel.getRoot());
		initialize();
		obj=new fcfs();
	}
	public FileSystem(Object t) {
		os=t;
		tmodel =new DefaultTreeModel(new DefaultMutableTreeNode(new item(true,"Root",System.getProperty("user.dir"))));
		tree = new JTree(tmodel);
		walk(System.getProperty("user.dir"),(DefaultMutableTreeNode)tmodel.getRoot());
		initialize();
		obj=new fcfs();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public static void deleteDir(File f)
	{
		if(f.isDirectory())
		{
			if(f.listFiles().length==0)
			{
				f.delete();
			}
			else
			{
				String files[] = f.list();

	        	   for (String temp : files) {
	        	      
	        	      File fileDelete = new File(f, temp);

	        	      
	        	     deleteDir(fileDelete);
	        	   }
			}
			if(f.listFiles().length==0)
			{
				f.delete();
			}
		}
		else
		{
			f.delete();
		}
	}
	private void initialize() {
		frame=new JInternalFrame("Space Explorer",true,true,true,true);
		frame.setBounds(100, 100, 450, 300);
		frame.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.toFront();
			}
		});
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 434, 239);
		frame.getContentPane().add(scrollPane);
		
		//tmodel.addTreeModelListener(new TListener());
		//tmodel.setAsksAllowsChildren (true);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=tree.getRowForLocation(e.getX(),e.getY());
			    if(row==-1) //When user clicks on the "empty surface"
			        tree.clearSelection();
			    if(e.getButton()==MouseEvent.BUTTON1)
			    {
			    	if(e.getClickCount()>=2)
			    	{
			    		TreePath temp=tree.getSelectionPath();
			    		if(temp!=null)
			    		{
			    			DefaultMutableTreeNode temp2=(DefaultMutableTreeNode)temp.getLastPathComponent();
			    			item k=(item)temp2.getUserObject();
			    			if(!k.id)
			    			{
			    				System.out.println(obj.ff(k.path));
			    				//if(obj.ff(k.path)!=null)
			    				{
			    					if(os instanceof JDesktopPane)
			    					{
			    						System.out.println("OH YEAH!!");
			    						try {
			    							Text_Editor h=new Text_Editor(k.path);
			    							h.frame.setVisible(true);
											((JDesktopPane) os).add(h.frame);
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
			    					}
			    				}
			    			}
			    		}
			    	}
			    }
			}
		});
		tree.setEditable(true);
		//tree.setCellEditor(cellEditor);
		//tree.setRootVisible(false);
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		tree.setCellRenderer(new MyTreeCellRenderer());
		scrollPane.setViewportView(tree);
		
		JButton btnNewButton = new JButton("Add File");
		btnNewButton.setAction(action);
		btnNewButton.setBounds(0, 238, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setToolTipText("Remove selected Item");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(arg0.getActionCommand().equals("Remove"))
				{
					DefaultTreeModel model=(DefaultTreeModel)tree.getModel();
					DefaultMutableTreeNode n = null;
					if(tree.getSelectionPath()!=null)
					{
						n=(DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
					}
					model.removeNodeFromParent(n);
					model.reload();
					item i=(item)n.getUserObject();
					File f=new File(i.path);
					if(f.isFile())
					{
						f.delete();
					}
					else
					{
						deleteDir(f);
					}
				}
			}
		});
		btnRemove.setBounds(248, 238, 89, 23);
		frame.getContentPane().add(btnRemove);
		
		
		JButton btnAddFolder = new JButton("Add Folder");
		btnAddFolder.setAction(action_1);
		btnAddFolder.setBounds(88, 238, 120, 23);
		frame.getContentPane().add(btnAddFolder);
	}
	private class SwingAction extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		public SwingAction() {
			putValue(NAME, "Add File");
			putValue(SHORT_DESCRIPTION, "Add file to selected Folder");
		}
		public void actionPerformed(ActionEvent e) {
			DefaultTreeModel model=(DefaultTreeModel)tree.getModel();
			DefaultMutableTreeNode r;
			if(tree.getSelectionPath()==null)
			{
				r=(DefaultMutableTreeNode)model.getRoot();
			}
			else
			{
				r=(DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
			}
			item i=(item)r.getUserObject();
			if(i.id)
			{
				model.insertNodeInto(new DefaultMutableTreeNode(new item(false, "New File_"+x+".txt",i.path+"\\New File_"+x+".txt")), r, r.getChildCount());
				try {
					File f=new File(i.path+"\\New File_"+x+".txt");
					f.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				x+=1;
			}
		}
	}
	private class SwingAction_1 extends AbstractAction {
		/**
		 * 
		 */
		private static final long serialVersionUID = 2L;
		public SwingAction_1() {
			putValue(NAME, "Add Folder");
			putValue(SHORT_DESCRIPTION, "Add folder to selected Folder");
		}
		public void actionPerformed(ActionEvent e) {
			DefaultTreeModel model=(DefaultTreeModel)tree.getModel();
			DefaultMutableTreeNode r;
			if(tree.getSelectionPath()==null)
			{
				r=(DefaultMutableTreeNode)model.getRoot();
			}
			else
			{
				r=(DefaultMutableTreeNode)tree.getSelectionPath().getLastPathComponent();
			}
			item i=(item)r.getUserObject();
			if(i.id)
			{
				model.insertNodeInto(new DefaultMutableTreeNode(new item(true, "New Folder_"+x,i.path+"\\New Folder_"+x)), r, r.getChildCount());
				File f=new File(i.path+"\\New Folder_"+x);
				f.mkdir();
				x+=1;
			}
		}
	}
}
