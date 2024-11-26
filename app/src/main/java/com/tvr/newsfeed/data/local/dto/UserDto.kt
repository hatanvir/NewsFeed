package com.tvr.newsfeed.data.local.dto

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tvr.newsfeed.data.models.Reactions
import kotlinx.android.parcel.Parcelize

/**
 * Created By Tanvir Hasan on 11/25/24 9:11 PM
 * Email: tanvirhasan553@gmail.com
 */
@Entity("users")
@Parcelize
data class UserDto(
    @PrimaryKey
    val id: Long,
    val firstName: String,
    val lastName: String,
    val maidenName: String,
    val age: Long,
    val gender: String,
    val email: String,
    val phone: String,
    val username: String,
    val password: String,
    val birthDate: String,
    val image: String,
    val bloodGroup: String,
    val height: Double,
    val weight: Double,
    val eyeColor: String,
    val ip: String,
    val address: String,
    val macAddress: String,
    val university: String,
    val company: String,
    val ein: String,
    val ssn: String,
    val userAgent: String,
    val role: String,
) : Parcelable