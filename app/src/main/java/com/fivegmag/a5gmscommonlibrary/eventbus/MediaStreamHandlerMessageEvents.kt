package com.fivegmag.a5gmscommonlibrary.eventbus

import androidx.media3.exoplayer.analytics.AnalyticsListener
import androidx.media3.exoplayer.source.MediaLoadData

class DownstreamFormatChangedEvent(
    val eventTime: AnalyticsListener.EventTime,
    val mediaLoadData: MediaLoadData
)

class PlaybackStateChangedEvent(
    val eventTime: AnalyticsListener.EventTime,
    val playbackState: String
)