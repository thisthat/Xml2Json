@echo off
set /p version=<version.txt
set /a NewBuild=%version%+1

echo --------------
echo Build 0.1.%NewBuild%
echo --------------
echo. 
echo --- MAIN TEST ---
java -cp output Main test.txt
echo -----------------
echo.
echo --- Book1 TEST ---
java -cp output Main test/book1.xml
echo ------------------
echo. 
echo --- Book2 TEST ---
java -cp output Main test/book2.xml
echo --------------
