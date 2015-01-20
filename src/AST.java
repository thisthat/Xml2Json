
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
        public ASTAttribute(String n, String v){
            name = n;
            value = v;
        }
        public String toString(){
            return name + "=" + value;
        }
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
        public Table(List a, List i) {
            items = i;
            attributes = a;
        }
        public String toString() {
            String out = "Table @";
            for(Object c:attributes){
                out += c.toString() + ", ";
            }
            out += ": \n";
            for(Object c:items){
                out += c.toString() + "\n ";
            }
            return out + "]";
        }

    }
    public class Row extends ASTElement {
        public Row(){
            items = new ArrayList<Cell>();
        }
        public Row(List<Cell> list){
            items = new ArrayList<Cell>();
            items.addAll(list);
        }
        public void addCell(Cell n){
            items.add(n);
        }
        public String toString(){
            String out = "[";
            for(Object c:items){
                out += c.toString() + ", ";
            }
            return out + "]";
        }
    }
    public class Cell extends ASTElement {
        private String value;
        public Cell(String v){
            this.value = v;
        }
        public String toString(){
            return "Cell value:" + this.value+ "";
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

