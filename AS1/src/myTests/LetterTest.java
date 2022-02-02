package myTests;

import mySample.Letter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LetterTest {
    private static final String ch = "H";

    private Letter myLetter;

    @Before
    public void before() {
        myLetter = new Letter(ch);
        Assert.assertEquals(ch, myLetter.getlChar());
    }

    @Test
    public void aggregate() {
        String aggregate = myLetter.aggregate(null);
        Assert.assertEquals(ch, aggregate);
        final String st = "e";
        Assert.assertEquals(ch + st, myLetter.aggregate(st)); //casting is redundant
    }

    @Test
    public void deepClone() {
        Letter letter_clone = myLetter.deepClone();
        Assert.assertEquals(myLetter.getlChar(), letter_clone.getlChar());
        Assert.assertNotSame(myLetter, letter_clone);
    }
}
