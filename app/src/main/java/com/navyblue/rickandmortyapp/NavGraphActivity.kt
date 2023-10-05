package com.navyblue.rickandmortyapp

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView

class NavGraphActivity : AppCompatActivity() {

    private lateinit var toolbarHome: Toolbar
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_nav_graph)

        toolbarHome = findViewById(R.id.toolbarhome)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setSupportActionBar(toolbarHome)
        val actionBar = supportActionBar
        actionBar?.setDisplayShowTitleEnabled(false)
        val drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        appBarConfiguration = AppBarConfiguration(topLevelDestinationIds = setOf(R.id.characterListFragment, R.id.episodeListFragment), drawerLayout)
        setupActionBarWithNavController(
            navController = navController,
            configuration = appBarConfiguration
        )
        findViewById<NavigationView>(R.id.nav_view).setupWithNavController(navController)
        findViewById<NavigationView>(R.id.nav_view).setCheckedItem(
            navController.graph.startDestinationId
        )

        val hola = findViewById<Button>(R.id.buttonSearch)
        hola.setOnClickListener {
            Toast.makeText(this@NavGraphActivity, "Buscar en proceso", Toast.LENGTH_SHORT).show()
        }


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}