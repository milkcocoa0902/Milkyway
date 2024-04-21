package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.domain.Domain

class Repo(private val domain: Domain) {
    suspend fun createRecord(request: CreateRecord.CreateRecordRequest) = CreateRecord(domain).execute(request)

    suspend fun deleteRecord(request: DeleteRecord.DeleteRecordRequest) = DeleteRecord(domain).execute(request)

    suspend fun describeRepo(request: DescribeRepo.DescribeRepoRequest) = DescribeRepo(domain).execute(request)

    suspend fun getRecord(request: GetRecord.GetRecordRequest) = GetRecord(domain).execute(request)

    suspend fun listMissingBlobs(request: ListMissingBlobs.ListMissingBlobsRequest) = ListMissingBlobs(domain).execute(request)

    suspend fun listRecords(request: ListRecords.ListRecordsRequest) = ListRecords(domain).execute(request)

    suspend fun putRecord(request: PutRecord.PutRecordRequest) = PutRecord(domain).execute(request)

    suspend fun uploadBlob(request: UploadBlob.UploadBlobRequest) = UploadBlob(domain).execute(request)
}