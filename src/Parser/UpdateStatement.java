package Parser;

public class UpdateStatement extends Statement {
	
	public static String identifier = "update";
	
	private String name;
	
	private Expression value;
	
	public UpdateStatement(String name, Expression value)
	{
		super("Update Statement");
		this.name = name;
		this.value = value;
	}
	
	public String toString()
	{
		return super.toString() + "\n\t" + 
	" " + this.name + " = " + this.value;
	}

	public String getName() {
		return name;
	}

	public Expression getValue() {
		return value;
	}
	
	
}
