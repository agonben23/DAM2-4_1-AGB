class Modulo(val maxAlumnos: Int) {
    var lisAlumnos= arrayOfNulls<Alumno>(maxAlumnos)
    var lisnotas = arrayOf<Array<Int>>()
    var lisnotas1 = listaNotas("1")
    var lisnotas2 = listaNotas("2")
    var lisnotas3 = listaNotas("3")
    var lisnotas4 = listaNotas("4")
    fun anadirAlumno(alumno: Alumno) {
        lisAlumnos[lisAlumnos.indexOfFirst { false }] = alumno
        lisnotas[lisnotas.indexOfFirst { false }] = alumno.notas
    }

    fun listaNotas(evaluacion: String): ArrayList<Pair<String, Int>> {
        val notas = when (evaluacion) {
            "1" -> obtenerCalificacion(1)
            "2" -> obtenerCalificacion(2)
            "3" -> obtenerCalificacion(3)
            else -> obtenerCalificacion(4)
        }
        return notas
    }

    fun obtenerCalificacion(evaluacion: Int): ArrayList<Pair<String, Int>> {
        var contadorMax = lisAlumnos.size
        var i = 0
        var notasEv = ArrayList<Pair<String, Int>>()
        do {
            var aActual = lisAlumnos[i]
            var nombreComp = aActual?.nombre + "" + aActual?.apellidos
            val pair = Pair(nombreComp, aActual!!.notas[evaluacion - 1])
            notasEv.add(i, pair)
            i++
        } while (i != contadorMax)
        return notasEv
    }

    fun numeroAprobados(evaluacion: String): Int {
        var aprobados = 0
        when (evaluacion) {
            "1" -> {
                lisnotas1.filter {it.second >= 5}
                aprobados = lisnotas1.size
            }
            "2" -> {
                lisnotas2.filter {it.second >= 5}
                aprobados = lisnotas2.size
            }
            "3" -> {
                lisnotas3.filter {it.second >= 5}
                aprobados = lisnotas3.size
                }
            else -> {
                lisnotas4.filter {it.second >= 5}
                aprobados = lisnotas1.size
            }
        }
        return aprobados
    }
}

class Alumno(val nombre: String, val apellidos: String, val nota1t: Int, val nota2t: Int, val nota3t: Int) {
    var notas = arrayOf(nota1t, nota2t, nota3t, calculaEvaluacionFinal())
    fun calculaEvaluacionFinal(): Int {
        var notafinal = ((nota1t + nota2t + nota3t) / 3).toDouble()
        return redondear(notafinal)
    }

    private fun redondear(num: Double): Int {
        "%.0f".format(num)
        return num.toInt()
    }
}

fun main() {
    var modulo1 = Modulo(15)
    var alumno1 = Alumno("Alejandro","González",6,8,4)
    modulo1.anadirAlumno(alumno1)
    var alumno2 = Alumno("Pepito","Grillo",6,3,8)
    modulo1.anadirAlumno(alumno2)
    println(modulo1.numeroAprobados("1"))
}