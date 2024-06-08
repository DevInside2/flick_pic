package artkhizh.flickpic

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import artkhizh.flickpic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isDarkMode = false
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private val filmDataBase = listOf(
        Film(
            1,
            "Pixels",
            R.drawable.pixels,
            R.string.pixels_desc
        ),
        Film(
            2,
            "Moonlight",
            R.drawable.moonlight,
            R.string.moonlight_desc
        ),
        Film(
            3,
            "Predators",
            R.drawable.predators,
            R.string.predators_desc
        ),
        Film(
            4,
            "Horrible bosses",
            R.drawable.horrible_bosses,
            R.string.horrible_bosses_desc
        ),
        Film(
            5,
            "Madea's witness protection",
            R.drawable.madeas_witness_protection,
            R.string.madeas_witness_protection_desc
        ),
        Film(
            6,
            "Mr popper's penguins",
            R.drawable.mr_poppers_penguins,
            R.string.mr_poppers_penguins_desc
        ),
        Film(
            7,
            "Murder on the orient express",
            R.drawable.murder_on_the_orient_express,
            R.string.murder_on_the_orient_express_desc
        ),
        Film(
            8,
            "The dark knight rises bane",
            R.drawable.the_dark_knight_rises_bane,
            R.string.the_dark_knight_rises_bane_desc
        ),
        Film(
            9,
            "The intruder",
            R.drawable.the_intruder,
            R.string.the_intruder_desc
        ),
        Film(
            10,
            "Game of thrones",
            R.drawable.game_of_thrones,
            R.string.game_of_thrones_desc
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initNavigation()
        initRecyclerView()
        filmsAdapter.addItems(filmDataBase)
    }


    private fun initRecyclerView() {
        binding.mainRecycler?.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(clickListener = {
                    val intent = Intent(this@MainActivity, DetailsActivity::class.java)
                    intent.putExtra(FILM_KEY, it)
                    startActivity(intent)

                })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)

        }
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

    companion object {
        const val FILM_KEY = "FILM_KEY"
    }
}
