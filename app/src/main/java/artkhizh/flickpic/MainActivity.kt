package artkhizh.flickpic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import artkhizh.flickpic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
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
