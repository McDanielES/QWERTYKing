import java.io.FileReader;
import java.util.*;
/**
 * <p>This is the main class of the QWERTYKing Typing Game. This program is a simple
 * command line Java typing tutor and game for improving personal typing skills.</p>
 * </p>
 *
 * @author Eric McDaniel
 * @version 1.1
 */
public class QWERTYKing
{
    public static final int      DEFAULT_SIZE    = 10;
    public static final boolean  DEFAULT_CAPS    = false;
    public static final String   FLAG_FILE       = "-f";
    public static final String   FLAG_DIFFICULTY = "-d";
    public static final String   FLAG_SIZE       = "-s";
    public static final String   FLAG_CAPS       = "-C";
    public static final String[] GAME_FILES      = { "100-Words.txt",
                                                     "1000-Words.txt",
                                                     "3000-Words.txt" };

    public static void main(String[] args)
    {
        try
        {
            Words gameWords = parseCommandLineArgs(args);
            System.out.printf("Welcome to the QWERTYKing game!\n%sType quit to end the game at any time.\nDuplicate "
                            + "the following words.\n\n", (gameWords.getSize() == DEFAULT_SIZE) ? String.format("This " + 
                              "game session will run the %s-word default.\n", DEFAULT_SIZE) : "\n");

            // Game loop logic
            do
            {
                gameWords.fillAndPrintRandomWords();
                gameWords.getUserEntry();

                if (gameWords.validInput() && gameWords.continueGame())
                    gameWords.printAnyErrors();
                else if (gameWords.continueGame())
                    System.err.printf("You did not type the same number of words as provided. "
                                    + "There should be %d words.\n\n", gameWords.getSize());
                gameWords.clear();
            } while (gameWords.continueGame()); // End game loop
        }
        catch (java.io.FileNotFoundException ex)
        {
            System.err.println("Error locating file. Terminating.");
            System.exit(1);
        }
        catch (NumberFormatException ex)
        {
            System.err.println("Error parsing argument(s). Terminating");
            System.exit(2);
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            System.err.println("Invalid difficulty value. Please select a difficulty "
                            + "level between 1 to 3. Terminating");
            System.exit(3);
        }
        catch (IllegalArgumentException ex)
        {
            System.err.println("Error parsing argument(s). Terminating");
            System.exit(4);
        }
    } // End main()

    public static Words parseCommandLineArgs(String[] args) throws java.io.FileNotFoundException
    {
        List<String> argsList = Arrays.asList(args);
        if (argsList.contains(FLAG_FILE) && argsList.contains(FLAG_DIFFICULTY))
            throw new IllegalArgumentException();

        String customFile = (argsList.contains(FLAG_FILE)) ?
                                    (argsList.get(argsList.indexOf(FLAG_FILE) + 1)) : GAME_FILES[GAME_FILES.length - 1];
        int    customSize = (argsList.contains(FLAG_SIZE)) ? 
                                    (Integer.parseInt(argsList.get(argsList.indexOf(FLAG_SIZE) + 1))) : DEFAULT_SIZE;
        
        customFile = (argsList.contains(FLAG_DIFFICULTY)) ?
                                    (GAME_FILES[Integer.parseInt(argsList.get(argsList.indexOf(FLAG_DIFFICULTY) + 1)) - 1]) : GAME_FILES[GAME_FILES.length - 1];
        if (customSize < 1)
            throw new IllegalArgumentException();

        boolean customCaps = (argsList.contains(FLAG_CAPS)) ? !DEFAULT_CAPS : DEFAULT_CAPS;

        return new Words(new FileReader(customFile), customSize, customCaps);
    }
} // End class QWERTYKing
