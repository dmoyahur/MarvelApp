package com.hiberus.mobile.android.model.characters.error

import java.lang.Exception

class AsyncException(val asyncError: AsyncError): Exception() {
    override fun toString(): String {
        return asyncError.toString()
    }
}