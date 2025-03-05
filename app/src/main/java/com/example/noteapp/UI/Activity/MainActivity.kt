package com.example.noteapp.UI.Activity

import android.app.ProgressDialog.show
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.utils.PreferenceHelper
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        PreferenceHelper.init(this)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val drawerLayout: DrawerLayout =  findViewById(R.id.drawer_layout)
        val navView:NavigationView = findViewById(R.id.nav_view)

        toggle=ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R. id.nav_onboard -> Toast.makeText(applicationContext, "Clicked On Board",Toast .LENGTH_SHORT).show()
                R. id.nav_notes-> Toast.makeText(applicationContext, "Clicked Notes",Toast .LENGTH_SHORT).show()
                R. id.nav_onboard -> Toast.makeText(applicationContext, "Clicked Auth",Toast .LENGTH_SHORT).show()



            }
            true
        }


        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment

        navController = navHostFragment.navController
    }
    override fun onResume() {
        super.onResume()

        if (PreferenceHelper.onBoardShown && PreferenceHelper.authShown) {
            navController.navigate(R.id.noteFragment)
        }
        else{
            navController.navigate(R.id.onBoardFragment)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}