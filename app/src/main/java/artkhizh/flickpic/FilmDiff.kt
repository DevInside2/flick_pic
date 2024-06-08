package artkhizh.flickpic

import androidx.recyclerview.widget.DiffUtil

class FilmDiff(private val oldList: List<Film>, private val newList: List<Film>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldList = oldList[oldItemPosition]
        val newList = newList[newItemPosition]
        return oldList.poster == newList.poster &&
                oldList.title == newList.title &&
                oldList.description == newList.description
    }
}