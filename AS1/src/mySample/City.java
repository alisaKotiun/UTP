package mySample;

import myInterface.IAggregable;
import myInterface.IDeeplyCloneable;

public class City implements IAggregable<City, Integer>, IDeeplyCloneable<City> {

    private int population;

    public City() {}

    public City(int p){
        population = p;
    }

    public int getPopulation(){
        return population;
    }

    @Override
    public Integer aggregate(Integer intermediateResult) {
        if  (intermediateResult == null) {
            return population;
        }
        return population + intermediateResult; //sum for all cities
    }

    @Override
    public City deepClone() {
        City clone = new City();
        clone.population = population;
        return clone;
    }
}