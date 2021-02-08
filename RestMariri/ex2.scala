import com.cra.figaro.library.atomic.discrete.Binomial
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.library.collection
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.library.compound.{*, OneOf, RichCPD}
import com.cra.figaro.language.Element._
import com.cra.figaro.language.ElementCollection
import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.library.collection
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.library.compound.{*, OneOf, RichCPD}
import com.cra.figaro.language.Element._
import com.cra.figaro.language.ElementCollection
import com.cra.figaro.library.atomic.continuous.Normal
import com.cra.figaro.algorithm.sampling.Importance


object test {
  def main(args: Array[String]): Unit = {
    val avg_temperature = Normal(7, 5)
    val variance = Flip(0.5)
    variance1 = If(variance, 20, 30)
    val avg_temp = Normal(7, variance1)
    //def greaterThan50(d: Double) = d > 50
    //println(Importance.probability(avg_temperature, greaterThan50 _))

  }

}