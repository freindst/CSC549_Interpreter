package Parser;

public class SymbolTree extends ExpressionPart {

	public String symbol;
	
	public SymbolTree(String symbol) {
		super("Symbol Tree");
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public String toString()
	{
		return super.toString() + ": " + this.symbol;
	}
}
