package adjancency.matrix;

public class AdjancencyMatrix {
	private int graph[][];
	private boolean isDirected;
	
	public AdjancencyMatrix(int N, boolean isDirected) {
		graph = new int[N][N];
		this.isDirected = isDirected;
	}
	
	public void addEdge(int u, int v, int weight) {
		if (u < 0 || v < 0 || u >= graph.length || v >= graph.length)
			throw new IllegalArgumentException();
		
		graph[u][v] = weight;
		if (!isDirected)
			graph[v][u] = weight;
	}
	
	public boolean hasEdge(int u, int v) {
		return graph[u][v] != 0;			
	}
	
	public int removeEdge(int u, int v){
		if(!hasEdge(u, v))
			return -1;
		
		int result = graph[u][v];
		graph[u][v] = 0;
		if(!isDirected)
			graph[v][u] = 0;
		
		return result;
	}
	
	public int size(){
		return graph.length;
	}
}
