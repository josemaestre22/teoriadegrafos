import java.io.File

class GrafoLista (val nombreArchivo: String)
{
    val ady = abrirArchivo()
    val color = CharArray(numVertices()) {'B'} // Cada posicion representa el color de ese vertice 'B' blanco, 'G' Gris, 'N' Negro
    val distancia = IntArray(numVertices()) {0}
    val cuentaAristas = IntArray(3) {0} // Cada posicion representa el numero de aristas de cada tipo [0] Arbol [1] Retorno [2] Avance o Cruzadas
    val predecesor = IntArray(numVertices()) {-1}
    val origen = 0 // Vertice de el cual parten los recorridos
    var esDirigido = 0
    var cuenta = 0 // Numero de vertices visitados en un recorrido

    //TODO: Implementar recorridos como Nested Class con Inheritance (Recorrido Anchura y Reccorrido Profundidad heredan de Recorrido)

    fun numVertices(): Int {
        return ady.size
    }

    fun agregarArista(v: Int, w: Int, ady: Array<MutableList<Int>>)
    {
        ady[v].add(w)

        if (esDirigido == 0) // Si es un Grafo No Dirigido
        {
            ady[w].add(v)
        }
    }

    fun Adyacentes(v: Int): List<Int>
    {
        return ady[v]
    }

    fun Grado(v: Int): Int
    {
        return Adyacentes(v).size
    }

    fun inGrado(v: Int): Int
    {
        var n = 0
        for (adyacentes in ady)
        {
            for (w in adyacentes)
            {
                if (w == v)
                {
                    n++
                }
            }
        }
        return n
    }

    fun mostrarGrafo()
    {
        for (v in 0..<numVertices())
            println("$v: ${ady[v]}")
    }

    fun abrirArchivo(): Array<MutableList<Int>>
    {
        try
        {
            val lineas = File(nombreArchivo).readLines().filter { it.isNotBlank() }
            val adyacentes = Array(lineas.first().toInt()) {mutableListOf<Int>()}
            var numeros: List<String>
            esDirigido = lineas[1].toInt()
            
            for (l in lineas)
            {
                if (l != lineas.first() && l != lineas[1])
                {
                    numeros = l.split(' ')
                    agregarArista(numeros[0].toInt(), numeros[1].toInt(), adyacentes)
                }
            }
            return adyacentes
        }
        catch (e: Exception)
        {
            error("Ocurrio un error al tratar de abrir el archivo: ${e.message}")
        }
    }
    

    fun caminoAleatorio()
    {
        val limPasos = numVertices()
        var v = (0..< numVertices()).random()

        for (numPasos in 0..< limPasos)
        {
            print("$v")

            if (numPasos != limPasos-1 && Adyacentes(v).isNotEmpty())
            {
                print(" -> ")
            }

            if (Adyacentes(v).isNotEmpty())
            {
                v = Adyacentes(v)[(0..<Adyacentes(v).size).random()]
            }
            else
            {
                break
            }
        }
        println()
    }

    fun recorridoProfundidad(origen: Int = this.origen)
    {
        color[origen] = 'G' //  Marcarlo como siendo visitado
        cuenta++

        for (v in Adyacentes(origen))
        {
            if (color[v] == 'B') // No ha sido visitado
            {
                cuentaAristas[0]++
                predecesor[v] = origen
                distancia[v] = distancia[origen] + 1
                recorridoProfundidad(v)
            }
            else {
                if (color[v] == 'G' && origen == predecesor[v]) {
                    cuentaAristas[1]++
                }
                else if (color[v] == 'N')
                {
                    cuentaAristas[2]++
                }
            }
        }

        color[origen] = 'N' // Marcarlo como Visitado
    }

    fun recorridoAnchura(origen: Int = this.origen)
    {
        val cola = ArrayDeque<Int>()
        var  v: Int
        var adyacentes: List<Int>
        color[origen] = 'G'
        distancia[origen] = 0
        cola.addLast(origen)

        while (cola.isNotEmpty())
        {
            v = cola.removeFirst()
            adyacentes = Adyacentes(v)

            for (w in adyacentes)
            {
                if (color[w] == 'B')
                {
                    color[w] = 'G'
                    cuenta++
                    cuentaAristas[0]++
                    predecesor[w] = v
                    distancia[w] = distancia[v] + 1
                    cola.addLast(w)
                }
                else
                {
                    if (color[w] == 'G' && v == predecesor[w])
                    {
                        cuentaAristas[1]++
                    }
                    else if (color[w] == 'N' && v == predecesor[w])
                    {
                        cuentaAristas[2]++
                    }
                }
            }

            color[v] = 'N'
        }
    }

    fun contarAristas()
    {
        var arbol = 0
        var retorno = 0
        var avanceCruzada = 0

        for (c in color)
        {
            if (c == 'B')
            {
                arbol++
            }

            if (c == 'G')
            {
                retorno++
            }

            if (c == 'N')
            {
                avanceCruzada++
            }
        }
        println("Arbol: ${cuentaAristas[0]}")
        println("Retorno: ${cuentaAristas[1]}")
        println("Avance/Cruzada: ${cuentaAristas[2]}")
    }

    fun mostrarCamino(destino: Int)
    {
        if (destino == origen)
            print(origen)
        else
        {
            if (predecesor[destino] == -1)
            {
                println("no existe camino desde $destino al origen")
            }
            else
            {
                mostrarCamino(predecesor[destino])
                print("->")
                print(destino)
            }
        }
    }
}