package studentskills.util;

public class MyLogger{

    public static enum DebugLevel { NONE, CONSTRUCTOR, FILE_PROCESSOR, 
            INPUT_PARSER, TREE_HELPER, STUDENT_RECORD, BST, DRIVER
    };

    private static DebugLevel debugLevel;

    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
        case 1: debugLevel = DebugLevel.FILE_PROCESSOR; break;
        case 2: debugLevel = DebugLevel.CONSTRUCTOR; break;
        case 3: debugLevel = DebugLevel.INPUT_PARSER; break;
        case 4: debugLevel = DebugLevel.TREE_HELPER; break;
        case 5: debugLevel = DebugLevel.STUDENT_RECORD; break;
        case 6: debugLevel = DebugLevel.BST; break;
        case 7: debugLevel = DebugLevel.DRIVER; break;
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