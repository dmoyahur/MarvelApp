package com.hiberus.mobile.android.marvelapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.hiberus.mobile.android.model.characters.error.AsyncError
import com.hiberus.mobile.android.repository.util.AsyncResult

abstract class BaseFragment : Fragment() {

    internal fun <T> handleDataState(
        asyncResult: AsyncResult<Any?>,
        loadingView: View?,
        errorView: View?,
        errorMessageView: View?,
        errorButtonRetryView: View?,
        retryFunction: () -> T
    ) {
        when (asyncResult) {
            is AsyncResult.Loading -> showLoading(loadingView, true)
            is AsyncResult.Success -> {
                loadingView?.visibility = View.GONE
                errorView?.visibility = View.GONE
                showResult(asyncResult.data)
            }
            is AsyncResult.Error -> {
                loadingView?.visibility = View.GONE
                showError(
                    asyncResult.error,
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