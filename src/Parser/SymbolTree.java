package Parser;

public class SymbolTree extends ExpressionPart {

	public String symbol;
	
	public SymbolTree(PartTree root, String symbol) {
		super(root);
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}
	
}
