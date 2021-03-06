import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;

public class Pathy {
    public static void main(String[] args) throws Exception {
        String inputFile = null; 
        if(args.length > 0)
		inputFile = args[0];

        InputStream is = System.in;
        if (inputFile != null)
		is = new FileInputStream(inputFile);

        ANTLRInputStream input = new ANTLRInputStream(is);
        PathyLexer lexer = new PathyLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        PathyParser parser = new PathyParser(tokens);

        ParseTree tree = parser.r();
        
        boolean debug = false;
        if(debug)
        {
        	System.out.println(tree.toStringTree(parser));
        }
        
		HashMap<String, PathyObject> worldDict = new HashMap<String, PathyObject>();
	
		PathTreeVisitor eval = new PathTreeVisitor(worldDict);
		try
		{
			eval.visit(tree);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
    }
}
