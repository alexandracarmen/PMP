package Lab4

import com.cra.figaro.library.compound._
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.factored._

object HelloWorldbedPart{

    def main(args: Array[String]){
        val bedPart = Flip(0.5)
        val greeting = If(bedPart,
        Select(0.6->"Hello world!",0.4->"Howdy, universe!"),
        Select(0.2 ->"Oh no, not again!",0.8 ->"Oh no, not again!") )
        println("Probability of Hello world:")
        println(VariableElimination.probability(greeting, "Helloworld!"))
        println("Probability of Goodbye world:")
        println(VariableElimination.probability(greeting,"Howdy, universe!"))
        println("Probability of Oh no, not again!:")
        println(VariableElimination.probability(greeting,"Oh no, not again!"))
    }
}