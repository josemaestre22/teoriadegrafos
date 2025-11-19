class GrafoMalla(val filas: Int, val columnas: Int, val lleno: Boolean = true)
{
    val tamaño = filas * columnas
    val ady = Array(tamaño) {mutableListOf<Int>()}

    init {
        crearMalla()
    }

    fun agregarArista(v: Int, w: Int)
    {
        ady[v].add(w)
        ady[w].add(v)
    }

    fun eliminarArista(v: Int, w: Int)
    {
        ady[v].remove(w)
        ady[w].remove(v)
    }

    fun verticesGrado1(): List<Int>
    {
        val lista: MutableList<Int> = mutableListOf()

        for (v in 0..< tamaño)
        {
            if (grado(v) == 1)
            {
                lista.add(v)
            }
        }

        return lista
    }

    fun grado(v: Int): Int
    {
        return ady[v].size
    }

    fun crearMalla()
    {
        var v: Int
        var limiteDerecha: Int

        for (i in 0 ..< filas)
        {
            limiteDerecha =  (i+1) * columnas
            for (j in 0..< columnas)
            {
                v = (i*columnas) + j

                if (v+1 < limiteDerecha)
                {
                    agregarArista(v, v+1)
                }
                if (v+columnas < tamaño)
                {
                    agregarArista(v, v+columnas)
                }
            }
        }
    }

    fun mostrarGrafo()
    {
        for (v in 0..<tamaño)
            println("$v: ${ady[v]}")
    }

    fun adyacentes(v: Int): List<Int>
    {
        return ady[v]
    }


    fun mostrar() {
        var v: Int
        var vDebajo: Int
        var vDerecha: Int
        var vIzquierda: Int
        var vArriba: Int
        var limiteDerecha: Int
        var limiteIzquierda: Int

        for (i in 0 ..< filas)
        {
            limiteDerecha = (i+1)  * columnas
            limiteIzquierda = i*columnas
            for (j in 0..< columnas)
            {
                v = (i*columnas) + j

                vDebajo = v+columnas
                vArriba= v-columnas
                vDerecha = v+1
                vIzquierda = v-1

                if (grado(v) == 1)
                {
                    if (vArriba >= 0 && vArriba in adyacentes(v)) // Arriba
                    {
                        print('╵')
                    }

                    else if (vDebajo <= tamaño-1 && vDebajo in adyacentes(v)) //Abajo
                    {
                        print('╷')
                    }

                    else if (vDerecha < limiteDerecha && vDerecha in adyacentes(v))
                    {
                        print('╶')
                    }

                    else if (vIzquierda >= limiteIzquierda && vIzquierda in adyacentes(v))
                    {
                        print('╴')
                    }
                }

                else if (grado(v) == 2)
                {
                    if (vArriba >= 0 && vArriba in adyacentes(v)) // Arriba
                    {
                        if (vDebajo <= tamaño-1 && vDebajo in adyacentes(v))
                        {
                            print('│')
                        }

                        else if (vDerecha < limiteDerecha && vDerecha in adyacentes(v))
                        {
                            print('└')
                        }

                        else if (vIzquierda >= limiteIzquierda && vIzquierda in adyacentes(v))
                        {
                            print('┘')
                        }
                    }

                    else if (vDebajo <= tamaño-1 && vDebajo in adyacentes(v))
                    {
                        if (vDerecha < limiteDerecha && vDerecha in adyacentes(v))
                        {
                            print('┌')
                        }

                        else if (vIzquierda >= limiteIzquierda && vIzquierda in adyacentes(v))
                        {
                            print('┐')
                        }
                    }

                    else if (vDerecha < limiteDerecha && vDerecha in adyacentes(v))
                    {
                        if (vIzquierda >= limiteIzquierda && vIzquierda in adyacentes(v))
                        {
                            print('─')
                        }
                    }

                }
                else if (grado(v) == 3)
                {
                    if (vArriba >= 0 && vArriba in adyacentes(v)) // Arriba
                    {
                        if (vDebajo <= tamaño-1 && vDebajo in adyacentes(v))
                        {
                            if (vDerecha < limiteDerecha && vDerecha in adyacentes(v))
                            {
                                print('├')
                            }

                            else if (vIzquierda >= limiteIzquierda && vIzquierda in adyacentes(v))
                            {
                                print('┤')
                            }
                        }
                        else
                        {
                            print('┴')
                        }
                    }

                    else
                    {
                        print('┬')
                    }
                }

                else if (grado(v) == 4)
                {
                    print('┼')
                }
            }
            println()
        }
    }
}