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
			interpretStatement(s);
		}
	}
	
	public static void interpretStatement(Statement s)
	{
		if(s instanceof RememberStatement)
		{
			SpyderInterpreter.interpretRememberStatement((RememberStatement)s);
		}
		else if (s instanceof QuestionStatement)
		{
			SpyderInterpreter.interpretQuestionStatement((QuestionStatement)s);
		}
		else if (s instanceof UpdateStatement)
		{
			SpyderInterpreter.interpretUpdateStatement((UpdateStatement)s);
		}
		else if (s instanceof WhileStatement)
		{
			SpyderInterpreter.interpretWhileStatement((WhileStatement)s);
		}
		else if (s instanceof PrintStatement)
		{
			SpyderInterpreter.interpretPrintStatement((PrintStatement)s);
		}
		else if (s instanceof BlockStatement)
		{
			SpyderInterpreter.interpretBlockStatement((BlockStatement)s);
		}
	}
	
	private static int getExpressionValue(Expression e)
	{
		if(e instanceof ResolveExpression)
		{
			return SpyderInterpreter.interpretResolveExpression((ResolveExpression)e);
		}
		else if(e instanceof LiteralExpression)
		{
			return SpyderInterpreter.interpretLiteralExpression((LiteralExpression) e);
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
	
	private static void interpretQuestionStatement(QuestionStatement s)
	{
		TestExpression e = s.getTestExpression();
		boolean condition = SpyderInterpreter.interpretTestExpression(e);
		if (condition)
		{
			Statement resolve = s.getTrueStatement();
			if (resolve instanceof RememberStatement)
			{
				interpretRememberStatement((RememberStatement)resolve);
			}
		}
		else
		{
			Statement resolve = s.getFalseStatement();
			if (resolve instanceof RememberStatement)
			{
				interpretRememberStatement((RememberStatement)resolve);
			}
		}
	}
	
	private static void interpretUpdateStatement(UpdateStatement s)
	{
		Expression valueExpression = s.getValue();
		int value = SpyderInterpreter.getExpressionValue(valueExpression);
		SpyderInterpreter.theEnv.updateVariable(s.getName(), value);		
		SpyderInterpreter.theOutput.add("<HIDDEN> Updated " + s.getName() + " = " + s.getValue() + " in the variable environment.");
	}
	
	private static void interpretWhileStatement(WhileStatement s)
	{
		SpyderInterpreter.theOutput.add("<HIDDEN> Execute while loop statement");
		TestExpression e = s.getTestExpression();
		while(SpyderInterpreter.interpretTestExpression(e))
		{
			SpyderInterpreter.interpretStatement(s.getStatement());
		}
	}
	
	private static void interpretPrintStatement(PrintStatement s)
	{
		Expression valueExpression = s.getValueExpression();
		int value = SpyderInterpreter.getExpressionValue(valueExpression);
		SpyderInterpreter.theOutput.add("<HIDDEN> Print " + valueExpression.toString());
		SpyderInterpreter.theOutput.add("" + value);
	}
	
	private static void interpretBlockStatement(BlockStatement s)
	{
		ArrayList<Statement> statements = s.getStatements();
		SpyderInterpreter.interpret(statements);
	}
	
	private static int interpretDoMathExpression(DoMathExpression dme)
	{
		Expression left = dme.getLeft();
		int leftValue = SpyderInterpreter.getExpressionValue(left);
		Expression right = dme.getRight();
		int rightValue = SpyderInterpreter.getExpressionValue(right);
		String math_op = dme.getOp();
		return DoMathExpression.math(leftValue, rightValue, math_op);
	}
	
	private static boolean interpretTestExpression(TestExpression e)
	{
		Expression left = e.getLeft();
		int leftValue = SpyderInterpreter.getExpressionValue(left);
		Expression right = e.getRight();
		int rightValue = SpyderInterpreter.getExpressionValue(right);
		String operator = e.getOperator();
		return TestExpression.test(leftValue, operator, rightValue);
	}
	
	private static int interpretResolveExpression(ResolveExpression rs)
	{
		return SpyderInterpreter.interpretResolveValue(rs.getName());
	}
	
	private static int interpretLiteralExpression(LiteralExpression le)
	{
		if(le instanceof Int_LiteralExpression)
		{
			return ((Int_LiteralExpression) le).getValue();
		}
		throw new RuntimeException("Not a valid literal type...");
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
