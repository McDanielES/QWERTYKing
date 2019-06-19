import java.io.*;
import java.util.*;
public class QWERTYKing
{
    public static final int GAME_SIZE = 10;
    public static void main(String[] args)
    {
        try
        {
            FileReader wordsFile = new FileReader("3000Words.txt");
            ArrayList<String> words = loadFile(wordsFile);
            String[] inGameWords = new String[GAME_SIZE];

            System.out.printf("Duplicate the following words.\n------------------------------\n");
            for (int i = 0; i < GAME_SIZE; ++i)
            {
                inGameWords[i] = getRandomWord(words);
                System.out.printf("%s ", inGameWords[i]);
            }
            System.out.printf("\n\n");

            ArrayList<String> userTypedWords = fillUserInputArray(new Scanner(System.in).nextLine());

            if (!validInput(userTypedWords))
            {
                System.out.printf("You did not type the same number of words as provided. There should be %d words.\n", GAME_SIZE);
            }

            
        }
        catch (FileNotFoundException ex)
        {
            System.err.println("Error locating file.");
            System.exit(1);
        }
    }

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
    }

    public static String getRandomWord(ArrayList<String> words)
    {
        Random rand = new Random();
        return words.get(rand.nextInt(words.size()));
    }

    public static boolean validInput(ArrayList<String> userTypedWords)
    {        
        return (userTypedWords.size() == GAME_SIZE) ? true : false;
    }

    public static ArrayList<String> fillUserInputArray(String userTypedWords)
    {
        ArrayList<String> userInput = new ArrayList<String>();
        Scanner scan = new Scanner(userTypedWords);
        while (scan.hasNext())
            userInput.add(scan.next().trim());
        return userInput;
    }
}