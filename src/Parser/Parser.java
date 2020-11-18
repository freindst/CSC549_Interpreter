package Parser;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser 
{
	private static ArrayList<Statement> theListOfStatements = new ArrayList<Statement>();
	
	public static ArrayList<Statement> getParsedStatements()
	{
		return theListOfStatements;
	}
	
	public static void display()
	{
		for(Statement s : theListOfStatements)
		{
			System.out.println(s);
		}
	}
	
	static RememberStatement parseRemember(String type, String name, String value)
	{
		Expression re = Parser.parseExpression(value);
		RememberStatement rs = new RememberStatement(type, name, re);
		return rs;
	}
	
	static LiteralExpression parseLiteral(String value)
	{
		//We ONLY have a single LiteralType - int literal
		return new Int_LiteralExpression(Integer.parseInt(value));
	}
	
	static ResolveExpression parseResolve(String name)
	{
		ResolveExpression rs = new ResolveExpression(name);
		return rs;
	}
	
	static DoMathExpression parseDoMath(String expression)
	{
		//do-math do-math a + 7 + 4 - doesn't work for this YET!
		//make the above work for HW
		String operator_sign = "+-*/%";
		String doMath_indicator = "do-math";
		String[] theParts = expression.split("\\s+");
		String leftStr = "";
		String rightStr = "";
		String math_op = "";
		int indicatorCount = 1;
		boolean doneLeft = false;		
		for (int i = 1; i < theParts.length; i++)
		{
			if (!doneLeft)
			{
				if (theParts[i].equals(doMath_indicator))
				{
					indicatorCount++;
				}
				else if (operator_sign.indexOf(theParts[i]) >= 0)
				{
					indicatorCount--;
				}
				if (indicatorCount > 0)
				{
					leftStr = leftStr + " " + theParts[i];
				}
				else
				{
					doneLeft = true;
					math_op = theParts[i];
				}
			}
			else
			{
				rightStr = rightStr + " " + theParts[i];
			}
		}
		
		//do-math a + 7 - will work for this
		// (resolve expression a) + (int_lit expression 7)
		//right now we are assuming only a single level of do-math
		
		Expression left = Parser.parseExpression(leftStr.trim());
		
		Expression right = Parser.parseExpression(rightStr.trim());
		
		//create and return an instance of DoMathExpression
		DoMathExpression theResult = new DoMathExpression(left, math_op, right);
		return theResult;
	}
	
	public static void parse(String filename)
	{
		try
		{
			Scanner input = new Scanner(new File(System.getProperty("user.dir") + 
					"/src/" + filename));
			String fileContents = "";
			while(input.hasNext())
			{
				fileContents += input.nextLine().trim();
			}
			
			//System.out.println(fileContents);
			String[] theProgramLines = fileContents.split(";");
			for(int i = 0; i < theProgramLines.length; i++)
			{
				parseStatement(theProgramLines[i]);
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getStackTrace());
			System.err.println("File Not Found!");
		}
	}
	
	static void parseStatement(String s)
	{
		System.out.println(s);
		String[] theParts = s.split("\\s+");
		if(theParts[0].equals("remember"))	// "remember int a = 5"
		{
			int posOfEqualSign = s.indexOf('=');
			String str = s.substring(posOfEqualSign+1).trim();
			theListOfStatements.add(Parser.parseRemember(theParts[1], 
					theParts[2], str));
		}
		
	}
	
	static Expression parseExpression(String e)
	{
		String[] theParts = e.split("\\s+");
		if(theParts[0].equals(DoMathExpression.identifier))
		{
			//must be a do-math expression
			return Parser.parseDoMath(e);
		}
		else if(Character.isDigit(theParts[0].charAt(0))) //does the value start with a number
		{
			//must a literal expression
			return Parser.parseLiteral(e);
		}
		else
		{
			//must be a var name
			return Parser.parseResolve(e);
		}
	}
}
