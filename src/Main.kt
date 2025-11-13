fun main(args: Array<String>) {
    if (args.isNotEmpty())
    {
        val grafo = GrafoLista(args[0])
        var titulo = "Grafo"
        println("\u001B[1m$titulo\u001B[0m")
        grafo.mostrarGrafo()
        println()

        titulo ="Anchura"
        println("\u001B[1m$titulo\u001B[0m")
        val rAnch = grafo.Recorrido()
        rAnch.anchura()
        rAnch.clasificarAristas()

        titulo = "Caminos"
        println("\u001B[1m$titulo\u001B[0m")
        for (v in 0..< grafo.ady.size)
        {
            println("Camino desde ${rAnch.origen} hasta $v")
            rAnch.mostrarCamino(v)
            println()
        }

        titulo = "\nProfundidad"
        println("\u001B[1m$titulo\u001B[0m")
        val rProf = grafo.Recorrido()
        rProf.profundidad()
        rProf.clasificarAristas()

        titulo = "Caminos"
        println("\u001B[1m$titulo\u001B[0m")
        for (v in 0..< grafo.ady.size)
        {
            println("Camino desde ${rProf.origen} hasta $v")
            rProf.mostrarCamino(v)
            println()
        }
    }
    else
    {
        println("No se especifico el nombre de el archivo")
    }
}