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

val numSnowyDays= binomial(7,0.6)
 for binomial{7 <- SnowyDays}
 println
 
val snowy=Binomial(7,0.6)
	println("normal : "+(VariableElimination.probability(snowy,3)+VariableElimination.probability(snowy,4)+VariableElimination.probability(snowy,5))


    //1.C
    //cu cat se creste numarul de rulari cu atat se mareste acuratetea programului