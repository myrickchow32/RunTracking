package com.raywenderlich.android.runtracking

import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng
import kotlin.math.pow
import kotlin.math.sqrt

@Entity
data class TrackingEntity(
    @PrimaryKey val timestamp: Long,
    @ColumnInfo val latitude: Double,
    @ColumnInfo val longitude: Double
) {
    // 4. The distance in meter between the current Tracking Entity and the previous one in the Room database
    @ColumnInfo var distanceTravelled = 0f

    // 5
    fun asLatLng() = LatLng(latitude, longitude)

    // 6
    fun distanceTo(newTrackingEntity: TrackingEntity): Float {
        val locationA = Location("Previous Location")
        locationA.latitude = latitude
        locationA.longitude = longitude

        val locationB = Location("New Location")
        locationB.latitude = newTrackingEntity.latitude
        locationB.longitude = newTrackingEntity.longitude

        val distanceInMeter = locationA.distanceTo(locationB)
        return distanceInMeter
    }
}
