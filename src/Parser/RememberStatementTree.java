package Parser;

public class RememberStatementTree extends StatementTree {
	private SymbolTree variableType;
	private SymbolTree variableName;
	private LiteralExpressionTree variableValue;
	
	public RememberStatementTree(SymbolTree variableType, SymbolTree variableName, LiteralExpressionTree variableValue)
	{
		super("Remember Statement Tree");
		this.variableType = variableType;
		this.variableName = variableName;
		this.variableValue = variableValue;
	}

	public SymbolTree getVariableType() {
		return variableType;
	}

	public SymbolTree getVariableName() {
		return variableName;
	}

	public LiteralExpressionTree getVariableValue() {
		return variableValue;
	}
	
	public String toString()
	{
		String result = "\t";
		result += super.toString();
		result += "\r\n\t\t" + this.variableType.toString();
		result += "\r\n\t\t" + this.variableName.toString();
		result += "\r\n\t\t" + this.variableValue.toString();
		return result;
	}
}
