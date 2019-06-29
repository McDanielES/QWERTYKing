import java.io.FileReader;
import java.util.*;
/**
 * <p>This is the main class of the QWERTYKing Typing Game. This program is a simple
 * command line Java typing tutor and game for improving personal typing skills.</p>
 * </p>
 *
 * @author Eric McDaniel
 * @version 2.0
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
            Words gameWords = parseCommandLineArgs(Arrays.asList(args));
            System.out.printf("Welcome to the QWERTYKing game!\nType quit to end the game at any time.\n%s\nDuplicate "
                            + "the following words.\n\n", gameDetails(gameWords));

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

    public static Words parseCommandLineArgs(List<String> args) throws java.io.FileNotFoundException
    {
        if (args.contains(FLAG_FILE) && args.contains(FLAG_DIFFICULTY))
            throw new IllegalArgumentException();

        boolean caps = (args.contains(FLAG_CAPS)) ? !DEFAULT_CAPS : DEFAULT_CAPS;
        String  file = (args.contains(FLAG_FILE)) ?
                           (args.get(args.indexOf(FLAG_FILE) + 1)) : GAME_FILES[GAME_FILES.length - 1];
        int     size = (args.contains(FLAG_SIZE)) ? 
                           (Integer.parseInt(args.get(args.indexOf(FLAG_SIZE) + 1))) : DEFAULT_SIZE;
        
        if (args.contains(FLAG_DIFFICULTY))
            file = (GAME_FILES[Integer.parseInt(args.get(args.indexOf(FLAG_DIFFICULTY) + 1)) - 1]);
        if (size < 1)
            throw new IllegalArgumentException();

        return new Words(new FileReader(file), size, caps);
    }

    public static String gameDetails(Words game)
    {
        String text = (game.getSize() == DEFAULT_SIZE) ?
                          String.format("This session will challenge you with the %s-word default.\n", DEFAULT_SIZE) :
                          String.format("This session will challenge you with a custom number of %,d words\n", game.getSize());
        text += String.format("This session will select from a dictionary containing %,d words.\n", game.getDictionarySize());
        if (game.randomCaps())
            text += "Random Capitalization mode activated.\n";
        return text;
    }
} // End class QWERTYKing
