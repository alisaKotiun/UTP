package myTests;


import mySample.City;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CityTest {

    private static final int POP = 12340;

    private City myCity;

    @Before
    public void before() {
        myCity = new City(POP);
        Assert.assertEquals(POP, myCity.getPopulation());
    }

    @Test
    public void aggregate() {
        int aggregate = myCity.aggregate(null);
        Assert.assertEquals(POP, aggregate);
        final int init = 2345;
        Assert.assertEquals((int) (POP + init), (int) (myCity.aggregate(init)));
    }

    @Test
    public void deepClone() {
        City city_clone = myCity.deepClone();
        Assert.assertEquals(myCity.getPopulation(), city_clone.getPopulation());
        Assert.assertNotSame(myCity, city_clone);
    }
}