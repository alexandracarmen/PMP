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


//2.A
//temperatura medie are distributia  normala cu media 7 si variatia 5
val tempMean = Normal(7, 5)
//variatia poate fi 20 sau 30 cu probabilitatea de 0.5
	val tempVariance = Select(0.5 -> 20.0, 0.5 -> 30.0)
	val temperature = Normal(tempMean, tempVariance)
    println(Importance.probability(temperature, (d: Double) => d < 50&& d>20)

 //2.B   
 //interogam modelul pentru a afla probabilitatea ca temp sa fie cuprinsa intre 20 si 50
val temperature = Normal(9, tempVariance)
    println(Importance.probability(temperature, (d: Double) => d < 50&& d>20)
    
//2.C
tempMean.observe(9)
	println(Importance.probability(temperature, (d: Double) => d < 50&& d>20)