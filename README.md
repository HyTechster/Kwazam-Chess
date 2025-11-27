To compile manually for all java files

1. Go to parent folder and in terminal (for Windows using Powershell)
2. write :
javac -d bin (Get-ChildItem -Recurse -Filter *.java | ForEach-Object { $_.FullName })

To run
1. Go to parent folder
2. write :
java -cp bin core/run