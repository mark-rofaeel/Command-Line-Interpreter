import java.nio.file.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class Terminal 
{
	Scanner scan= new Scanner(System.in);
	public String dir;
	File file;
	public Terminal()
	{
		dir = System.getProperty("user.dir");
		file=new File(dir);
	}
	public void ShortPath(String x)
	{
		file = new File(x);
		dir=""+file.getAbsoluteFile();
	}
	public void touch(String sourcePath) throws IOException
	{
		ShortPath(sourcePath);
		file.createNewFile();
		file=new File(dir);
		cd();
	}
	public void cp(String sourcePath, String destinationPath) throws IOException 
	{
		ShortPath(sourcePath);
	    //Creating a destination file object
	    File dest = new File(destinationPath+"\\"+sourcePath);
	    dir=""+dest.getAbsoluteFile(); 
	    if(file.exists())
        {
	    Files.copy(file.toPath(), dest.toPath());
        System.out.println("File copied successfully..");
        }
        else 
        {
        	System.out.println("File doesn't exist");
        }
	    cd();
	}
	public void mv(String sourcePath, String destinationPath) throws IOException //cut
	{
		ShortPath(sourcePath); 
	    //Creating a destination file object
	    File dest = new File(destinationPath+"\\"+sourcePath);
	    boolean bool = file.renameTo(dest); 
	    //The renameTo() method is a part of File class. 
	    //The renameTo() function is used to rename the abstract path name of a File to a given path name.
	    //The function returns true if the file is renamed else returns false
	    if(bool) 
	    {
	    	System.out.println("File moved successfully..");
	    }
	    else 
	    {
	         System.out.println("Unable to move the file..");
	    }  
	}
	public void rm(String sourcePath) 
	{
		ShortPath(sourcePath);
        if(file.delete())
        {
        System.out.println("File deleted");
        }
        else 
        {
        	System.out.println("File doesn't exist");
        }
        cd();
	}
	public void cat1(String x) throws IOException //file creation and writing ">"
	{
		Parser parser=new Parser(); 
		File file = new File(x);
		{
			file.createNewFile();
			PrintWriter out=new PrintWriter(file);
			String Input= scan.nextLine();
			for(int i=0;i<Input.length();i++) 
			{
				if(Input.charAt(i)=='\n') 
				{
					out.println();
				}
				else 
				{
					out.print(Input.charAt(i));
				}
			}
			out.close();
		}
	}
		public void cat2(String y) throws IOException // file appending ">>"
		{
			Parser parser=new Parser(); 
			File file = new File(y);
			if(file.exists())
			{
				String Input= scan.nextLine();
				BufferedWriter out=new BufferedWriter(new FileWriter(file,true));
				out.write(Input);
				out.close();
			}
			else 
				System.out.println("File doesn't exist");
		}
		public void cat3(String z) throws IOException //cat file or cat < file
		{ 
			Parser parser=new Parser(); 
			File file = new File(z);
		if(file.exists())
		{
			FileReader fileReader = new FileReader(z);
	        BufferedReader in = new BufferedReader(fileReader);
	        String line;
	        while((line = in.readLine())!= null)
	        {
	        	System.out.println(line);
	        }
	        	in.close();
		}
		else 
			System.out.println("File doesn't exist");
		}
	/*public void cat(String x) throws IOException
	{
		Parser2 parser=new Parser2(); 
		File file = new File(x);
		if(parser.operator.indexOf('>')==parser.operator.lastIndexOf('>')) //file creation and writing
		{
			file.createNewFile();
			PrintWriter out=new PrintWriter(file);
			String Input= scan.nextLine();
			for(int i=0;i<Input.length();i++) 
			{
				if(Input.charAt(i)=='\n') 
				{
					out.println();
				}
				else 
				{
					out.print(Input.charAt(i));
				}
			}
			out.close();
		}
		else if(parser.operator==">>") // file appending
		{
			if(file.exists())
			{
				String Input= scan.nextLine();
				BufferedWriter out=new BufferedWriter(new FileWriter(file,true));
				out.write(Input);
				out.close();
			}
			else 
				System.out.println("File doesn't exist");
		}
		else
		{ ///cat file cat < file
		if(file.exists())
		{
			FileReader fileReader = new FileReader(x);
	        BufferedReader in = new BufferedReader(fileReader);
	        String line;
	        while((line = in.readLine())!= null)
	        {
	        	System.out.println(line);
	        }
	        	in.close();
		}
		else 
			System.out.println("File doesn't exist");
	}
	}*/
	public void cd()
	{
		dir = System.getProperty("user.dir");
		file=new File(dir);
	}
	public void cd(String sourcePath)
	{
		File temp = new File(sourcePath);
		if(temp.exists() && temp.isDirectory())
		{
			dir=""+temp.getAbsoluteFile();
			System.out.println(temp.getAbsolutePath());
		}
		else if(temp.exists()) 
		{

			System.out.println("Failed to change because its file not directory");
		}
		else
			System.out.println("Failed to change Directory");
	}
	public void ls()
	{
		File dir = new File(System.getProperty("user.dir"));
        String arr[] = dir.list();
        for(int i = 0;i < arr.length;i++)
        {
        	System.out.println(arr[i]);
        }
	}
	public void ls(String sourcePath) throws IOException
	{
		File source = new File(sourcePath) ;
		if(!source.exists())
		{
			source.createNewFile();
		}
    	PrintWriter out=new PrintWriter(source);
    	String arr[] = file.list() ;
        for(int i = 0;i < arr.length;i++)
        {
			for(int j=0;j<arr[i].length();j++) 
			{
					out.print(((CharSequence) arr[i]).charAt(j));
			}
			out.println();
        }	
		out.close();
	}
	public void pwd() 
	{
		System.out.println(dir);
	}
	public void mkdir(String sourcePath)
	{
		ShortPath(sourcePath);
        if (file.mkdir()) 
        { 
            System.out.println("Directory is created"); 
        } 
        else 
        { 
            System.out.println("Directory cannot be created");
        }
        cd();
	}
	public void rmdir(String sourcePath)
	{
		ShortPath(sourcePath);
		if(file.isDirectory() == false) 
		{
			System.out.println("There is no such directory");
			return;
		}
		File[] listFiles = file.listFiles();
		if(listFiles.length==0)
			System.out.println("Deleted");
		else 
		{
			System.out.println("Cannot delete Directory as it contains files");
		}
		cd();
	}
	public void date()
	{
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");  
		Date date = new Date();
		System.out.println(formatter.format(date));  
	}
	public void more(String sourcePath) 
	{
		File f = new File(sourcePath);
		dir=""+file.getAbsoluteFile();
		if (f.exists()) {
			try {
				FileInputStream a = new FileInputStream(f);
				BufferedReader br = new BufferedReader(new InputStreamReader(a));
				String l;
				int c = 0;
				int x;
				Scanner in = new Scanner(System.in);
				while ((l = br.readLine()) != null) 
				{
					System.out.println(l);
					c++;
					if (c % 10 == 0) {
						System.out.print(".................................MORE press 1 quit press 2 ");
						x = in.nextInt();
						if (x == 2)
							break;
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}
	public void help()
	{
		System.out.println("cd: Changes current working directory");
		System.out.println("ls: List all contents of current directory");
		System.out.println("cp: Copies files");
		System.out.println("cat: Displays contents of a file and concatenates files and display output");
		System.out.println("more: Let us display and scroll down the output in one direction only");
		System.out.println("Pipe Operator");
		System.out.println("Redirect Operator <");
		System.out.println("Redirect Operator <<");
		System.out.println("mkdir: Creates a new directory");
		System.out.println("rmdir: Deletes a directory");
		System.out.println("mv: Moves files");
		System.out.println("rm: removes files");
		System.out.println("args: List all commands arguments");
		System.out.println("date: Current date/time");
		System.out.println("help: List all commands and functionalities");
		System.out.println("pwd: Displays the absolute path of current directory");
		System.out.println("clear: Clears the console");
		System.out.println("exit: Stop all");
	}
	public void Args(String args)
	{
		if(args.equals("touch"))
		{			
			System.out.println("args touch: arg: sourcePath");
		}
		else if(args.equals("cp"))
		{			
			System.out.println("args cp: arg1: sourcePath, arg2: destinationPath");
		}
		else if(args.equals("mv"))
		{			
			System.out.println("args mv: arg1: sourcePath, arg2: destinationPath");
		}
		else if(args.equals("rm"))
		{
			System.out.println("args rm: arg: sourcePath");	
		}
		else if(args.equals("cat"))
		{		
			System.out.println("args cat: arg: ");
		}
		else if(args.equals("cd"))
		{		
			System.out.println("args cd: no args");
			System.out.println("args cd: arg: sourcePath");
		}
		else if(args.equals("ls"))
		{		
			System.out.println("args ls: no args");
			System.out.println("args ls: arg: sourcePath");
		}
		else if(args.equals("pwd"))
		{			
			System.out.println("args pwd: no args");
		}
		else if(args.equals("mkdir"))
		{	
			System.out.println("args mkdir: arg: sourcePath");
		}
		else if(args.equals("rmdir"))
		{		
			System.out.println("args rkdir: arg: sourcePath");
		}
		else if(args.equals("date"))
		{			
			System.out.println("args date: no args");
		}
		else if(args.equals("more"))
		{			
			System.out.println("args more: arg: sourcePath");
		}
		else if(args.equals("help"))
		{			
			System.out.println("args help: no args");
		}
		else if(args.equals("args"))
		{			
			System.out.println("args args: arg: str");
		}
		else if(args.equals("clear"))
		{			
			System.out.println("args clear: no args");
		}
		else if(args.equals("exit"))
		{
			System.out.println("args exit: no args");
		}	
	}
	public void clear() 
	{  
		for(int i=0;i<50;i++) 
		{
		    System.out.println();
		} 
	} 
	public void exit(){System.exit(0);}
}