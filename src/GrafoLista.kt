import java.io.File
import kotlin.inc

class GrafoLista (val nombreArchivo: String)
{
    var esDirigido = 0
    val ady = abrirArchivo()

    inner class Recorrido (val origen: Int = 0)
    {
        val marcados = CharArray(numVertices()) {'B'} // Cada posicion representa el color con el que ese vertice esta marcado: 'B' blanco, 'G' Gris, 'N' Negro
        val distancia = IntArray(numVertices()) {-1}
        val cuentaAristas = IntArray(3) {0} // Cada posicion representa el numero de aristas de cada tipo [0] Arbol [1] Retorno [2] Avance o Cruzadas
        val predecesor = IntArray(numVertices()) {-1}
        var cuenta = 0 // Numero total de vertices visitados en un recorrido

        fun clasificarAristas()
        {
            println("Arbol: ${cuentaAristas[0]}")
            println("Retorno: ${cuentaAristas[1]}")
            println("Avance/Cruzada: ${cuentaAristas[2]}\n")
        }

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
                    print("->")
                    print(destino)
                }
            }
        }

        fun profundidad(origen: Int = this.origen)
        {
            marcados[origen] = 'G' //  Marcarlo como siendo visitado
            cuenta++

            for (v in Adyacentes(origen))
            {
                if (marcados[v] == 'B') // No ha sido visitado
                {
                    cuentaAristas[0]++
                    predecesor[v] = origen
                    distancia[v] = distancia[origen] + 1
                    profundidad(v)
                }

                else if (esDirigido == 0 && v == predecesor[origen])
                {
                    // Ignora la Arista Inversa  (para evitar doble conteo)
                    continue
                }

                else if (esDirigido == 0 && origen > v)
                {
                    // Ignora la segunda dirección de la arista (para evitar doble conteo)
                    continue
                }

                else {
                    if (marcados[v] == 'G') {
                        cuentaAristas[1]++
                    }

                    else if (marcados[v] == 'N')
                    {
                        cuentaAristas[2]++
                    }
                }
            }

            marcados[origen] = 'N' // Marcarlo como Visitado
        }

        fun anchura(origen: Int = this.origen)
        {
            val cola = ArrayDeque<Int>()
            var  v: Int
            var adyacentes: List<Int>

            marcados[origen] = 'G'
            distancia[origen] = 0
            cola.addLast(origen)

            while (cola.isNotEmpty())
            {
                v = cola.removeFirst()
                adyacentes = Adyacentes(v)

                for (w in adyacentes)
                {
                    if (marcados[w] == 'B')
                    {
                        marcados[w] = 'G'
                        cuenta++
                        cuentaAristas[0]++
                        predecesor[w] = v
                        distancia[w] = distancia[v] + 1
                        cola.addLast(w)
                    }
                    else if (esDirigido == 0 && w == predecesor[v])
                    {
                        // Ignora la Arista Inversa  (para evitar doble conteo)
                        continue
                    }

                    else if (esDirigido == 0 && v > w)
                    {
                        // Ignora la segunda dirección de la arista (para evitar doble conteo)
                        continue
                    }

                    else
                    {
                        if (marcados[w] == 'G')
                        {
                            cuentaAristas[1]++
                        }

                        else if (marcados[w] == 'N')
                        {
                            cuentaAristas[2]++
                        }
                    }
                }
                marcados[v] = 'N'
            }
        }
    }


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
}