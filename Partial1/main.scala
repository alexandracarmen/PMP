package Partial1

import com.cra.figaro.library.atomic.discrete
import com.cra.figaro.language.Chain
import com.cra.figaro.library.compound.{RichCPD, OneOf, *}
import com.cra.figaro.language{Flip, Constant, Apply}
import com.cra.figaro.algorithm.factored.VariableElimination

object Partial1{
    val setatAlarma=Flip(0.9)//Probabilitatea de 10/100 sa uitam sa setam alarma

    val trezitTarziu = CPD(setatAlarma,
    (true)->Flip(0.9),//avem sansa de 0.9(10/100) sa adormim la loc desi a sunat alarma
    (false)-> Flip(0.1)//desi alarma  nu a fost setata, in 1 din 10 cazuri de trezim la timp, fara alarma
    )

    val intarzieAutobuzul =Flip(0.2)//autobuzul are sanasa sa intarzie 20/100

    val ajungLaTimp = CPD(trezitLaTimp, intarzieAutobuzul,
    (false, false)-> Flip(0.9),//ne trezim tarziu si autobuzul nu intarzie
    (false, true)->Flip(0.2),//ne trezim tarziu si autobuzul intarzie
    (true, true)->Flip(0.3),//ne trezim la timp si autobuzul nu intarzie
    (true, true)->Flip(0.1)//ne trezim la timp si intarzie autobuzul
    )
  

      """ b) Interogari: """

    """ 1. probabilitatea să ajungi la serviciu la timp, având în vedere ca ai adormit?"""
    wakeUpLate.observe(true)
    println("Probabilitatea (1) de a ajaunge la timp la serviciu: " + ajungLaTimp)
    //    OR:
    //    val alg = Importance(30000, trezitTarziu, intarzieAutobuzul)
    //    alg.start()
    //    println("Probabilitatea (2) de a ajunge la timp la serviciu: " + alg.probability(trezitTarziu, true))
    //    alg.kill

    trezitTarziu.unobserve()

    """ 2. Având în vedere ca ai ajuns la timp la serviciu, care este probabilitatea să îți fi
setat alarma? """

    ajungLaTimp.observe(true)
    println("Probabilitatea a iti fi setat alarma si sa ajungi la timp la serviciu: " + (1-forgetSetAlarm))
    // P(a setat alarma) = 1 - P(a uitat sa seteze alarma)
    ajungLaTimp.unobserve()

    """ 3. Care este probabilitatea sa adormi? """

    trezitTarziu.observe(true)
    println(trezitTarziu)
    trezitTarziu.unobserve()

   



}

  