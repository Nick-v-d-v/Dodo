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

    public void walkToWorldEdgePrintingCoordinates(){
        while( ! borderAhead() ){
        int x = getX();
        int y = getY();
        System.out.println("(" + x + ", " + y + ")");
        move();
        }
    }
    
    public void walkToWorldEdge(){
        while( ! borderAhead() ){
        move();
        }
    }
    
    public void goBackToStartOfRowAndFaceBack() {
        turn180();
        walkToWorldEdge();
        turn180();           
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
