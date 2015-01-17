@echo off

REM file version generation

if EXIST version.txt goto Start
set Build="0"
echo %Build%>version.txt


:Start

cls

REM increment the built number

set /p version=<version.txt
set /a NewBuild=%version%+1
echo %NewBuild% > version.txt



REM setting the right path to variables

set FILE_NAME=calc
set JFLEX_HOME= %~dp0bin\
set CLPATH=%JAVA_HOME%\lib\classes.zip;%JFLEX_HOME%\lib\JFlex.jar

REM clean some old stuff

del output\*.java
del output\*.class

copy src\*.java output

REM Build 

bin\yacc -J %FILE_NAME%.y
java -Xmx128m -jar %JFLEX_HOME%lib\JFlex.jar %FILE_NAME%.flex %2 %3 %4 %5 %6 %7 %8 %9

move %~dp0*.java %~dp0output\

javac output\*.java 

REM Move to the output dir


REM move %~dp0*.class %~dp0output\

REM And finally run

@echo on
echo Build 0.1.%NewBuild%
java -cp output Main < test.txt


