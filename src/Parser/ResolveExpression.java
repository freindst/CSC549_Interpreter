package Parser;

public class ResolveExpression extends Expression {
	public String name;
	
	public ResolveExpression(String name) 
	{
		super("Resolve Expression");
		this.name = name;
	}
	
	public String toString()
	{
		return super.toString() + "\n\t" + this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
