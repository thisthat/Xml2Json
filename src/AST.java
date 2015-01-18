
import java.util.ArrayList;
import java.util.List;

/* 
    Class to define the abstrac syntax tree
    this data structure will be filled by the Parser
    with the token's value
*/

public class AST {
    public class ASTAttribute {
        private String name;
        private String value; 
    }

    public abstract class ASTElement {
        public List<ASTAttribute> attributes;
        public List items;
        public List getItems(){
            return items;
        }
    }

    public class Table extends ASTElement {
        public Table(){
            items = new ArrayList<Note>();
        }
        public void addRow(Cell n){
            items.add(n);
        }
        public void addAttr(ASTAttribute attr){
            attributes.add(attr);
        }

    }
    public class Row extends ASTElement {
        public Row(){
            items = new ArrayList<Note>();
        }
        public void addCell(Cell n){
            items.add(n);
        }
    }
    public class Cell extends ASTElement {
        private String value;
        public Cell(String v){
            this.value = v;
        }
        public String toString(){
            return "Cell value:" + this.value+ "\n";
        }
    }

    public class Note extends ASTElement {
        private String value;
        public Note(String v){
            this.value = v;
        }
        public String toString(){
            return this.value+ "\n\n\n";
        }
    }
    public class AuthorNotes extends ASTElement {
        public AuthorNotes(){
            items = new ArrayList<Note>();
        }

        public void addNote(Note n){
            items.add(n);
        }
        
    }
}

