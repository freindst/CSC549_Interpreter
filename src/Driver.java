
public class Driver 
{

	public static void main(String[] args) 
	{
		VariableEnvironment env = new VariableEnvironment();
		env.addVariable("a", 5);
		env.addVariable("b", 7);
		env.display(); //show all of the variables
		System.out.println(env.getValue("a")); //5
		System.out.println(env.getValue("b")); //7
	}
}
