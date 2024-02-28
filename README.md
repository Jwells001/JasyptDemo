# JasyptDemo

## Download the Jasypt binaries and Javadocs @
https://github.com/jasypt/jasypt/releases/download/jasypt-1.9.3/jasypt-1.9.3-dist.zip

## To encrypt serect:
- Open terminal/powershell
- CD to the jasypt-1.9.3 directory
- CD into bin directory
- Run the following command in the terminal for Unix-like systems: encrypt.sh input="your_password_here" password="your_secret_key_here" algorithm="your_algorithm_here"
- Run the following command in powershell for Windows: encrypt.bat input="your_password_here" password="your_secret_key_here" algorithm="your_algorithm_here"
- After running the command, the secret you put in the "input" field will be encrypted and the encrypted value will be displayed at the bottom.
- Use that encrypted value in your program
