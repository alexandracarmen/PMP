import com.cra.figaro.language._
import com.cra.figaro.library.compound.{If, CPD, RichCPD, OneOf}
import com.cra.figaro.algorithm.factored.VariableElimination

object Ex
{
	def main(args: Array[String])
	{
		val hours = 6 // nr de ore contorizate
		// in primul array avem vremea pentru fiecare ora
		val weather: Array[Element[Symbol]] = Array.fill(hours)(Constant('insorit))
		//
		val umbrela: Array[Element[Symbol]] = Array.fill(hours)(Constant('nu))

		weather(0) = Select(0.5 -> 'insorit, 0.3 -> 'innorat, 0.2 -> 'ploios) // se pune random prima valoare in weather, cu anumite probabilitati
		for { hour <- 1 until hours } //  in functie de cum a fost in ora anterioara se stabileste vremea actuala, pentru fiecare ora in parte
		{
			weather(hour) = CPD(weather(hour - 1),
				'insorit -> Select(0.1 -> 'ploios, 0.3 -> 'innorat, 0.6 -> 'insorit),
				'innorat -> Select(0.35 -> 'ploios, 0.5 -> 'innorat, 0.15 -> 'insorit),
				'ploios -> Select(0.45 -> 'ploios, 0.4 -> 'innorat, 0.15 -> 'insorit))
		}

		for { hour <- 0 until hours }
		{   // in functie de vremea de afara, luam umbrela sau nu
			umbrela(hour) = CPD(weather(hour),
				'insorit -> Select(0.15 -> 'da, 0.85 -> 'nu),
				'innorat -> Select(0.65 -> 'da, 0.35 -> 'nu),
				'ploios -> Select(0.75 -> 'da, 0.25 -> 'nu))
		}

// rezolvare 2b

    weather(3).observe('innorat)
    weather(4).observe('innorat)
    println("Probabilitatea de a lua umbrela la ora 6 este de: " + VariableElimination.probability(umbrela(5),'da))

	weather(3).unobserve
	weather(4).unobserve

// rezolvare 2c

	umbrela(4).observe('nu)
	println("Probabilitatea ca acum 3 ore sa fi plouat daca la ora 5 nu am luat umbrela este: " + VariableElimination.probability(weather(1),'ploios))
	}
}
