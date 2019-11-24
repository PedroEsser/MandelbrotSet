package Graphics;

import java.util.ArrayList;

import utils.Complex;

public class CoolCenters {

	private final static ArrayList<Complex> coolCenters;
	private static int index;
	
	static {
		coolCenters = new ArrayList<Complex>();
		addComplex(0.360240443437614363236125, -0.64131306106480317486037);
		addComplex(-0.7458471555429712, 0.12437509139737363);
		addComplex(-1.76938317919551501821384728608547378290, 0.0042368479187367722149265071713679970766);
	}
	
	private static void addComplex(double a, double b) {
		coolCenters.add(new Complex(a, b));
	}
	
	public static void go(int dir) {
		index=(index + dir + coolCenters.size())%coolCenters.size();
		Menu.setCenter(coolCenters.get(index));
	}
	
}
