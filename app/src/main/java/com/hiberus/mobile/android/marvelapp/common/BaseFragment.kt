package com.hiberus.mobile.android.marvelapp.common

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hiberus.mobile.android.marvelapp.R
import com.hiberus.mobile.android.marvelapp.common.model.ResourceState
import com.hiberus.mobile.android.marvelapp.util.setVisible
import com.hiberus.mobile.android.model.error.AsyncError
import timber.log.Timber

abstract class BaseFragment : Fragment() {

    internal fun <T> handleDataState(
        resourceState: ResourceState<Any?>,
        successView: View?,
        loadingView: View?,
        errorView: View?,
        errorMessageView: View?,
        errorButtonRetryView: View?,
        retryFunction: () -> T
    ) {
        when (resourceState) {
            is ResourceState.Loading -> loadingView.setVisible(true)
            is ResourceState.Success -> {
                loadingView.setVisible(false)
                errorView.setVisible(false)
                successView.setVisible(true)
                showResult(resourceState.data)
            }
            is ResourceState.Error -> {
                Timber.e(resourceState.error.debugMessage)
                loadingView.setVisible(false)
                successView.setVisible(false)
                errorView.setVisible(true)
                showError(
                    resourceState.error,
                    errorMessageView,
                    errorButtonRetryView,
                    retryFunction
                )
            }
        }
    }

    private fun <T> showError(
        asyncError: AsyncError,
        errorMessageView: View?,
        errorButtonRetryView: View?,
        retryFunction: () -> T
    ) {
        errorButtonRetryView?.setOnClickListener { retryFunction() }
        (errorMessageView as? TextView)?.text = when (asyncError) {
            is AsyncError.ConnectionError -> resources.getString(R.string.connection_error)
            is AsyncError.ServerError -> resources.getString(R.string.server_error)
            else -> resources.getString(R.string.unknown_error)
        }
    }

    protected abstract fun showResult(result: Any?)
}
