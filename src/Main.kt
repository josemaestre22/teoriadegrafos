fun main(args: Array<String>) {
    if (args.isNotEmpty())
    {
        //val grafo = GrafoLista(args[0])
        val grafoM = GrafoMalla(6,6)
        grafoM.mostrar()
        RProfMod(grafoM, 0).recorrido()
        grafoM.mostrar()
        RAncho(grafoM).recorrido()

    }
    else
    {
        println("No se especifico el nombre de el archivo")
    }
}