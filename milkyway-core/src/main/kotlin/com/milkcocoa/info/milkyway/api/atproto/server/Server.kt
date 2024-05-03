package com.milkcocoa.info.milkyway.api.atproto.server

import com.milkcocoa.info.milkyway.domain.Domain

class Server(private val domain: Domain) {
    suspend fun activateAccount(request: ActivateAccount.ActivateAccountRequest) =
        ActivateAccount(domain).execute(request)

    suspend fun checkAccountStatus(request: CheckAccountStatus.CheckAccountStatusRequest) =
        CheckAccountStatus(domain).execute(request)

    suspend fun confirmEmail(request: ConfirmEmail.ConfirmEmailRequest) = ConfirmEmail(domain).execute(request)

    suspend fun createAccount(request: CreateAccount.CreateAccountRequest) = CreateAccount(domain).execute(request)

    suspend fun createAppPassword(request: CreateAppPassword.CreateAppPasswordRequest) =
        CreateAppPassword(domain).execute(request)

    suspend fun createInviteCode(request: CreateInviteCode.CreateInviteCodeRequest) =
        CreateInviteCode(domain).execute(request)

    suspend fun createInviteCodes(request: CreateInviteCodes.CreateInvitesCodeRequest) =
        CreateInviteCodes(domain).execute(request)

    suspend fun createSession(request: CreateSession.CreateSessionRequest) = CreateSession(domain).execute(request)

    suspend fun deactivateAccount(request: DeactivateAccount.DeactivateAccountRequest) =
        DeactivateAccount(domain).execute(request)

    suspend fun deleteAccount(request: DeleteAccount.DeleteAccountRequest) = DeleteAccount(domain).execute(request)

    suspend fun deleteSession(request: DeleteSession.DeleteSessionRequest) = DeleteSession(domain).execute(request)

    suspend fun describeServer(request: DescribeServer.DescribeServerRequest) = DescribeServer(domain).execute(request)

    suspend fun getAccountInviteCodes(request: GetAccountInviteCodes.GetAccountInviteCodesRequest) =
        GetAccountInviteCodes(domain).execute(request)

    suspend fun getServiceAuth(request: GetServiceAuth.GetServiceAuthRequest) = GetServiceAuth(domain).execute(request)

    suspend fun getSession(request: GetSession.GetSessionRequest) = GetSession(domain).execute(request)

    suspend fun listAppPasswords(request: ListAppPasswords.ListAppPasswordsRequest) =
        ListAppPasswords(domain).execute(request)

    suspend fun refreshSession(request: RefreshSession.RefreshSessionRequest) = RefreshSession(domain).execute(request)

    suspend fun requestAccountDelete(request: RequestAccountDelete.RequestAccountDeleteRequest) =
        RequestAccountDelete(domain).execute(request)

    suspend fun requestEmailConfirmation(request: RequestEmailConfirmation.RequestEmailConfirmationRequest) =
        RequestEmailConfirmation(domain).execute(request)

    suspend fun requestEmailUpdate(request: RequestEmailUpdate.RequestEmailUpdateRequest) =
        RequestEmailUpdate(domain).execute(request)

    suspend fun requestPasswordReset(request: RequestPasswordReset.RequestPasswordResetRequest) =
        RequestPasswordReset(domain).execute(request)

    suspend fun reserveSigningKey(request: ReserveSigningKey.ReserveSigningKeyRequest) =
        ReserveSigningKey(domain).execute(request)

    suspend fun resetPassword(request: ResetPassword.ResetPasswordRequest) = ResetPassword(domain).execute(request)

    suspend fun revokeAccountPassword(request: RevokeAppPassword.RevokeAppPasswordRequest) =
        RevokeAppPassword(domain).execute(request)

    suspend fun updateEmail(request: UpdateEmail.UpdateEmailRequest) = UpdateEmail(domain).execute(request)
}