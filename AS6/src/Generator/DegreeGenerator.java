package Generator;

import Person.Degree;

import java.util.Random;

public class DegreeGenerator {

    public static Degree generateDegree(){
        Random random = new Random();
        Degree[] degrees = Degree.values();
        int i = random.nextInt(degrees.length);
        return degrees[i];

    }
}
