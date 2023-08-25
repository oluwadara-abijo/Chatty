package com.fueled.chatty.feature.events.presentation

import com.fueled.chatty.core.common.BaseViewModel
import com.fueled.chatty.core.common.DispatcherProvider
import com.fueled.chatty.core.common.Ignored
import com.fueled.chatty.feature.events.presentation.contract.EventsAction
import com.fueled.chatty.feature.events.presentation.contract.EventsState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class EventsViewModel @Inject constructor(
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel<EventsState, EventsAction>(EventsState.initialState, dispatcherProvider) {

    // TODO: Not yet implemented
    override fun onViewAction(viewAction: EventsAction) = Ignored

    // TODO: Not yet implemented
    override fun handleError(throwable: Throwable) = Ignored
}
