package world.mitchmiller.foodanddrinkdiary.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import world.mitchmiller.foodanddrinkdiary.db.dao.FoodDao
import world.mitchmiller.foodanddrinkdiary.db.entity.FoodItem

@Database(entities = [FoodItem::class], version = 1)
public abstract class FoodRoomDatabase : RoomDatabase() {

    abstract fun foodDao(): FoodDao

    companion object {
        @Volatile
        private var INSTANCE: FoodRoomDatabase? = null

        fun getDatabase(context: Context): FoodRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodRoomDatabase::class.java,
                    "Food_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}