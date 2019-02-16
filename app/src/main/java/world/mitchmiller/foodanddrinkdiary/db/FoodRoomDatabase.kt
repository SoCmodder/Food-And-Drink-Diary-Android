package world.mitchmiller.foodanddrinkdiary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import world.mitchmiller.foodanddrinkdiary.db.dao.FoodDao
import world.mitchmiller.foodanddrinkdiary.db.entity.FoodItem

@Database(entities = [FoodItem::class], version = 1)
public abstract class FoodRoomDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): FoodRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodRoomDatabase::class.java,
                    "Food_database"
                ).addCallback(FoodDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class FoodDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database.foodDao())
                }
            }
        }

        fun populateDatabase(foodDao: FoodDao) {
            foodDao.deleteAll()

            val item1 = FoodItem("Bacon", 200f, "example of notes blah blah", "123456789912345adsfdsads45")
            foodDao.insert(item1)
            val item2 = FoodItem("Cheese", 100f, "example of notes blah blah", "123456789912345adsfdsads45")
            foodDao.insert(item2)
            val item3 = FoodItem("Water", 0f, "example of notes blah blah", "123456789912345adsfdsads45")
            foodDao.insert(item3)
            val item4 = FoodItem("Bacon Cheeseburger", 500f, "example of notes blah blah", "123456789912345adsfdsads45")
            foodDao.insert(item4)
        }
    }
}