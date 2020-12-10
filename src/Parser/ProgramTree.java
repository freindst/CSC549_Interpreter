package Parser;

import java.util.ArrayList;

public class ProgramTree {
	private ArrayList<StatementTree> statementParts;
	
	public ProgramTree()
	{
		this.statementParts = new ArrayList<StatementTree>();
	}
	
	public ArrayList<StatementTree> getStatementParts()
	{
		return this.statementParts;
	}
	
	public String toString()
	{
		String result = "Program Tree";
		for(StatementTree st : this.statementParts)
		{
			result = result + "\r\n\t" + st.toString();
		}
		return result;
	}
}
