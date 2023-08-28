plugins {
    id("com.fueled.chatty.feature")
}

android {
    namespace = "com.fueled.chatty.feature.chats"
}

dependencies {
    implementation(libs.androidx.palette.ktx)
}
