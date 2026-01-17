package ui

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import be.kotlinprojet.R
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.launch
import network.ConnectServer
import ui.login.LoginFragment
import ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    // =====================================================
    // VARIABLES GLOBALES (Drawer / Toolbar)
    // =====================================================

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle

    // =====================================================
    // CYCLE DE VIE
    // =====================================================

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDrawer()
        initDrawerMenu()
    }

    // =====================================================
    // INITIALISATION
    // =====================================================

    private fun initDrawer() {
        drawerLayout = findViewById(R.id.drawerLayout)
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }

    private fun initDrawerMenu() {
        val navigationView = findViewById<NavigationView>(R.id.navigationView)

        navigationView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {

                R.id.menu_addConsultation -> {
                    notifyAddConsultation()
                }

                R.id.menu_addPatient -> {
                    notifyAddPatient()
                }

                R.id.menu_logout -> {
                    notifyLogout()
                }
            }

            drawerLayout.closeDrawers()
            true
        }
    }

    // =====================================================
    // TOOLBAR / HAMBURGER
    // =====================================================

    fun setupToolbar(toolbar: androidx.appcompat.widget.Toolbar) {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(toggle)
        toggle.drawerArrowDrawable.color = getColor(android.R.color.white)
        toggle.syncState()
    }

    fun enableDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        toggle.isDrawerIndicatorEnabled = true
    }

    fun disableDrawer() {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        toggle.isDrawerIndicatorEnabled = false
    }

    // =====================================================
    // NOTIFY
    // =====================================================


    private fun notifyAddConsultation() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        val currentFragment = navHostFragment
                ?.childFragmentManager
                ?.fragments
                ?.firstOrNull()

        if (currentFragment is MainFragment) {
            currentFragment.showAddConsultationDialog()
        }
    }


    private fun notifyLogout() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        val currentFragment =
            navHostFragment?.childFragmentManager?.fragments?.firstOrNull()

        if (currentFragment is MainFragment) {
            currentFragment.handlelogout()
        }
    }


    private fun notifyAddPatient() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment)

        val currentFragment =
            navHostFragment?.childFragmentManager?.fragments?.firstOrNull()

        if (currentFragment is MainFragment) {
            currentFragment.showAddPatientDialog()
        }
    }


}




