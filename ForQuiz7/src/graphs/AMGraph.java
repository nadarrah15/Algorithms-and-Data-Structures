package graphs;

public class AMGraph {
	private int graph[][];
	private boolean isDirected;
	private int inDegree[], outDegree[];
	
	public AMGraph(int N, boolean isDirected) {
		graph = new int[N][N];
		this.isDirected = isDirected;
		inDegree = new int[N];
		outDegree = new int[N];
	}
	
	public AMGraph floydWarshalls(){
		AMGraph result = new AMGraph(graph.length, isDirected);
		
		//clone the graph
		result.graph = graph.clone();
		
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph.length; j++){
				for(int k = 0; k < graph.length; k++){
					if(hasEdge(i, j) && hasEdge(j, k) && (!hasEdge(i, k) || (hasEdge(i, k) && getEdge(i, j) + getEdge(j, k) < getEdge(i, k)))){
							addEdge(i, k, getEdge(i, j) + getEdge(j, k));	
					}
				}
			}
		}
		
		return result;
	}
	
	public AMGraph warshalls(){
		AMGraph result = new AMGraph(graph.length, isDirected);
		
		//clone the graph
		result.graph = graph.clone();
		
		for(int i = 0; i < graph.length; i++){
			for(int j = 0; j < graph.length; j++){
				for(int k = 0; k < graph.length; k++){
					if(hasEdge(i, j) && hasEdge(j, k)){
						addEdge(i, k, getEdge(i, j) + getEdge(j, k));
					}
				}
			}
		}
		
		return result;
	}
	
	/** Adds an edge between two vertices
	 * 
	 * @param u first vertex
	 * @param v second vertex
	 * @param weight weight of the edge
	 */
	public void addEdge(int u, int v, int weight) {
		if (u < 0 || v < 0 || u >= graph.length || v >= graph.length)
			throw new IllegalArgumentException();
		
		graph[u][v] = weight;
		outDegree[u]++;
		inDegree[v]++;
		if (!isDirected){
			graph[v][u] = weight;
			outDegree[v]++;
			inDegree[u]++;
		}
	}
	
	/** Method to see if there exists an edge between vertices u and v
	 * 
	 * @param u first vertex
	 * @param v second vertex
	 * @return boolean
	 */
	public boolean hasEdge(int u, int v) {
		if (u < 0 || v < 0 || u >= graph.length || v >= graph.length)
			throw new IllegalArgumentException();
		
		return graph[u][v] != 0;			
	}
	
	/** Removes an edge between vertices u and v in our representation of a graph. If an edge
	 * 	does not exist between vertices u and v, then we return 0
	 * 
	 * @param u first vertex
	 * @param v second vertex
	 * @return weight of the edge
	 */
	public int removeEdge(int u, int v){
		if (u < 0 || v < 0 || u >= graph.length || v >= graph.length)
			throw new IllegalArgumentException();
		
		if(!hasEdge(u, v))
			return 0;
		
		int result = graph[u][v];
		graph[u][v] = 0;
		outDegree[u]--;
		inDegree[v]--;
		if(!isDirected){
			graph[v][u] = 0;
			outDegree[v]--;
			inDegree[u]--;
		}
		
		return result;
	}
	/** Method to get the number of vertices in the graph
	 * 
	 * @return the number of vertices in the graph
	 */
	public int size(){
		return graph.length;
	}
	
	/** Gets the edge between two vertices
	 * 
	 * @param u first vertex
	 * @param v second vertex
	 * @return weight of the edge. If no edge exists, it will return 0
	 */
	public int getEdge(int u, int v){
		return graph[u][v];
	}
	
	/** Returns how many edges point to this vertex
	 * 
	 * @param v the vertex
	 * @return -1 if the vertex is not in our graph, else we return the amount of
	 * 			edges pointing to this vertex
	 */
	public int inDegree(int v){
		if (v < 0 || v >= graph.length)
			return -1;
		
		return inDegree[v];
	}
	
	/** Returns how many edges start at this vertex
	 * 
	 * @param v the vertex
	 * @return -1 if the vertex is not in our graph, else we return the amount of edges
	 * 			that start at this vertex 
	 */
	public int outDegree(int v){
		return outDegree[v];
	}
}
