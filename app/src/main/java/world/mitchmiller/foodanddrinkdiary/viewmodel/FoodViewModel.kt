package world.mitchmiller.foodanddrinkdiary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import world.mitchmiller.foodanddrinkdiary.db.FoodItemRepository
import world.mitchmiller.foodanddrinkdiary.db.FoodRoomDatabase
import world.mitchmiller.foodanddrinkdiary.db.entity.FoodItem
import kotlin.coroutines.CoroutineContext

class FoodViewModel(application: Application) : AndroidViewModel(application) {

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Main
    private val scope = CoroutineScope(coroutineContext)

    private val repository: FoodItemRepository
    val allFoodItems: LiveData<List<FoodItem>>

    init {
        val foodDao = FoodRoomDatabase.getDatabase(application).foodDao()
        repository = FoodItemRepository(foodDao)
        allFoodItems = repository.allFoodItems
    }

    fun insert(foodItem: FoodItem) = scope.launch(Dispatchers.IO) {
        repository.insert(foodItem)
    }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}