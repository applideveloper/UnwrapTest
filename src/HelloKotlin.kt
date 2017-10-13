fun main(args: Array<String>) {
    val string1: String? = HelloJava().get1()
    val string2: String? = HelloJava().get2()
    val string3: String? = HelloJava().getNull()
    // val string4: String = HelloJava().getNull()
    // Exception in thread "main" java.lang.IllegalStateException: HelloJava().getNull() must not be null
    // at HelloKotlinKt.main(HelloKotlin.kt:5)

    println(string1) // 1
    println(string2) // 2
    println(string3) // null
    // println(string4) // string

    try {
        string1?.let { string1 ->
            string2?.let { string2 ->
                string3?.let { string3 ->
                    println(string1) // String
                    println(string2) // String
                    println(string3) // String
                    println("$string1 + $string2 + $string3 = ${string1.toInt() + string2.toInt() + string3.toInt()}") // Int
                } ?: run {
                    throw Exception("string3 is null")
                }
            } ?: run {
                throw Exception("string2 is null")
            }
        } ?: run {
            throw Exception("string1 is null")
        }
    } catch(numberFormatException: NumberFormatException) {
        println(numberFormatException.message)
    } catch(exception: Exception) {
        println(exception.message)
    }

    try {
        when (string1) {
            null -> throw Exception("string1 is null")
            else -> when (string2) {
                null -> throw Exception("string2 is null")
                else -> when (string3) {
                    null -> throw Exception("string3 is null")
                    else ->
                        // string1: String?
                        // string2: String?
                        // string3: String?
                        println("$string1 + $string2 + $string3 = ${string1.toInt() + string2.toInt() + string3.toInt()}")
                }

            }
        }
    } catch(numberFormatException: NumberFormatException) {
        println(numberFormatException.message)
    } catch(exception: Exception) {
        println(exception.message)
    }

    try {
        if (string1 != null && string2 != null && string3 != null) {
            println(string1) // String?
            println(string2) // String?
            println(string3) // String?
            println("$string1 + $string2 + $string3 = ${string1.toInt() + string2.toInt() + string3.toInt()}") // Int
        } else {
            throw Exception("string1 is null or string2 is null or string3 is null")
        }
    } catch(numberFormatException: NumberFormatException) {
        println(numberFormatException.message)
    } catch(exception: Exception) {
        println(exception.message)
    }
 }

