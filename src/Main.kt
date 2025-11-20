fun main(args: Array<String>) {
    if (args.isNotEmpty())
    {
        val grafoM = GrafoMalla(args[0].toInt(), args[1].toInt())

        RProfMod(grafoM).recorrido()
        grafoM.mostrar()
        RAncho(grafoM).recorrido()
    }
    else
    {
        println("Faltaron argumentos")
    }
}