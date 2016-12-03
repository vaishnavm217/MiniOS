package osmini;
import java.io.*;
import java.util.concurrent.ThreadLocalRandom;

public class fcfs {
	public static String [] disk;
	public static int dp;
	public class fill extends Thread{
		public void run()
		{
			while(true)
			{
				//System.out.println("Started");
				//for(int i=0;i<)
				FCFS(System.getProperty("user.dir"));
				
			}
		}
	}
	public fcfs()
	{
		dp=0;
		disk=new String[1000];
		for(int i=0;i<1000;i++)
			disk[i]="-1";
		FCFS(System.getProperty("user.dir"));
		fill r=new fill();
		r.start();
	}
	public String ff(String h)
	{
		int prev=dp,flag=1;
		for(dp=prev;dp<1000;dp++)
		{
			if(disk[dp].equals(h))
				return h;
			else if (!disk[dp].equals("-1"))
			{
				System.out.println(disk[dp]);
			}
			if(dp==999)
				dp=0;
			if(dp==prev && flag==0)
			{
				break;
			}
			flag=1;
		}
		return null;
	}
	public static void FCFS(String directory)
	{
		File dir=new File(directory);
		File[] files=dir.listFiles();
		if (files.length == 0) {
			return;
		    //System.out.println("The directory is empty");
		} 
		else 
		{
		    for (File aFile : files) {
		    	if(aFile.isDirectory())
		    	{
		    		//System.out.println();
		    		FCFS(aFile.getAbsolutePath());
		    	}
		    	else		    	
		        {
		    		boolean flag=true;
		    		//System.out.println(aFile.getName() + " - " + aFile.length());
		    		for(int i=0;i<1000;i++)
		    		{
		    			try{
		    			if(disk[i].equals(aFile.getAbsolutePath()))
		    			{
		    				flag=false;
		    				break;
		    			}
		    			}catch(NullPointerException e){
		    				continue;
		    			}
		    		}
		    		if(flag)
		    		{
			    		int u=ThreadLocalRandom.current().nextInt(0, 1000);
			    		while(!disk[u].equals("-1"))
						{
							u=ThreadLocalRandom.current().nextInt(0, 1000);
						}
						disk[u]=aFile.getAbsolutePath();
					}	    		
		        }
		    }
		}
	}
	
}
