package com.example.roomdemo.database.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TaskDescription")
class TaskDescription (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "taskId") val taskId: Int?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "createdAt") val createdAt: String?,
    @ColumnInfo(name = "updatedAt") val updatedAt: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeValue(taskId)
        parcel.writeString(description)
        parcel.writeString(createdAt)
        parcel.writeString(updatedAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TaskDescription> {
        override fun createFromParcel(parcel: Parcel): TaskDescription {
            return TaskDescription(parcel)
        }

        override fun newArray(size: Int): Array<TaskDescription?> {
            return arrayOfNulls(size)
        }
    }
}