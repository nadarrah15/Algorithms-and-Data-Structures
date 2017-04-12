package adjacency.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AdjacencyList {
	
	private static class Edge {
		int dest;
		int weight;
		Edge next;
		int in;
		int out;
		
		Edge(int dest, int weight, Edge next) {
			this.dest = dest;
			this.weight = weight;
			this.next = next;
		}
	}
	
	private Edge[] graph;
	private boolean isDirected;
	//book-keeping for inDegree() and outDegree()
	private int[] in, out;
	
	public AdjacencyList(int N, boolean isDirected) {
		graph = new Edge[N];
		this.isDirected = isDirected;
		in = new int[N];
		out = new int[N];
	}
	
	public void addEdge(int u, int v, int weight) {
		graph[u] = new Edge(v, weight, graph[u]);
		out[u]++;
		in[v]++;
		if (!isDirected){
			graph[v] = new Edge(u, weight, graph[v]);
			out[v]++;
			in[u]++;
		}
	}
	
	public int removeEdge(int u, int v) {
		int w = removeEdgeDirected(u, v);
		if (!isDirected)
			removeEdgeDirected(v, u);
		
		return w;
	}
	
	private int removeEdgeDirected(int u, int v) {
		Edge cur = graph[u];
		
		// empty list
		if (cur == null)
			return -1;
		
		// first node is special
		if (cur.dest == v) {
			int w = cur.weight;
			graph[u] = cur.next;
			return w;
		}
		
		// rest of the list 
		while (cur.next != null) {
			if (cur.next.dest == v)
				break;			
			cur = cur.next;
		}
		
		if (cur.next == null)
			return -1;
		
		//if we've made it this far, the edge exists
		int w = cur.next.weight;
		cur.next = cur.next.next;
		in[v]--;
		out[u]--;
		return w;
	}
	
	public boolean hasEdge(int u, int v) {
		Edge cur = graph[u];
		while (cur != null) {
			if (cur.dest == v)
				return true;
			
			cur = cur.next;
		}
		return false;
	}

	public int getEdgeWeight(int u, int v) {
		Edge cur = graph[u];
		while (cur != null) {
			if (cur.dest == v)
				return cur.weight;
			
			cur = cur.next;
		}
		throw new IllegalArgumentException();
	}
	
	//WILL ONLY WORK ON DAGS
	/** Retruns a collection made by a topological sort algorithm. If the graph is not
	 * 	directed, an empty list will be returned
	 * 
	 * @return ArrayList collection of the numbers in order.
	 */
	public ArrayList<Integer> topSort(){
		if(!isDirected)
			return new ArrayList<Integer>();
		
		ArrayList<Integer> result = new ArrayList<Integer>(size());
		int inDegree[] = new int[size()];
		Queue<Integer> toBeVisited = new LinkedList<Integer>();
		
		for(int i = 0; i < size(); i++){
			inDegree[i] = inDegree(i);
			
			if(inDegree(i) == 0)
				toBeVisited.add(i);
		}
		
		while (!toBeVisited.isEmpty()) {
			int cur = toBeVisited.remove();
			
			result.add(cur);
			
			Edge edge = graph[cur];
			while (edge != null) {
				int neighbor = edge.dest;
				inDegree[neighbor]--;
				
				if(inDegree[neighbor] == 0)
					toBeVisited.add(neighbor);
				
				edge = edge.next;
			}
		}
		
		return result;
	}
	
	public boolean[] depthFirstSearch(int source) {
		boolean reachable[] = new boolean[graph.length];
		
		Stack<Integer> toBeVisited = new Stack<Integer>();
		
		toBeVisited.push(source);
		while (!toBeVisited.isEmpty()) {
			int cur = toBeVisited.pop();
			if (reachable[cur])
				continue;

			reachable[cur] = true;

			// visit all neighbors
			Edge edge = graph[cur];
			while (edge != null) {
				int neighbor = edge.dest;
				if (!reachable[neighbor]) 
					toBeVisited.push(neighbor);
				edge = edge.next;
			}
		}
		
		return reachable;
	}
	
	public boolean[] breadthFirstSearch(int source) {
		boolean reachable[] = new boolean[graph.length];
		
		Queue<Integer> toBeVisited = new LinkedList<Integer>();
		
		toBeVisited.add(source);
		while (!toBeVisited.isEmpty()) {
			int cur = toBeVisited.remove();
			if (reachable[cur])
				continue;

			reachable[cur] = true;

			// visit all neighbors
			Edge edge = graph[cur];
			while (edge != null) {
				int neighbor = edge.dest;
				if (!reachable[neighbor]) 
					toBeVisited.add(neighbor);
				edge = edge.next;
			}
		}
		
		return reachable;
	}
	
	/** Returns the amount of edges whose destination is a specified vertex or -1 if the vertex does not exist
	 * 
	 * @param u vertex
	 * @return count of the in degree
	 */
	public int inDegree(int u){
		if(u < 0 || u >= graph.length)
			return -1;
		
		return in[u];
	}
	
	/** Returns the amount of edges whose source is a specified vertex or -1 if the vertex does not exist
	 * 
	 * @param u vertex
	 * @return count of the out degree
	 */
	public int outDegree(int u){
		if(u < 0 || u >= graph.length)
			return -1;
		
		return out[u];
	}
	
	public int size(){
		return graph.length;
	}
}
