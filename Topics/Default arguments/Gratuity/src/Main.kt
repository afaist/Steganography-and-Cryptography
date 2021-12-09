const val HUNDRED = 100.0
fun tip(bill: Int, percentage: Int = 10) = (bill / HUNDRED * percentage).toInt()