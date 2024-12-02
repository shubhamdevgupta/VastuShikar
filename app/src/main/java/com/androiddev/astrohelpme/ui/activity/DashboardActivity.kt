package com.androiddev.astrohelpme.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.androiddev.astrohelpme.R
import com.androiddev.astrohelpme.data.local.AppPreference
import com.androiddev.astrohelpme.databinding.ActivityDashboardBinding
import com.androiddev.astrohelpme.ui.fragment.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DashboardActivity @Inject constructor(): AppCompatActivity() {

    private lateinit var binding: ActivityDashboardBinding
    private lateinit var drawerLayout: DrawerLayout

    @Inject
    lateinit var appPreference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize DrawerLayout
        drawerLayout = binding.drawerLayout


        binding.navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> loadFragment(DashboardFragment())
                R.id.nav_view -> loadFragment(DashboardFragment())
                R.id.logout -> logout()
            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
    }


    private fun logout() {
        val intent = Intent(this, AuthActivity::class.java)
        appPreference.setLoggedIn(false)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}
