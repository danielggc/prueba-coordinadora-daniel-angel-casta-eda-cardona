package com.cursokotlin.mvvmexample.data.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.cursokotlin.mvvmexample.BR

class User : BaseObservable() {
    @get:Bindable
    var username: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR)
        }

    @get:Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
}