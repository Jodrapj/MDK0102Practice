package nmedia.viewmodel

import android.app.Application
import androidx.lifecycle.*
import nmedia.repository.PostRepositorySQLiteImpl
import ru.netology.nmedia.db.AppDb
import nmedia.dto.Post
import nmedia.repository.*

private val empty = Post(
    id = 0,
    content = "",
    author = "",
    likedByMe = false,
    likes = 0,
    published = "",
    kolLikes = false,
    kolRep = 12
)

class PostViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: PostRepository = PostRepositorySQLiteImpl(
        AppDb.getInstance(application).postDao
    )
    val data = repository.getAll()
    val edited = MutableLiveData(empty)

    fun save() {
        edited.value?.let {
            //repository.save(it)
        }
        edited.value = empty
    }

    fun edit(post: Post) {
        edited.value = post
    }

    fun changeContent(content: String) {
        val text = content.trim()
        if (edited.value?.content == text) {
            return
        }
        edited.value = edited.value?.copy(content = text)
    }

    fun likeById(id: Long) = repository.likeById(id)
    fun removeById(id: Long) = repository.removeById(id)
}