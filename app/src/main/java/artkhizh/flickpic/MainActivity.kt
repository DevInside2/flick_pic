package artkhizh.flickpic

import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import artkhizh.flickpic.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isDarkMode = false
    private var backPressed = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
        setupOnBackPressedListener()
    }

    private fun setupOnBackPressedListener() {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                }
                if(backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                    Toast.makeText(this@MainActivity, "Bye", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Toast.makeText(this@MainActivity, "Double tap for exit", Toast.LENGTH_SHORT).show()
                }
                backPressed = System.currentTimeMillis()
            }
        }
        )
    }


    private fun initNavigation() {
        binding.topAppBar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu -> {
                    showToast("Меню")
                    true
                }

                R.id.changeTheme -> {
                    if (!isDarkMode) {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                        isDarkMode = true
                    } else {
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                        isDarkMode = false
                    }
                    true
                }

                else -> false
            }
        }
        binding.bottomNavigation?.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.itemCollections -> {
                    showToast("Коллекции")
                    true
                }

                R.id.itemFavorite -> {
                    showToast("Избранное")
                    true
                }

                R.id.itemSettings -> {
                    showToast("Настройки")
                    true
                }

                R.id.itemWatchLater -> {
                    showToast("Смотреть позже")
                    true
                }

                else -> false

            }
        }
    }

    fun launchDetailsFragment(film: Film) {
        val bundle = Bundle()
        bundle.putParcelable(FILM_KEY, film)
        val fragment = DetailsFragment()
        fragment.arguments = bundle
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_placeholder, fragment)
            .addToBackStack(null)
            .commit()
    }


    companion object {
        const val FILM_KEY = "FILM_KEY"
        const val TIME_INTERVAL = 2000
    }
}
