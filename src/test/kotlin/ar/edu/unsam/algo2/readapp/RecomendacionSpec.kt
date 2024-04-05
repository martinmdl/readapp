@file:Suppress("SpellCheckingInspection")

package ar.edu.unsam.algo2.readapp

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.time.LocalDate

class RecomendacionesSpec : DescribeSpec({
    describe("Test de las Recomendaciones") {
        //Arrange
        /*LIBROS*/
        val harryPotter = Libro(
            "HarryPotter",
            "Salamandra",
            800,
            50000,
            true,
            5,
            setOf(Lenguaje.es_ES, Lenguaje.ja_JP),
            10001,
            "alguien"
        )
        val harryPotter2 = Libro(
            "HarryPotter",
            "Salamandra",
            800,
            55000,
            true,
            5,
            setOf(Lenguaje.es_ES, Lenguaje.ja_JP),
            10001,
            "alguien"
        )
        val harryPotter3 = Libro(
            "HarryPotter",
            "Salamandra",
            800,
            60000,
            true,
            5,
            setOf(Lenguaje.es_ES, Lenguaje.ja_JP),
            10001,
            "alguien"
        )
        val harryPotter4 = Libro(
            "HarryPotter",
            "Salamandra",
            800,
            65000,
            true,
            5,
            setOf(Lenguaje.es_ES, Lenguaje.ja_JP),
            10001,
            "alguien"
        )
        val harryPotter5 = Libro(
            "HarryPotter",
            "Salamandra",
            800,
            70000,
            true,
            5,
            setOf(Lenguaje.es_ES, Lenguaje.ja_JP),
            10001,
            "alguien"
        )
        /*USUARIOS*/
        val usuario1 = Usuario(
            "pipo1",
            "alegre",
            "pipojr10",
            mutableSetOf(),
            mutableSetOf(harryPotter, harryPotter2, harryPotter3, harryPotter4),
            150,
            (LocalDate.of(2000, 10, 1)),
            "alguien"
        )
        val usuario2 = Usuario(
            "pipo2",
            "alegre",
            "pipojr10",
            mutableSetOf(usuario1),
            mutableSetOf(harryPotter2, harryPotter3, harryPotter5),
            200,
            (LocalDate.of(2000, 10, 1)),
            "alguien"
        )
        val usuario3 = Usuario(
            "pipo3",
            "alegre",
            "pipojr10",
            mutableSetOf(usuario1, usuario2),
            mutableSetOf(harryPotter, harryPotter2, harryPotter3, harryPotter4, harryPotter5),
            250,
            (LocalDate.of(2000, 10, 1)),
            "alguien"
        )


        /*RECOMENDACIONES*/
        val recomendacion1 = Recomendacion(
            esPrivado = true,
            creador = usuario1,
            libroRecomendados = mutableSetOf(harryPotter2),
            descripcion = "no se leer",
            mutableMapOf()
        )
        val recomendacion2 = Recomendacion(
            esPrivado = false,
            creador = usuario2,
            libroRecomendados = mutableSetOf(harryPotter2, harryPotter3),
            descripcion = "no se leer",
            mutableMapOf()
        )
        val recomendacion3 = Recomendacion(
            esPrivado = true,
            creador = usuario3,
            libroRecomendados = mutableSetOf(harryPotter, harryPotter2),
            descripcion = "no se leer",
            mutableMapOf()
        )
        describe("Test de edicion de la privacidad") {

            it("El creador puede editar la privacidad en la recomendacion") {
                //Act
                recomendacion3.editarPrivacidad(usuario3)
                //Assert
                recomendacion3.esPrivado shouldBe false
            }
            it("El amigo no puede editar la privacidad en la recomendacion") {
                //Act
                recomendacion3.editarPrivacidad(usuario2)
                //Assert
                recomendacion3.esPrivado shouldBe false
            }
        }
        describe("Test de edicion de la descripcion") {

            it("El creador puede editar la descripcion en la recomendacion") {
                //Act
                recomendacion3.editarDescripcion(usuario3, "nueva descripcion")
                //Assert
                recomendacion3.descripcion shouldBe "nueva descripcion"
            }
            it("El amigo puede editar la descripcion en la recomendacion") {
                //Act
                recomendacion3.editarDescripcion(usuario2, "cambio de descripcion")
                //Assert
                recomendacion3.descripcion shouldBe "cambio de descripcion"
            }
            it("El usuario no puede editar la descripcion en la recomendacion") {
                //Act
                recomendacion1.editarDescripcion(usuario2, "cambio de descripcion")
                //Assert
                recomendacion1.descripcion shouldBe "no se leer"
            }
        }
        describe("Test de agregar un libro") {

            it("El creador puede agregar un libro (a la recomendacion)") {
                //Act
                recomendacion3.agregarALibrosDeRecomendacion(usuario3, harryPotter3)
                //Assert
                recomendacion3.libroRecomendados shouldBe mutableSetOf(harryPotter, harryPotter2, harryPotter3)
            }
            it("El creador no puede agregar un libro a la recomendacion porque no leyo ese libro") {
                //Act
                recomendacion1.agregarALibrosDeRecomendacion(usuario1, harryPotter5)
                //Assert
                recomendacion1.libroRecomendados shouldBe mutableSetOf(harryPotter2)
            }
            it("El amigo puede agregar un libro (a la recomendacion)") {
                //Act
                recomendacion3.agregarALibrosDeRecomendacion(usuario3, harryPotter4)
                //Assert
                recomendacion3.libroRecomendados shouldBe mutableSetOf(
                    harryPotter,
                    harryPotter2,
                    harryPotter3,
                    harryPotter4
                )
            }
            it("El amigo no puede agregar un libro (a la recomendacion) porque no leyo ese libro") {
                //Act
                recomendacion3.agregarALibrosDeRecomendacion(usuario1, harryPotter5)
                //Assert
                recomendacion3.libroRecomendados shouldBe mutableSetOf(
                    harryPotter,
                    harryPotter2,
                    harryPotter3,
                    harryPotter4
                )
            }
            it("El usuario no puede agregar un libro (a la recomendacion) porque es privada (la recomendacion)") {
                //Act
                recomendacion1.agregarALibrosDeRecomendacion(usuario2, harryPotter3)
                //Assert
                recomendacion1.libroRecomendados shouldBe mutableSetOf(harryPotter2)
            }
            it("El amigo no puede agregar un libro (a la recomencadion) porque no leyo todos los libros (de la recomendacion)") {
                //Act
                recomendacion3.agregarALibrosDeRecomendacion(usuario2, harryPotter5)
                //Assert
                recomendacion3.libroRecomendados shouldBe mutableSetOf(
                    harryPotter,
                    harryPotter2,
                    harryPotter3,
                    harryPotter4
                )
            }
        }
        describe("Test de leer recomendacion") {

            it("Creador puede leer la recomendacion") {
                //Act
                //Assert
                recomendacion1.leerRecomendacion(usuario1) shouldBe true
            }
            it("amigo puede leer la recomendacion") {
                //Act
                //Assert
                recomendacion3.leerRecomendacion(usuario1) shouldBe true
            }
            it("usuario puede leer la recomendacion") {
                //Act
                //Assert
                recomendacion2.leerRecomendacion(usuario3) shouldBe true
            }
            it("usuario no puede leer la recomendacion") {
                //Act
                //Assert
                recomendacion1.leerRecomendacion(usuario2) shouldBe false
            }
        }
        describe("Test de Tiempos de lectura") {
            it("Tiempo de lectura total; Recomendacion tiene 4 libros") {
                //Act
                val comp: Int = 50000 / (150 / 2) + 55000 / (150 / 2) + 60000 / (150 / 2) + 65000 / (150 / 2)
                //Assert
                recomendacion3.tiempoDeLecturaTotal(usuario1) shouldBe comp
            }
            it("Tiempo de lectura neto; Recomendacion tiene 4 libros") {
                //Act
                val comp: Int = 50000 / (200 / 2) + 65000 / (200 / 2)
                //Assert
                recomendacion3.tiempoDeLecturaNeto(usuario2) shouldBe comp
            }
            it("Tiempo de lectura ahorrado; Recomendacion tiene 4 libros") {
                //Act
                val total: Int = 50000 / (200 / 2) + 55000 / (200 / 2) + 60000 / (200 / 2) + 65000 / (200 / 2)
                val neto: Int = 50000 / (200 / 2) + 65000 / (200 / 2)
                val comp: Int = total - neto
                //Assert
                recomendacion3.tiempoDeLecturaAhorrado(usuario2) shouldBe comp
            }
        }
    }
})