import java.util.List;
public class ASTHandler {
    public AST _AST = new AST();
    
    AST.AuthorNotes authorNotes = _AST.new AuthorNotes();
    
    public void addCell(String v){

    }
    public void addNote(AST.Note n){
        authorNotes.addNote(n);
    }
    public void addNote(String val){
        AST.Note n = this.createNote(val);
        authorNotes.addNote(n);
    }
    public AST.Note createNote(String value) {
        return _AST.new Note(value);
    }
    
    public List getAuthorNotes(){
        return authorNotes.getItems();
    }
}