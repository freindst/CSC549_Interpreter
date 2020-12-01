package Parser;

public class WhileStatement extends Statement 
{
	public static String identifier = "while";
	
	public static String secKeyword = "do";
	
	public TestExpression testExpression;
	
	public Statement statement;
	
	public WhileStatement(TestExpression testExpression, Statement statement) {
		super("While Statement");
		this.testExpression = testExpression;
		this.statement = statement;
	}
	
	public String toString()
	{
		return super.toString() + "\n\t" +
	"until " + this.testExpression.toString() + "\n\t\t" +
				this.statementType.toString();
	}

	public TestExpression getTestExpression() {
		return testExpression;
	}

	public Statement getStatement() {
		return statement;
	}
}
