package me.renespies.daedalus

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import io.kotest.property.checkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import me.renespies.daedalus.database.WeightDatabase
import me.renespies.daedalus.weight.service.data.Weight
import me.renespies.daedalus.weight.service.data.WeightDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.io.IOException

class WeightDatabaseTest {
    private lateinit var weightDao: WeightDao
    private lateinit var database: WeightDatabase

    @Before
    fun createDatabase() {
        val context = ApplicationProvider.getApplicationContext<android.content.Context>()
        database = Room.inMemoryDatabaseBuilder(context, WeightDatabase::class.java).build()
        weightDao = database.weightDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDatabase() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun readWriteWeight() {
        runBlocking {
            checkAll<Float, String> { weight, note ->
                val optionalNote = if (note.length % 2 == 0) null else note
                weightDao.insert(Weight(weight = weight, note = optionalNote))
                val weights = weightDao.weights()
                weights.first().first().also {
                    assert(it.weight == weight)
                    assert(it.note == optionalNote)
                }
                weightDao.clear()
            }
        }
    }
}
