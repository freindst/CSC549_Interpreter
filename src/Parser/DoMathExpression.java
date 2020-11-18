package Parser;

public class DoMathExpression extends Expression 
{

	public static String identifier = "do-math";
	
	public static String operatorSymbol = "+-*/%";
	private Expression left;
	private Expression right;
	private String op;
	
	public DoMathExpression(Expression left, String op, Expression right)
	{
		super("Do-Math Expression");
		this.left = left;
		this.right = right;
		this.op = op;
	}
	
	public String toString()
	{
		return super.toString() + "\n\t" + this.left.toString() + " "
				+ this.op + " " + this.right.toString();
	}

	public Expression getLeft() {
		return left;
	}

	public Expression getRight() {
		return right;
	}

	public String getOp() {
		return op;
	}
	
	public static int math(int a, int b, String op)
	{
		int index = operatorSymbol.indexOf(op);
		switch(index)
		{
		case 0:
			return a + b;
		case 1:
			return a - b;
		case 2:
			return a * b;
		case 3:
			if (b == 0)
			{
				throw new ArithmeticException();
			}
			return a / b;
		case 4:
			if (b == 0)
			{
				throw new ArithmeticException();
			}
			return a % b;
		default:
			throw new RuntimeException("Found An Unknown Operator");
		}
	}
}
