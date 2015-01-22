import java.io.*;
import java.util.*;
public class Main {
    
    public static void main(String args[]) throws IOException {

        System.out.println("Parsing the file..");

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
        System.out.println("Parsing done.");
        System.out.println("Creating converted.json file");
        //String json = yyparser.root.toString();
    }
}