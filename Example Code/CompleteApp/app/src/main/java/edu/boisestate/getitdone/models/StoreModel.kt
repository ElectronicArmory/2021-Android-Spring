package edu.boisestate.getitdone.models

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class StoreModel(
    @Id var id:Long = 0,
    var storeName: String,
    var storeNumber: String
) {
    lateinit var storeFullName: String // "StoreName + StoreNumber"
}
