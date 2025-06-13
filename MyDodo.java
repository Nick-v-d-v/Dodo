import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author Sjaak Smetsers & Renske Smetsers-Weeda
 * @version 3.0 -- 20-01-2017
 */
public class MyDodo extends Dodo
{
    private int myNrOfEggsHatched;
    
    public MyDodo() {
        super( EAST );
        myNrOfEggsHatched = 0;
    }

    public void act() {
    }

    public void move() {
        if ( canMove() ) {
            step();
        } else {
            showError( "I'm stuck!" );
        }
    }

    public boolean canMove() {
        if ( borderAhead() ){
            return false;
        } else {
            return true;
        }
    }
    
    public boolean validCoordinates(int x, int y) {
        if (x < 0) {
            showError( "Invalid coordinates" );
            return false;
        }
        
        if (x > getWorld().getWidth()) {
            showError( "Invalid coordinates" );
            return false;
        }
        
        if (y < 0) {
            showError( "Invalid coordinates" );
            return false;
        }
        
        if (x > getWorld().getHeight()) {
            showError( "Invalid coordinates" );
            return false;
        }
        return true;
    }
   
    public void hatchEgg () {
        if ( onEgg() ) {
            pickUpEgg();
            myNrOfEggsHatched++;
        } else {
            showError( "There was no egg in this cell" );
        }
    }
    
        public void gotoEgg() {
        while (!onEgg()) {
            step();
         }
    }

    public int getNrOfEggsHatched() {
        return myNrOfEggsHatched;
    }
    
    public void jump( int distance ) {
        int nrStepsTaken = 0;     
        while ( nrStepsTaken < distance ) {
            move();                   
            nrStepsTaken++;           
            System.out.println("Moved" + nrStepsTaken);
        }
    }
    
    public void turn180(){
        turnRight();
        turnRight();
    }
    
    public void faceNorth() { 
    while (getDirection() != NORTH) {
        turnLeft();
        }
    }
    
    public void faceEast() { 
    while (getDirection() != EAST) {
        turnLeft();
        }
    }
    
    public void faceSouth() { 
    while (getDirection() != SOUTH) {
        turnLeft();
        }
    }
    
    public void faceWest() { 
    while (getDirection() != WEST) {
        turnLeft();
        }
    }
    
    public void goToLocation(int coordX, int coordY) {
        while (getX() < coordX) {
            faceEast();
            move();
        }
        while (getX() > coordX) {
            faceWest();
            move();
        }
        while (getY() < coordY) {
            faceSouth();
            move();
        }
        while (getY() > coordY) {
            faceNorth();
            move();
        }
    } 
    
        public void climbOverFence() {
        if (fenceAhead() && !borderAhead()) {
            turnLeft();
            move();
            turnRight(); 
            move();
            move();
            turnRight();
            move();
            turnLeft();
        } else {
            showError("Kan niet");
        }
    }
    
        public boolean grainAhead(){
        move();
        if (onGrain()){
        stepOneCellBackwards();
        return true;
        }else {
        stepOneCellBackwards();
        return false;
        }
    }
    
    public void stepOneCellBackwards() {
        turn180();
        move();
        turn180(); 
    }
    
    public void walkAroundFencedArea() {
        while (!onEgg()) {
            turnRight();
            if (canMove() && !fenceAhead()) {
                move();
            } else {
                turnLeft();
                if (canMove() && !fenceAhead()) {
                    move();
                } else {
                    turnLeft();
                }
            }
        }
    }

    public void eggTrailToNest() {
        while(!onNest() ){
            if (canMove() && nestAhead()) {
                move();
            }
            if (canMove() && eggAhead()) {
                move();
            } else {
                turnLeft();
                if (canMove() && nestAhead()) {
                move();
            }
            if (canMove() && eggAhead()) {
                move();
            } else {
                turn180();
            }
            }
        }
    }
    
    public void eenVoudigDoolhof() {
        while (!onNest()) {
            turnRight();
            if (canMove() && !fenceAhead()) {
                move();
            } else {
                turnLeft();
                if (canMove() && !fenceAhead()) {
                    move();
                } else {
                    turnLeft();
                }
            }
            if (onNest()) {
                showCompliment("Goed gedaan dodo!!!");
            }
        }
    }
    
    public void walkToWorldEdgePrintingCoordinates(){
        while( ! borderAhead() ){
        int x = getX();
        int y = getY();
        System.out.println("(" + x + ", " + y + ")");
        move();
        }
    }
    
    public void walkToWorldEdge(){
        while(!borderAhead() ){
        move();
        }
    }

    public void goBackToStartOfRowAndFaceBack() {
        turn180();
        walkToWorldEdge();
        turn180();           
    }
    
    public void countEggsInRow() {
        int x = 0;
        while (!borderAhead()) {
            if (onEgg()) {
                x++;
            }
            move();
        }
        if (onEgg()) {
            x++;
        }
        showCompliment("Eggs:" + x);
        goBackToStartOfRowAndFaceBack();
    }

    public void walkToWorldEdgeClimbingOverFences() {
        while (!borderAhead()) {
            if (fenceAhead()) {
                climbOverFence();  
            } else if (canMove()) {
                move();           
            } else {
                showError("Kan niet"); 
                break;
            }
        }
    }
    
        public void walkToWorldEdgeClimbingOverFencesStopAtNest() {
        while (!borderAhead()) {
            if (onNest() && canLayEgg()) {
                layEgg();
            }
            if (fenceAhead()) {
                climbOverFence(); 
            } else if (canMove()) {
                move(); 
            } else {
                showError("Kan niet"); 
                break;
            }
        }
    }

        public void pickUpGrainsAndPrintCoordinates() {
        while (!borderAhead()) {
        step();
        if (onGrain()) {
            int x = getX();
            int y = getY();
            System.out.println("Grain op: (" + x + ", " + y + ")");
            pickUpGrain();
            } 
        }
    }   
        
        public void walkToEdgeAndLayEggsInEmptyNests() {
        while (!borderAhead()) {
        step();
        if (onNest() && canLayEgg()) {
            layEgg();
            }
        }
    }

    public boolean canLayEgg( ) {
        if( onEgg() ){
            return false;
       }else{
            return true;
           }
        }  
    }
