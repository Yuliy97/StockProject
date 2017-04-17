import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
/**
 * Created by Yuli on 4/15/17.
 */

public class Read {
    public static Double[] expectedValue;
    public static Double[] variance;
    public static double[][] covariance;
    public static double[][] correlation;
    public static void main(String[] args) {
        try {
            File dailyReturn = new File("/Users/Yuli/Desktop/StockProject/src/DailyReturn.csv");
            Scanner scan = new Scanner(dailyReturn);
            expectedValue = new Double[800];
            variance = new Double[800];
            covariance = new double[800][800];
            correlation = new double[800][800];
            String[] lines = new String[800];
            int index = 0;
            while (scan.hasNextLine()) {
                lines[index] = scan.nextLine();
                index++;
            }
            scan.close();
            getExpectedValue(lines);
            System.out.println("Test ExpectedValue");
            for (int i = 0; i < 10; i++) {
                System.out.println(expectedValue[i]);
            }
            getVariance(lines);
            System.out.println("\nTest Variance");
            for (int i = 0; i < 10; i++) {
                System.out.println(variance[i]);
            }
            getCovariance(lines);
            System.out.println("\nTest covariance");
            for (int i = 0; i <10; i++) {
                System.out.println(covariance[0][i]);
            }
            getCorrelation(lines);
            System.out.println("\nTest correlation");
            for (int i = 0; i < 10; i++) {
                System.out.println(correlation[0][i]);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
        }
        try {
            FileWriter outputCov = new FileWriter("OutputCov");
            for (int x = 0; x < covariance.length; x++) {
                for (int y = 0; y < 800; y++) {
                    outputCov.append(Double.toString(covariance[x][y]));
                    outputCov.append(",");
                }
                outputCov.append("\n");
            }

        } catch (IOException e) {
            System.out.println("Exception");
        }
        try {
            FileWriter output = new FileWriter("Output");
            for (int x = 0; x < correlation.length; x++) {
                for (int y = 0; y < correlation[x].length; y++) {
                    output.append(Double.toString(correlation[x][y]));
                    output.append(",");
                }
                output.append("\n");
            }

        } catch (IOException e) {
            System.out.println("Exception");
        }
    }

    public static void getExpectedValue(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(",");
            double sum = 0.0;
            for (int j = 1; j < line.length; j++) {
                sum += Double.parseDouble(line[j]);
            }
            expectedValue[i] = sum / (line.length - 1);
        }

    }
    public static void getVariance(String[] lines) {
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i].split(",");
            double sum = 0.0;
            for (int j = 1; j < line.length; j++) {
                sum += Math.pow(Double.parseDouble(line[j]), 2.0);
            }
            variance[i] = (sum / (line.length -1)) - Math.pow(expectedValue[i], 2.0);
        }

    }
    public static void getCovariance(String[] lines) {
        for (int x = 0; x < lines.length; x++) {
            for (int y = x; y < lines.length; y++) {
                if (y == x) {
                    covariance[x][y] = variance[x];
                } else {

                    String[] xLine = lines[x].split(",");
                    String[] yLine = lines[y].split(",");
                    double sum = 0.0;
                    for (int i = 1; i < xLine.length; i++) {
                        sum += (Double.parseDouble(xLine[i]) * Double.parseDouble(yLine[i]));
                    }
                    double eXY = sum / (xLine.length - 1);
                    covariance[x][y] = eXY - (expectedValue[x] * expectedValue[y]);
                    covariance[y][x] = covariance[x][y];
                }
            }
        }

    }
    public static void getCorrelation(String[] lines) {
        for (int x = 0; x < lines.length; x++) {
           for (int y = x; y < lines.length; y++) {
               if (y == x) {
                   correlation[x][y] = 1;
               } else {
                   correlation[x][y] = covariance[x][y] / (Math.sqrt(variance[x]) * Math.sqrt(variance[y]));
                   correlation[y][x] = correlation[x][y];
               }
           }
        }
    }



}

