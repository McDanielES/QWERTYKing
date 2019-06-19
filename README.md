# QWERTYKing
## A simple CLI typing teacher

![Sample image of QWERTYKing](https://i.imgur.com/vfSD7rf.png)

---

Welcome to QWERTYKing, written by Eric McDaniel - June 2019.

#### Purpose
There is a strong probability this program won't be helpful for you. I wrote it for my own benefit. I was never taught how to properly type using the homerow. I have what I refer to as "refined and advanced hunt-and-pecking", where I use four fingers to type. Although I've developed a lot of speed, it's terribly inefficient, inaccurate, and takes my eyes away from the screen and on to a keyboard too often.

#### But tons of other, better software exists, why this one?
+ I've been practicing on some other websites e.g. [Typing Club](https://www.typingclub.com/sportal/) and [ZType](https://zty.pe/), but I get frustrated with all of the frills and nonsense, needless animation and wasted time. I just wanted to create a custom, no-nonsense typing game.
+ It was a fun thought experiment in programming. 

#### Usage
The game will generate ten random words in lowercase. You must type them all back, separated by spaces, then type enter. The number of words, choice of random capitalization, and choice of dictionary to be used are all options that can be configured as a command line argument (See "Run the Application/ Optional Flags" for more details). 

While you're free to play the game in anyway you wish, the idea method is to use proper technique and do not look down at the keyboard.

#### Note: Non-Linux users beware
This program was primarily intended for Linux users, and was not tested in a Windows or macOS environments. Actually, I lied. It was intended just for me. Should you be brave enough, please mention me at `@McDanielES` if you find an issue in Windows or macOS. Alternatively, you can open a new issue on GitHub.

---

### Get the Application
+ Being a Java application, users will need to install Oracle's Java Development Kit (JDK). <a href="http://www.oracle.com/technetwork/java/javase/downloads/index.html">The JDK can be downloaded here.</a> The `*.class` files are not provided, and the `javac` command is not included if you only have the JRE.
+ For those unfamiliar with GitHub, click the green "Clone or download" button, download the repository as a ZIP, and extract it on your local machine. Cloning/ Forking/ or downloading via SSH will work just as fine too for users with previous GitHub experience.
+ Compile the source code using the `javac` command. You must be in the root directory containing the `QWERTYKing.java` file.
```
javac QWERTYKing.java
```

### Run the Application
+ Run the program using the `java` keyword, followed by the name of the program (without the <i>.java</i> suffix).
```
java QWERTYKing [ARGUMENTS]
```
To get the most out of this utility, set an alias in your shell's <i>rc</i> file to have java reference the path of the program. This also eliminates typing the keyword `java`. For example, you can append:
```
alias QWERTYKing='java -cp ~/<path>/<to>/<directory>/QWERTYKing QWERTYKing'
```
This would require you to only need to type `QWERTYKing` into your shell. You can even shorten it to `RP` or whatever you choose.

By default, the help menu will appear if no arguments were provided.

#### Optional Flags:
| Flag | | Description |
| --- | --- | --- |
| `-f` | `<VALUE>` | Provide a custom textfile dictionary for use. Easy (100-Words.txt), Medium (1000-Words.txt), and Hard (3000-Words.txt) are provided. The program defaults to Hard if no flag is provided. |
| `-s` | `<VALUE>` | Choose the number of words to be used in gameplay. Default is 10 |
| `-C` | | Approximately a third of the words will have a capitalized first letter. Default is off. |


#### Contact the Author
Should you find an error in the program or would like to contact me for any suggestions, improvements, or threats, you can use GitHub's `@McDanielES` mention system to contact me. I will try to respond as soon as I am available.

I computer science student at the University of Wisconsin - Madison. I am learning the fundamentals of programming in Java, Visual Basic.NET, Python, and C++. This program was written primarily for my benefit and to apply the skills learned in class into a real-world context. I am aware that it may not be realistic and often crude or unsophisticated. But <i>live and learn</i>.

You are free to clone, fork, modify and use this application as you please.