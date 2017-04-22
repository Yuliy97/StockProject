import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Line {

    public double[] vect;
    private int cluster_num = 0;
    public String index;

    public Line(double[] v, String ind) {
        vect = v;
        index = ind;
    }

    public String getIndex() {
        return index;
    }

    public double[] getVect() {
        return vect;
    }
    public void setVect(double[] v) {
        vect = v;
    }
    public int getCluster() {
        return cluster_num;
    }
    public void setCluster(int x) {
        cluster_num = x;
    }


    public static List createLines(double[][] x) {
        List lines = new ArrayList();
                for (int j = 0; j < x.length; j++) {
                    lines.add(new Line(x[j], j+""));
                }
        return lines;
    }

    public static double distance(Line p, Line centroid) {
        double[] a = p.getVect();
        double[] b = centroid.getVect();
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += Math.pow((a[i] - b[i]), 2);
        }
        return Math.sqrt(sum);
    }

    public String toString() {
        return index;
    }

}
