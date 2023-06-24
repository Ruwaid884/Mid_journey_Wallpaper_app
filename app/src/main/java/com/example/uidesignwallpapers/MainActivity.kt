package com.example.uidesignwallpapers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.uidesignwallpapers.fragments.categories_fragment
import com.example.uidesignwallpapers.fragments.home_fragment
import com.example.uidesignwallpapers.fragments.special_fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)












        var drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navDrawer: NavigationView =findViewById(R.id.nav_drawer)

        navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.item1 -> Toast.makeText(this,"item1",Toast.LENGTH_SHORT).show()
                R.id.item2 -> Toast.makeText(this,"item2",Toast.LENGTH_SHORT).show()

                R.id.item3 -> Toast.makeText(this,"item3",Toast.LENGTH_SHORT).show()
            }
            true
        }

        val homefragment = home_fragment()
        val categories = categories_fragment()
        val special = special_fragment()

        setCurretFragment(homefragment)






        val bottomNav: BottomNavigationView =findViewById(R.id.bottomNav)
        bottomNav.setOnNavigationItemSelectedListener() {
            when(it.itemId){
                R.id.home-> setCurretFragment(homefragment)
                R.id.categories->setCurretFragment(categories)
                R.id.special->setCurretFragment(special)
            }
            true
        }


    }

    private fun setCurretFragment(fragment: Fragment) {
     supportFragmentManager.beginTransaction().apply {
         replace(R.id.fragment_app,fragment)
         commit()
     }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}