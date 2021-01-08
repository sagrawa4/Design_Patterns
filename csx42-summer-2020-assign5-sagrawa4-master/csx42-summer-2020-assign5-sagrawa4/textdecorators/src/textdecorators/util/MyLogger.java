package textdecorators.util;

/*
 * @author Shruti Agrawal
 */

public class MyLogger{

    public static enum DebugLevel { NONE, CONSTRUCTOR, FILE_PROCESSOR, 
            INPUT_DETAIL, KEYWORD_DECORATOR, MOSTFREQUENT_DECORATOR,SENTENCE_DECORATOR,SPELLCHECK_DECORATOR, DRIVER
    };

    private static DebugLevel debugLevel;

    /*
    DEBUG_VALUE=8 [Prints value from DRIVER class]
    DEBUG_VALUE=7 [Prints value from SPELLCHECK_DECORATORR class]
    DEBUG_VALUE=6 [Prints value from SENTENCE_DECORATOR class]
    DEBUG_VALUE=5 [Prints value from MOSTFREQUENT_DECORATOR class]
    DEBUG_VALUE=4 [Print value from KEYWORD_DECORATOR class]
    DEBUG_VALUE=3 [Print value from INPUT_DETAIL class]
    DEBUG_VALUE=2 [Print value from FILE_PROCESSOR class]
    DEBUG_VALUE=1 [Print value from CONSTRUCTOR class]*/

    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
        case 1: debugLevel = DebugLevel.CONSTRUCTOR; break;
        case 2: debugLevel = DebugLevel.FILE_PROCESSOR; break;
        case 3: debugLevel = DebugLevel.INPUT_DETAIL; break;
        case 4: debugLevel = DebugLevel.KEYWORD_DECORATOR; break;
        case 5: debugLevel = DebugLevel.MOSTFREQUENT_DECORATOR; break;
        case 6: debugLevel = DebugLevel.SENTENCE_DECORATOR; break;
        case 7: debugLevel = DebugLevel.SPELLCHECK_DECORATOR; break;
        case 8: debugLevel = DebugLevel.DRIVER; break;
        default: debugLevel = DebugLevel.NONE; break;
        }
    }

    public static void setDebugValue (DebugLevel levelIn) {
	    debugLevel = levelIn;
    }

    public static void writeMessage (String     message  ,
                                     DebugLevel levelIn ) {
	if (levelIn == debugLevel)
	    System.out.println(message);
    }

    public String toString() {
	    return "The debug level has been set to the following " + debugLevel;
    }
}