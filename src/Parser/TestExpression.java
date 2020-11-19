package Parser;

public class TestExpression extends Expression {
	public static String identifier = "test";
	
	public static String booleanKeywords = "> < >= <= == !=";
	
	public Expression left;
	
	public String operator;
	
	public Expression right;
	
	public TestExpression(Expression left, String operator, Expression right)
	{
		super("Test Expression");
		this.left = left;
		this.operator = operator;
		this.right = right;		
	}
	
	public static boolean test(int leftValue, String operator, int rightValue)
	{
		switch (operator)
		{
		case ">":
			return leftValue > rightValue;
		case "<":
			return leftValue < rightValue;
		case ">=":
			return leftValue >= rightValue;
		case "<=":
			return leftValue <= rightValue;
		case "!=":
			return leftValue != rightValue;
		case "==":
			return leftValue == rightValue;
		default:
			throw new RuntimeException("Operator is not supported by Test Expression.");
		}		
	}

	public Expression getLeft() {
		return left;
	}

	public String getOperator() {
		return operator;
	}

	public Expression getRight() {
		return right;
	}
	
	public String toString()
	{
		return super.toString() + "\n\t" + this.left.toString() + " "
				+ this.operator + " " + this.right.toString();
	}
}
