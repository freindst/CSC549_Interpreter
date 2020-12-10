package Parser;

public abstract class PartTree {
	
	private String nodeType;
	
	public PartTree(String nodeType)
	{
		this.nodeType = nodeType;
	}
	
	public String toString()
	{
		return this.nodeType;
	}
}
