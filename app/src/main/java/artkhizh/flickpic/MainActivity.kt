package artkhizh.flickpic

import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import artkhizh.flickpic.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var backPressed = 0L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        toHomeFragment()
        setupOnBackPressedListener()
    }

    private fun toHomeFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_placeholder, HomeFragment())
            .addToBackStack(null)
            .commit()
    }

    private fun setupOnBackPressedListener() {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                }
                if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                    showToast("Bye")
                    finish()
                } else {
                    showToast("double tap to exit")
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
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fragment_placeholder, FavoritesFragment())
                        .addToBackStack(null)
                        .commit()
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
