package fr.nuroz.quickdiner.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseFragment : Fragment() {
    lateinit var retrofit: Retrofit
    val observerList: ArrayList<LiveData<*>> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initRetrofit()
    }

    fun initRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl("https://quick-diner.k-gouzien.fr/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun onPause() {
        super.onPause()

        observerList.forEach { observer ->
            observer.removeObservers(this)
        }
    }

    fun addObservers(liveData: LiveData<*>) {
        observerList.add(liveData)
    }
}