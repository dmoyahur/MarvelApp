package com.hiberus.mobile.android.marvelapp.common

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hiberus.mobile.android.marvelapp.common.model.ResourceState
import com.hiberus.mobile.android.model.error.AsyncError

abstract class BaseFragment : Fragment() {

    internal fun <T> handleDataState(
        resourceState: ResourceState<Any?>,
        loadingView: View?,
        errorView: View?,
        errorMessageView: View?,
        errorButtonRetryView: View?,
        retryFunction: () -> T
    ) {
        when (resourceState) {
            is ResourceState.Loading -> showLoading(loadingView, true)
            is ResourceState.Success -> {
                loadingView?.visibility = View.GONE
                errorView?.visibility = View.GONE
                showResult(resourceState.data)
            }
            is ResourceState.Error -> {
                loadingView?.visibility = View.GONE
                showError(
                    resourceState.error,
                    errorView,
                    errorMessageView,
                    errorButtonRetryView,
                    retryFunction
                )
            }
        }
    }

    private fun showLoading(loadingView: View?, showLoading: Boolean) {
        if (showLoading) {
            loadingView?.visibility = View.VISIBLE
        } else {
            loadingView?.visibility = View.GONE
        }
    }

    private fun <T> showError(
        asyncError: AsyncError,
        errorView: View?,
        errorMessageView: View?,
        errorButtonRetryView: View?,
        retryFunction: () -> T
    ) {
        errorView?.visibility = View.VISIBLE
        (errorMessageView as? TextView)?.text = asyncError.debugMessage
        errorButtonRetryView?.setOnClickListener { retryFunction() }
    }

    protected abstract fun showResult(result: Any?)
}