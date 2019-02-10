package world.mitchmiller.foodanddrinkdiary.db

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import world.mitchmiller.foodanddrinkdiary.db.dao.FoodDao
import world.mitchmiller.foodanddrinkdiary.db.entity.FoodItem

class FoodItemRepository(private val foodDao: FoodDao) {
    val allFoodItems: LiveData<List<FoodItem>> = foodDao.getAllFoodItems()

    @WorkerThread
    suspend fun insert(foodItem: FoodItem) {
        foodDao.insert(foodItem)
    }
}