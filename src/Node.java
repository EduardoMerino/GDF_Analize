import java.util.ArrayList;

class Node {
	
	public String id;
	public String name;
	public ArrayList<Node> vertexOut = new ArrayList<Node>();
	public ArrayList<Node> vertexIn = new ArrayList<Node>();
	public ArrayList<Node> history;
	int level;
	private double pageRank = 0;
	private static double d  = 0.85;
	
	/* Constructor
	 * Param: 
	 * 	id : unique String that identifies this node.
	 * 	name : name that represents this node
	 */
	public Node(String id, String name){
		this.id = id;
		this.name = name;
	}
	
	public void addVertexOut(Node neighbour){
		this.vertexOut.add(neighbour);
	}
	
	public void addVertexIn(Node neighbour){
		this.vertexIn.add(neighbour);
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public String toString(){
		return this.level + " : " + this.name;
	}
	
	public double getPageRank(){
		return this.pageRank;
	}
	
	public double calculatePageRank(){
		double sum = 0;
		for(int i = 0; i<this.vertexIn.size(); i++){
			sum += (this.vertexIn.get(i).getPageRank()) / (this.vertexIn.get(i).vertexOut.size());
		}
		this.pageRank = (1 - Node.d) + (Node.d * sum);
		return this.pageRank;
	}

}
