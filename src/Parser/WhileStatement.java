package Parser;

import java.util.ArrayList;

public class WhileStatement extends Statement 
{
	public static String identifier = "while";
	
	public static String secKeyword = "do";
	
	public TestExpression testExpression;
	
	public ArrayList<Statement> loopStatement;
	
	public WhileStatement(TestExpression testExpression,
			ArrayList<Statement> loopStatement) {
		super("While Statement");
		this.testExpression = testExpression;
		this.loopStatement = loopStatement;
	}
	
	public String toString()
	{
		String statements = "";
		for(Statement s: this.loopStatement)
		{
			statements += s.toString() + "\n\t\t";
		}
		return super.toString() + "\n\t" +
	"until " + this.testExpression.toString() + "\n\t\t" +
				statements;
	}

	public TestExpression getTestExpression() {
		return testExpression;
	}

	public ArrayList<Statement> getLoopStatement() {
		return loopStatement;
	}
}
