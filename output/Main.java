/*

This class lunches the parsing of the file and then create the json rapresentation. 

Notes: 
    - The output file name is the same of the input file, but different extension
    - If it is forced a stream reading, the file is called 'converted.json'

*/

import java.io.*;
import java.util.*;
public class Main {

    
    public static void main(String args[]) throws IOException {

        String filename = "converted";
        System.out.println("Parsing the file..");

        Parser yyparser;
        if ( args.length > 0 ) {
            // parse a file
            yyparser = new Parser( new FileReader( args[0] ) );
            filename = args[0].substring( 0 , args[0].lastIndexOf(".") );
        }
        else {
            yyparser = new Parser(new InputStreamReader(System.in));
        }

        yyparser.yyparse();
    
        //Finish to parse the file
        System.out.println("Parsing done.");

        System.out.println("Creating " + filename + ".json file");
        
        PrettyPrinter pp = new PrettyPrinter(yyparser.root);
        String json = pp.toJson();

        System.out.println(json);
    }
}