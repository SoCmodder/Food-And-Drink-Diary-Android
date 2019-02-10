package world.mitchmiller.foodanddrinkdiary.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodItem(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "calories") val calories: Float,
    @ColumnInfo(name = "notes") val notes: String,
    @ColumnInfo(name = "barcode_value") val barcodeValue: String
)