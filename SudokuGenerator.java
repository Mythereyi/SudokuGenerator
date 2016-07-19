import java.util.ArrayList;

import java.util.Collections;

import java.util.List;



public class SudokuGenerator {
   
	private int[][] solution;
    
	private int[][] game;

    
	private int[][] copy ;


public void startGame() {
    	
	solution = generateSolution(new int[9][9], 0);
        
	game = generateGame(copy(solution));
        
	printSolution(solution);
     
 	printSolution(game);
  
	printSolution(copy(solution));
  }

  

private int[][] generateGame(int[][] game) {
  
     	 List<Integer> positions = new ArrayList<Integer>();
      
 	 for (int i = 0; i < 81; i++)
          
 	 positions.add(i);
       
	 Collections.shuffle(positions);
       
	 return generateGame(game, positions);
    
  }

    

private int[][] generateGame(int[][] game, List<Integer> positions) {

      	  while (positions.size() > 0) {
       
    	 int position = positions.remove(0);
   
        	 int x = position % 9;
            
	int y = position / 9;
        
  	 int temp = game[y][x];
           
 	game[y][x] = 0;

          
 	 if (!isValid(game))
             
  	 game[y][x] = temp;
    
    }

        
	return game;
 
   }

   
 
private boolean isValid(int[][] game) {
    
  	  return isValid(game, 0, new int[] { 0 });
   
 }

   

private boolean isValid(int[][] game, int index, int[] numberOfSolutions) {
    
 	   if (index > 80)
    
    	    return ++numberOfSolutions[0] == 1;

  
   	   int x = index % 9;
      
	  int y = index / 9;

      
	  if (game[y][x] == 0) {
         
 	  List<Integer> numbers = new ArrayList<Integer>();
          
 	 for (int i = 1; i <= 9; i++)
               
	 numbers.add(i);

          
	  while (numbers.size() > 0) {
             
  	 int number = getNextPossibleNumber(game, x, y, numbers);
              
	  if (number == -1)
        
       	     break;
       
       	  game[y][x] = number;

       
    	     if (!isValid(game, index + 1, numberOfSolutions)) {
              
   	   game[y][x] = 0;
       
       	      return false;
             
   }
    
           	 game[y][x] = 0;
         
   }
     
   }
 	else if (!isValid(game, index + 1, numberOfSolutions))
    
   	     return false;

  
  
    return true;
   
 }

   

private int[][] generateSolution(int[][] game, int index) {
	
	if (index > 80)
           
	 return game;

       
	 int row = index % 9;
      
 	 int column = index / 9;

       
	 List<Integer> numbers = new ArrayList<Integer>();
   
   	  for (int i = 1; i <= 9; i++)
	 numbers.add(i);
     
  	 Collections.shuffle(numbers);

    
  	  while (numbers.size() > 0 && column < 9 && row < 9) {
       
   	  int number = getNextPossibleNumber(game, row, column, numbers);
          
	  if (number == -1)
       
      	   return null;

        
   	 game[column][row] = number;
       
   	  int[][] tmpGame = generateSolution(game, index + 1);
        
	    if (tmpGame != null)
    
            return tmpGame;
        
 	   game[column][row] = 0;
    
    }

       
 return null;
    
}

  

private int getNextPossibleNumber(int[][] game, int row, int column, List<Integer> numbers) {
       
	 while (numbers.size() > 0) {
            
	int number = numbers.remove(0);
           
	 if (isPossibleRow(game, column, number) && isPossibleColumn(game, row, number) && isPossibleMatrix(game, row, column, number))
              
 	 return number;
  
      }
        
return -1;
  
  }

   

private boolean isPossibleRow(int[][] game, int column, int number) {
       
 	for (int row = 0; row < 9; row++) {
           
	 if (game[column][row] == number)
         
      	 return false;
    
    }
    
    return true;
   
 }

   

private boolean isPossibleColumn(int[][] game, int row, int number) {
        
	for (int column = 0; column < 9; column++) {
           
	 if (game[column][row] == number)
     
          	 return false;
    
    }
       
	 return true;
   
 }

   

 private boolean isPossibleMatrix(int[][] game, int row, int column, int number) {
       
 	int row1 = row < 3 ? 0 : row < 6 ? 3 : 6;
       
 	int comlumn1 = column < 3 ? 0 : column < 6 ? 3 : 6;
       
 	for (int y = comlumn1; y < comlumn1 + 3; y++) {
           
	 for (int x = row1; x < row1 + 3; x++) {
              
 	 if (game[y][x] == number)
                   
	 return false;
            
}
        
}
      
 	 return true;
  
  }

    

private int[][] copy(int[][] game) {
      
 	 int[][] copy = new int[9][9];
      
	  for (int y = 0; y < 9; y++) {
      
    	  for (int x = 0; x < 9; x++)
         
    	   copy[y][x] = game[y][x];
     
   }
    
   	 return copy;
  
  }

    

private void printSolution(int[][] solution) {
	
		for (int i = 0; i < 9; ++i) {
		    
		if (i % 3 == 0) {
	       
  		System.out.println(" -----------------------");
          
  }

	
	 	   for (int j = 0; j < 9; ++j) {
             
  		 if (j % 3 == 0) System.out.print("| ");
 {
                    
		System.out.print(solution[i][j] == 0 ? " " : Integer.toString(solution[i][j]));
       
         }
       
       			  System.out.print(' ');
		  
  }
	
	  
 		 System.out.println("|");
		
}
		
System.out.println(" -----------------------");
  
 
}

   

public static void main(String[] args) {
	
	
SudokuGenerator sg = new SudokuGenerator();
	
	sg.startGame();
    
}

}