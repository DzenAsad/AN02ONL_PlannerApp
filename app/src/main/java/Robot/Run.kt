import kotlinRobot.fabric.RobotFabric
import kotlin.random.Random

fun main() {
    val listBrands = listOf("Samsung", "Toshiba", "Lenovo")
    fun randomPrice(): Int {
        return Random.nextInt(100)
    }


    listBrands.forEach {
        val robotFabric = RobotFabric(it, randomPrice())
        val robot = robotFabric.getObj()
        robot.head.speak()
        robot.hand.upHand()
        robot.leg.step()
        println("Цена: ${robot.price}")
    }
}


