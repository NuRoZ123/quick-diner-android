package fr.nuroz.quickdiner.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData

open class BaseFragment : Fragment() {
    val observerList: ArrayList<LiveData<*>> = ArrayList()

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