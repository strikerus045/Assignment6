
/**
@authour Chris Duong
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

	public class Graph implements GraphInterface<Town,Road>{

	private Set<Town> towns; 
	private Set<Road> roads; 
	private Map <String, Town> previousVertex;
	
	Graph(){
		towns = new HashSet<>();
		roads = new HashSet<>();
		previousVertex = new HashMap<>();
	}

	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		
		if(containsEdge(sourceVertex, destinationVertex)) {
			
			return roads[townIndex(sourceVertex)][townIndex(destinationVertex)];
		}
		
		return null;
	}
	
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		
		
		if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex) )
			throw new IllegalArgumentException();
		
		
		if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();
		
		
		Road newRoad = new Road(sourceVertex, destinationVertex, weight, description);
		roads.add(newRoad);
		
		
		if (containsEdge(sourceVertex, destinationVertex))
			return newRoad;
		else
			return null;
	}

	public boolean addVertex(Town v) {
		
	if(!containsVertex(v)) {
		
		townList.add(v);
		vertices.add(v);
		return true;
	}
	
	return false;
	
	}

	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		
	if(townIndex(sourceVertex)!=-1 && townIndex(destinationVertex)!=-1) {
		
		if(roads[townIndex(sourceVertex)][townIndex(destinationVertex)]!=null) {
			
			return true;
		}
	}
		return false;
	}
	
	public boolean containsVertex(Town v) {
	
	for (Town thisVertex : towns) {
		if (thisVertex.equals(v))
			return true;
	}
	return false;
	
	}

	public Set<Road> edgeSet() {
	return roads;
	
	}

	public Set<Road> edgesOf(Town vertex) {
		
	Set<Road> edgesOf=new HashSet<>();
	
	Iterator<Road> check=edges.iterator();
	
	Road include;
	
	while(check.hasNext()) {
		
		include=check.next();
		
			if(include.contains(vertex)) {
			edgesOf.add(include);
			
			}
	}
	
	return edgesOf;
	}

	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {

	
	Road roadRemoved = new Road(sourceVertex,destinationVertex,weight,description);
	
	
		if (containsVertex(sourceVertex) && containsVertex(destinationVertex))
			for (Road thisRoad : roads) {
				if (thisRoad.equals(roadRemoved)) {
					roads.remove(thisRoad);
					return roadRemoved;
				}
				
			}
		
		return null;
		
		}

	public boolean removeVertex(Town v) {
		
		if(v==null) {
		
		return false;
		
	}
		
	Set<Road> vEdges=edgesOf(v);
	
	Iterator<Road> check=vEdges.iterator();
	
	Road include;
	
		if(vEdges!=null) {
			
			while(check.hasNext()) {
				
			include=check.next();
			
			removeEdge(include.getSource(),include.getDestination(),include.getWeight(),include.getName());
			
		}
	}
		
	for(int i=0;i<townList.size();i++) {
		
		if(townList.get(i).equals(v)) {
			
			townList.remove(i);
			
		}
	}
	
		return false;
	
	}

	public Set<Town> vertexSet() {
		
		return towns;
	
	}

	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
	
	ArrayList<String> output = new ArrayList<String>();
	
	dijkstraShortestPath(sourceVertex);
	
	Town nextTown = destinationVertex;
	
	while (!nextTown.equals(sourceVertex)) {
		
		if (!previousVertex.containsKey(nextTown.getName())) {
			
			output.clear();
			break;
			}
		
		Town previousTown = previousVertex.get(nextTown.getName());
		
			if (previousTown == null) {
			return output;
		}
		
		Road edge = getEdge(previousTown, nextTown);
		
		output.add(0, previousTown.getName() + " via " + edge.getName() + " to " + nextTown.getName() + " " + edge.getWeight() + " mi");
		
		nextTown = previousTown;
		
		}
	
		return output;
	}

	public void dijkstraShortestPath(Town sourceVertex) {
	
	
		ArrayList<Town> unchecked = new ArrayList<Town>();
		
		Set<Town> visisted = new HashSet<Town>();
	
		HashMap<String, Integer> distance = new HashMap<String, Integer>();
		previousVertex.clear();
	
		for (Town thisTown : vertices) {
		
		unchecked.add(thisTown);
		
		distance.put(thisTown.getName(), Integer.MAX_VALUE);
		
		previousVertex.put(thisTown.getName(), null);
		
		}
	
		distance.put(sourceVertex.getName(), 0);
	
		while (!unchecked.isEmpty()) {
		
		int shortest = 0; 
		
		for (int i = 0; i < unchecked.size(); i++) {
			
			Town uncheckedVertex = unchecked.get(i);
			
			if ( distance.get(unchecked.get(shortest).getName()) > distance.get(uncheckedVertex.getName()) )
				shortest = i;
			}
		
		Town closest = unchecked.remove(shortest);
		
		visisted.add(closest);
		
		if (distance.get(closest.getName())==Integer.MAX_VALUE) {
			return;
			}
		
			for (Road eachEdge : edgesOf(closest)) {
			
				Town adjacent = eachEdge.getDestination();
			
				if (adjacent.equals(closest)) {
				
					adjacent = eachEdge.getSource();
					}
			
					if (visisted.contains(adjacent)) {
						continue;
						}
			
			int adjDistance = distance.get(closest.getName()) + eachEdge.getWeight();
			
			if (adjDistance < distance.get(adjacent.getName())) {
				distance.put(adjacent.getName(), adjDistance);
				previousVertex.put(adjacent.getName(), closest);
				
				}
			}
		}
	}
	
}
