import java.util.ArrayList;

import Parser.Parser;
import Parser.Statement;

public class Driver 
{

	public static void main(String[] args) 
	{
		Parser.parse("input.spyder");
		Parser.display();
		ArrayList<Statement> theStatements = Parser.getParsedStatements();
		Interpreter.SpyderInterpreter.interpret(theStatements);
		Interpreter.SpyderInterpreter.displayResults();
	}
}
