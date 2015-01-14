@echo off

set FILE_NAME=calc
set JFLEX_HOME= %~dp0bin\
set CLPATH=%JAVA_HOME%\lib\classes.zip;%JFLEX_HOME%\lib\JFlex.jar


del *.java

bin\yacc -J %FILE_NAME%.y

java -Xmx128m -jar %JFLEX_HOME%lib\JFlex.jar %FILE_NAME%.flex %2 %3 %4 %5 %6 %7 %8 %9

javac *.java

move %~dp0*.java %~dp0output\
move %~dp0*.class %~dp0output\

@echo on

java -cp output Parser < test.txt


