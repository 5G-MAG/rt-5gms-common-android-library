package com.fivegmag.a5gmscommonlibrary.eventbus

import android.telephony.CellInfo
import androidx.media3.exoplayer.analytics.AnalyticsListener.EventTime
import androidx.media3.exoplayer.source.LoadEventInfo
import androidx.media3.exoplayer.source.MediaLoadData

class DownstreamFormatChangedEvent(
    val eventTime: EventTime,
    val mediaLoadData: MediaLoadData
)

class PlaybackStateChangedEvent(
    val eventTime: EventTime,
    val playbackState: String
)

class LoadStartedEvent(
    val eventTime: EventTime,
    val loadEventInfo: LoadEventInfo,
    val mediaLoadData: MediaLoadData
)

class CellInfoUpdatedEvent(
    val cellInfoList: MutableList<CellInfo>
)