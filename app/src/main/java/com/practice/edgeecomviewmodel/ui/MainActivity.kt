package com.practice.edgeecomviewmodel.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.practice.edgeecomviewmodel.R

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        bottomNav = findViewById(R.id.bottomNav)
        bottomNav.setOnApplyWindowInsetsListener(null)
        bottomNav.setPadding(0, 0, 0, 0)



        val homeFragment: Fragment = HomeFragment()
        val shopFragment: Fragment = ShopFragment()
        val savedFragment: Fragment = SavedFragment()
        val inboxFragment: Fragment = InboxFragment()
        val profileFragment: Fragment = ProfileFragment()

        var activeFragment: Fragment = homeFragment

        supportFragmentManager.beginTransaction().apply {
            add(R.id.content, homeFragment)
            add(R.id.content, shopFragment)
            add(R.id.content, savedFragment)
            add(R.id.content, inboxFragment)
            add(R.id.content, profileFragment)
            hide(shopFragment)
            hide(savedFragment)
            hide(inboxFragment)
            hide(profileFragment)
            commit()
        }

        bottomNav.setOnItemSelectedListener { item ->
            val newFragment =
                when (item.itemId) {
                    R.id.home -> {
                        replaceFragment(HomeFragment())
                        true
                    }
                    R.id.shop -> {
                        replaceFragment(ShopFragment())
                        true
                    }
                    R.id.saved -> {
                        replaceFragment(SavedFragment())
                        true
                    }
                    R.id.inbox -> {
                        replaceFragment(InboxFragment())
                        true
                    }
                    R.id.profile -> {
                        replaceFragment(ProfileFragment())
                        true
                    }
                    else -> return@setOnItemSelectedListener false
                }
            true
        }

        replaceFragment(HomeFragment())

    }

    private fun replaceFragment(homeFragment: Fragment) {
        val  fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.content,homeFragment)
        fragmentTransaction.commit()

    }

}