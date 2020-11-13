package Parser;

public abstract class Expression 
{
	protected String expressionType;
	
	public Expression(String expressionType)
	{
		this.expressionType = expressionType;
	}
	
	public String toString()
	{
		return "Statement: " + this.expressionType;
	}
	
	public String getExpressionType() {
		return this.expressionType;
	}
}
