package com.milkcocoa.info.milkyway.api.atproto.admin

import com.milkcocoa.info.milkyway.domain.Domain

class Admin(val domain: Domain) {
    suspend fun deleteAccount(request: DeleteAccount.DeleteAccountRequest) = DeleteAccount(domain).execute(request)

    suspend fun disableAccountInvites(request: DisableAccountInvites.DisableAccountInvitesRequest) =
        DisableAccountInvites(domain).execute(request)

    suspend fun disableInviteCodes(request: DisableInviteCodes.DisableInviteCodesRequest) =
        DisableInviteCodes(domain).execute(request)

    suspend fun enableAccountInvites(request: EnableAccountInvites.EnableAccountInvitesRequest) =
        EnableAccountInvites(domain).execute(request)

    suspend fun getAccountInfo(request: GetAccountInfo.GetAccountInfoRequest) = GetAccountInfo(domain).execute(request)

    suspend fun getInviteCodes(request: GetInviteCodes.GetInviteCodesRequest) = GetInviteCodes(domain).execute(request)

    suspend fun getSubjectStatus(request: GetSubjectStatus.GetSubjectStatusRequest) =
        GetSubjectStatus(domain).execute(request)

    suspend fun sendEmail(request: SendEmail.SendEmailRequest) = SendEmail(domain).execute(request)

    suspend fun updateAccountEmail(request: UpdateAccountEmail.UpdateAccountEmailRequest) =
        UpdateAccountEmail(domain).execute(request)

    suspend fun updateAccountHandle(request: UpdateAccountHandle.UpdateAccountHandleRequest) =
        UpdateAccountHandle(domain).execute(request)

    suspend fun updateAccountPassword(request: UpdateAccountPassword.UpdateAccountPasswordRequest) =
        UpdateAccountPassword(domain).execute(request)

    suspend fun updateSubjectStatus(request: UpdateSubjectStatus.UpdateSubjectStatusRequest) =
        UpdateSubjectStatus(domain).execute(request)
}