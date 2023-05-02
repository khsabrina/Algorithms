import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Initialization Init = new Initialization("bla");
        Algorithms algo = new Algorithms(Init);
        long startTime = System.currentTimeMillis();
        System.out.println("*************************************");

        System.out.println(algo.BFS(Init.StartPoint,Init.EndPoint));
        System.out.println("*************************************");

        System.out.println(algo.DFID(Init.StartPoint,Init.EndPoint));
        System.out.println("*************************************");

        System.out.println(algo.Astar(Init.StartPoint,Init.EndPoint));
        System.out.println("*************************************");

        System.out.println(algo.IDAStar(Init.StartPoint,Init.EndPoint));
        System.out.println("*************************************");
        System.out.println(algo.DFDnB(Init.StartPoint,Init.EndPoint));


        long endTime   = System.currentTimeMillis();
        float totalTime = (endTime - startTime) / 1000F;
        System.out.println(totalTime);

    }
}