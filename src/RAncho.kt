class RAncho(val grafo: GrafoMalla)
{
    val origen = grafo.verticesGrado1().random()
    val distancia = IntArray(grafo.tamaño) {-1}
    val visitado = BooleanArray(grafo.tamaño) {false}
    val predecesor = IntArray(grafo.tamaño) {-1}
    var cuenta = 0

    fun mostrarCamino(destino: Int)
    {
        if (destino == origen)
            print(origen)
        else
        {
            if (predecesor[destino] == -1)
            {
                println("no existe camino desde $destino al origen\n")
            }
            else
            {
                mostrarCamino(predecesor[destino])
                print(" ")
                print(destino)
            }
        }
    }

    fun recorrido()
    {
        val cola = ArrayDeque<Int>()
        var v = -1
        var adyacentes: List<Int>


        visitado[origen] = true
        distancia[origen] = 0
        cuenta++
        cola.addLast(origen)

        while(cola.isNotEmpty())
        {
            v = cola.removeFirst()
            adyacentes = grafo.adyacentes(v)

            for (w in adyacentes)
            {
                if(!visitado[w])
                {
                    visitado[w] = true
                    cuenta++
                    predecesor[w] = v
                    distancia[w] = distancia[v] + 1
                    cola.addLast(w)
                }
            }
        }

        println(cuenta)
        mostrarCamino(v)
    }
}