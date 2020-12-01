package Parser;
import java.util.ArrayList;

public class BlockStatement extends Statement {
	private ArrayList<Statement> statements;
	
	public static String startIdentifier = "begin";
	
	public static String endIdentifier = "end";
	
	public static String separator = ",";
	
	public BlockStatement(ArrayList<Statement> statements)
	{
		super("Block Statement");
		this.statements = statements;
	}
	
	public String toString()
	{
		String str = "";
		for(Statement s: this.statements)
		{
			str += s.toString() + "\n\t";
		}
		return super.toString() + "\n\t" + str;
	}

	public ArrayList<Statement> getStatements() {
		return statements;
	}
}
