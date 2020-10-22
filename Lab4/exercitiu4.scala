package Lab4

import com.cra.figaro.library.compound._
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.library.atomic.discrete.FromRange

object exercitiu4{
	val dice1 = FromRange(1, 7)
	val dice2 = FromRange(1, 7)
	val total = Apply(dice1, dice2, (i1: Int, i2: Int) => i1 + i2)
	println(VariableElimination.probability(total, 11))

}