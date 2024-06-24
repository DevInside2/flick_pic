package artkhizh.flickpic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import artkhizh.flickpic.databinding.FragmentHomeBinding
import java.util.Locale

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var filmsAdapter: FilmListRecyclerAdapter
    private val filmDataBase = listOf(
        Film(
            1,
            R.string.pixels_title,
            R.drawable.pixels,
            R.string.pixels_desc
        ),
        Film(
            2,
            R.string.moonlight_title,
            R.drawable.moonlight,
            R.string.moonlight_desc
        ),
        Film(
            3,
            R.string.predators_title,
            R.drawable.predators,
            R.string.predators_desc
        ),
        Film(
            4,
            R.string.horrible_bosses_title,
            R.drawable.horrible_bosses,
            R.string.horrible_bosses_desc
        ),
        Film(
            5,
            R.string.madeas_witness_title,
            R.drawable.madeas_witness_protection,
            R.string.madeas_witness_protection_desc
        ),
        Film(
            6,
            R.string.mr_popper_title,
            R.drawable.mr_poppers_penguins,
            R.string.mr_poppers_penguins_desc
        ),
        Film(
            7,
            R.string.murder_on_the_orient_express_title,
            R.drawable.murder_on_the_orient_express,
            R.string.murder_on_the_orient_express_desc
        ),
        Film(
            8,
            R.string.the_dark_knight_rises_bane_title,
            R.drawable.the_dark_knight_rises_bane,
            R.string.the_dark_knight_rises_bane_desc
        ),
        Film(
            9,
            R.string.the_intruder_title,
            R.drawable.the_intruder,
            R.string.the_intruder_desc
        ),
        Film(
            10,
            R.string.game_of_thrones_title,
            R.drawable.game_of_thrones,
            R.string.game_of_thrones_desc
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentHomeBinding.inflate(requireActivity().layoutInflater)
        initRecyclerView()
        with(binding.searchView) {
            setOnClickListener {
                isIconified = false
            }
            setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText?.isEmpty() == true) {
                        filmsAdapter.addItems(filmDataBase)
                    }
                    val result = filmDataBase.filter {
                        resources.getString(it.title).lowercase(Locale.getDefault()).contains(
                            newText?.lowercase(Locale.getDefault()).toString()
                        )
                    }
                    filmsAdapter.addItems(result)
                    return true
                }
            })
        }
        filmsAdapter.addItems(filmDataBase)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    private fun initRecyclerView() {
        binding.mainRecycler.apply {
            filmsAdapter =
                FilmListRecyclerAdapter(clickListener = {
                    (requireActivity() as MainActivity).launchDetailsFragment(it)
                })
            adapter = filmsAdapter
            layoutManager = LinearLayoutManager(requireContext())
            val decorator = TopSpacingItemDecoration(8)
            addItemDecoration(decorator)
            addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (dy > 0) {
                        binding.searchView.animate()
                            .scaleY(0.9f)
                            .withEndAction {
                                binding.searchView.visibility = SearchView.GONE
                            }
                            .start()
                    } else if (dy < 0) {
                        binding.searchView.scaleY = 1f
                        binding.searchView.visibility = SearchView.VISIBLE
                    }
                }
            })
        }
    }

}