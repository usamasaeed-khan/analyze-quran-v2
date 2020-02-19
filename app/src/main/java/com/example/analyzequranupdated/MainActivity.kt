package com.example.analyzequranupdated

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawer:DrawerLayout

    private lateinit var toolbar:Toolbar

    private lateinit var navView:NavigationView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)

        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.Open, R.string.Close)


        drawer.addDrawerListener(toggle)

        toggle.syncState()

        navView = findViewById(R.id.nav_view)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        drawer.addDrawerListener(toggle)

        toggle.syncState()

        navView.setNavigationItemSelectedListener(this)

    }


    override fun onNavigationItemSelected(p0: MenuItem): Boolean {

        when(p0.itemId) {

            R.id.home -> Toast.makeText(this,"Home",Toast.LENGTH_LONG).show()


            R.id.donate -> Toast.makeText(this,"Donate",Toast.LENGTH_LONG).show()


            R.id.about -> Toast.makeText(this,"About",Toast.LENGTH_LONG).show()

        }

        drawer.closeDrawer(GravityCompat.START)

        return true
    }
}
