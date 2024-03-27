package com.milkcocoa.info.milkyway.api.com.atproto.repo

import com.milkcocoa.info.milkyway.atproto.action.AtProtoActions
import com.milkcocoa.info.milkyway.atproto.method.AtProtocolPost
import com.milkcocoa.info.milkyway.atproto.models.com.atproto.repo.CreateRecordResponse
import com.milkcocoa.info.milkyway.atproto.models.com.atproto.repo.CreateRecordRequest
import com.milkcocoa.info.milkyway.domain.Domain

class CreateRecord(val domain: Domain): AtProtocolPost<CreateRecordRequest, CreateRecordResponse>(
    AtProtoActions.ATPROTO_CREATE_RECORD,
    domain,
    CreateRecordRequest::class,
    CreateRecordResponse::class
)

