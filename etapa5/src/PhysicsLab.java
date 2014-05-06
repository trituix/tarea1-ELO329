public class PhysicsLab {
   public static void main(String[] args) {
      if (args.length != 3)  {
        System.out.println("usage: java PhysicsLab <delta_time[s]> <end_time[s]> <sampling_time[s]>");
        System.exit(-1);
      }
      double deltaTime = Double.parseDouble(args[0]);    // [s]
      double endTime = Double.parseDouble(args[1]);      // [s]
      double samplingTime = Double.parseDouble(args[2]); // [s]
      MyWorld world = new MyWorld(System.out);
      
      double mass = 1.0;      // 1 [kg] 
      double radius = 0.1;    // 10 [cm] 
      double position = 0;  // 0 [m] 
      double speed = 0;     // 0 [m/s]
      FixedHook fh0 = new FixedHook(position);
      FixedHook fh1 = new FixedHook(position + 3);
      Ball b0 = new Ball(mass, radius, 0.5, 0);
      Ball b1 = new Ball(mass, radius, 2.5, 0);
      Spring spring0 = new Spring(1.0, 1.0);
      Spring spring1 = new Spring(1.0, 1.0);
      spring0.attachEnd(fh0);
      spring0.attachEnd(b0);
      spring1.attachEnd(b1);
      spring1.attachEnd(fh1);
      
      world.addElement(spring0);
      world.addElement(spring1);
      world.addElement(fh0);
      world.addElement(fh1);
      world.addElement(b0);
      world.addElement(b1);
      world.simulate(deltaTime, endTime, samplingTime); // delta time[s], total simulation time [s].
   }
}