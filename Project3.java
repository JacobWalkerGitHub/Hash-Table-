import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



/**
* COP 3530: Project 3 – Hash Tables * <p>
* Reads in a files name then scan files and its contents and saves it to variables
* <p>
* After, calls the insert method in HashTable.java and saves the contents of the files into a hash table
* <p>
* Then it repeatedly prompts the user for commands on how to view the files contents
* <p>
* @author Jacob Walker
* @version 06/24/2022 */
public class Project3 {

	

	public static void main(String[] args){
		
		int size = 293;
		
		System.out.print("Enter file name: ");
		Scanner fn = new Scanner(System.in);
		String fileName = fn.nextLine();
		Scanner inFile = null;

		
		try {
			inFile = new Scanner(new File(fileName));
		}
		catch(FileNotFoundException e) {
			System.out.println("Could not find file.");
			System.exit(1);
		}
		
		inFile.useDelimiter(",|\n");
		for(int i = 0; i < 12; i++)//gets rid of headers in csv file
			inFile.next();
		
		HashTable theHashTable = new HashTable(size);
		

		String countryName;	
		long pop;
		long cCases;
		while(inFile.hasNext()) {
			countryName = inFile.next();
			
			inFile.next();
			 pop = inFile.nextLong();
			inFile.next();
			 cCases = inFile.nextLong();


			 if(countryName != null && pop != 0 & cCases != 0)
				theHashTable.insert(countryName, pop, cCases);//insert information to hash table
			
			 for(int i = 0; i < 7; i++)//skips over non-important information in file
				 inFile.next();

		}
		
		while(true){
			prompt();
			Scanner scChoice = new Scanner(System.in);
			int choice = scChoice.nextInt();
			
			switch(choice) {
				case 1: 
					theHashTable.displayTable();
					break;
				case 2:
					System.out.print("Enter a countries name: ");
					Scanner scDelete = new Scanner(System.in);
					String dName = scDelete.next();
					System.out.println();
					theHashTable.delete(dName);
					break;
				case 3:
					Scanner in = new Scanner(System.in);
					String inName;
					long inPop;
					long inCases;
					
					System.out.print("Enter country Name: ");
					inName = in.next();
					System.out.print("Enter country Population: ");
					inPop = in.nextLong();
					System.out.print("Enter country Covid Cases: ");
					inCases = in.nextLong();
					
					theHashTable.insert(inName, inPop, inCases);
					break;
				case 4:
					System.out.print("Enter a countries name: ");
					Scanner scFind = new Scanner(System.in);
					String fName = scFind.next();
					theHashTable.find(fName);
					break;
				case 5:
					theHashTable.printEmptyAndCollidedCells();
					break;
				case 6:
					System.out.print("Thank you for a great semester!\nGood-Bye.");
					return;
			}
		}
		
		
		
	}
	
	public static void prompt() {
		System.out.println("1) Print hash table");
		System.out.println("2) Delete a country of a given name");
		System.out.println("3) Insert a country of its name, population, and COVID cases");
		System.out.println("4) Search and print a country and its case rate for a given name");
		System.out.println("5) Print number of empty cells and collided cells");
		System.out.println("6) Exit");
	}

}

