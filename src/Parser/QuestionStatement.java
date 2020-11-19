package Parser;

public class QuestionStatement extends Statement {
	
	public static String qIdentifier = "question";
	
	public static String firstIndentifier = "do";
	
	public static String secondIndentifier = "otherwise";
	
	public TestExpression testExpression;
	
	public Statement trueStatement;
	
	public Statement falseStatement;
	
	public QuestionStatement(TestExpression testExpression,
			Statement trueStatement,
			Statement falseStatement)
	{
		super("Question Statement");
		this.testExpression = testExpression;
		this.trueStatement = trueStatement;
		this.falseStatement = falseStatement;
	}

	public TestExpression getTestExpression() {
		return testExpression;
	}

	public Statement getTrueStatement() {
		return trueStatement;
	}

	public Statement getFalseStatement() {
		return falseStatement;
	}
	
	public String toString()
	{
		return super.toString() + "\n\t" +
	"if " + this.testExpression.toString()+ "\n\t\t" +
		"then " + this.trueStatement.toString() + "\n\t\t" +
		"else " + this.falseStatement.toString();
	}
}
