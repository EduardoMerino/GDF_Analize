import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class Pathfinding {
	/* Constructor
	 * 
	 */
	public Pathfinding(){
		
	}
	
	public ArrayList<Node> BreadthwiseSearch(Node start, Node end){

		LinkedBlockingQueue<Node> working = new LinkedBlockingQueue<Node> ();
		HashMap<String, Node> visited = new HashMap<String, Node> ();

		start.history = new ArrayList<Node> ();
		working.add (start);
		visited.put(start.id, start);
		int level = 0;

		while(working.size() > 0){

			Node currentNode = working.remove();
			currentNode.setLevel(level);
			level++;
			
			if (currentNode == end) {

				// this is the end!
				ArrayList<Node> result = currentNode.history;
				result.add(currentNode);
				return result;

			} else {

				for (int i = 0; i < currentNode.neighbours.size(); i++) {

					Node currentChild = currentNode.neighbours.get(i);
					// check if it hasn't been visited
					if(!visited.containsKey(currentChild.id)){

						// update history
						currentChild.history = new ArrayList<Node>(currentNode.history);
						currentChild.history.add(currentNode);

						// add to queue
						working.add(currentChild);

						// add to visited
						visited.put(currentChild.id, currentChild);

					}
				}
			}
		}

		return null;
	}
	
	public ArrayList<Node> DepthwiseSearch(Node start, Node end){

		ArrayList<Node> visited = new ArrayList<Node> ();
		Stack<Node> stack = new Stack<Node> ();

		start.history = new ArrayList<Node> ();
		visited.add(start);
		stack.push(start);
		int level = 0;

		while (stack.size() > 0) {
			Node current = stack.pop();
			current.setLevel(level);
			level++;

			if (current == end) {
				//find final node
				ArrayList<Node> result=current.history;
				result.add(current);
				return result;

			}else{
				//not the final node

				for (int i = 0; i < current.neighbours.size(); i++) {
					Node currentNeighbor = current.neighbours.get(i);

					if (!visited.contains(currentNeighbor)) {
						visited.add(currentNeighbor);
						stack.push(currentNeighbor);

						currentNeighbor.history = new ArrayList<Node> (current.history);
						currentNeighbor.history.add (current);
					}
				}
			}
		}

		return null;
	}

}
