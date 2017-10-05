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
		/* Requires 2 comma separated files (CSV) in txt format with no spaces
		 * nodos: id,name
		 * aristas: 
		 */
		File nodos = new File("/Users/Merino/Documents/TareasTec/BD_Avanzadas/prácticas/2.2/nodos.txt");
		File aristas = new File("/Users/Merino/Documents/TareasTec/BD_Avanzadas/prácticas/2.2/aristas.txt");
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
			node0.addVertexOut(node1);
			node1.addVertexIn(node0);
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

		//Page Rank
		System.out.println("\n Page ranks: ");
		ArrayList<Node> nodeVals = new ArrayList<Node>(nodeList.values()); 
		double ranks[] = new double[nodeVals.size()];
		for(int k = 0; k < 9; k++){
			for(int j = 0; j < nodeVals.size(); j++){
				ranks[j] = nodeVals.get(j).calculatePageRank();
			}
		}
		double avrgRank = 0;
		for(int l = 0; l < nodeVals.size(); l++){
			avrgRank += ranks[l];
			System.out.println(nodeVals.get(l).name + " : " + nodeVals.get(l).getPageRank());
		}
		avrgRank = avrgRank / ranks.length;
		System.out.println("\n Rank Average : " + avrgRank);


	}

}
