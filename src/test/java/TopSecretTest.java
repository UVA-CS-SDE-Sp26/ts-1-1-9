import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//using a testing variable to check that my code follows logical sense and enters the correct if/else if/catch statements
//checked off/confirmed by TA Callie for comprehensiveness and correct logic
class TopSecretTest {

    //Reset state before each test so tests don't affect each other.
    @BeforeEach
    void resetTesting(){
        // Easiest reset is just run a valid call that sets it deterministically.
        // (No args sets testing = 0)
        new TopSecret().setTesting(-1);
    }

    //check that if there is no arguments that it enters the correct if statement and updates the testing variable to 0
    @Test
    void noArg(){
        TopSecret.main(new String[] {});
        //make sure the testing variable = 0
        assertEquals(0, new TopSecret().getTesting());
    }

    //check that if there is one argument that it enters the correct else if statement and updates the testing variable to 1
    @Test
    void oneArg(){
        TopSecret.main(new String[] {"01"});
        //make sure the testing variable = 1
        assertEquals(1, new TopSecret().getTesting());
    }

    //check that if there is one argument that it enters the correct else if statement and updates the testing variable to 1
    @Test
    void oneArgTwo(){
        TopSecret.main(new String[] {"03"});
        //make sure the testing variable = 1
        assertEquals(1, new TopSecret().getTesting());
    }

    //check that if there is two arguments that it enters the correct else if statement and updates the testing variable to 2
    @Test
    void twoArg(){
        TopSecret.main(new String[] {"01", "mycipher.txt"});
        //make sure the testing variable = 2
        assertEquals(2, new TopSecret().getTesting());
    }

    //too many arguments (>2) -> validation throws -> catch block -> testing = 3
    @Test
    void tooManyArgs(){
        TopSecret.main(new String[] {"01", "02", "mycipher.txt"});
        //make sure the testing variable = 3
        assertEquals(3, new TopSecret().getTesting());
    }

    //invalid file number (not two digits) -> validation throws -> catch block -> testing = 3
    @Test
    void invalidFileNumber() {
        TopSecret.main(new String[]{"1"});
        assertEquals(3, new TopSecret().getTesting());
    }

    //blank key -> validation throws -> catch block -> testing = 3
    @Test
    void blankKey() {
        TopSecret.main(new String[]{"01", "   "});
        assertEquals(3, new TopSecret().getTesting());
    }

    @Test
    //makes sure validateArgs throws an exception when more than 2 arguments are provided
    void throwsOnTooManyArgs() {
        assertThrows(IllegalArgumentException.class,
                () -> TopSecret.validateArgs(new String[]{"01", "key", "extra"}));
    }

    @Test
    //makes sure validateArgs rejects a file number that does not match the correct two digits format
    void throwsOnInvalidFileNumber() {
        assertThrows(IllegalArgumentException.class,
                () -> TopSecret.validateArgs(new String[]{"AA"}));
    }

    //makes sure validateArgs rejects a second argument that is empty or blank
    @Test
    void throwsOnBlankKey() {
        assertThrows(IllegalArgumentException.class,
                () -> TopSecret.validateArgs(new String[]{"01", ""}));
    }

}
