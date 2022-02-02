package myTests;

import mySample.Container;
import mySample.Letter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class ContainerTest{
    private Container <Letter, String> myContainer;
    private Letter l1, l2, l3;
    private ArrayList<Letter> list;

    @Before
    public void before() {
        l1 = new Letter("W");
        l2 = new Letter("o");
        l3 = new Letter("w");

        list = new ArrayList<>();
        myContainer = new Container<>();

        list.add(l1); list.add(l2); list.add(l3);

        myContainer.addEl(l1); myContainer.addEl(l2); myContainer.addEl(l3);

        Assert.assertEquals(l1, myContainer.getElement(0));
        Assert.assertEquals(l2, myContainer.getElement(1));
        Assert.assertNotEquals(l3, myContainer.getElement(1));
    }

    @Test
    public void elements(){
        List<Letter> elements = myContainer.elements();
        Assert.assertEquals(list, elements);
    }

    @Test
    public void aggregateAllElements(){
        String agg = myContainer.aggregateAllElements();
        String let = l1.aggregate(l2.aggregate(l3.aggregate(null)));
        Assert.assertEquals(let, agg);
    }

    @Test
    public void cloneElementAtIndex(){
        Letter clone = myContainer.cloneElementAtIndex(1);
        Letter orig = myContainer.getElement(1); // =l2
        Assert.assertEquals(orig.getlChar(), clone.getlChar());
        Assert.assertNotSame(orig, clone);

        Letter notExistingClone = myContainer.cloneElementAtIndex(-1);
        Letter notExistingOrig = myContainer.getElement(-1);
        Assert.assertSame(notExistingOrig,notExistingClone);
    }
}
