import java.util.ArrayList;
import java.util.Arrays;

public class Parser 
{
	String[] args;
	String cmd="";
	boolean Parsed=false;
	int index=-1;	
	String operator="";
	
	public Parser()
	{
		args= new String[2];
		args[0]="";
		args[1]="";
	}
	public boolean parse(String input)
	{
		ArrayList<String> arr1=new ArrayList<String>();
		ArrayList<String> arr2=new ArrayList<String>(Arrays.asList("touch","cd","pwd","ls","date",
			"help","args","clear","cat","mv","rm","mkdir","rmdir","cp","more","exit"));
		String x="";
		for(int i=0;i<input.length();i++) //splits the input form user and store it in arr1
		{
			if(input.charAt(i)!=' ' && i<input.length()-1)
				x+=input.charAt(i);
			else if(i==input.length()-1)
			{
				x+=input.charAt(i);
				arr1.add(x);
			}
			else
			{
				arr1.add(x);
				x="";
			}
		}
		for(int i=0;i<arr1.size();i++) //check for the command
		{
			if(i==0 && arr2.contains(arr1.get(i))) //is the command available
			{
				cmd=arr1.get(i);
				Parsed=true;
			}
			else if(i==0)
			{
				return false;
			}
			else if(arr1.get(i).equals(">>") || arr1.get(i).equals(">") || arr1.get(i).equals("<"))
			{
				operator=arr1.get(i);
			}
			else if(index<args.length-1) //store the argument
			{
				args[++index]=arr1.get(i);
			}
		}
		return Parsed;
	}	
	public String getCmd() 
	{
		return cmd;
	}
	public String[] getArguments() 
	{
		return args;
	}
}