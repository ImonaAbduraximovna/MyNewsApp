package uz.azamat.myapplication.core.models.TopHeadLine


import com.example.newsapp.core.models.Article
import com.google.gson.annotations.SerializedName

data class TopHeadLine(
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("articles")
    val articles: List<Article>
)