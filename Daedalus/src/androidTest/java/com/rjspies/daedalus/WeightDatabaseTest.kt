package com.rjspies.daedalus

import android.database.sqlite.SQLiteConstraintException
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import com.rjspies.daedalus.database.WeightDatabase
import com.rjspies.daedalus.weight.service.data.Weight
import com.rjspies.daedalus.weight.service.data.WeightDao
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.property.checkAll
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
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
    @SmallTest
    @Throws(Exception::class)
    fun readWriteWeight() {
        runBlocking {
            checkAll<Float, String> { weight, note ->
                val optionalNote = if (note.length % 2 == 0) null else note

                if (weight.isNaN()) {
                    shouldThrowExactly<SQLiteConstraintException> {
                        weightDao.insert(Weight(value = weight, note = optionalNote))
                    }
                } else {
                    weightDao.insert(Weight(value = weight, note = optionalNote))
                }

                weightDao.weights().onEach { weights ->
                    weights.forEach {
                        assert(it.value == weight)
                        assert(it.note == optionalNote)
                        weightDao.deleteWeight(it.id)
                    }
                }
            }
        }
    }
}
