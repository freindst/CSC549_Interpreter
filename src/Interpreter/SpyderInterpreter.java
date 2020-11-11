package Interpreter;

import java.util.ArrayList;

import Parser.*;

public class SpyderInterpreter 
{
	private static VariableEnvironment theEnv = new VariableEnvironment();
	private static ArrayList<String> theOutput = new ArrayList<String>();
	
	public static void displayResults()
	{
		System.out.println("Current Variable Environment");
		SpyderInterpreter.theEnv.display();
		for(String s: SpyderInterpreter.theOutput) 
		{
			System.out.println(s);
		}
	}
	
	public static void interpret(ArrayList<Statement> theStatements)
	{
		for(Statement s: theStatements)
		{
			if(s instanceof RememberStatement)
			{
				SpyderInterpreter.interpretRememberStatement((RememberStatement)s);
			} 
			else if(s instanceof ResolveStatement)
			{
				SpyderInterpreter.interpretResolveStatement((ResolveStatement)s);
			}
		}
	}
	
	private static void interpretRememberStatement(RememberStatement rs)
	{
		SpyderInterpreter.theEnv.addVariable(rs.getName(), rs.getIntValue());
		SpyderInterpreter.theOutput.add("<HIDDEN> Added " + rs.getName() + " = " + rs.getValue() + " to the variable environment.");
	}
	
	private static void interpretResolveStatement(ResolveStatement rs)
	{
		try
		{
			int result = SpyderInterpreter.theEnv.getValue(rs.getName());
			SpyderInterpreter.theOutput.add("<HIDDEN> Variable " + rs.getName() + " is resoved as " + result + ".");
		}
		catch(Exception e)
		{
			System.err.println("Variable " + rs.getName() + " NOT FOUND!");
		}
	}
}
