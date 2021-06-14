import java.math.BigInteger
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.floor
import kotlin.math.ln


fun main(){
    decodeWithNePhiN()
}

fun decodeWithNePhiN(){
    /* EXAMPLE

    Decrypt my super sick RSA:
    c: 421345306292040663864066688931456845278496274597031632020995583473619804626233684
    n: 631371953793368771804570727896887140714495090919073481680274581226742748040342637
    e: 65537

    GO TO https://www.alpertron.com.ar/ECMC.HTM AND GET FACTORING OF n
    FOR THIS EXAMPLE THE RESULT OF FACTORING IS
    φ(n) = 631371953793368771804570727896887140714061729769155038068711341335911329840163136
     */


    val sc = Scanner(System.`in`)
    val d: BigInteger
    val m: BigInteger

    // cipertext c, plaintext m

    println("Insert n")
    val n = BigInteger(sc.nextLine())

    println("Input e")
    val e = BigInteger(sc.nextLine())

    println("Input c")
    val c = BigInteger(sc.nextLine())

    println("Input φ(n)")
    val phiN = BigInteger(sc.nextLine())

    sc.close()

    d = e.modInverse(phiN)
    m = c.modPow(d, n)

    println("d = $d")
    println("m = $m")

    println("m in base 256 = " + base256(m))
    println(
        """
                 
                ${encode256(base256(m))}
                """.trimIndent()
            //.split("").reversed().joinToString("")
    )
}

fun base256(M: BigInteger): ArrayList<BigInteger> {
    val base = BigInteger("256")
    val message256 = ArrayList<BigInteger>()
    var sisa = M
    var k: BigInteger
    val z = M.toString().toDouble()
    val p = floor(ln(z) / ln(256.0))
    val r = p.toInt()
    for (j in 0..r) {
        k = sisa.mod(base)
        sisa = sisa.divide(base)
        message256.add(k)
    }
    return message256
}

fun encode256(ascii: ArrayList<BigInteger>): String? {
    var ascii256 = ""
    var g: Int
    for (i in ascii.indices) {
        g = ("" + ascii[i]).toInt()
        ascii256 += g.toChar()
    }
    return ascii256
}