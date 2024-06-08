package artkhizh.flickpic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import artkhizh.flickpic.databinding.FilmItemBinding

class FilmListRecyclerAdapter(private val clickListener: (item: Film) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = listOf<Film>()

    override fun getItemCount() = items.size

    inner class FilmViewHolder(private val binding: FilmItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Film) {
            binding.poster.setImageResource(film.poster)
            binding.title.text = film.title
            binding.description.setText(film.description)
            binding.root.setOnClickListener {
                clickListener(film)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = FilmItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FilmViewHolder -> {
                holder.bind(items[position])
            }
        }
    }


    fun addItems(newList: List<Film>) {
        val oldList = items
        val filmDiff = FilmDiff(oldList, newList)
        val diffResult = DiffUtil.calculateDiff(filmDiff)
        items = newList
        diffResult.dispatchUpdatesTo(this)
    }

}