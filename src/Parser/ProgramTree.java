package Parser;

import java.util.ArrayList;

public class ProgramTree {
	ArrayList<StatementTree> statementParts;
	
	public ProgramTree(ArrayList<StatementTree> statementParts)
	{
		this.statementParts = statementParts;
	}
	
	public ArrayList<StatementTree> getStatementParts()
	{
		return this.statementParts;
	}
}
