/* ********************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * This program simulates the famous MontyHall problem originating from the American T.V show "Let's Make A Deal". The 
 * concept of the show was that a contestant would go on stage and the host, Monty Hall, would let them choose between 
 * three doors in front of them. Behind one of the doors was a dream car that the player could possibly go home with, 
 * and behind the other two were goats, or “ZONKS”. After the player chooses a door, Monty reveals a one of the other 
 * doors that has a goat, and then gives the player the option to keep or switch their choice. There is a statistically
 * better choice to make when given this decision and that is what our program successfully proves. It simulates the 
 * game multiple times and displays the data collected from each game to the user. 
 * ***************************************************************************************************************** */
package montyhall;
import java.util.Random;
import javax.swing.*;

public class MontyHall {
 // Implementing polymorphism with Door class and WoodenDoor sub-class
 Door polymorphismTest = new WoodenDoor();
 Door one ;
 Door two ;
 Door three;
 Door x;
 Door y;
 Door z;
 Door a;
 Door b;
 Random test = new Random();
 Door[] list;
 Door[] allDoors;
 int doors;
 Statistics stats;
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * This is the class constructor that takes the nubmer of doors inputted by the user and sets the array index to match
 * the number.
 * @param:int numberOfDoors 
 * @return:none
 * *************************************************************************************************************** */
 public MontyHall(int numberOfDoors){
  doors=numberOfDoors;
  int count=0;
  int secondCount=0;
  String tmp;
  
  allDoors=new Door[doors];
  for (int i=0;i<doors;i++){
   secondCount+=1;
    tmp=Integer.toString(secondCount);
   allDoors[i]=new Door(tmp);
  }
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * This method calls and loops the oneGame() method depending on how many times ht user chose to simulate the game.
 * @param:int number,boolean command 
 * @return:none 
 * *************************************************************************************************************** */
 public void runGames(int number,boolean command){
  stats= new Statistics(doors);
  if (command==false){
   for(int i=0;i<number;i++){
   oneGame();
   }
   System.out.println(stats);
  }
  else{
   for(int i=0;i<number;i++){
   oneGame();
   }
   JOptionPane.showMessageDialog (null,stats.toString(), "Results", JOptionPane.INFORMATION_MESSAGE);
  }
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Calls the pickADoor(), choose(), setPrize() methods to simulate one game of the MOnty Hall problem.
 * @param:none 
 * @return:none
 * *************************************************************************************************************** */
 public void oneGame(){
  for(int i=0;i<doors;i++){
   allDoors[i].reset();
  }
  x=pickADoor();
  x.choose();
  y=pickADoor();
  y.setPrize();
  openOtherDoors(x,y);
  stats.updateStatistics(allDoors);
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Generates a random nubmer.
 * @param:randomNumber 
 * @return:int min, int max 
 * *************************************************************************************************************** */
 private int rand(int min, int max){
  int randomNumber=test.nextInt((max-min)+1)+min;
  return randomNumber;
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Uses the random number that was generated to choose a random door from the array.
 * @param:none 
 * @return:allDoors[randomNumber]
 * *************************************************************************************************************** */
 private Door pickADoor(){
  int randomNumber=test.nextInt(doors);
  return allDoors[randomNumber];
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Opens all the other doors in the array that were not chosen.
 * @param:Door chosen,Door prize 
 * @return:none 
 * *************************************************************************************************************** */
 private void openOtherDoors(Door chosen,Door prize){
  int z=Integer.parseInt(chosen.door);
  if ((chosen.door).equals(prize.door)){

   int randomNumber=rand(1,doors);

   if (randomNumber==(z)){
    while (randomNumber==z){
    randomNumber=rand(1,doors);
   }
   }
   String tmp=Integer.toString(randomNumber);
   for (int i=0;i<doors;i++){
   if (allDoors[i].isChosen()==false && ((allDoors[i].door).equals(tmp))==false&&(tmp.equals("0"))==false){
    allDoors[i].open();
   }
  }
  }
  else{
   for (int i=0;i<doors;i++){
   if (allDoors[i].isChosen()==false && allDoors[i].hasPrize()==false){
    allDoors[i].open();
   }
  }
  }
  for (int i=0;i<doors;i++){
   if (allDoors[i].isOpen()==true){
   }
  }
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * This is the main method that starts the program, contains the menu system which asks the user to inout information 
 * needed for the program.
 * @param:String[]args 
 * @return:none 
 * *************************************************************************************************************** */
 public static void main(String[]args){
  int w=0;
  int numberOfSim;
  int numberOfDoors;
  boolean command=true;

  if (args.length==2){
   numberOfSim=Integer.parseInt(args[0]);
   numberOfDoors=Integer.parseInt(args[1]);
   command=true;
  }
  else{
   numberOfSim = Integer.parseInt(JOptionPane.showInputDialog("Input the number of games to play", "1000"));
   numberOfDoors = Integer.parseInt(JOptionPane.showInputDialog("Input the number of doors", "3"));
   command=false;
  }
  MontyHall game = new MontyHall(numberOfDoors);
  game.runGames(numberOfSim,command);
 }
}
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * This class takes the selected doors in the arrays and classifys them as opened, slected, or the prize door.
 * *************************************************************************************************************** */
class Door{
 boolean open=false;
 boolean selection=false;
 boolean prize=false;
 String door;
 
 Door (String identity){ //Door class constructor
  door=identity;
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * @param:none 
 * @return:none 
 * *************************************************************************************************************** */
 public Door() {}
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Resets Door
 * @param:none 
 * @return:none
 * *************************************************************************************************************** */
 public void reset(){
  open=false;
  selection=false;
  prize=false;
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Opens Door
 * @param:none
 * @return:none
 * *************************************************************************************************************** */
 public void open(){
  open=true;
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Returns boolean statement saying if current door is a boolean or not.
 * @param:none 
 * @return: boolean open
 * *************************************************************************************************************** */
 public boolean isOpen(){
  if (open==true){
  }
  return (open);
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Gets prize door
 * @param:none 
 * @return: boolean prize
 * *************************************************************************************************************** */
 public boolean hasPrize(){
  return prize;
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Gets the chosen door
 * @param:none
 * @return: boolean selection
 * *************************************************************************************************************** */
 public boolean isChosen(){
  if (selection==true){
  }
  return(selection);
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Chooses a door
 * @param:none
 * @return:none
 * *************************************************************************************************************** */
 public void choose(){
  selection=true;
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Sets the prize
 * @param:none
 * @return: none
 * *************************************************************************************************************** */
 public void setPrize(){
  prize=true;
 }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Gets the door name
 * @param:none 
 * @return:String door 
 * *************************************************************************************************************** */
 public String getName(){
  return (door);
 }
}
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * This is the statistics class in which the data is gathered from the simulated games, and and is calculated. The
 * information is then dispalyed to the user.
 * *************************************************************************************************************** */
class Statistics{
 int switchWin=0;
 int switchLose=0;
 int totalGames=0;
 int[][] list;
 int doors;
 int prizeIndex;
 int selectIndex;
 int totalChosen;
 int totalOpen;
 int totalPrize;
  // Constructor
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * This is the class constructor that sets up the variable 'list' depending on the number of doors in the simualtion
 * for the rest of the class to use.
 * @param:int numberOfDoors
 * @return:none
 * *************************************************************************************************************** */
  public Statistics(int numberOfDoors){
   doors=numberOfDoors;
   list= new int[doors][];
   for (int i=0;i<doors;i++){
    list[i]=new int[3];
   }
  }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Calcualtes how many times each door was chosen to take on each of the roles (Prize, Opened "ZONK", Chosen).
 * @param:Door[] doorArray 
 * @return:none
 * *************************************************************************************************************** */
  public void updateStatistics(Door[] doorArray){
   for (int i=0;i<doors;i++){
    int z=Integer.parseInt((doorArray[i]).door);
    if(doorArray[i].isChosen()==true&&doorArray[i].hasPrize()==true){

     list[z-1][0]+=1;
     list[z-1][2]+=1;
     switchLose+=1;
     totalChosen+=1;
     totalPrize+=1;

    }
    else if(doorArray[i].isChosen()==true){
     list[z-1][0]+=1;
     selectIndex=z;
     totalChosen+=1;
    }

    else if(doorArray[i].isOpen()==true){
     list[z-1][1]+=1;
     totalOpen+=1;

    }

    else if(doorArray[i].hasPrize()==true){
     list[z-1][2]+=1;
     prizeIndex=z;
     totalPrize+=1;

    }
   }
   totalGames+=1;
   switchWin=totalGames-switchLose;
  }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Concatenates statistical data to strings
 * @param:none 
 * @return:String output
 * *************************************************************************************************************** */
  public String toString(){
   String []output= new String [4];
   output[0]="Number of Games Played: "+totalGames+"\n";
   output[0]=output[0]+"Staying strategy won "+ switchLose+" times "+ (switchLose*100/totalGames)+"%\n";
   output[0]=output[0]+"Switching strategy won "+ switchWin+" times "+ (switchWin*100/totalGames)+"%\n";
   output[1]=("Chosen Doors\n");

   for (int i=0;i<(list).length;i++){
    output[1]=output[1]+"Door "+(i+1)+": "+ list[i][0]+ " "+ ((list[i][0])*100/totalChosen)+"%\n";
   }
   output[2]=("Opened Doors\n");
   for (int i=0;i<(list).length;i++){
    output[2]=output[2]+"Door "+(i+1)+": "+ list[i][1]+" "+ ((list[i][1])*100/totalOpen)+"%\n";
   }
   output[3]=("Prize Doors\n");
   for (int i=0;i<(list).length;i++){
    output[3]=output[3]+"Door "+(i+1)+": "+ list[i][2]+" "+ ((list[i][2])*100/totalPrize)+"%\n";
   }

   output[0]=output[0]+output[1]+output[2]+output[3];
   return output[0];
  }
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Concatenates to CSV (Comma-separated values)
 * @param:none 
 * @return:String output 
 * *************************************************************************************************************** */
  public String toCSV(){
   String []output= new String[4];
   output[0]="Number of Games "+ totalGames+"\nNumber of Doors "+ doors+"\n,Wins,Losses\n"+"Staying strategy "+switchWin+","+switchLose+"\nStaying strategy "+ switchLose+ ", " +switchWin+"\n";
   output[1]=",Selected doors, Winning doors, Open doors\n";
   output[2]="";
   for (int i=0;i<(list).length;i++){
    output[2]=output[2]+"Door "+ (i+1)+","+list[i][0]+","+list[i][1]+","+list[i][2]+"\n";
    }
   output[0]=output[0]+output[1]+output[2];
   return output[0];
  }
}
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Wooden Door class that implements inheritance from Door class and encapsulation 
 * *************************************************************************************************************** */
class WoodenDoor extends Door {
 private String type = "wood";
/* ******************************************************************************************************************
 * @Author: Hamdan Basharat and Rushil Asudani @Date: January 18th, 2017
 * Gets type of door
 * @param:none 
 * @return: String type
 * *************************************************************************************************************** */
 public String getType() {
  return type;
 }
}
