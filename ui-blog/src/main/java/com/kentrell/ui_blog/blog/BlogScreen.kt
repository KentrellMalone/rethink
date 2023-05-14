package com.kentrell.ui_blog.blog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.kentrell.domain.base.isError
import com.kentrell.domain.base.isLoading
import com.kentrell.domain.error.NetworkError
import com.kentrell.domain.model.Comment
import com.kentrell.domain.model.Post
import com.kentrell.domain.model.User
import com.kentrell.ui_blog.R
import com.kentrell.ui_blog.component.ExpandableCard
import com.kentrell.ui_common.mvi.collectAsState

@Composable
fun BlogScreen(viewModel: BlogViewModel = hiltViewModel()) {
    val state by viewModel.collectAsState()

    BlogScreen(
        state = state,
    )
}

@Composable
private fun BlogScreen(
    state: BlogState,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Header(state.itemsCount)

        if (state.status.isLoading())
            LinearProgressIndicator(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth()
            )
        else if (state.status.isError())
            ErrorBox(error = state.status.error)

        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(
                items = state.users,
                key = { it.id },
            ) {
                UserItem(it)
            }
        }
    }
}

@Composable
private fun UserItem(user: User) {
    ExpandableCard(
        title = stringResource(id = R.string.user_name, user.name),
        count = user.posts.count(),
    ) {
        Text(text = stringResource(id = R.string.email, user.email))

        user.posts.forEach { post ->
            key(post.id) {
                PostItem(post)
            }
        }
    }
}

@Composable
private fun PostItem(post: Post) {
    ExpandableCard(
        modifier = Modifier.padding(horizontal = 4.dp),
        title = stringResource(id = R.string.post_title, post.title),
        count = post.comments.count(),
    ) {
        Text(text = stringResource(id = R.string.body, post.body))

        post.comments.forEach { comment ->
            key(comment.id) {
                CommentItem(comment)
            }

        }
    }
}

@Composable
private fun CommentItem(comment: Comment) {
    ExpandableCard(
        title = stringResource(id = R.string.comment_name, comment.name),
    ) {
        Text(text = stringResource(id = R.string.email, comment.email))

        Text(text = stringResource(id = R.string.body, comment.body))
    }
}

@Composable
private fun Header(itemsCount: Long) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = stringResource(R.string.header),
            style = MaterialTheme.typography.h6,
        )

        Text(
            text = itemsCount.toString(),
            style = MaterialTheme.typography.h6,
        )
    }
}

@Composable
private fun ErrorBox(error: Throwable?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .border(1.dp, MaterialTheme.colors.primary, MaterialTheme.shapes.medium)
            .padding(horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        val errorMessageRes = when (error) {
            NetworkError.NoInternet -> R.string.internet_is_unavailable
            NetworkError.Server -> R.string.it_s_not_you_it_s_us
            NetworkError.Timeout -> R.string.connection_problem
            else -> R.string.something_went_wrong
        }

        Text(text = stringResource(errorMessageRes))
    }
}


