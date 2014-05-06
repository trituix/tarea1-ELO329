public class Spring extends PhysicsElement {
   private static int id=0;  // Spring identification
   protected final double restLength;
   private final double stiffness;
   protected Ball a_end, b_end;

   private Spring(){   // nobody can create a block without state
      this(0,0);
   }
   public Spring(double restLength, double stiffness){
      super(id++);
      this.restLength = restLength;
      this.stiffness = stiffness;
      a_end = b_end = null;
   }
   public void attachEnd (Ball sa) {  // note: we attach a spring to a ball, 
      if(a_end==null)                             //       not the other way around.
         a_end = sa;                     
      else if(b_end == null)
    	  b_end = sa;
      else {
    	  System.out.println("Solo puedes unir dos bolas a un resorte");
    	  System.exit(-1);
      }
      sa.attachSpring(this);
   }
   
   public double getMass() {
	   double springMass = 0;
	   if (a_end != null)
		   springMass += a_end.getMass();
	   if (b_end != null)
		   springMass += b_end.getMass();
	   return springMass;
   }
   
   public double getAendPosition() {
      if (a_end != null)
         return a_end.getPosition()+a_end.getRadius();
      if (b_end != null)
         return b_end.getPosition()-restLength;
      return 0;
   }
   public double getBendPosition() {
	   if (b_end != null)
		   return b_end.getPosition()-b_end.getRadius();
	   if (a_end != null)
	       return a_end.getPosition()+restLength;
	   return 0;
   }
   
   public double getForce(Ball ball) {
      double force = 0;
      if ((a_end == null) || (b_end == null))
         return force;
      if ((ball != a_end) && (ball != b_end))
         return force;
      if(ball == a_end) {
    	  double delta_pos = (this.getBendPosition()-this.getAendPosition()) - restLength;
    	  return stiffness*delta_pos;
      }
      if(ball == b_end) {
    	  double delta_pos = (this.getBendPosition()-this.getAendPosition()) - restLength;
    	  return -1*stiffness*delta_pos;
      }
      return force;
   }
   public void computeNextState(double delta_t, MyWorld w) {
	   
   }
   
   public void updateState() {
	   
   }

   public String getDescription() {
      return "Spring_"+ getId()+":a_end\tb_end";
   }
   public String getState() {
	  return String.valueOf(getAendPosition()) + "\t\t" + String.valueOf(getBendPosition());
   }
}