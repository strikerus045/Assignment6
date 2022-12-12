
/**
@authour Chris Duong
*/

public class Road implements Comparable<Road> {

	private Town source;
	private Town destination;
	private int degrees;
	private int distance;
	private String name;
	private int weight;
	
	Road(Town source, Town destination, int degrees, String name){
		this.source = source;
		this.destination = destination;
		this.distance = degrees;
		this.name = name;
	}
	
	Road(Town source, Town destination, String name){
		this.source = source;
		this.destination = destination;
		this.distance = 1;
		this.name = name;
	}
	
	public int compareTo(Road o)
	{
		if(source.equals(o.getSource())&& destination.equals(o.getDestination()))
		{
			return 0;
		}
		return 1;
	}
	
	public boolean contains(Town t)
	{
		if(source.equals(t) || destination.equals(t))
		{
			return true;
		}
		return false;
	}
	
	public boolean equals(Object x)
	{
		if(source.equals(((Road) x).getSource())&& destination.equals(((Road) x).getDestination()))
		{
			return true;
		}
		return false;
	}
	
	public Town getDestination() {
		return this.destination;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Town getSource() {
		return this.source;
	}
	
	public int getWeight() {
		return this.distance;
	}
	
	public String toString() {
		return name + ", " + distance + "; " + source + "; " + destination;
	}
	
}
