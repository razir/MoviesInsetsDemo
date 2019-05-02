package com.github.razir.movies

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.marginTop
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.GridLayoutManager
import com.github.razir.movies.utils.GridItemDecorator
import com.github.razir.movies.utils.getTestData
import kotlinx.android.synthetic.main.activity_movies.*
import android.util.DisplayMetrics


class MoviesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies)
        initView()
        setupInsets()
    }

    private fun setupInsets() {
        val baseMoviesPadding = pxFromDp(10f)
        val toolbarHeight = moviesToolbar.layoutParams.height

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            moviesRootLayout.systemUiVisibility = SYSTEM_UI_FLAG_LAYOUT_STABLE or
                    SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
        } else {
            moviesRecyclerView.updatePadding(top = toolbarHeight + baseMoviesPadding)
        }

        ViewCompat.setOnApplyWindowInsetsListener(moviesToolbar) { view, insets ->
            moviesToolbar.setMarginTop(insets.systemWindowInsetTop)
            moviesRecyclerView.updatePadding(top = toolbarHeight + insets.systemWindowInsetTop + baseMoviesPadding)
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(moviesRecyclerView) { view, insets ->
            moviesRecyclerView.updatePadding(bottom = insets.systemWindowInsetBottom)
            insets
        }
    }

    private fun initView() {
        moviesRecyclerView.apply {
            addItemDecoration(GridItemDecorator(ContextCompat.getDrawable(context, R.drawable.movies_divider)!!))
            layoutManager = GridLayoutManager(context, 2)
            adapter = MoviesAdapter(getTestData())
        }
    }

    private fun View.setMarginTop(value: Int) = updateLayoutParams<ViewGroup.MarginLayoutParams> {
        topMargin = value
    }

    private fun pxFromDp(dp: Float): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }
}
