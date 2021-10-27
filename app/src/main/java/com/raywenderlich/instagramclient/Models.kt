package com.raywenderlich.instagramclient

data class Post(
    val username: String,
    val post: Int,
    val description: String = "",
    var likes: Int = 0,
)

val posts: ArrayList<Post> = arrayListOf(
    Post("cikanikola96", R.drawable.bolto, "Bolto the Bulldog", 120),
    Post("jelenajovancevic96", R.drawable.micko, "Micko the Maltese", 250),
    Post("stankela33", R.drawable.coolpost, "Mnogo kul sliga"),
    Post("ivan", R.drawable.asdsadsad, "", 1),
    Post("natasasevo", R.drawable.op_1019__001av2, "One piece"),
    Post("aleksandar.fineti", R.drawable.guts, "Guts")
)

val users: ArrayList<User> = arrayListOf(
    User("cikanikola96",
        "momca96",
        R.drawable.bjelica,
        "208 Posts",
        "495 Followers",
        "296 Following"),
    User("jelenajovancevic96",
        "jeca96",
        R.drawable.jeca,
        "300 Posts",
        "500 Followers",
        "400 Following")
)

data class User(
    val username: String,
    val password: String,
    val profilePicture: Int = 0,
    var posts: String = "0 Posts",
    var followers: String = "0 Followers",
    var following: String = "0 Following",
)