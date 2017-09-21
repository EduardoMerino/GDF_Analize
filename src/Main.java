import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		System.out.println("GDF Aanalyze");
		//open files
		/* Requires 2 comma separated files (CSV) in txt format with no spaces.
		 * nodos: id,name
		 * aristas: origin id,destination id 
		 */
		File nodos = new File("full_path/nodos.txt");
		File aristas = new File("full_path/aristas.txt");
		System.out.println("Opening Files...");
		
		//read files
		System.out.println("Reading Files...");
		HashMap<String, Node> nodeList = new HashMap<String, Node>();
		
		//nodes
		Scanner scanNodes = new Scanner(nodos);
		String nodesLine;		
		//int a = 1;
		String[] splt;
		while(scanNodes.hasNextLine()){
			nodesLine = scanNodes.nextLine();
			splt = nodesLine.split(",");
			nodeList.put(splt[0], new Node(splt[0], splt[1]));
			//System.out.println(" " + a + " ");
			//a++;
		}
		scanNodes.close();
		
		//vertices
		Scanner scanVertex = new Scanner(aristas);
		String vertexLine;
		String[] splt2;
		while(scanVertex.hasNextLine()){
			vertexLine = scanVertex.nextLine();
			splt2 = vertexLine.split(",");
			Node node0 = nodeList.get(splt2[0]);
			Node node1 = nodeList.get(splt2[1]);
			node0.addNeighbour(node1);
		}
		scanVertex.close();
		
		System.out.println("Finished Reading");
		
		//search paths
		System.out.println("Searching paths...");
		Pathfinding path = new Pathfinding();
		Node start = nodeList.get("159872204031684"); //node id
		Node end = nodeList.get("1575396189376005"); //node id
		
		System.out.println("\n Breathwise search");
		ArrayList<Node> breathwisePath = path.BreadthwiseSearch(start, end);
		for(int i = 0; i < breathwisePath.size(); i++){
			System.out.println(breathwisePath.get(i));
		}
		
		System.out.println("\n Depthwise search");
		ArrayList<Node> depthPath = path.DepthwiseSearch(start, end);
		for(int i = 0; i < depthPath.size(); i++){
			System.out.println(depthPath.get(i));
		}
		
		
	}

}
