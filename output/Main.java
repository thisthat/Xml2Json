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
        Boolean errors_idref = false;
        BufferedWriter writer = null;
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
        filename += ".json";
        yyparser.yyparse();
    
        //Finish to parse the file
        System.out.println("Parsing done.");
        //Control IDREF
        //System.out.println(Arrays.toString(yyparser.ids.toArray()));
        //System.out.println(Arrays.toString(yyparser.ref_ids.toArray()));
        errors_idref = checkIDREF(yyparser.ids, yyparser.ref_ids);

        if(yyparser.errors || errors_idref){
            System.out.println("The file is not conform to the DTD.\nNo Json conversion is possible");
        }
        else {
            System.out.println("Creating " + filename + " file");
            File jsonFile = new File(filename);
            
            PrettyPrinter pp = new PrettyPrinter(yyparser.root);
            String json = pp.toJson();

            writer = new BufferedWriter(new FileWriter(jsonFile));
            writer.write(json);
            writer.close();
            System.out.println("Done.");
        }

    }

    public static Boolean checkIDREF(List<String> ids,List<String> ref_ids){
        Boolean ret = false;
        for(int i=0;i<ref_ids.size();i++){
            if(!ids.contains(ref_ids.get(i))){
                System.out.println("Missing element with ID: " + ref_ids.get(i));
                ret = true;
            }
        }
        return ret;
    }
}