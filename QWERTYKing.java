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
    public static final int     GAME_SIZE    =  10;
    public static final String  DEFAULT_FILE = "3000-Words.txt";
    public static final boolean DEFAULT_CAPS =  false;

    public static void main(String[] args)
    {
        try
        {
            System.out.printf("Type quit to end the game at any time.\nDuplicate the following words.\n\n");

            List<String> argsList     =  Arrays.asList(args);
            String       customFile   = (argsList.size() > 1 && argsList.contains("-f")) ? (argsList.get(argsList.indexOf("-f") + 1)) : DEFAULT_FILE;
            int          customSize   = (argsList.size() > 1 && argsList.contains("-s")) ? (Integer.parseInt(argsList.get(argsList.indexOf("-s") + 1))) : GAME_SIZE;
            boolean      randomCaps   = (argsList.contains("-C")) ? !DEFAULT_CAPS : DEFAULT_CAPS;
            boolean      continueGame = true;
            FileReader   wordsFile    = new FileReader(customFile);
            Words        gameWords    = new Words(wordsFile,
                                                 (customSize > 0) ? customSize : GAME_SIZE, // Discard negative integers to default size
                                                  randomCaps);

            // Game loop logic
            while (continueGame)
            {   // The statement resolves a weird bug where the object prints things
                // in improper order. I'm not sure why, however this solution works.
                try { Thread.sleep(5); } catch (InterruptedException ex) {}

                gameWords.fillAndPrintRandomWords();
                gameWords.getUserEntry();

                if (gameWords.validInput() && !gameWords.quitGame())
                    gameWords.printAnyErrors();
                else if (gameWords.quitGame())
                    continueGame = false;
                else
                    System.err.printf("You did not type the same number of words as provided. "
                                    + "There should be %d words.\n\n", customSize);

                gameWords.clear();
            } // End game loop
        }
        catch (java.io.FileNotFoundException ex)
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
} // End class QWERTYKing
