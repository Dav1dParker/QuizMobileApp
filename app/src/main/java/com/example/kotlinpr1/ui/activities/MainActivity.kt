package com.example.kotlinpr1.ui.activities

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.kotlinpr1.R
import com.example.kotlinpr1.databinding.ActivityMainBinding
import com.example.kotlinpr1.ui.fragments.FirstFragment
import com.example.kotlinpr1.ui.fragments.SecondFragment
import com.example.kotlinpr1.ui.fragments.ThirdFragment
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
    private val ff: FirstFragment,
    private val sf: SecondFragment,
    private val tf: ThirdFragment
) : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //private lateinit var viewModel: MainActivityViewModel
    //private val QuizViewModel: QuizViewModel by activityViewModels()
    private lateinit var toggle: ActionBarDrawerToggle
    lateinit var drawerLayout: DrawerLayout
    //var getAll: LiveData<List<QuestionsEntity>> = QuizViewModel(application).getAll
    //private lateinit var repository: QuizRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Quiz Logic starts
        /*val endpoint: String = "https://opentdb.com/"

        //val doneLayout: ConstraintLayout
        //Get data from API using retrofit
        val retrofit = Retrofit.Builder().baseUrl(endpoint).addConverterFactory(GsonConverterFactory.create()).build()
        val service = retrofit.create(ApiInterface::class.java)
        val call = service.getQuizResults(5, 10, "easy", "multiple")*/


        //Quiz Logic ends


        //DB logic starts
        /*val weatherDao = QuizDataBase.getDataBase(application).QuestionDao()
        repository = QuizRepository(weatherDao)
        getAll = repository.getAll
        val entity = QuestionsEntity(1, "What is the capital of India?", "Delhi", "Mumbai", "Kolkata", "Chennai")*/
        //QuizViewModel.insertQuestions(entity)

        /*val QuestionDao = QuizDataBase.getDataBase(application).QuestionDao()
        val repository = QuizRepository(QuestionDao)
        repository.insertQuestions(entity)
        repository.getAll.observe(this){
            it.forEach{
                println(it)
            }
        }*/


        //DB logic ends


        drawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close)

        //change the icon of the drawer to hamburger symbol
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        navView.setNavigationItemSelectedListener {
            it.isChecked = true
            when (it.itemId) {
                R.id.nav_home -> {
                    //open first fragment
                    replaceFragment(ff, it.title.toString())
                    /*val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, FirstFragment())
                    transaction.commit()*/
                }

                R.id.nav_settings -> {
                    //open third fragment
                    replaceFragment(tf, it.title.toString())
                    /*val transaction = supportFragmentManager.beginTransaction()
                    transaction.replace(R.id.nav_host_fragment, ThirdFragment())
                    transaction.commit()*/
                    //supportFragmentManager.beginTransaction().replace(R.id.fragment_container, GalleryFragment()).commit()
                    //drawerLayout.closeDrawers()
                }

                R.id.nav_ShowDatabase -> {
                    //show toast with blank text
                    replaceFragment(sf, it.title.toString())
                    Toast.makeText(this, "To do", Toast.LENGTH_SHORT).show()
                }

                R.id.nav_logOut -> {
                    Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                }

                R.id.nav_exit -> {
                    //close app
                    finishAffinity()
                }
            }
            true
        }


    }

    fun replaceFragment(fragment: Fragment, title: String) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nav_host_fragment, fragment)
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
        setTitle(title)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}