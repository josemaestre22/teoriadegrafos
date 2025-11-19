class RProfMod (val grafo: GrafoMalla, val origen: Int)
{
    val visitado = BooleanArray(grafo.tamaño) {false}
    val predecesor = IntArray(grafo.tamaño) {-1}

    fun recorrido(v: Int = this.origen)
    {
        visitado[v] = true
        val adyacentes = grafo.adyacentes(v).toList()

        for (w in adyacentes)
        {
            if (!visitado[w])
            {
                predecesor[w] = v
                recorrido(w)
            }

            else
            {
                if (w != predecesor[v])
                {
                    grafo.eliminarArista(v, w)
                }
            }
        }
    }
}