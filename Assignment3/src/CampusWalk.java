
public class CampusWalk {
	private Map map;
	
	//Constructor
	public CampusWalk(String filename, boolean showMap) {
		try {
			//Initialize Map object
			map = new Map(filename);
			if(showMap) {
				map.showGUI();
			}else {
				map.hideGUI();
			}
		}catch(Exception e){
			System.out.println("Error occurred");
		}
	}
	
	//Count and return number of goose cells surrounding given
	public int neighbourGooseCount(Hexagon cell) {
		int gooseCells = 0;
		
		for(int i = 0; i < 6; i++ ) {
			if(!(cell.getNeighbour(i) == null)) {
				if(cell.getNeighbour(i).isGooseCell()) {
					gooseCells++;
				}
			}
		}
		return gooseCells;
	}
	
	
	//Find next cell to walk to
	public Hexagon findBest(Hexagon cell) {
		
		//1. End cell
		for(int i = 0; i < 6; i++ ) {
			if(cell.getNeighbour(i).isEnd()) {
				return cell.getNeighbour(i);
			}	
		}
		
		//2. Cell with book and less than 3 goose neighbors
		for (int i = 0; i < 6; i++) {
	        Hexagon neighbour = cell.getNeighbour(i);
	        if (neighbour != null && !neighbour.isMarked() && neighbour.isBookCell() && neighbourGooseCount(neighbour) < 3) {
	            return neighbour; 
	        }
	    }
		
		//Gets grass cell with smallest goose count and its index
		int smallestGooseCount = Integer.MAX_VALUE; 
		int index = Integer.MAX_VALUE;
		
		//3. Finds a grass cell
		for (int i = 0; i < 6; i++) {
	        Hexagon neighbour = cell.getNeighbour(i);
	        if (neighbour != null && !neighbour.isMarked() && neighbour.isGrassCell() && neighbourGooseCount(neighbour) < 3) {
	        	//Neighbor with smallest goose neighbors
				if(smallestGooseCount > neighbourGooseCount(cell.getNeighbour(i))) {
					smallestGooseCount = neighbourGooseCount(cell.getNeighbour(i));
					index = i;
				}
	        }
	    }
		
		//Checks if grass cell is available
		if(smallestGooseCount != Integer.MAX_VALUE) {
			return cell.getNeighbour(index);
		//4. Check for snow cells
		}else {
			for(int i = 0; i < 6; i++ ) {
				Hexagon neighbour = cell.getNeighbour(i);
				if(neighbour != null && !neighbour.isMarked() && neighbour.isSnowCell() && neighbourGooseCount(neighbour) < 3 ) {
					return cell.getNeighbour(i);
				}
			}
		}
		return null;
	}
	
	
	public String findPath() {
		ArrayStack<Hexagon> S = new ArrayStack<Hexagon>();
		String path = "";
		
		boolean running = true;
		
		Hexagon startCell = map.getStart();
		S.push(startCell);
		startCell.markInStack();
		
		Hexagon curr;
		while(!S.isEmpty() && running) {
			curr = S.peek();
			path+=curr.getID();
			
			//If exit cell
			if(curr.isEnd()) {
				running = false;
				break;
			}
			Hexagon next = findBest(curr);
			if(next == null) {
				S.pop();
				curr.markOutStack();
			}else {
				S.push(next);
				next.markInStack();
			}
		}
		
		if(running == false) {
			return path;
		}else {
			return "No path found";
		}
	}
	
	
	//Force map to be closed
	public void exit() {
		map.exit();
	}
	
	public static void main(String[] args) {
		Hexagon.TIME_DELAY = 500; // Change speed of animation.
		String file = "map1.txt"; // Change when trying other maps.
		CampusWalk walk = new CampusWalk(file, true);
		String result = walk.findPath();
		System.out.println(result);
	}
}
