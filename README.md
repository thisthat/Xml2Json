# Xml2Json
XML to Json :: Manuale

# Struttura
Path          | Descrizione
------------- | -------------
/             | Ci sono file batch per la compilazione e i file per flex/yacc 
/bin          | Ci sono i binari di flex e yacc
/output       | I risultati di compilazione sono creati qua dentro
/src          | Ci sono i file java di supporto a quelli creati da flex/yacc
/test         | Directory con i file di testing

#I FILE

File          | Descrizione
------------- | -------------
xml2json.flex           | Definizione dei Token
xml2json.y              | Definizione della Grammatica
src/AST.java            | Definizione della struttura dati per l'AST
src/Main.java           | Definizione dell'entry point
src/PrettyPrinter.java  | Definizione del convertitore in json

<h3>Descrizione</h3>
Il Main legge in input un file e lo passa al parser. 
Il parser utilizza i token creati dal lexer.
Il parser costruisce l'albero di sintassi astratta (AST) utilizzando la struttura dati del file AST.java
Una volta creato l'AST, il main crea il file json di output e richiama il PrettyPrinter.
Il PrettyPrinter legge l'AST e lo converte in JSON.


# Motivazione
Ho strutturato il codice nel seguente modo perchè volevo riutilizzare l' AST per fare il contratio (json2xml).
Quindi creare una classe astratta PrettyPrint con un solo metodo:
```Java
String convert();
```
Estendere poi la classe con due sotto-classi:
  - PrettyPrintXML
  - PrettyPrintJSON
  
Non avendo avuto tempo, ho creato solo una classe PrettyPrint che esporta il metodo:
```Java
String toJSON();
```
Questo metodo esegue una visita dell'albero e lo stampa seguendo le specifiche JSON.
<hr>
Per ogni dubbio aprite un issue nell'issue tracker, in questo modo:
  - mi arriva una mail che mi avvisa
  - si tiene traccia di tutte le domande/risposte
  - si tiene traccia di tutti i problemi riscontrati
  - si tiene traccia di tutti i consigli che mi date

Spero sia tutto chiaro :neckbeard: