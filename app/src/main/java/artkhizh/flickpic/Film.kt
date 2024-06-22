package artkhizh.flickpic

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Int,
    @StringRes val title: Int,
    @DrawableRes val poster: Int,
    @StringRes val description: Int,
    var isInFavorites: Boolean = false,
) : Parcelable

object FavoritesManager {
    private val favoriteFilms = mutableListOf<Film>()

    fun addFilm(film: Film) {
        if (!favoriteFilms.contains(film)) {
            favoriteFilms.add(film)
        }
    }

    fun removeFilm(film: Film) {
        favoriteFilms.remove(film)
    }

    fun getFilms(): List<Film> {
        return favoriteFilms.toList()
    }
}