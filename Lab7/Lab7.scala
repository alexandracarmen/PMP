import com.cra.figaro.language.Select
import com.cra.figaro.library.atomic.continuous.Uniform
import com.cra.figaro.language.{Element, Chain, Apply}
import com.cra.figaro.library.collection.Container
import com.cra.figaro.algorithm.factored.VariableElimination
import com.cra.figaro.algorithm.sampling.Importance

object ex3
{
    def main(args: Array[String])
    {
        //3 4 3 5 4 3 5 4 5 3 4 5 4 5 3 4 3 4
        val par = for {hole <- 0 until 18} yield args(hole).toInt
        val skill = Uniform(0.0, 8.0/13.0)
        val shots = Array.tabulate(18)((hole: Int) => Chain(skill, (s: Double) =>
                                Select(s/8.0 -> (par(hole)-2),
                                       s/2.0 -> (par(hole)-1),
                                       s -> par(hole),
                                       (4.0/5.0) * (1.0 - (13.0 * s)/8.0) -> (par(hole)+1),
                                       (1.0/5.0) * (1.0 - (13.0 * s)/8.0) -> (par(hole)+2))))
        val vars = for { i <- 0 until 18} yield shots(i)
        val sum = Container(vars:_*).reduce(_+_)
        def greaterThan80(s: Int) = s >= 80
        def greaterThan03(s: Double) = s >= 0.3
        skill.addConstraint(s => if(s >= 0.3) 1.0; else 0.0)
        println(Importance.probability(sum, greaterThan80 _))
        // sum.observe(80)
        // println(Importance.probability(skill, greaterThan03 _))

        val skill_player1 = Uniform(0.0, 8.0/13.0)
        val shots_player1 =  Array.tabulate(18)((hole: Int) => Chain(skill_player1, (s: Double) =>
                                                Select(s/8.0 -> (par(hole)-2),
                                                       s/2.0 -> (par(hole)-1),
                                                       s -> par(hole),
                                                       (4.0/5.0) * (1.0 - (13.0 * s)/8.0) -> (par(hole)+1),
                                                       (1.0/5.0) * (1.0 - (13.0 * s)/8.0) -> (par(hole)+2))))
        val skill_player2 = Uniform(0.0, 8.0/13.0)
        val shots_player2 =  Array.tabulate(18)((hole: Int) => Chain(skill_player2, (s: Double) =>
                                                Select(s/8.0 -> (par(hole)-2),
                                                       s/2.0 -> (par(hole)-1),
                                                       s -> par(hole),
                                                       (4.0/5.0) * (1.0 - (13.0 * s)/8.0) -> (par(hole)+1),
                                                       (1.0/5.0) * (1.0 - (13.0 * s)/8.0) -> (par(hole)+2))))
        val shots_p1 = for { i <- 0 until 18} yield shots_player1(i)
        val shots_p2 = for { i <- 0 until 18} yield shots_player2(i)
        val points_p1 = Array.tabulate(18)((hole: Int) => Apply(shots_p1(hole), shots_p2(hole), (p1: Int, p2: Int) =>
                                        if (p1 > p2) -1; else if (p1 < p2) 1; else 0))
        val p1 = for { i <- 0 until 18} yield points_p1(i)
        val total = Container(p1:_*).reduce(_+_)
        def greaterThan0(s: Int) = s > 0

        skill_player1.observe(0.5)
        skill_player2.observe(0.5)
        println(Importance.probability(total, greaterThan0 _))
    }
}
