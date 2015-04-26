/**
 *  Unit-API - Units of Measurement API for Java
 *  Copyright (c) 2005-2015, Jean-Marie Dautelle, Werner Keil, V2COM.
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 *
 * 3. Neither the name of JSR-363, Unit-API nor the names of their contributors may be used to endorse or promote products derived from this software without specific prior written permission.
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
package tec.uom.demo.edison.hello;

import java.text.MessageFormat;

import javax.measure.Quantity; 
import javax.measure.Unit;
import javax.measure.quantity.LuminousFlux;
import javax.measure.quantity.Power;

import tec.uom.se.quantity.Quantities;
import tec.uom.se.unit.SI;

public class HelloEdison {

	public static void main(String[] args) {
		System.out.println("Hello Edison.");
		System.out.println("How about a little lightbulb efficiency calculation?;-)");
		final MessageFormat question = new MessageFormat(
				"What is the power consumption of a lamp that has luminous flux of {0} and luminous efficacy of {1} lumens per watt (lm/W)?");
		final MessageFormat answer = new MessageFormat("You need a {0} lightbulb.");
		
		Quantity<LuminousFlux> lm = Quantities.getQuantity(900, SI.LUMEN);
		final Unit<LuminousEfficacy> LM_PER_WATT = SI.LUMEN.divide(SI.WATT).asType(LuminousEfficacy.class); // TODO make available in unit system
		final Number LM_VALUE = 15;
		final Object[] questionArgs = {lm, LM_VALUE};
		System.out.println(question.format(questionArgs));
		Quantity<LuminousEfficacy>  luminousEfficacy = Quantities.getQuantity(LM_VALUE, LM_PER_WATT);
		Quantity<Power> p = lm.divide(luminousEfficacy).asType(Power.class);
		final Object[] answerArgs = {p};
		System.out.println(answer.format(answerArgs));
	}
}
