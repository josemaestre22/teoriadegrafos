fun main(args: Array<String>) {
    if (args.isNotEmpty())
    {
        val grafo = GrafoLista(args[0])
        grafo.recorridoAnchura()
        grafo.contarAristas()

        for (v in 0..<grafo.ady.size)
        {
            grafo.mostrarCamino(v)
            println()
        }

    }
    else
    {
        println("No se especifico el nombre de el archivo")
    }
}