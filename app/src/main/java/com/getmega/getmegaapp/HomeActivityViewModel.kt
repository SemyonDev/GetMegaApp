package com.getmega.getmegaapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getmega.domain.usecase.TrendingUseCase
import com.getmega.entities.Developer
import com.getmega.entities.Repo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class HomeActivityViewModel(private val trendingUseCase: TrendingUseCase) : ViewModel() {

    private val _repoList = MutableStateFlow(listOf<Repo>())
    val repoList: StateFlow<List<Repo>> get() = _repoList

    private val _expandedRepoIdList = MutableStateFlow(listOf<Int>())
    val expandedRepoIdList: StateFlow<List<Int>> get() = _expandedRepoIdList

    init {
        getFakeData()
    }

    private fun getFakeData() {
        viewModelScope.launch(Dispatchers.Default) {
            val testList = arrayListOf<Repo>()
            repeat(20) {
                testList += Repo(
                    id = it,
                    author = "xingshaocheng",
                    name = "architect-awesome",
                    avatar = "https://github.com/xingshaocheng.png",
                    url = "https://github.com/xingshaocheng/architect-awesome",
                    description = "后端架构师技术图谱",
                    language = "Go",
                    languageColor = "#3572A5",
                    stars = 7333,
                    forks = 1546,
                    currentPeriodStars = 1528,
                    builtBy = listOf(
                        Developer(
                            href = "https://github.com/viatsko",
                            avatar = "https://avatars0.githubusercontent.com/u/376065",
                            username = "viatsko"
                        )
                    ),
                )
            }
            _repoList.emit(testList)
        }
    }

    fun onExpandArrowClicked(repoId: Int) {
        _expandedRepoIdList.value = _expandedRepoIdList.value.toMutableList().also { list ->
            if (list.contains(repoId)) list.remove(repoId) else list.add(repoId)
        }
    }

    fun getTrendingList() {
        val coroutineExceptionHandler =
            CoroutineExceptionHandler { coroutineContext: CoroutineContext, throwable: Throwable ->
                Log.d("Tag", "Response = " + throwable.toString())
            }
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            val profileResponse = trendingUseCase.getTrendingDeveloperList()
            Log.d("Tag", "Response = " + profileResponse.toString())
        }

    }


}