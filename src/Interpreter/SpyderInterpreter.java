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
		}
	}
	
	private static int getExpressionValue(Expression e)
	{
		if(e instanceof ResolveExpression)
		{
			return SpyderInterpreter.interpretResolveExpressiont((ResolveExpression)e);
		}
		else if (e instanceof DoMathExpression)
		{
			return SpyderInterpreter.interpretDoMathExpression((DoMathExpression)e);
		}
		throw new RuntimeException("Not a known expression type: " + e.toString());
	}
	
	private static void interpretRememberStatement(RememberStatement rs)
	{
		Expression valueExpression = rs.getValueExpression();
		int value = SpyderInterpreter.getExpressionValue(valueExpression);
		SpyderInterpreter.theEnv.addVariable(rs.getName(), value);
		SpyderInterpreter.theOutput.add("<HIDDEN> Added " + rs.getName() + " = " + rs.getValue() + " to the variable environment.");
	}
	
	private static int interpretDoMathExpression(DoMathExpression me)
	{
		int p1 = SpyderInterpreter.interpretResolveExpressiont((ResolveExpression)me.a1);
		int p2 = SpyderInterpreter.interpretResolveExpressiont((ResolveExpression)me.a2);
		return DoMathExpression.math(p1, p2, me.op);
	}
	
	private static int interpretResolveExpressiont(ResolveExpression rs)
	{
		return SpyderInterpreter.interpretResolveValue(rs.getName());
	}
	
	private static int interpretResolveValue(String name)
	{
		if (tryParseInt(name))
		{
			return Integer.parseInt(name);
		}
		else {
			try
			{
				return SpyderInterpreter.theEnv.getValue(name);
			}
			catch(Exception e)
			{
				throw new RuntimeException("Variable " + name + " NOT FOUND!");
			}
		}
	}
	

	public static Boolean tryParseInt(String input)
	{
		if (input == null || input.trim().length() == 0 )
		{
			return false;
		}
		try
		{
			Integer.parseInt(input);
			return true;
		} 
		catch(NumberFormatException e)
		{
			return false;
		}
	}
}
