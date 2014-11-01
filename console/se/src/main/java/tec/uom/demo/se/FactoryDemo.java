package tec.uom.demo.se;

import javax.measure.Quantity;
import javax.measure.function.QuantityFactory;
import javax.measure.quantity.Length;
import javax.measure.quantity.Mass;

import tec.uom.se.quantity.OldProxyQuantityFactory;
import tec.uom.se.quantity.ProxyQuantityFactory;
import tec.uom.se.util.SI;

public class FactoryDemo {
	public static void main(String... args) {
		OldProxyQuantityFactory<Length> lenFactory = OldProxyQuantityFactory.getInstance(Length.class);
		Length len = lenFactory.create(10, SI.METRE);
		
		System.out.println(len);
//		Quantity<Length> len2 = len.multiply(2);
//		System.out.println(len2);
		
		QuantityFactory<Mass> massFactory = ProxyQuantityFactory.getInstance(Mass.class);
		Quantity<Mass> mass = massFactory.create(50, SI.KILOGRAM);
		System.out.println(mass);
	}
}