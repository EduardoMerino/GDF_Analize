import java.util.ArrayList;

class Node {
	
	public String id;
	public String name;
	public ArrayList<Node> neighbours = new ArrayList<Node>();
	public ArrayList<Node> history;
	int level;
	
	/* Constructor
	 * Param: 
	 * 	id : unique String that identifies this node.
	 * 	name : name that represents this node
	 */
	public Node(String id, String name){
		this.id = id;
		this.name = name;
	}
	
	public void addNeighbour(Node neighbour){
		this.neighbours.add(neighbour);
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public String toString(){
		return this.level + " : " + this.name;
	}

}
