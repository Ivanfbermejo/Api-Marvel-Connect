package ivan.fernandez.marvel

import org.junit.Test

import org.junit.Assert.*

class UnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun apiobtenerPersonajes() {
        assertNotEquals(Api().obtenerPersonajes().size,0)
    }
    @Test
    fun apiobtenerPersonaje() {
        assertNotNull(Api().obtenerPersonaje("1009144"))
    }
    @Test
    fun apiobtenerSeriesPersonaje() {
        assertNotEquals(Api().obtenerSeriesPersonaje("1009144").size,0)
    }
    @Test
    fun apiobtenerEventsPersonaje() {
        assertNotEquals(Api().obtenerEventsPersonaje("1009146").size,0)
    }
}