
/**
@authour Chris Duong
*/

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TownGraphManager implements TownGraphManagerInterface {
	
	private Graph<Town,Road> graph=new Graph<Town, Road>();
	
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		Road addedRoad = townGraph.addEdge(source, destination, weight, roadName);
		
			if (townGraph.getEdge(source, destination) == addedRoad) {
			return true;
			}
		
			return false;
		}
	
	public boolean addTown(String v) {
		
		try {
			
			Town town1=new Town(v);
			graph.addVertex(town1);
		}
		
		catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return false;
	}
	
	public Town getTown(String name) {
		
		try {
			
			Town Town1;
			
			Iterator<Town> iterator=graph.vertexSet().iterator();
			
			while(iterator.hasNext()) { 
				
				Town1=iterator.next();
				
					if(Town1.getName().equals(name)) {
						
					return Town1;
					
					}
			}
			
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public String getRoad(String town1, String town2) {
		
		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		Road road = townGraph.getEdge(source, destination);
		return road.getName();
	}
	
	public boolean containsRoadConnection(String town1, String town2) {
		
	Town Town1=new Town(town1);
	Town Town2=new Town(town2);
	Town current;
	
	Set<Town> find=graph.vertexSet();
	
	Iterator<Town> locate=find.iterator();
	
		while(locate.hasNext()) {
			
			current=locate.next();
			
		if(current.getName().equals(town1)) {
			
			Town1=current;
			
		}
		
			if(current.getName().equals(town2))
		{
				
			Town2=current;
		}
			
	}
		
		return graph.containsEdge(Town1, Town2);
	}
	
	public ArrayList<String> allRoads() {
		
		ArrayList<String> allRoads = new ArrayList<String>();
		
		for (Road thisRoad : townGraph.edgeSet()) {
			allRoads.add(thisRoad.getName());
		}
		
		Collections.sort(allRoads);
		return allRoads;
	}

	public boolean deleteTown(String v) {
		Town Town1=new Town(v);
		return graph.removeVertex(Town1);
	}
	
	public ArrayList<String> getPath(String town1, String town2) {
		Town source = new Town(town1);
		Town destination = new Town(town2);
		return townGraph.shortestPath(source, destination);
	}

	public boolean deleteRoadConnection(String town1, String town2, String roadName) {

		Town source = new Town(town1);
		Town destination = new Town(town2);
		
		Road removeRoad = townGraph.getEdge(source, destination);
		
		if (removeRoad.getName().equals(roadName)) 
			townGraph.removeEdge(source, destination, removeRoad.getWeight(), roadName);

		if (!townGraph.containsEdge(source, destination))
			return true;

		return false;

	}
	
	public void populateTownGraph(File selectedFile) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(selectedFile);
		String[] line;
		String[] road_miles;
		String road = "";
		String miles = "";
		String town1 = "";
		String town2 = "";
		
		while(scanner.hasNextLine()) {
			line = scanner.nextLine().split(";");
			
			road_miles = line[0].split(",");
			road = road_miles[0];
			miles = road_miles[1];
			
			town1 = line[1];
			town2 = line[2];
			
			addTown(town1);
			addTown(town2);
			addRoad(town1, town2, Integer.parseInt(miles), road);
			
		}
		
	}
	
}
