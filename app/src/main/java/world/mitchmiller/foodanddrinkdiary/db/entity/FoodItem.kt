package world.mitchmiller.foodanddrinkdiary.db.entity

import androidx.room.Entity

@Entity
data class FoodItem(val name: String, val calories: Float, val notes: String, val barcodeValue: String)