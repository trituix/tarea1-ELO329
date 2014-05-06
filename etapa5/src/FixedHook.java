import java.util.*;

public class FixedHook extends PhysicsElement implements SpringAttachable{
	private static int id = 0;
	private final double mass;
	private final double radius;
	private double pos_t;
	private ArrayList<Spring> springs;
	
	protected FixedHook() {
		this(1.0);
	}
	
	public FixedHook(double position) {
		super(id++);
		this.mass = 0;
		this.pos_t = position;
		this.radius = 0;
		springs = new ArrayList<Spring>();
	}

	public void attachSpring(Spring s) {
		springs.add(s);
		
	}

	public double getPosition() {
		return this.pos_t;
	}

	public double getRadius() {
		return this.radius;
	}
	
	public String getDescription() {
		return "FixedHook_"+this.getId();
	}

	public String getState() {
		return String.valueOf(this.pos_t);
	}

	public void computeNextState(double delta_t, MyWorld myWorld) {
		// TODO Apéndice de método generado automáticamente
		
	}

	public void updateState() {
		// TODO Apéndice de método generado automáticamente
		
	}

	public double getMass() {
		return this.mass;
	}
} 