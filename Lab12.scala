import com.cra.figaro.language._
import com.cra.figaro.algorithm.sampling._
import com.cra.figaro.library.compound._
import com.cra.figaro.library.atomic.discrete._
import com.cra.figaro.algorithm.factored._
import com.cra.figaro.experimental.normalproposals.Normal
import scala.collection._

object Lab12 extends App with scalax.chart.module.Charting {
   
        val x0 = Apply(Normal(0.75, 0.3), (d: Double) => d.max(0).min(1))
        val y0 = Apply(Normal(0.4, 0.2), (d: Double) => d.max(0).min(1))
        val x = Flip(x0)
        val y = Flip(y0)
        val z = If(x === y, Flip(0.8), Flip(0.2))

        z.observe(false)

        val veAnswer = VariableElimination.probability(y,true)
        println(veAnswer)

val data = for { i <- 1000 to 10000 by 1000 } yield {    
var totalSquaredError = 0.0
    for { j <- 1 to 100 } {      
        val imp = Importance(i, y)      
        imp.start()      
        val impAnswer = imp.probability(y, true)      
        val diff = veAnswer - impAnswer      
        totalSquaredError = totalSquaredError + diff * diff    
        }    
    val rmse = math.sqrt(totalSquaredError / 100)
    println(i + " samples: RMSE = " + rmse)
    (i,rmse)      
    }
    val chart = XYLineChart(data)
    chart.show()
    chart.saveAsPNG("D:/tmp/chart1.png")



val data2 =   for { i <- 10000 to 100000 by 10000 } yield {    
    var totalSquaredError = 0.0    
    for { j <- 1 to 100 } {      
        Universe.createNew()      
        val x = Flip(0.8)      
        val y = Flip(0.6)      
        val z = If(x === y, Flip(0.9), Flip(0.1))      
        z.observe(false)      
        val mh = MetropolisHastings(i, ProposalScheme.default, y)
        mh.start()      
        val mhAnswer = mh.probability(y, true)      
        val diff = veAnswer - mhAnswer      
        totalSquaredError = totalSquaredError + diff * diff    
        }    
    val rmse = math.sqrt(totalSquaredError / 100)    
    println(i + " samples: RMSE = " + rmse)
    (i,rmse)
    }
    val chart2 = XYLineChart(data2)
    chart2.show()
    chart2.saveAsPNG("D:/tmp/chart2.png")



 x1 = Flip(0.999)
 y1 = Flip(0.99)
 z1 = If(x1 === y1, Flip(0.9999), Flip(0.001))

z1.observe(false)

 veAnswer1 = VariableElimination.probability(y1,true)
println(veAnswer1)

   
 val totalSquaredError1 = 0.0
        val imp = Importance(1000000, y1)      
        imp.start()      
        val impAnswer = imp.probability(y1, true)      
        val diff = veAnswer - impAnswer      
        totalSquaredError1 = totalSquaredError + diff * diff    
            
    val rmse1 = math.sqrt(totalSquaredError / 100)
    println(1000000 + " samples: RMSE = " + rmse1)
    

  
     totalSquaredError1 = 0.0    
        Universe.createNew()      
        val x = Flip(0.999)      
        val y = Flip(0.99)      
        val z = If(x === y, Flip(0.9999), Flip(0.0001))      
        z.observe(false)      
        val mh = MetropolisHastings(10000000, ProposalScheme.default, y)
        mh.start()      
        val mhAnswer = mh.probability(y, true)      
        val diff = veAnswer1 - mhAnswer      
        totalSquaredError1 = totalSquaredError1 + diff * diff    
            
     rmse1 = math.sqrt(totalSquaredError / 100)    
    println(1000000 + " samples: RMSE = " + rmse1)
    
}
