import java.util.*;
import java.io.FileReader;
public class Words
{
    private ArrayList<String>  dictionary;
    private ArrayList<String>  inGameWords;
    private ArrayList<String>  userTypedWords;
    private ArrayList<Integer> errors;
    private int                gameSize;
    private boolean            randomCaps;

    public Words(FileReader wordsFile, int size, boolean caps)
    {
        dictionary  = loadFile(wordsFile);
        inGameWords = new ArrayList<String>();
        gameSize    = size;
        randomCaps  = caps;
    }

    private static ArrayList<String> loadFile(FileReader wordsFile)
    {
        // Instantiate reading objects
        Scanner           reader = new Scanner(wordsFile);
        ArrayList<String> words  = new ArrayList<String>();

        // Add words into array and return object
        while (reader.hasNext())
            words.add(reader.next());
        reader.close();
        return words;
    } // End loadFile()

    public void fillAndPrintRandomWords()
    {
        for (int i = 0; i < gameSize; ++i)
        {
            inGameWords.add(getRandomWord());
            System.out.printf("%s ", inGameWords.get(i));
            if ((i + 1) % 10 == 0)
                System.out.println();
        }
        System.out.printf("\n\n\t");
    } // End fillAndPrintRandomWords()

    private String getRandomWord()
    {
        // Return random word from file
        String word = dictionary.get(new Random().nextInt(dictionary.size())).toLowerCase();
        return (randomCaps && new Random().nextInt(3) != 0) ? (word.substring(0, 1).toUpperCase() + word.substring(1)) : word;
    }

    public void getUserEntry()
    {
        userTypedWords = fillUserInputArray(new Scanner(System.in).nextLine());
    }

    private static ArrayList<String> fillUserInputArray(String userTypedWords)
    {
        // Apply single line from user into ArrayList
        ArrayList<String> userInput = new ArrayList<String>();
        Scanner           scan      = new Scanner(userTypedWords);
        while (scan.hasNext())
            userInput.add(scan.next().trim());
        return userInput;
    } // End fillUserInputArray

    public boolean validInput()
    {
        return (userTypedWords.size() == gameSize) ? true : false;
    }

    private void getUserErrors()
    {
        // Helper method to store index values of the user error(s)
        errors = new ArrayList<Integer>();
        for (int i = 0; i < inGameWords.size(); ++i)
            if (!(inGameWords.get(i).trim().equals(userTypedWords.get(i).trim())))
                errors.add((Integer) i);
    } // End getUserErrors()

    public void printAnyErrors()
    {
        getUserErrors();
        if (errors.size() == 0)
            System.out.println("\nGreat job! You typed perfectly!\n------------------------------");
        else
        {
            System.err.printf("\n|     You accidentally made %2d errors    |\n|             Word |               Input |\n"
                            + "|------------------|---------------------|\n", errors.size());
            for (int i = 0; i < errors.size(); ++i)
                System.err.printf("|%17s |%20s |\n", inGameWords.get(errors.get(i)), userTypedWords.get(errors.get(i)));
            System.err.printf("|-----------------------------------------\n\n");
        }
    } // End printAnyErrors()

    public boolean quitGame()
    {
        return userTypedWords.get(0).trim().equalsIgnoreCase("quit");
    }

    public void clear()
    {
        inGameWords.removeAll(inGameWords);
    }
}