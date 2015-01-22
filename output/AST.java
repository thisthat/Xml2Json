
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

    public class PartItems extends ASTElement {
        public TOC toc;
        public LOF lof;
        public LOT lot;
        public PartItems(){
            items = new ArrayList<ASTElement>();
            toc = null;
            lot = null;
            lof = null;
        }
        public PartItems(TOC t, List c){
            items = c;
            toc = t;
            lot = null;
            lof = null;
        }
        public PartItems(TOC t, List c, LOF l){
            items = c;
            toc = t;
            lot = null;
            lof = l;
        }
        public PartItems(TOC t, List c, LOT l){
            items = c;
            toc = t;
            lot = l;
            lof = null;
        }
        public PartItems(TOC t, List c, LOF lf, LOT lt){
            items = c;
            toc = t;
            lot = lt;
            lof = lf;
        }
    }

    public class TOC extends ASTElement {
        public TOC(){
            items = new ArrayList<ASTElement>();
        }
        public TOC(List i) {
            items = i;
        }
    }

    public class LOF extends ASTElement {
        public LOF(){
            items = new ArrayList<ASTElement>();
        }
        public LOF(List i) {
            items = i;
        }
    }

    public class LOT extends ASTElement {
        public LOT(){
            items = new ArrayList<ASTElement>();
        }
        public LOT(List i) {
            items = i;
        }
    }

    public class Item extends ASTElement {
        private String value;
        public Item(){
            items = new ArrayList<ASTElement>();
        }
        public Item(List a, String v) {
            value = v;
            attributes = a;
        }
    }

    public class Chapter extends ASTElement {
        public Chapter(){
            items = new ArrayList<ASTElement>();
        }
        public Chapter(List a, List i) {
            items = i;
            attributes = a;
        }
    }
    public class Section extends ASTElement {
        public Section(){
            items = new ArrayList<ASTElement>();
        }
        public Section(List a, List i) {
            items = i;
            attributes = a;
        }
    }
    public class Figure extends ASTElement {
        public Figure(){
        }
        public Figure(List a) {
            attributes = a;
        }
    }

    public class Table extends ASTElement {
        public Table(){
            items = new ArrayList<Row>();
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
            out += ": \n{";
            for(Object c:items){
                out += "\t" + c.toString() + "\n ";
            }
            return out + "\n\t}";
        }

    }
    public class Row extends ASTElement {
        public Row(){
            items = new ArrayList<Cell>();
        }
        public Row(List<Cell> list){
            items = list;
        }
        public String toString(){
            String out = "Row : [";
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
        public AuthorNotes(List<Note> notes){
            items = notes;
        }
    }
}

