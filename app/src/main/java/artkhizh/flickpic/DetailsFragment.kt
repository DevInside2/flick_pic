package artkhizh.flickpic

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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailsBinding.inflate(requireActivity().layoutInflater)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val film = arguments?.get(FILM_KEY) as Film
        renderUI(film)
        initToolBar()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
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
                    }.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.green)).show()
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
