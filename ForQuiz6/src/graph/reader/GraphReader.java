package graph.reader;

import java.util.InputMismatchException;
import java.util.Scanner;

import adjancency.matrix.AdjancencyMatrix;

public class GraphReader {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int N = in.nextInt();
		int M = in.nextInt();
		
		AdjancencyMatrix graph = new AdjancencyMatrix(N, false);
		
		for(int i = 0; i < M; i++){
			graph.addEdge(in.nextInt(), in.nextInt(), in.nextInt());
		}
		
		int input;
		while(true){
			System.out.print("Choose a vertex of -1 to quit: ");
			try{
				input = in.nextInt();
				if(input == -1)
					break;
				
				if(input < 0 || input >= graph.size())
					System.out.println("No such vertex exists");
				
				else{
					boolean foundAdjacent = false;
					for(int i = 0; i < graph.size(); i++){
						if(i == input)
							continue;
						
						if(graph.hasEdge(input, i)){
							foundAdjacent = true;
							System.out.println("Vertex " + input + " is adjacent it vertex " + i);
						}
					}
					if(!foundAdjacent)
						System.out.println("Vertex " + input + " is not adjacent to any other vertices");
				}
			} catch(InputMismatchException e){
				System.out.println("I'm sorry, I didn't understand that. Please try again");
				in.nextLine();
			}
		}
		
		System.out.println("Goodbye!");
	}

}
