package artkhizh.flickpic

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import artkhizh.flickpic.MainActivity.Companion.FILM_KEY
import artkhizh.flickpic.databinding.FragmentDetailsBinding
import com.google.android.material.snackbar.Snackbar

class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private lateinit var film: Film
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        @Suppress("DEPRECATION")
        film = arguments?.getParcelable<Film>(FILM_KEY) as Film
        binding = FragmentDetailsBinding.inflate(requireActivity().layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        renderUI(film)
        initToolBar()
        initFavButton()
        initShareButton()
    }

    private fun initShareButton() {
        binding.detailsFabShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(
                Intent.EXTRA_TEXT,
                "Checkout this film: ${film.title} /n /n ${film.description}"
            )
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share to:"))
        }
    }

    private fun initFavButton() {
        with(binding.detailsFabFavorites) {
            setImageResource(
                if (film.isInFavorites) R.drawable.baseline_favorite_24
                else R.drawable.baseline_favorite_border_24
            )
            setOnClickListener {
                if (!film.isInFavorites) {
                    setImageResource(R.drawable.baseline_favorite_24)
                    film.isInFavorites = true
                    FavoritesManager.addFilm(film)
                } else {
                    setImageResource(R.drawable.baseline_favorite_border_24)
                    film.isInFavorites = false
                    FavoritesManager.removeFilm(film)
                }
            }
        }
    }

    private fun initToolBar() {
        binding.detailsToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.addToFavoriteButton -> {
                    Snackbar.make(
                        binding.detailsLayout, "Добавлено в избранное",
                        Snackbar.LENGTH_SHORT
                    ).setAction("Action") {
                        Toast.makeText(requireContext(), "Action", Toast.LENGTH_SHORT).show()
                    }.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.green))
                        .show()
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
            detailsToolbar.setTitle(film.title)
        }
    }
}
