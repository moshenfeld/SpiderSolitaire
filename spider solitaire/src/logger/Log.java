package logger;

import logic.Move;
import search.Node;

public class Log {
	
	public static final boolean PRINT_MSG = true;
	public static final boolean PRINT_STRUCTURE = false;
	public static final boolean PRINT_ERR_MSG = true;
	
	public static void log(String msg)
	{
		if(PRINT_MSG)
		{
			System.out.println("Log: "+msg);
		}
	}
	
	public static void err(String msg)
	{
		if(PRINT_ERR_MSG)
		{
			System.err.println("Log: "+msg);
		}
	}

	public static void log(Node node) {
		if(PRINT_STRUCTURE)
		{
			System.out.println(node);
		}
	}

	public static void log(Move move) {
		if(PRINT_STRUCTURE)
		{
			System.out.println(move);
		}
		
	}

}
