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


//clasa State va avea un singur parametru, un Symbol(A,B,C,D <=> 'buna ,'nuPreaBuna ,'bolnav ,'decedat)
	abstract class State(val dictionary: Dictionary){
		val Markov: Symbol;
	}

	//valoarea initiala va fi 'buna
	class InitialState(val dictionary: Dictionary, val Markov: Symbol)extends State{
		val Markov=Element('buna);
	}

	//pe parcursul iteratiilor, noua valoare Markov va fi interpretata bazanduse de cea anterioara
	//cu ajutorul valorile de mai joi 
	class NextState(val dictionary: Dictionary, val Markov: Symbol )extends State{
			Markov = CPD(Markov,
				'buna -> Select(0.721 -> 'buna, 0.202 -> 'nuPreaBuna, 0.067 -> 'bolnav,0.1->'decedat),
				'nuPreaBuna -> Select(0.581 -> 'nuPreaBuna, 0.407 -> 'bolnav,0.012->'decedat),
				'bolnav -> Select0(0.075 -> 'bolnav,0.25->'decedat),
				'decedat -> Select(1->'decedat));
	}