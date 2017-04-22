import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;

public class KMeans {

    private int NUM_CLUSTERS = 8;
    private int NUM = 800;
    double[][] vals;
    public String outP = "";
    Random rand = new Random();
    private List<Line> lines;
    private List<Cluster> clusters;

    //constructor
    public KMeans(double[][] v) {
    	this.lines = new ArrayList();
    	this.clusters = new ArrayList();
      vals = v;
    }

    public String getOUTP() {
        return outP;
    }

    public void init() {
      //create list of points
      lines = Line.createLines(vals);


      //create list of clusters
      //set random centroids
      for (int i = 0; i < NUM_CLUSTERS; i++) {
    		Cluster cluster = new Cluster(i);

        double[] newLine = lines.get(rand.nextInt(NUM)).getVect();

    		Line centroid = new Line(newLine, i+"");
        cluster.setCentroid(centroid);
    		clusters.add(cluster);
    	}
      //print init state
      plotClusters();
    }

    //plot each cluster
    private void plotClusters() {
        outP = "";
      	for (int i = 0; i < NUM_CLUSTERS; i++) {
      		Cluster c = clusters.get(i);
      		outP += c.plotCluster();
      		outP +="/n";
      	}
      }

      //calculate KMeans
      public void calculate() {
          boolean finish = false;
          int iteration = 0;

        //  while(!finish) {

        	   clearClusters();
             List<Line> lastCentroids = getCentroids();
             assignCluster();       //re-assign pts to closer clusters
             calculateCentroids();//caculate new centroids
             iteration++;
             List<Line> currentCentroids = getCentroids();

             //caculate distance b/t new & old centroids
             double distance = 0;
             for(int i = 0; i < lastCentroids.size(); i++) {
             		distance += Line.distance(lastCentroids.get(i),currentCentroids.get(i));
             }
             System.out.println("#################");
             System.out.println("Iteration: " + iteration);
             System.out.println("Centroid distances: " + distance);
             plotClusters();

             if(distance < 0.5) {
             		finish = true;
             }

     }

     private void clearClusters() {
     	for(Cluster cluster : clusters) {
     		cluster.clear();
     	}
     }

     private List getCentroids() {
    	List centroids = new ArrayList(NUM_CLUSTERS);
    	for(Cluster cluster : clusters) {
    		Line aux = cluster.getCentroid();
    		Line line = new Line(aux.getVect(), aux.getIndex());
    		centroids.add(line);
    	}
    	return centroids;
    }

    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max;
        int cluster = 0;
        double distance = 0.0;

        for(Line line : lines) {
        	min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
            	Cluster c = clusters.get(i);
                distance = Line.distance(line, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            line.setCluster(cluster);
            clusters.get(cluster).addLine(line);
        }

    }

    private void calculateCentroids() {
        for(Cluster cluster : clusters) {

            List<Line> list = cluster.getLines();
            int n_lines = list.size();
            double[] sumCor = new double[NUM];
            double[] newVect = new double[NUM];


            for(int j = 0; j < n_lines; j++) {
               Line vect = list.get(j);
               double[] temp = vect.getVect();

            Line centroid = cluster.getCentroid();

            if(n_lines > 0) {

                for (int i = 0; i < NUM; i++) {
                  sumCor[i] = temp[i];
                  newVect[i] = sumCor[i] / n_lines;
                  double number = sumCor[j];
                }

                centroid.setVect(newVect);

            }
          }
        }
    }

}
