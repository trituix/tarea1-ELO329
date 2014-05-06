import java.util.*;

public class Ball extends PhysicsElement implements SpringAttachable{
   private static int id=0;  // Ball identification number
   private final double mass;
   private final double radius;
   private double pos_t;     // current position at time t
   private double pos_tPlusDelta;  // next position in delta time in future
   private double speed_t;   // speed at time t
   private double speed_tPlusDelta;   // speed in delta time in future
   private double a_t;    // acceleration at time t
   private double a_tMinusDelta;  // acceleration delta time ago;
   private ArrayList<Spring> springs;  // ArrayList can grow, arrays cannot. 

   protected Ball(){   // nobody can create a block without state
     this(1.0,0.1,0,0);
   }
   public Ball(double mass, double radius, double position, double speed){
      super(id++);
      this.mass = mass;
      this.radius = radius;
      pos_t = position;
      speed_t = speed;
   
      springs = new ArrayList<Spring>();
   }
   public double getRadius() {
      return radius;
   }
   public double getSpeed() {
      return speed_t;
   }
   public String getState() {
	   return String.valueOf(pos_t);
   }
   public double getMass() {
	    return mass;
   }
   public double getPosition() {
	    return pos_t;
   }
   private double getNetForce() {
	   double sum = 0;
	   for (Spring s: springs) {
		   sum += s.getForce(this);
	   }
	   return sum;
   }
  
   public void attachSpring(Spring s) {
	   springs.add(s);
   }
   

   public void computeNextState(double delta_t, MyWorld world) {
     Ball b;  // Assumption: on collision we only change speed.   
     if ((b=world.findCollidingBall(this))!= null){ /* elastic collision */
        speed_tPlusDelta=(speed_t*(mass-b.getMass())+2*b.getMass()*b.getSpeed())/(mass+b.getMass());
        a_t= getNetForce()/mass;
        pos_tPlusDelta = pos_t + 0.5*a_t*delta_t*delta_t + speed_tPlusDelta*delta_t;
     } else {
    	 a_tMinusDelta = a_t;
    	 a_t= getNetForce()/mass;
    	 speed_tPlusDelta = speed_t + 0.5*(3*a_t - a_tMinusDelta)*delta_t;
    	 //speed_tPlusDelta=speed_t + a_t*delta_t;
    	 pos_tPlusDelta = pos_t + speed_t*delta_t + (1/6)*(4*a_t - a_tMinusDelta)*delta_t*delta_t;
         //pos_tPlusDelta = pos_t + 0.5*a_t*delta_t*delta_t + speed_t*delta_t;
     }
   }
   public boolean collide(Ball b) {
	   boolean collision;
	   double xDiff;
	   double yDiff;
	   double distanceSquared;
	   xDiff = this.pos_t - b.pos_t;
	   yDiff = this.radius - b.radius;
	   distanceSquared = xDiff*xDiff + yDiff*yDiff;
	   collision = distanceSquared < (this.radius + b.radius)*(this.radius + b.radius);
	   return collision;
    }
   public void updateState(){
     pos_t = pos_tPlusDelta;
     speed_t = speed_tPlusDelta;
     a_tMinusDelta = a_t;
   }
   
   public String getDescription() {
	   return "Ball."+this.getId();
   }
}
