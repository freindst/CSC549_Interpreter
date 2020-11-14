package Parser;

public class DoMathExpression extends Expression 
{

	public String name;
	
	public Expression a1;
	
	public Expression a2;
	
	public String op;
	
	public static String identifier = "do-math";
	
	public static String operatorSymbol = "+-*/%";
	
	public DoMathExpression(String name)
	{
		super("Do-Math Expression");
		this.name = name;
		String[] parts = name.split("\\s+");
		a1 = new ResolveExpression(parts[1]);
		op = parts[2];
		a2 = new ResolveExpression(parts[3]);
	}
	
	public String toString()
	{
		return super.toString() + "\r\t" + this.name;
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public void setName(String name)
	{
		this.name = name;
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
