package Parser;

public class PrintStatement extends Statement {
	private Expression valueExpression;
	
	public static String identifer = "print";
	
	public PrintStatement(Expression valueExpression)
	{
		super("Print Statement");
		this.valueExpression = valueExpression;
	}
	
	public String toString()
	{
		return super.toString() + "\n\t" + this.valueExpression.toString();
	}
	
	public Expression getValueExpression()
	{
		return this.valueExpression;
	}
}
