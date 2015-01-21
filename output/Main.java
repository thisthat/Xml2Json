import java.io.*;
import java.util.*;
public class Main {
    
    public static void main(String args[]) throws IOException {

        System.out.println("BYACC/Java with JFlex Calculator Demo");

        Parser yyparser;
        if ( args.length > 0 ) {
            // parse a file
            yyparser = new Parser(new FileReader(args[0]));
        }
        else {
            yyparser = new Parser(new InputStreamReader(System.in));
        }

        yyparser.yyparse();
    
        //Finish to parse the file

        
    }
}