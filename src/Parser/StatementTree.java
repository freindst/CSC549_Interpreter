package Parser;

import java.util.ArrayList;

public class StatementTree extends StatementPart {
	public ArrayList<PartTree> childen;
	
	public StatementTree(PartTree root, ArrayList<PartTree> childen) {
		super(root);
		this.childen = childen;
	}

	public ArrayList<PartTree> getChildren() {
		return this.childen;
	}
	
}
