package br.ind.siam.alcinolago.maleta.core.preference

interface PreferenceRepository {

    companion object {
        const val USER_DATA = "PERSON_DATA"
        const val PREVENT_DATA = "PREVENT_DATA"
    }

    var userIsFirstAccess: Boolean
    var preventRepeatData: Int

    fun cleanAll()

    /**
     * Cleans all preferences except [exceptions].
     * See [PreferenceRepository#companion object] for valid values
     */
    fun cleanAll(vararg exceptions: String)
}
