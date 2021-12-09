package com.raywenderlich.instagramclient

import com.raywenderlich.instagramclient.model.Post
import com.raywenderlich.instagramclient.model.User

val posts: ArrayList<Post> = arrayListOf(
    Post(0, "cikanikola96", R.drawable.bolto, "Bolto the Bulldog", 120),
    Post(1, "jelenajovancevic96", R.drawable.micko, "Micko the Maltese", 250),
    Post(2, "stankela33", R.drawable.coolpost, "Mnogo kul sliga",0),
    Post(3, "ivan", R.drawable.asdsadsad, "", 1),
    Post(4, "natasasevo", R.drawable.op_1019__001av2, "One piece",0),
    Post(5, "aleksandar.fineti", R.drawable.guts, "Guts",0)
)

val users: ArrayList<User> = arrayListOf(
    User(
        0,
        "cikanikola96",
        "momca96",
        R.drawable.bjelica,
        "208 Posts",
        "495 Followers",
        "296 Following"
    ),
    User(
        1,
        "jelenajovancevic96",
        "jeca96",
        R.drawable.jeca,
        "300 Posts",
        "500 Followers",
        "400 Following"
    )
)