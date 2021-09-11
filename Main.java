import java.io.IOException;
import java.util.Scanner;

public class Main 
{
	public static void main(String args[]) throws IOException
	{
		Scanner scan= new Scanner(System.in);
		Terminal terminal=new Terminal();
		while(true)
		{
			System.out.print(terminal.dir+" ");
			String Input= scan.nextLine();
			String Inputs[]=Input.split("\\|");
			for(int j=0;j<Inputs.length;j++)
			{
				Parser parser=new Parser();
				if(parser.parse(Inputs[j])) 
				{
					if(parser.cmd.equals("touch"))
					{			
						terminal.touch(parser.args[0]);
					}
					else if(parser.cmd.equals("cp")) 
					{			
						terminal.cp(parser.args[0], parser.args[1]);
					}
					else if(parser.cmd.equals("mv")) 
					{			
						terminal.mv(parser.args[0], parser.args[1]);
					}
					else if(parser.cmd.equals("rm"))
					{
						terminal.rm(parser.args[0]);	
					}
					else if(parser.cmd.equals("cat"))
					{		
						if(parser.operator.equals(">"))
								terminal.cat1(parser.args[0]);
						else if(parser.operator.equals(">>"))
							terminal.cat2(parser.args[0]);
						else
							terminal.cat3(parser.args[0]);
					}
					else if(parser.cmd.equals("cd"))
					{		
						if (parser.args[0]=="")
							terminal.cd();
						else
							terminal.cd(parser.args[0]);
					}
					else if(parser.cmd.equals("ls"))
					{		
						if (parser.args[0]=="") 
							terminal.ls();
						else
							terminal.ls(parser.args[0]);
					}
					else if(parser.cmd.equals("pwd"))
					{			
						terminal.pwd();
					}
					else if(parser.cmd.equals("mkdir"))
					{	
						terminal.mkdir(parser.args[0]);
					}
					else if(parser.cmd.equals("rmdir"))
					{		
						terminal.rmdir(parser.args[0]);
					}
					else if(parser.cmd.equals("date"))
					{			
						terminal.date();
					}
					else if(parser.cmd.equals("more"))
					{			
						terminal.more(parser.args[0]);
					}
					else if(parser.cmd.equals("help"))
					{			
						terminal.help();
					}
					else if(parser.cmd.equals("args"))
					{			
						terminal.Args(parser.args[0]);
					}
					else if(parser.cmd.equals("clear"))
					{			
						terminal.clear();
					}
					else if(parser.cmd.equals("exit"))
					{
						terminal.exit();
					}	
				}
				else 
				{
					System.out.println("Wrong Input");
				}
			}	
		}
	}
}
//Pipe operator
/*String before = "";
String after ="";
String Inputs[]=null;
boolean q=true;
for (int k=0;k<Input.length();k++)
{
	if(Input.charAt(k)=='|')
	{
		for(int i=0;i<Input.length();i++) 
		{
			if(Input.charAt(i)=='|' && i<Input.length()-1)
			{
				q=false;
				i+=1;
				continue;
			}
			else if(q==true)
			{
				before+=Input.charAt(i);
			}
			else
			{
				after+=Input.charAt(i);
			}
		}
	}
	else
	{
		 Inputs=Input.split("\\|");
	}
}
	System.out.println(before+"\n");
	System.out.println(after+"\n");*/