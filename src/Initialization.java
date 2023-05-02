import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Initialization {

    String AlgorithmName = "";
    Boolean Clockwise = false;
    Boolean Time = false;
    Boolean Open = false;
    int SizeMatrix = 0;
    Node StartPoint;
    Node EndPoint;
    Character[][] matrix = new Character[10][10];

    public Initialization(String FileName){
        try {
            File file = new File("C:\\project\\SearchAlgoritems\\src\\input.txt");
            Scanner scanner = new Scanner(file);
            AlgorithmName = scanner.nextLine();
            Clockwise = (scanner.nextLine().contains("counter"))? false : true;
            Time = (scanner.nextLine().contains("no"))? false : true;
            Open = (scanner.nextLine().contains("no"))? false : true;
            SizeMatrix = Integer.parseInt(scanner.nextLine());
            //setting the start and end point
            String points = scanner.nextLine().replaceAll(",", "").replaceAll("\\(", "").replaceAll("\\)", "");
            System.out.println(points);

            int startX = Character.getNumericValue(points.charAt(0));
            int startY = Character.getNumericValue(points.charAt(1));
            int endX = Character.getNumericValue(points.charAt(2));
            int endY = Character.getNumericValue(points.charAt(3));

            StartPoint = new Node(startX, startY, null,"",0);
            EndPoint = new Node(endX, endY, null,"",0);
            System.out.println(StartPoint.getX() + ":"+ StartPoint.getY());
            System.out.println(EndPoint.getX() + ":"+ EndPoint.getY());


            //setting the Matrix
            matrix = new Character[SizeMatrix][SizeMatrix];
            int countLinesMatrix = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    matrix[countLinesMatrix][i]= line.charAt(i);
                }
                countLinesMatrix++;
            }
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    System.out.print(matrix[i][j]+",");
                }
                System.out.println("\n");
            }
            System.out.println(AlgorithmName);
            System.out.println(Clockwise);
            System.out.println(Time);
            System.out.println(Open);
            System.out.println(SizeMatrix);
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
    public String getAlgorithmName() {
        return AlgorithmName;
    }

    public Boolean getClockwise() {
        return Clockwise;
    }

    public Boolean getTime() {
        return Time;
    }

    public Boolean getOpen() {
        return Open;
    }

    public int getSizeMatrix() {
        return SizeMatrix;
    }

    public Node getStartPoint() {
        return StartPoint;
    }

    public Node getEndPoint() {
        return EndPoint;
    }

    public Character[][] getMatrix() {
        return matrix;
    }


}
