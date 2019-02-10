package world.mitchmiller.foodanddrinkdiary.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import world.mitchmiller.foodanddrinkdiary.db.entity.FoodItem

@Dao
interface FoodDao {

    @Query("SELECT * from food_table ORDER BY name ASC")
    fun getAllFoodItems(): LiveData<List<FoodItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(foodItem: FoodItem)

    @Query("DELETE FROM food_table")
    fun deleteAll()

}