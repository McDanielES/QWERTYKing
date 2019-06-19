import java.io.FileReader;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;
public class QWERTYKing
{
    public static final int     GAME_SIZE    =  10;
    public static final String  DEFAULT_FILE = "3000-Words.txt";
    public static final boolean DEFAULT_CAPS = false;

    public static void main(String[] args)
    {
        try
        {
            java.util.List<String> argsList = java.util.Arrays.asList(args);
            int     gameSize   = (argsList.size() > 1 && argsList.contains("-s")) ? (Integer.parseInt(argsList.get(argsList.indexOf("-s") + 1))) : GAME_SIZE;
            String  customFile = (argsList.size() > 1 && argsList.contains("-f")) ? (argsList.get(argsList.indexOf("-f") + 1)) : DEFAULT_FILE;
            boolean randomCaps = (argsList.size() > 1 && argsList.contains("-C")) ? !DEFAULT_CAPS : DEFAULT_CAPS;
            System.out.printf("Type quit to end the game at any time.\nDuplicate the following words.\n\n");

            boolean           continueGame = true;
            FileReader        wordsFile    = new FileReader(customFile);
            ArrayList<String> words        = loadFile(wordsFile);
            ArrayList<String> inGameWords  = new ArrayList<String>();

            while (continueGame)
            {
                for (int i = 0; i < gameSize; ++i)
                {
                    inGameWords.add(getRandomWord(words, randomCaps));
                    System.out.printf("%s ", inGameWords.get(i));
                    if ((i + 1) % 10 == 0)
                        System.out.println();
                }
                System.out.printf("\n\n\t");

                ArrayList<String> userTypedWords = fillUserInputArray(new Scanner(System.in).nextLine());

                if (validInput(userTypedWords, gameSize))
                {
                    ArrayList<Integer> errors = getUserErrors(inGameWords, userTypedWords);
                    printErrors(inGameWords, userTypedWords, errors);
                }
                else if (userTypedWords.get(0).trim().equalsIgnoreCase("quit"))
                    continueGame = false;
                else
                    System.err.printf("You did not type the same number of words as provided. "
                                   + "There should be %d words.\n\n", gameSize);

                inGameWords.removeAll(inGameWords);

            } // End game loop            
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Error locating file. Terminating.");
            System.exit(1);
        }
        catch (NumberFormatException ex)
        {
            System.err.println("Error parsing argument(s). Terminating");
            System.exit(1);
        }
    } // End main()

    public static ArrayList<String> loadFile(FileReader wordsFile)
    {
        // Instantiate reading objects.
        Scanner           reader = new Scanner(wordsFile);
        ArrayList<String> words  = new ArrayList<String>();

        // Add words into array and return object
        while (reader.hasNext())
            words.add(reader.next());
        reader.close();
        return words;
    } // End loadFile()

    public static String getRandomWord(ArrayList<String> words, boolean randomCaps)
    {
        // Return random word from file
        String word = words.get(new Random().nextInt(words.size())).toLowerCase();
        return (new Random().nextInt(3) != 0) ? word : (word.substring(0, 1).toUpperCase() + word.substring(1));
    }

    public static boolean validInput(ArrayList<String> userTypedWords, int gameSize)
    {
        return (userTypedWords.size() == gameSize) ? true : false;
    }

    public static ArrayList<String> fillUserInputArray(String userTypedWords)
    {
        // Apply single line from user into ArrayList
        ArrayList<String> userInput = new ArrayList<String>();
        Scanner           scan      = new Scanner(userTypedWords);
        while (scan.hasNext())
            userInput.add(scan.next().trim());
        return userInput;
    } // End fillUserInputArray

    public static ArrayList<Integer> getUserErrors(ArrayList<String> inGameWords,
                                                   ArrayList<String> userTypedWords)
    {   // Store index values of the user error(s)
        ArrayList<Integer> userErrors = new ArrayList<Integer>();
        for (int i = 0; i < inGameWords.size(); ++i)
            if (!(inGameWords.get(i).trim().equals(userTypedWords.get(i).trim())))
                userErrors.add((Integer) i);
        return userErrors;
    } // End getUserErrors()

    public static void printErrors(ArrayList<String> inGameWords,
                                   ArrayList<String> userTypedWords,
                                   ArrayList<Integer> errors)
    {
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
    } // End printErrors()
} // End class QWERTYKing