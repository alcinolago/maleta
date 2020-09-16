package br.ind.siam.alcinolago.maleta.provider.string

import android.content.Context

class StringProviderImpl(private val context: Context): StringProvider {
    override fun getString(stringID: Int): String {
        return context.getString(stringID)
    }
}
