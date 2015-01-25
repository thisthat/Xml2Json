/*

This class perform a traversal of the tree and gives as output the json encoding of it

Notes: 
    - The toJson method is define for each AST element
    - The parameter 'isLastElement' is used to decide if we have to put the ',' or not 
        when we compute children of list element

*/

import java.util.*;

public class PrettyPrinter {

    AST.Root root;

    private String indent(int tabs){
        String out = "";
        for(int i = 0; i < tabs; i++){
            out += "\t";
        }
        return out;
    }

    public PrettyPrinter(AST.Root r){
        root = r;
    }

    public String toJson(){
        return toJson(root,1);
    }

    public String toJson(AST.ASTAttribute attr, int tab){
        return indent(tab) + "\"@" + attr.name + "\" : \"" + attr.value + "\",\n";
    }

    public String toJson(AST.Root root, int tab){
        String out = "{\n";
        out += indent(tab) + "\"tag\": \"book\",\n";
        for(AST.ASTAttribute c: root.attributes){
                out += toJson(c,tab);
        }
        out += "content: [\n";
        //
        for(int j = 0; j < root.items.size(); j++){
            Object c = root.items.get(j);
            if(c instanceof AST.Dedication){
                out += toJson( (AST.Dedication) c,tab+1);
            }
            else if(c instanceof AST.Preface){
                out += toJson( (AST.Preface) c,tab+1);
            }
            else if(c instanceof java.util.ArrayList){
                //part items
                for(int i = 0; i < ((java.util.ArrayList)c).size(); i++){
                    Object item = ((java.util.ArrayList)c).get(i);
                    out += toJson((AST.Part) item, tab + 1);
                }
            }
            else if(c instanceof AST.AuthorNotes){
                out += toJson( (AST.AuthorNotes) c,tab+1);
            }
        }
        out += indent(tab) + "]\n";
        out += "}";
        return out;
    }

    public String toJson(AST.Dedication dedication, int tab){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"dedication\",\n";
        out += indent(tab+1) + "\"content\" : \"" + dedication.value + "\"\n";
        out += indent(tab) + "},\n";
        return out;
    }

    public String toJson(AST.Preface preface, int tab){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"preface\",\n";
        out += indent(tab+1) + "\"content\" : \"" + preface.value + "\"\n";
        out += indent(tab) + "},\n";
        return out;
    }

    public String toJson(AST.Part part, int tab){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"part\",\n";
        out += indent(tab) + "},\n";
        return out;
    }

    public String toJson(AST.AuthorNotes part, int tab){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"authornotes\",\n";
        out += indent(tab+1) + "content: [\n";
        int last = part.items.size();
        int lastElm = last-1;
        for(int i = 0; i < last; i++){
            out += toJson( (AST.Note) part.items.get(i), tab + 2, lastElm == i);
        }
        out += indent(tab+1) + "]\n";
        out += indent(tab) + "},\n";
        return out;
    }

    public String toJson(AST.Note note, int tab, Boolean isLastElm){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"note\",\n";
        out += indent(tab+1) + "\"content\": \"" + note.value + "\"\n";
        out += indent(tab) + "}";
        if(isLastElm){
            out += "\n";
        }
        else {
            out += ",\n";
        }
        return out;
    }
}