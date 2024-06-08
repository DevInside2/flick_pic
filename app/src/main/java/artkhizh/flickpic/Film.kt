package artkhizh.flickpic

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Film(
    val id: Int,
    val title: String,
    @DrawableRes val poster: Int,
    @StringRes val description: Int,
) : Parcelable