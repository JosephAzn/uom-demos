/*
 *  Units of Measurement Console Demos
 *  Copyright (c) 2005-2019, Werner Keil and others.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-385, Units of Measurement nor the names of their contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED
 * AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE,
 * EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package tech.uom.demo.systems.common14;

import static tech.units.indriya.unit.Units.KILOMETRE_PER_HOUR;
import static tech.units.indriya.unit.Units.METRE;
import static tech.uom.demo.systems.common14.types.SaffirSimpsonHurricaneWindScale.Category.FIVE;
import static tech.uom.demo.systems.common14.types.SaffirSimpsonHurricaneWindScale.Category.FOUR;
import static tech.uom.demo.systems.common14.types.SaffirSimpsonHurricaneWindScale.Category.ONE;
import static tech.uom.demo.systems.common14.types.SaffirSimpsonHurricaneWindScale.Category.THREE;
import static tech.uom.demo.systems.common14.types.SaffirSimpsonHurricaneWindScale.Category.TROPICAL_DEPRESSION;
import static tech.uom.demo.systems.common14.types.SaffirSimpsonHurricaneWindScale.Category.TROPICAL_STORM;
import static tech.uom.demo.systems.common14.types.SaffirSimpsonHurricaneWindScale.Category.TWO;
import static javax.measure.MetricPrefix.KILO;
import static systems.uom.common.USCustomary.MILE_PER_HOUR;

import javax.measure.Quantity;
import javax.measure.quantity.Length;
import javax.measure.quantity.Speed;
import javax.measure.quantity.Time;

import tech.units.indriya.quantity.Quantities;
import tech.uom.demo.systems.common14.types.SaffirSimpsonHurricaneWindScale;

/**
 * @author Werner Keil
 * @version 1.0
 * @see {@link SaffirSimpsonHurricaneWindScale}
 */
public class ThePerfectStorm {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final SaffirSimpsonHurricaneWindScale std = SaffirSimpsonHurricaneWindScale.of(
				null, Quantities.getQuantity(38, MILE_PER_HOUR), TROPICAL_DEPRESSION);
		System.out.println(std);

		final var sts = SaffirSimpsonHurricaneWindScale.of(
				Quantities.getQuantity(39, MILE_PER_HOUR),
				Quantities.getQuantity(73, MILE_PER_HOUR), TROPICAL_STORM);
		System.out.println(sts);

		final var s1 = SaffirSimpsonHurricaneWindScale.of(
				Quantities.getQuantity(74, MILE_PER_HOUR),
				Quantities.getQuantity(95, MILE_PER_HOUR), ONE);
		System.out.println(s1);

		final var s2 = SaffirSimpsonHurricaneWindScale.of(
				Quantities.getQuantity(96, MILE_PER_HOUR),
				Quantities.getQuantity(110, MILE_PER_HOUR), TWO);
		System.out.println(s2);

		final var s3 = SaffirSimpsonHurricaneWindScale.of(
				Quantities.getQuantity(111, MILE_PER_HOUR),
				Quantities.getQuantity(129, MILE_PER_HOUR), THREE);
		System.out.println(s3);

		final var s4 = SaffirSimpsonHurricaneWindScale.of(
				Quantities.getQuantity(130, MILE_PER_HOUR),
				Quantities.getQuantity(156, MILE_PER_HOUR), FOUR);
		System.out.println(s4);

		final var s5 = SaffirSimpsonHurricaneWindScale.of(
				Quantities.getQuantity(157, MILE_PER_HOUR), null, FIVE);
		System.out.println(s5);

		int argument = -1;
		if (args!= null && args.length>0) {
			argument = Integer.valueOf(args[0]).intValue();
		}

		SaffirSimpsonHurricaneWindScale scale = switch (argument) {
			case 0 -> sts;
			case 1 -> s1;
			case 2 -> s2;
			case 3 -> s3;
			case 4 -> s4;
			case 5 -> s5;
			default -> std;
		};

		final Quantity<Speed> metricSpeed = scale.hasMaximum() ?
				scale.getMaximum().to(KILOMETRE_PER_HOUR) :
				scale.getMinimum().to(KILOMETRE_PER_HOUR);

		System.out.print(metricSpeed);
		System.out.println(" (" + scale.getCategory() + ")");
		Quantity<Length> l = Quantities.getQuantity(500, KILO(METRE));
		System.out.println(String.format("Distance: %s", l));
		Quantity<Time> timeToEvacuate = l.divide(metricSpeed).asType(Time.class);
		//Quantity<?> timeToEvacuate = l.divide(metricSpeed); if you don't want to cast ;-)
		System.out.println(String.format("Time to evacuate: %s", timeToEvacuate));
	}
}
