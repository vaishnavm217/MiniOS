package osmini;

import java.util.ArrayDeque;
import java.util.Queue;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class scheduling extends Thread {
	JDesktopPane h;
	JInternalFrame k;
	int mpro=4;
	static final Queue <JInternalFrame> q=new ArrayDeque<JInternalFrame>();
	scheduling(JDesktopPane d)
	{
		h=d;
	}
	public void run()
	{
		while(true)
		{
			try
			{
				JInternalFrame[] eh=h.getAllFrames();
				if(eh.length<mpro)
				{
					k=q.poll();
					if(k!=null)
					{
						h.add(k);
					}
				}
			}
			catch(ArrayIndexOutOfBoundsException e)
			{
				
			}
		}
	}
}
