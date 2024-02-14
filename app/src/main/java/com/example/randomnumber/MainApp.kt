package com.example.randomnumber

import android.app.Application

class MainApp : Application() {
   val appContainer by lazy{ AppContainer(applicationContext) }
}