class GrafoLista (val numV: Int)
{
    var ady = Array(numV) {mutableListOf<Int>()}

    fun numVertices(): Int {
        return this.ady.size
    }

    fun agregarArista(v: Int, w: Int)
    {
        this.ady[v].add(w)
        if (!esDirigido())
        {
            this.ady[w].add(v)
        }
    }

    fun Adyacentes(v: Int): List<Int>
    {
        return this.ady[v]
    }

    fun Grado(v: Int): Int
    {
        return this.ady[v].size
    }

    fun inGrado(v: Int): Int
    {
        var n = 0
        for (adyacentes in this.ady)
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
        for (v in 0..<this.ady.size)
            println("$v: ${ady[v]}")
    }

    fun esDirigido(): Boolean
    {
        var vertice: Int

        for (adyacentes in this.ady)
        {
            vertice = this.ady.indexOf(adyacentes)
            for (w in adyacentes)
            {
                if (vertice !in  ady[w])
                {
                    return true
                }
            }
        }
        return false
    }

    fun caminoAleatorio()
    {
        val numPasos = (1..10).random()
        var v = (0..<numVertices()).random()

        for (i in 0..numPasos)
        {
            if (i != numPasos)
            {
                print("$v->")
            }
            else
            {
                print("$v")
            }
            v = ady[v][(0..<this.ady[v].size).random()]
        }
    }
}