package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1Test {
    private static final List<String> listOne = Arrays.asList("Jack", "Tom", "Sam", "John", "James", "Jack");
    private static final List<String> listTwo = Arrays.asList("Jack", "Daniel", "Sam", "Alan", "James", "George");
    @Test
    void FourIsEven(){
        List<String> differences = new ArrayList<>(listOne);
        differences.removeAll(listTwo);
        System.out.println(Arrays.toString(differences.toArray()));
        Assert.assertTrue(differences.size()==2);
    }

    @Test
    void FiveIsOdd(){
        List<String> differences = new ArrayList<>(listTwo);
        differences.removeAll(listOne);
        System.out.println(Arrays.toString(differences.toArray()));
        Assert.assertTrue(differences.size()==3);
    }
}
