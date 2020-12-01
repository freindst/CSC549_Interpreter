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
	
	static RememberStatement parseRemember(String s)
	{
		String[] parts = s.split("\\s+");
		int posOfEqualSign = s.indexOf('=');
		String str = s.substring(posOfEqualSign+1).trim();
		Expression re = Parser.parseExpression(str);
		RememberStatement rs = new RememberStatement(parts[1], parts[2], re);
		return rs;
	}
	
	static QuestionStatement parseQuestion(String testExpression, String trueStatement, String falseStatement)
	{
		Statement a1 = null;
		if (trueStatement.startsWith(RememberStatement.identifier))
		{
			a1 = parseRemember(trueStatement);
		}
		Statement a2 = null;
		if (falseStatement.startsWith(RememberStatement.identifier))
		{
			a2 = parseRemember(falseStatement);
		}
		return new QuestionStatement(Parser.parseTest(testExpression), a1, a2);
	}
	
	static UpdateStatement parseUpdate(String s) 
	{
		int equalPos = s.indexOf('=');
		String name = s.substring(UpdateStatement.identifier.length(), equalPos).trim();
		String value = s.substring(equalPos + 1).trim();
		Expression valueExpression = Parser.parseExpression(value);
		return new UpdateStatement(name, valueExpression);
	}
	
	static WhileStatement parseWhile(String s)
	{
		int posOfDo = s.indexOf(WhileStatement.secKeyword);
		String testExpression = s.substring(WhileStatement.identifier.length(), posOfDo).trim();		
		String statementStr = s.substring(posOfDo + WhileStatement.secKeyword.length()).trim();
		return new WhileStatement(Parser.parseTest(testExpression), Parser.parseStatement(statementStr));
	}
	
	static PrintStatement parsePrint(String s)
	{
		return new PrintStatement(Parser.parseExpression(s.trim()));
	}
	
	static BlockStatement parseBlock(String s)
	{
		String[] blocks = s.split(BlockStatement.separator);
		ArrayList<Statement> statements = new ArrayList<Statement>();
		for(String str: blocks)
		{
			statements.add(Parser.parseStatement(str.trim()));
		}
		return new BlockStatement(statements);
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
				if (theParts[i].equals(DoMathExpression.identifier))
				{
					indicatorCount++;
				}
				else if (DoMathExpression.operatorSymbol.indexOf(theParts[i]) >= 0)
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
		Expression left = Parser.parseExpression(leftStr.trim());		
		Expression right = Parser.parseExpression(rightStr.trim());		
		//create and return an instance of DoMathExpression
		DoMathExpression theResult = new DoMathExpression(left, math_op, right);
		return theResult;
	}
	
	static TestExpression parseTest(String expression)
	{
		String[] parts = expression.split("\\s+");
		String[] operators = TestExpression.booleanKeywords.split(" ");		
		String leftStr = "";
		String rightStr = "";
		String op = "";
		boolean doneLeft = false;
		for(int i = 1; i < parts.length; i++)
		{
			if (doneLeft)
			{
				rightStr += " " + parts[i];
			}
			else
			{
				boolean match = false;
				for(int j = 0; j < operators.length; j++)
				{
					if (operators[j].matches(parts[i]))
					{
						match = true;
					}
				}
				if (match)
				{
					op = parts[i];
					doneLeft = true;
				}
				else
				{
					leftStr += " " + parts[i];
				}
			}
		}
		Expression left = Parser.parseExpression(leftStr.trim());		
		Expression right = Parser.parseExpression(rightStr.trim());		
		return new TestExpression(left, op, right);
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
				theListOfStatements.add(parseStatement(theProgramLines[i]));
			}
		}
		catch(Exception e)
		{
			System.err.println(e.getStackTrace());
			System.err.println("File Not Found!");
		}
	}
	
	static Statement parseStatement(String s)
	{
		String[] theParts = s.split("\\s+");
		if(theParts[0].equals(RememberStatement.identifier))	// "remember int a = 5"
		{
			return Parser.parseRemember(s);
		}
		else if (theParts[0].equals(QuestionStatement.qIdentifier))
		{
			String testExpression = "";
			String trueStatement = "";
			String falseStatement = "";
			int partProcessing = 1; 
			for(int i = 1; i < theParts.length; i++)
			{
				if (theParts[i].equals(QuestionStatement.firstIndentifier))
				{
					partProcessing = 2;
				}
				else if (theParts[i].equals(QuestionStatement.secondIndentifier))
				{
					partProcessing = 3;
				}
				else
				{
					switch (partProcessing)
					{
						case 1:
							testExpression += " " + theParts[i];
							break;
						case 2:
							trueStatement += " " + theParts[i];
							break;
						case 3:
							falseStatement += " " + theParts[i];
							break;
					}
				}				
			}
			return Parser.parseQuestion(testExpression.trim(), trueStatement.trim(), falseStatement.trim());
		}
		else if (theParts[0].equals(UpdateStatement.identifier))
		{
			return Parser.parseUpdate(s);
		}
		else if (theParts[0].equals(WhileStatement.identifier))
		{
			return Parser.parseWhile(s);
		}
		else if (theParts[0].equals(PrintStatement.identifer))
		{
			return Parser.parsePrint(s.substring(PrintStatement.identifer.length()));
		}
		else if (theParts[0].equals(BlockStatement.startIdentifier))
		{
			int endIndex = s.indexOf(BlockStatement.endIdentifier);
			endIndex = endIndex > -1 ? endIndex : s.length();
			return Parser.parseBlock(s.substring(BlockStatement.startIdentifier.length(), endIndex).trim());
		}
		else
		{
			return null;
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
		else if (theParts[0].equals(TestExpression.identifier))
		{
			return Parser.parseTest(e);
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
