package com.example.worktest.ui

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.worktest.R
import com.example.worktest.databinding.ActivityMainBinding
import com.example.worktest.ui.auth.AuthFragment
import com.example.worktest.ui.images.ImagesFragment

class MainActivity : AppCompatActivity() {

    private var imageFragment = ImagesFragment()
    private var authFragment = AuthFragment()
    private lateinit var binding: ActivityMainBinding
    private val tags = listOf(FRAGMENT_IMAGES, FRAGMENT_AUTH)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showOrAddFragment(imageFragment, FRAGMENT_IMAGES)
        binding.bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionImages -> showOrAddFragment(imageFragment, FRAGMENT_IMAGES)
                R.id.actionAuth -> showOrAddFragment(authFragment, FRAGMENT_AUTH)
            }
            true
        }
    }

    private fun showOrAddFragment(fragment: Fragment, tag: String) {
        setTitle(tag)
        hideKeyboard(this)
        if (fragment.isAdded) {
            showFragment(fragment)
        } else {
            addFragment(fragment, tag)
        }
        val tagsToHide = tags.toMutableList()
        tagsToHide.remove(tag)
        hideFragments(tagsToHide)
    }

    private fun setTitle(tag: String) {
        when (tag) {
            FRAGMENT_IMAGES -> setTitle(R.string.images)
            FRAGMENT_AUTH -> setTitle(R.string.login)
        }
    }

    private fun hideFragments(tags: List<String>) {
        tags.forEach { tag ->
            val fragment = supportFragmentManager.findFragmentByTag(tag)
            fragment?.let {
                supportFragmentManager.beginTransaction()
                    .hide(fragment)
                    .commitAllowingStateLoss()
            }
        }
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .add(R.id.frameLayout, fragment, tag)
            .commitAllowingStateLoss()
    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .show(fragment)
            .commitAllowingStateLoss()
    }

    fun hideKeyboard(activity: Activity) {
        val imm = activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    companion object {
        const val FRAGMENT_IMAGES = "fragmentImages"
        const val FRAGMENT_AUTH = "fragmentAuth"
    }
}