package Parser;

public class ExpressionTree extends ExpressionPart {
	public ExpressionPart leftPart;
	public SymbolTree symbolPart;
	public ExpressionPart rightPart;
	
	public ExpressionTree(PartTree root, ExpressionPart leftPart, SymbolTree symbolPart, ExpressionPart rightPart) {
		super(root);
		this.leftPart = leftPart;
		this.symbolPart = symbolPart;
		this.rightPart = rightPart;
	}

	public ExpressionPart getLeftPart() {
		return leftPart;
	}

	public SymbolTree getSymbolPart() {
		return symbolPart;
	}

	public ExpressionPart getRightPart() {
		return rightPart;
	}
	
}
