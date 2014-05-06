public class Spring extends PhysicsElement {
   private static int id=0;  // Spring identification
   protected final double restLength;
   private final double stiffness;
   protected PhysicsElement a_end, b_end;

   protected Spring(){   // nobody can create a block without state
      this(0,0);
   }
   public Spring(double restLength, double stiffness){
      super(id++);
      this.restLength = restLength;
      this.stiffness = stiffness;
      a_end = b_end = null;
   }
   public void attachEnd (PhysicsElement sa) {  // note: we attach a spring to a ball, 
      if(a_end==null)                             //       not the other way around.
         a_end = sa;                     
      else if(b_end == null)
    	  b_end = sa;
      else {
    	  System.out.println("Solo puedes unir dos bolas a un resorte");
    	  System.exit(-1);
      }
      if(sa.getClass().equals(Ball.class)) {
    	  ((Ball) sa).attachSpring(this);
      }
      else if(sa.getClass().equals(FixedHook.class)) {
    	  ((FixedHook) sa).attachSpring(this);
      }
      
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
	   double position = 0;
	   if (a_end != null) {
		   if(a_end.getClass().equals(Ball.class)) {
			   position = ((Ball) a_end).getPosition() + ((Ball) a_end).getRadius();
		   }
		   else if(a_end.getClass().equals(FixedHook.class)) {
			   position = ((FixedHook) a_end).getPosition() + ((FixedHook) a_end).getRadius();
		   }
		   return position;
	   }
	   if (b_end != null) {
		   if(b_end.getClass().equals(Ball.class)) {
			   position = ((Ball) b_end).getPosition()-restLength;
		   }
		   else if(b_end.getClass().equals(FixedHook.class)) {
			   position = ((FixedHook) b_end).getPosition()-restLength;
		   }
		   return position;
	   }
	   return position;
   }
   public double getBendPosition() {
	   double position = 0;
	   if (b_end != null) {
		   if(b_end.getClass().equals(Ball.class)) {
			   position = ((Ball) b_end).getPosition() - ((Ball) b_end).getRadius();
		   }
		   else if(b_end.getClass().equals(FixedHook.class)) {
			   position = ((FixedHook) b_end).getPosition() - ((FixedHook) b_end).getRadius();
		   }
		   return position;
	   }
	   if (a_end != null) {
		   if(a_end.getClass().equals(Ball.class)) {
			   position = ((Ball) a_end).getPosition()-restLength;
		   }
		   else if(a_end.getClass().equals(FixedHook.class)) {
			   position = ((FixedHook) a_end).getPosition()-restLength;
		   }
		   return position;
	   }
	   return position;
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
	  return String.valueOf(getAendPosition()) + "\t" + String.valueOf(getBendPosition());
   }
}