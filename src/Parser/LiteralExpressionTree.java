package Parser;

public class LiteralExpressionTree extends ExpressionTree {
	private String value;
	
	public LiteralExpressionTree(String value)
	{
		super("Literal Expression Tree");
		this.value = value;
	}
	
	public String getValue()
	{
		return this.value;
	}
	
	public String toString()
	{
		return super.toString() + ": " + this.value;
	}
}
