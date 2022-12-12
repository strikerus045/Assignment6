
/**
@authour Chris Duong
*/

public class Town implements Comparable<Town> {

	private String name;
	private List<Town> adjacentTown;
	public Town(String name)
	
	Town (String name){
		this.name = name;
	}
	
	Town (Town templateTown){
		this.name = templateTown.getName();
	}
	
	public int compareTo(Town o) {
		return o.name.compareTo(this.name);
	}
	
	public boolean equals(Town x)
	{
		if(getName().equals(x.getName()))
		{
			return true;
		}
		return false;
	}
	
	public List<Town> getAdj()
	{
		return adjacentTown;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		String toString = this.name;
		return toString;
	}
	
	public int hashCode() {
		return name.hashCode();
		}
	
	public boolean equals(Object o) {
		Town obj = (Town) o;
		return this.name.equals(obj.name);
		}
	
	public String toString() {
		return "";
	}
}
