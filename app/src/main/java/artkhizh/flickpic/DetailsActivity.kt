package artkhizh.flickpic

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import artkhizh.flickpic.MainActivity.Companion.FILM_KEY
import artkhizh.flickpic.databinding.ActivityDetailsBinding
import com.google.android.material.snackbar.Snackbar

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val film = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(FILM_KEY, Film::class.java)
        } else {
            intent.getParcelableExtra(FILM_KEY)
        } ?: return
        renderUI(film)
        initToolBar()
    }

    private fun initToolBar() {
        binding.detailsToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addToFavoriteButton -> {
                    Snackbar.make(
                        binding.detailsLayout, "Добавлено в избранное",
                        Snackbar.LENGTH_SHORT
                    ).setAction("Action") {
                        Toast.makeText(this, "Action", Toast.LENGTH_SHORT).show()
                    }.setActionTextColor(ContextCompat.getColor(this, R.color.green)).show()
                    true
                }

                R.id.watchLater -> {
                    Snackbar.make(
                        binding.detailsLayout,
                        "Добавлено в плейлист",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    true
                }

                else -> false
            }
        }
    }

    private fun renderUI(film: Film) {
        with(binding) {
            detailsPoster.setImageResource(film.poster)
            detailsDescription.setText(film.description)
            detailsToolbar.title = film.title
        }
    }
}
