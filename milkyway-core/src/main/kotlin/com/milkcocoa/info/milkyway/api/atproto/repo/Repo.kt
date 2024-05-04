package com.milkcocoa.info.milkyway.api.atproto.repo

import com.milkcocoa.info.milkyway.domain.Domain
import com.milkcocoa.info.milkyway.models.Record
import com.milkcocoa.info.milkyway.util.KtorHttpClient
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass
import kotlin.reflect.KClass

class Repo(val domain: Domain) {
    suspend fun createRecord(request: CreateRecord.CreateRecordRequest) = CreateRecord(domain).execute(request)

    @JvmName("createRecordGeneric")
    suspend inline fun <reified R : Record<*>> createRecord(
        request: CreateRecord.CreateRecordRequest
    ): CreateRecord.CreateRecordResponse {
        KtorHttpClient.addSerializersModule(
            SerializersModule {
                polymorphic(Record::class) {
                    subclass(R::class)
                }
            }
        )
        return CreateRecord(domain).execute(request)
    }

    suspend fun deleteRecord(request: DeleteRecord.DeleteRecordRequest) = DeleteRecord(domain).execute(request)

    suspend fun describeRepo(request: DescribeRepo.DescribeRepoRequest) = DescribeRepo(domain).execute(request)

    suspend fun getRecord(request: GetRecord.GetRecordRequest) = GetRecord(domain).execute(request)

    /**
     *
     */
    @JvmName("getRecordGeneric")
    suspend inline fun <reified R : Record<*>> getRecord(
        request: GetRecord.GetRecordRequest
    ): GetRecord.GetRecordResponse {
        KtorHttpClient.addSerializersModule(
            SerializersModule {
                polymorphic(Record::class) {
                    subclass(R::class)
                }
            }
        )
        return GetRecord(domain).execute(request)
    }

    suspend fun listMissingBlobs(request: ListMissingBlobs.ListMissingBlobsRequest) =
        ListMissingBlobs(domain).execute(request)

    suspend fun listRecords(request: ListRecords.ListRecordsRequest) = ListRecords(domain).execute(request)

    /**
     *
     */
    @JvmName("listRecordGeneric")
    suspend inline fun <reified R : Record<*>> listRecords(
        recordClazz: List<KClass<R>>,
        request: ListRecords.ListRecordsRequest
    ): ListRecords.ListRecordsResponse {
        KtorHttpClient.addSerializersModule(
            SerializersModule {
                polymorphic(Record::class) {
                    recordClazz.forEach {
                        subclass(it)
                    }
                }
            }
        )
        return ListRecords(domain).execute(request)
    }

    suspend fun putRecord(request: PutRecord.PutRecordRequest) = PutRecord(domain).execute(request)

    /**
     *
     */
    @JvmName("putRecordGeneric")
    suspend inline fun <reified R : Record<*>> putRecord(
        request: PutRecord.PutRecordRequest
    ): PutRecord.PutRecordResponse {
        KtorHttpClient.addSerializersModule(
            SerializersModule {
                polymorphic(Record::class) {
                    subclass(R::class)
                }
            }
        )
        return PutRecord(domain).execute(request)
    }

    suspend fun uploadBlob(request: UploadBlob.UploadBlobRequest) = UploadBlob(domain).execute(request)
}