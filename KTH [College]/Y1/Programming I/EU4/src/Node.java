public class Node {
	private String name;
	private int x;
	private int y;
	private Node next;
	
	public Node(String name, int x, int y) {
		this.name = name;
		this.x = x;
		this.y = y;
	}
	
	public Node(Node n) {
		this.x = n.getX();
		this.y = n.getY();
		this.name = n.getName();
		this.next = n.getNext();
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getX() {
		return this.x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setNext(Node next) {
		this.next = next;
	}
	
	public Node getNext() {
		return this.next;
	}
	
	public double distance(Point p) {
		int distX = p.getX() - this.x;
		int distY = p.getY() - this.y;
		return Math.sqrt(Math.pow(distX, 2) + Math.pow(distY, 2));
	}
	
	public boolean equals(Point p2) {
		return this.x == p2.getX() && this.y == p2.getY();
	}
	
	public String toString() {
		return "(" + this.name + " " + this.x + " " + this.y + ")";
	}
}
