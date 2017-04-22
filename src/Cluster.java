import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.IOException;
import java.io.FileWriter;
import java.util.Arrays;

public class Cluster {

	public List<Line> lines;
	public Line centroid;
	public int id;

	//constructor
	public Cluster(int id) {
		this.id = id;
		this.lines = new ArrayList();
		this.centroid = null;
	}

	//getters & setters
	public List getLines() {
		return lines;
	}
	public void addLine(Line line) {
		lines.add(line);
	}
	public void setLines(List lines) {
		this.lines = lines;
	}
	public Line getCentroid() {
		return centroid;
	}
	public void setCentroid(Line centroid) {
		this.centroid = centroid;
	}
	public int getId() {
		return id;
	}
	public void clear() {
		lines.clear();
	}

	public String plotCluster() {

			double[] v = centroid.vect;
			System.out.println("[Cluster: " + id+"]");
			System.out.println("[Centroid: " + Arrays.toString(v) + "]");
			System.out.println("[Points: \n");

			String s = "";
			for (Line p : lines) {
					s += p.getIndex() + ",";
			}
			s += "\n";

		 	System.out.println("]");
			return s;
	}
	
}
