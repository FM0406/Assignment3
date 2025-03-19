
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
		
		for(int i = 0; i < 6; i++ )
			if(!(cell.getNeighbour(i) == null)) {
				if(cell.getNeighbour(i).isGooseCell()) {
					gooseCells++;
				}
			}
		return gooseCells;
	}
	
	//Find next cell to walk to
	public Hexagon findBest(Hexagon cell) {
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
