package logger;

public class Assert {
	
	public static void assertTrue(boolean condition)
	{
		assert condition; 
	}
	
	public static void assertTrue(boolean condition,String errMsg)
	{
		assert condition : errMsg; 
	}
	
	public static void assertFalse(boolean condition)
	{
		assert !condition; 
	}
	
	public static void assertFalse(boolean condition,String errMsg)
	{
		assert !condition : errMsg; 
	}
}
