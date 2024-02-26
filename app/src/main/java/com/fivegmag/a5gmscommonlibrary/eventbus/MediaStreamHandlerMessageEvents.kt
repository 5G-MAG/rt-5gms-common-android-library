/*
License: 5G-MAG Public License (v1.0)
Author: Daniel Silhavy
Copyright: (C) 2023 Fraunhofer FOKUS
For full license terms please see the LICENSE file distributed with this
program. If this file is missing then the license can be retrieved from
https://drive.google.com/file/d/1cinCiA778IErENZ3JN52VFW-1ffHpx7Z/view
*/

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
class LoadCompletedEvent(
    val eventTime: EventTime,
    val loadEventInfo: LoadEventInfo,
    val mediaLoadData: MediaLoadData
)

class CellInfoUpdatedEvent(
    val cellInfoList: MutableList<CellInfo>
)