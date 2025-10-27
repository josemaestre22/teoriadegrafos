import java.io.File

fun main(args: Array<String>) {
    val nombreArchivo = args[0]
    val archivo = File(nombreArchivo)
    val lineas = archivo.readLines()


    val grafo = GrafoLista(lineas.first().toInt())
    for (l in lineas)
    {
        println(lineas)
    }
}