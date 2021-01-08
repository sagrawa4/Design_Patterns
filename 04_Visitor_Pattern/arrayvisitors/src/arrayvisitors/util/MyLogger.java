package arrayvisitors.util;

public class MyLogger{

    public static enum DebugLevel { NONE, CONSTRUCTOR, FILE_PROCESSOR, 
            My_ARRAY, MY_ARRAYLIST, RESULT, POPULATE_MYARRAY, COMMON_INTSVISITOR, 
            MISSING_INTSVISITOR, DRIVER
    };

    private static DebugLevel debugLevel;

    public static void setDebugValue (int levelIn) {
	switch (levelIn) {
        case 1: debugLevel = DebugLevel.CONSTRUCTOR; break;
        case 2: debugLevel = DebugLevel.FILE_PROCESSOR; break;
        case 3: debugLevel = DebugLevel.My_ARRAY; break;
        case 4: debugLevel = DebugLevel.MY_ARRAYLIST; break;
        case 5: debugLevel = DebugLevel.RESULT; break;
        case 6: debugLevel = DebugLevel.POPULATE_MYARRAY; break;
        case 8: debugLevel = DebugLevel.COMMON_INTSVISITOR; break;
        case 9: debugLevel = DebugLevel.MISSING_INTSVISITOR; break;
        case 10: debugLevel = DebugLevel.DRIVER; break;
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