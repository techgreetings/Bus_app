package com.example.busschedulingapp.data


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.busschedulingapp.R


data class Bus(
    @DrawableRes val ImageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val description: Int,
    @StringRes val details:Int
)

val buses= listOf(
    Bus(R.drawable.bus1,R.string.bus_name1, R.string.bus_des1,R.string.bus_detail_1),
    Bus(R.drawable.bus2, R.string.bus_name2, R.string.bus_des2,R.string.bus_detail_2),
    Bus(R.drawable.bus3, R.string.bus_name3, R.string.bus_des3,R.string.bus_detail_3),
    Bus(R.drawable.bus4, R.string.bus_name4,R.string.bus_des4,R.string.bus_detail_4),
    Bus(R.drawable.bus5, R.string.bus_name5, R.string.bus_name5,R.string.bus_detail_5),
    Bus(R.drawable.bus6, R.string.bus_name6, R.string.bus_des6,R.string.bus_detail_6)
)

