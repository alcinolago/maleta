package br.ind.siam.alcinolago.maleta.core.state

sealed class LoadingState {
    /**
     * Only state that shouldn't show a progress bar, not following this contract may break existing
     * parts of the application
     */
    object NotLoading : LoadingState()

    object Loading : LoadingState()
    object Saving : LoadingState()
    object RequestingLocation : LoadingState()
}