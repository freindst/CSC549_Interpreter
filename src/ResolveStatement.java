
public class ResolveStatement extends Statement {
	public String name;
	
	public ResolveStatement(String name) 
	{
		super("Resolve Statement");
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
