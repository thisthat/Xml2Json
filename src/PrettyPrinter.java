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
        int last = root.items.size();
        int lastElm = last-1;
        for(int j = 0; j < last; j++){
            Object c = root.items.get(j);
            if(c instanceof AST.Dedication){
                out += toJson( (AST.Dedication) c,tab+1);
            }
            else if(c instanceof AST.Preface){
                out += toJson( (AST.Preface) c,tab+1);
            }
            else if(c instanceof java.util.ArrayList){
                //part items
                int lastList = ((java.util.ArrayList)c).size();
                for(int i = 0; i < lastList; i++){
                    Object item = ((java.util.ArrayList)c).get(i);
                    out += toJson((AST.Part) item, tab + 1, lastElm == i);
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

    public String toJson(AST.AuthorNotes notes, int tab){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"authornotes\",\n";
        out += indent(tab+1) + "content: [\n";
        int last = notes.items.size();
        int lastElm = last-1;
        for(int i = 0; i < last; i++){
            out += toJson( (AST.Note) notes.items.get(i), tab + 2, lastElm == i);
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

    public String toJson(AST.Part part, int tab, Boolean isLastElm){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"part\",\n";
        for(AST.ASTAttribute c: part.attributes){
                out += toJson(c,tab+1);
        }
        out += indent(tab+1) + "content: [\n";
        int last = part.items.size();
        int lastElm = last-1;
        for(int j = 0; j < last; j++){
            Object c = part.items.get(j);
            //toc chapters lof lot
            if(c instanceof AST.TOC){
                out += toJson( (AST.TOC) c,tab+2);
            }
            if(c instanceof AST.LOT){
                out += toJson( (AST.LOT) c,tab+2, lastElm == j);
            }
            if(c instanceof AST.LOF){
                out += toJson( (AST.LOF) c,tab+2, lastElm == j);
            }
            if(c instanceof java.util.ArrayList){
                //Chapter items
                for(int i = 0; i < ((java.util.ArrayList)c).size(); i++){
                    Object item = ((java.util.ArrayList)c).get(i);
                    out += toJson((AST.Chapter) item, tab + 1, lastElm == i);
                }
            }
        }
        out += indent(tab+1) + "]\n";
        out += indent(tab) + "}";
        if(isLastElm){
            out += "\n";
        }
        else {
            out += ",\n";
        }
        return out;
    }

    public String toJson(AST.TOC toc, int tab){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"toc\",\n";
        out += indent(tab+1) + "\"content\": [\n";
        int last = toc.items.size();
        int lastElm = last-1;
        for(int j = 0; j < last; j++){
            Object c = toc.items.get(j);
            //only items
            if(c instanceof AST.Item){
                out += toJson( (AST.Item) c, tab+2, lastElm == j);
            }
        }
        out += indent(tab+1) + "]\n";
        out += indent(tab) + "},\n";
        return out;
    }

    public String toJson(AST.Item item, int tab, Boolean isLastElm){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"item\",\n";
        for(AST.ASTAttribute c: item.attributes){
                out += toJson(c,tab+1);
        }
        out += indent(tab+1) + "\"content\" : \"" + item.value + "\"\n";
        out += indent(tab) + "}";
        if(isLastElm){
            out += "\n";
        }
        else {
            out += ",\n";
        }
        return out;
    }

    public String toJson(AST.LOT lot, int tab, Boolean isLastElm){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"lot\",\n";
        out += indent(tab+1) + "\"content\": [\n";
        int last = lot.items.size();
        int lastElm = last-1;
        for(int j = 0; j < last; j++){
            Object c = lot.items.get(j);
            //only items
            if(c instanceof AST.Item){
                out += toJson( (AST.Item) c, tab+2, lastElm == j);
            }
        }
        out += indent(tab+1) + "]\n";
        out += indent(tab) + "}";
        if(isLastElm){
            out += "\n";
        }
        else {
            out += ",\n";
        }
        return out;
    }
    public String toJson(AST.LOF lof, int tab, Boolean isLastElm){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"lof\",\n";
        out += indent(tab+1) + "\"content\": [\n";
        int last = lof.items.size();
        int lastElm = last-1;
        for(int j = 0; j < last; j++){
            Object c = lof.items.get(j);
            //only items
            if(c instanceof AST.Item){
                out += toJson( (AST.Item) c, tab+2, lastElm == j);
            }
        }
        out += indent(tab+1) + "]\n";
        out += indent(tab) + "}";
        if(isLastElm){
            out += "\n";
        }
        else {
            out += ",\n";
        }
        return out;
    }

    public String toJson(AST.Chapter lof, int tab, Boolean isLastElm){
        String out = indent(tab) + "{\n";
        out += indent(tab+1) + "\"tag\": \"lof\",\n";
        out += indent(tab+1) + "\"content\": [\n";
        out += "DA IMPLE";
        out += indent(tab+1) + "]\n";
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