# QWERTYKing
## A simple, configurable command-line Java typing tutor and game for improving personal typing skills

![Sample image of QWERTYKing with custom configured settings](https://i.imgur.com/AfsFeMy.png)

---

Welcome to QWERTYKing, written by Eric McDaniel - June 2019.

#### Purpose
Disclaimer: There is a strong probability that this program won't be useful to you. I wrote it for my own benefit. I was never taught how to properly type using the popular "homerow" technique. I joking describe my typing technique to others as <i>refined and advanced hunt-and-pecking</i>, where I use my four fingers to type. Although I've developed a lot of speed after years of practicing this poor habit, it's terribly inefficient, inaccurate, and often forces me to stare at a keyboard and not at the screen. QWERTYKing was developed as a custom, no-risk solution to provide a practice environment while learning proper typing techniques. 

#### But tons of other, better software exists. Why add another?
+ I've been practicing proper typing techniques on various other websites, e.g. [Typing Club](https://www.typingclub.com/sportal/) and [ZType](https://zty.pe/), but I get frustrated with all of the frills and nonsense, needless animation and wasted time. I just wanted to create a configurable, no-nonsense typing game that just simply spits out random words and demands them to be correctly typed.
+ I don't care about user statistics and progress, such as words per minute and its improvement over time. I just want the words and the practice, not the evaluation. I am my own disciplinarian.
+ I don't care about useless points, badges, accolades, and other rewards when playing the majority of games online. I just wanted a straight-to-the-point typing game.
+ It was a fun thought experiment and decent practice in programming. 

#### Usage
The game will generate by default ten random words in lowercase. You must type them all, separated by spaces, then press enter. The number of words selected, choice of random capitalization, difficulty, and choice of using a custom dictionary are all options that can be configured as a command line argument (See "Run the Application/ Optional Flags" for more details). 

While you're free to play the game in any way you wish, the ideal method is to use proper technique, not to look down at the keyboard, avoid backspacing and move on. Developing discipline is a skill that transcends more than just this console application.

#### Note: Non-Linux users beware
This program was primarily intended for Linux users, and was not tested in a Windows or macOS environments. Actually, I lied. It was intended just for me. Should you be brave enough, please mention me at `@McDanielES` if you find an issue in Windows or macOS. Alternatively, you can open a new issue on GitHub.

---

### Get the Application
+ Being a Java application, users will need to install Oracle's Java Development Kit (JDK). [The JDK can be downloaded here.](http://www.oracle.com/technetwork/java/javase/downloads/index.html) The `*.class` files are not provided, and the `javac` tool is not available if you only have the JRE.
+ For those unfamiliar with GitHub, click the green "Clone or download" button, download the repository as a ZIP, and extract it on your local machine. Cloning/ Forking/ or downloading via SSH will work just as fine too for users with previous GitHub experience.
+ Compile the source code using the `javac` command. You must be in the root directory containing the `QWERTYKing.java` file.
```
javac QWERTYKing.java
```

### Run the Application
+ Run the program using the `java` keyword, followed by the name of the program (without the <i>.java</i> suffix).
```
java QWERTYKing [FLAGS] [ARGUMENTS]
```

#### Optional Flags:
| Flag | | Description |
| --- | --- | --- |
| `-d` | `<VALUE>` | Select a difficulty level ranging from 1 (easy) to 3 (hard). The program defaults to Hard if no flag is provided.  |
| `-f` | `<VALUE>` | Provide a custom dictionary textfile for use. |
| `-s` | `<VALUE>` | Choose the number of words to be used in game play. The default is 10 |
| `-C` | | Approximately one third of the words will randomly have a capitalized first letter. The default is <i>off</i>. |


#### Contact the Author
Should you find an error in the program or would like to contact me for any suggestions, improvements, or threats, you can use GitHub's `@McDanielES` mention system to contact me. I will try to respond as soon as I am available.

I am a Computer Science student at the University of Wisconsin - Madison. I am learning the fundamentals of programming in Java, Visual Basic.NET, Python, and C++. This program was written primarily for my benefit and to apply the skills learned in class into a real-world context. I am aware that it may not be realistic and often crude or unsophisticated. But <i>live and learn</i>.

You are free to clone, fork, modify and use this application as you please.