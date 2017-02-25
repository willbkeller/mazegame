package edu.jalc.labs.mazegame;
import java.util.*;
import java.io.*;

public class Maze{

   public String defaultMaze(){
      String defaultFile = "Maze1.txt";
      return defaultFile;
   }
   
   public void introduction(){
      System.out.println("Hello, and welcome to The Maze!\n"+
                         "You will be given instructions where to move.\n"+
                         "Type in which direction you would like to go.\n"+
                         "Enter \"History\" if you want to see previous moves.\n" +
                         "Good Luck!");
   }


   public void instruction(char[][] m, int y, int x){
      if(m[y][x+1] == (' ') || m[y][x+1] == ('f')){System.out.print("RIGHT ");}
      if(m[y][x-1] == (' ') || m[y][x-1] == ('f')){System.out.print("LEFT ");}
      if(m[y+1][x] == (' ') || m[y+1][x] == ('f')){System.out.print("DOWN ");}
      if(m[y-1][x] == (' ') || m[y-1][x] == ('f')){System.out.print("UP");}
      System.out.println();
   }
   
   public void winner(ArrayList<String> h){
      System.out.println("Congratulations! You have beaten The Maze!");
      System.out.println("It took you " + h.size() + " steps!");
   }
         
   public static void main (String[] args) throws IOException{
      
      Maze maze = new Maze();
      
      String fileName;
      if(args.length > 0){fileName = args[0];}
      else{fileName = maze.defaultMaze();}
   
      Scanner input = new Scanner(System.in);
      ArrayList<String> history = new ArrayList<String>();
   
      Scanner file = new Scanner(new File(fileName));
   
      ArrayList<String> content = new ArrayList<>();

   
      while(file.hasNextLine()){
         content.add(file.nextLine());
      }

      int row = content.size();
      int col = 0;
     
      Iterator it = content.iterator();
   
      char[][] map = new char[row][];
         int i = 0;
         
         while(it.hasNext()){
            String line = (String)it.next();
            map[i] = new char[line.length()];
            for(int j = 0; j < map[i].length; j++){
               col = map[i].length;
               map[i][j] = line.charAt(j);
            }
            i++;
         }

      int pY = 1;
      int pX = 17;
   
      int totalSpots = row * col;
      Random rand = new Random();
      
      while(i != totalSpots){
         pY = rand.nextInt(row);
         pX = rand.nextInt(col);
         if(map[pY][pX] == (' ')) break;
      }
      
      maze.introduction();
   
      while(Character.toLowerCase(map[pY][pX]) != 'f'){
         System.out.print("Instruction: ");
         maze.instruction(map, pY, pX);
         System.out.print("Move: ");
            String move = input.nextLine();
            
         switch(move.toLowerCase()){
            case "right":
               pX += 1;
               history.add(move);
               break;
            case "left":
               pX -= 1;
               history.add(move);
               break;
            case "down":
               pY += 1;
               history.add(move);
               break;
            case "up":
               pY -= 1;
               history.add(move);
               break;
            case "history":
               System.out.println(history);
               break;
            default:
               System.out.println("Invalid Entry");
               history.remove(move);
               break;
         }
      
         if(map[pY][pX] == ('x')){
            System.out.println("You can't go there!");
            if(move.equalsIgnoreCase("left")){
               pX+=1;
               history.remove(move);
            }
            else if(move.equalsIgnoreCase("up")){
               pY+=1;
               history.remove(move);
            }
            else if(move.equalsIgnoreCase("right")){
               pX-=1;
               history.remove(move);
            }
            else if(move.equalsIgnoreCase("down")){
               pY-=1;
               history.remove(move);
            }
         }
      }
      maze.winner(history);
   }  
}