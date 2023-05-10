package recipes;


import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import recipes.dao.DbConnection;
import recipes.exception.DbException;
import recipes.service.RecipeService;

public class Recipes {
  private Scanner scanner = new Scanner(System.in);
  private RecipeService recipeService = new RecipeService();

  private List<String> operations = List.of(
  // @formatter:off
     
      "1) Create and populate all tables"
    //  "2) Add a recipe"
      );
      

  
  // @formatter: on
  
  
  public static void main(String[] args) {
DbConnection.getConnection(); 


new Recipes().displayMenu();

  }
 
  
  private void displayMenu() {
   boolean done = false;
   
   while(!done) {
     try {
     int operation = getOperation();
     
  
     switch(operation) {
       case -1: 
         done = exitMenu();
       break; 
       
       case 1: 
         creatTables();
         break;
         
      // case 2: 
      //   addRecipe();
      //   break;
         
         default:
           System.out.println("\n" + operation + " is not valid. Try again.");
     }
     } catch(Exception e) {
       System.out.println("\nError: " + e.toString() + " Try again.");
     }
   }
    
  }



//  private void addRecipe() {
//String name = getStringInput("Enter the recipe name"); 
//String notes = getStringInput("Enter recipe notes");
//Integer numServings = getIntInput("Enter number of servings"); 
//Integer numIngredients = getIntInputIngredients("Enter number of ingredients");
    
  //}


  private void creatTables() {
    recipeService.creatAndPopulateTables(); 
    System.out.println("\nTables created and populated!");
  }


  private boolean exitMenu() {
    System.out.println("\nExiting the menu. Ta ta for now!");
    return true;
  }


  private int getOperation() {
    printOperations(); 
    Integer op = getIntInput("\nEnter an operation number (press Enter to quit)");
    
    return Objects.isNull(op) ? -1 : op;
  }



 



  private void printOperations() {
   
System.out.println();
System.out.println("Here's what you can do:");

operations.forEach(op -> System.out.println("    " + op));
  
  }
  
  /** 
   * 
   * @param prompt
   * @return
   */
  
  private Integer getIntInput(String prompt) {
   String input = getStringInput(prompt) ;
    
   if(Objects.isNull(input)) {
     return null;
   }
   
   try {
     return Integer.parseInt(input); 
   }
   catch(NumberFormatException e) {
     throw new DbException(input + " is not a valid number.");
   }
  }

  /** 
   * 
   * @param prompt
   * @return
   */
  
  private Double getDoubleInput(String prompt) {
   String input = getStringInput(prompt) ;
    
   if(Objects.isNull(input)) {
     return null;
   }
   
   try {
     return Double.parseDouble(input); 
   }
   catch(NumberFormatException e) {
     throw new DbException(input + " is not a valid number.");
   }
  }


  private String getStringInput(String prompt) {
  System.out.print(prompt + ": ");
  String line = scanner.nextLine();
  
  
  return line.isBlank() ? null: line.trim(); 
   
  }

}


