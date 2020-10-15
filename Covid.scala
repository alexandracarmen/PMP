package Lab3

import com.cra.figaro.algorithm.factored._
import com.cra.figaro.language._
import com.cra.figaro.library.compound_

object Covid{

    private val Covid = Flip (2)

  val aiCovid =CPD (febra, tuse,
     (false, false)-> Fliip(0.02),
     (true, false) -> Flip (0,35),
     (false, true) -> Flip (0,25),
     (true, true) -> Flip (0,85))

  val teInternezi = CPD(aiCovid,
     true ->Flip(0,8),
     false ->Flip (0.2))

  def main(args: Array[String]) {
       aiCovid.observe(true)
       val alg = VariableElimination(febra, tuse)
       alg.start()
       println("Probability of febra:" + alg.probability(febra, tuse))
       alg.kill

  } 
     
}