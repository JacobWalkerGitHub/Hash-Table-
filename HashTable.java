
/**
* HashTable class stores all the data structures and algorithms to create a hash table and store items in it, also to allow full use of display different outputs 
* of the hash table.
*
* @author Jacob Walker
* @version 06/24/2022 */
public class HashTable {
	
	SortedList[] hashArray = null;
	private int arraySize;
	private Node nonItem;
	
	private class Node {
		   String name;
		   long population;
		   long cases;
		   Node nextNode;
		   
		/**
		* Creates the nodes to navigate through the hash table
	    * @param name - holds the value of the country name
	    * @param population - holds the value of a countries population
	    * @param cases - holds the number of covid cases in a country
	    */
		public Node(String name, long population, long cases) { 
			this.name = name;
			this.population = population;
			this.cases = cases;
		}
		/**
		* Prints the name and case rate
		*/
		public void printNode() { 
			System.out.printf("%-30s %-20.2f\n", name, (double)cases/population*100000);
		}//end printNode method
	}//end Node class
	
	class SortedList{
		private Node first;
		private Node sec;
		private void SortedList() {
			first = null;
			sec = first.nextNode;
		}
		/**
		* inserts the name, population and cases into the hash table
		* @param cName - holds the countries name
		* @param pop - holds the countries population
		* @param cases - holds the countries number of covid cases
		* @param theNode - creates a new node with cName, pop, and cases
		* @param previous - helps navigate through all nodes
		* @param current - helps navigate through all nodes
		*/
		public void insert(String cName, long pop, long cases) {
			Node theNode = new Node(cName, pop, cases);
			Node previous = null;
			Node current = first;
			
			while(current != null && !cName.equals(current.name)) {
				previous = current;
				current = current.nextNode;
			}
			if(previous == null)
				first = theNode;
			else
				previous.nextNode = theNode;
			theNode.nextNode = current;
		}//end insert method
		/**
		* deletes a country from the hash table
		* @param key - holds the countries name
		* @param previous - helps navigate through all nodes
		* @param current - helps navigate through all nodes
		*/
		public void delete(String key) {
			Node previous = null;
			Node current = first;
			
			while(current != null && !key.equals(current.name)) {
				previous = current;
				current = current.nextNode;
			}
			if(previous == null)
				first = first.nextNode;
			else
				previous.nextNode = current.nextNode;
		}//end delete method
		/**
		* finds specific countries in the hash table
		* @param cName - holds the countries name
		* @param pop - holds the countries population
		* @param cCases - holds the countries number of covid cases
		* @param CC - holds the case rate
		* @param current - helps navigate through all nodes
		*/
		public double find(String cName) {
			Node current = first;

			for(int j = 0; j < 293; j++) {	
				if(current.name.equals(cName)) {
					long cCases = current.cases;
					long pop = current.population;
					double CC = (double)cCases/pop*100000;
					return CC;
					
				}
				current = current.nextNode;
				if(j == 292) {
					return 0;
				}
			}

			return 0;
		}//end find method
		/**
		* displays the hash table
		* @param current - helps navigate through all nodes
		*/
		public void displayList() {
			Node current = first;
			while(current != null) {
				current.printNode();
				current = current.nextNode;
			}
			System.out.println();
		}//end displayList method
		
	}//end sortedList class
	
	public HashTable(int size) {
		arraySize = size;
		hashArray = new SortedList[arraySize];
		for(int i = 0; i < arraySize; i++)
			hashArray[i] = new SortedList();
	}//end HashTable constructor
	/**
	* calls the display table in the SortedList class
	*/
	public void displayTable() {
		for(int i = 0; i < arraySize; i++) {
			System.out.print(i + ". ");
			hashArray[i].displayList();
		}
	}//end displayTable method
	/**
	* creates the location of each country in the hash table
	* @param name - holds the countries name
	* @param hashVal - hold the numerical value of the countries name
	* @return hashVal - returns the numerical value with modulos 293
	*/
	public int hashFunc(String name) {
		int hashVal = 0;
		for (char c : name.toCharArray()) {
            hashVal += c;
        }
		return hashVal %= 293;
	}//end hashFunc method
	/**
	* calls the insert method in SortedList class
	* @param cName - holds the countries name
	* @param pop - holds the countries population
	* @param cases - holds the countries number of covid cases
	*/
	public void insert(String cName, long pop, long cases) {
		int hashVal = hashFunc(cName);
		hashArray[hashVal].insert(cName, pop, cases);
	}//end insert method
	/**
	* calls the find method in SortedList class
	* @param name - holds the countries name
	*/
	public int find(String name) {
		int hashVal = hashFunc(name);
		double Cases = hashArray[hashVal].find(name);
		if(Cases != 0) {
			System.out.print(name + " is found at index " + hashVal + " with case rate of ");
			System.out.printf("%.3f\n", Cases);
		}
		else if(Cases == 0.0){
			System.out.println(name + " was not found.");
		}
		return 0;
	}//end find method
	/**
	* calls the delete method in SortedList class
	* @param name - holds the countries name
	*/
	public void delete(String name) {
		int hashVal = hashFunc(name);
		
		hashArray[hashVal].delete(name);
		System.out.println(name + " was deleted from hash table.\n");
	}//end delete method
	/**
	* finds how many empty and collided cells are in the hash table
	* 
	*/
	public void printEmptyAndCollidedCells() {
		int y = 0;
		int x = 0;
		
        for (SortedList list : hashArray) {
            if (list.first == null) {
            	x++;
                continue;
            }


            if (list.first != list.sec) {
                y++;
            }
        }
        System.out.println("there are " + x + " empty cells and " + y + " collisions in the hash table.");		
	}//end printEmptyAndCollidedCellsmethod
	
}//end HashTable class


