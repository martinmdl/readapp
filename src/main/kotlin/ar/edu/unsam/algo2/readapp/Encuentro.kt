@file:Suppress("SpellCheckingInspection")

package ar.edu.unsam.algo2.readapp

import java.time.LocalDate

/**
 * Encuentro.
 *
 * @param fecha fecha del encuentro
 * @param duracion duracion en horas del tiempo
 * @param disponible cantidad de cupos restantes disponibles
 * @param centroDeLectura lugar donde se va realizar el encuentro
 *
 */

class Encuentro(
    private val fecha: LocalDate,
    private val duracion: Int,
    private var disponible: Int,
    private var centroDeLectura: CentroDeLectura
) {
    /**
     *  Al instanciar el objeto este adquiere el cupo definido segun el encuentro.
     */
    init {
        disponible = centroDeLectura.maximaCapacidadPorEncuentro()
    }

    /*ACCESORES*/
    fun fecha(): LocalDate = this.fecha
    fun duracion(): Int = this.duracion
    fun disponible(): Int = this.disponible

    /*METODOS*/

    fun reservaDisponible(): Boolean = this.disponibilidad() && this.vencido()

    fun disponibilidad(): Boolean = this.disponible > 0

    fun vencido(): Boolean = this.fecha.isBefore(LocalDate.now())

    fun reservarCupo() {this.disponible -= 1}

}