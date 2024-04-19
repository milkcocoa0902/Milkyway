package com.milkcocoa.info.milkyway.api.bsky.notification

import com.milkcocoa.info.milkyway.domain.Domain

class Notification(val domain: Domain) {
    suspend fun getUnreadCount(request: GetUnreadCount.GetUnreadCountRequest) = GetUnreadCount(domain).execute(request)

    suspend fun listNotifications(request: ListNotifications.ListNotificationsRequest) =
        ListNotifications(domain).execute(request)

    suspend fun registerPush(request: RegisterPush.RegisterPushRequest) = RegisterPush(domain).execute(request)

    suspend fun updateSeen(request: UpdateSeen.UpdateSeenRequest) = UpdateSeen(domain).execute(request)
}