package artkhizh.flickpic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import artkhizh.flickpic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isDarkMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()

    }

    private fun initNavigation() {
        binding.topAppBar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.menu -> {
                    showToast("Меню")
                    true
                }

                R.id.change_theme -> {
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
                R.id.item_collections -> {
                    showToast("Коллекции")
                    true
                }

                R.id.item_favorite -> {
                    showToast("Избранное")
                    true
                }

                R.id.item_settings -> {
                    showToast("Настройки")
                    true
                }

                R.id.item_watchLater -> {
                    showToast("Смотреть позже")
                    true
                }

                else -> false

            }
        }
    }

}
