package graphs;

import java.util.Queue;
import java.util.Stack;

public class ALGraph {
	private static class Edge {
		int dest;
		int weight;
		Edge next;
		
		Edge(int dest, int weight, Edge next) {
			this.dest = dest;
			this.weight = weight;
			this.next = next;
		}
	}
	
	private Edge[] graph;
	private boolean isDirected;
	
	public ALGraph(int N, boolean isDirected) {
		graph = new Edge[N];
		this.isDirected = isDirected;
	}
	
	public void addEdge(int u, int v, int weight) {
		graph[u] = new Edge(v, weight, graph[u]);
		if (!isDirected)
			graph[v] = new Edge(u, weight, graph[v]);
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
		
		int w = cur.next.weight;
		cur.next = cur.next.next;
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
	
	public boolean[] reachable(int source) {
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
	
}
