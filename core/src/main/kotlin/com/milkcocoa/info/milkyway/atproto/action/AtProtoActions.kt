package com.milkcocoa.info.milkyway.atproto.action

object AtProtoActions {
    // com.atproto.server
    val CreateSession = Action("com.atproto.server.createSession")


    // com.atproto.repo
    val ApplyWrites = Action("com.atproto.repo.applyWrites")
    val CreateRecord = Action("com.atproto.repo.createRecord")
    val DeleteRecord = Action("com.atproto.repo.deleteRecord")
    val DescribeRepo = Action("com.atproto.repo.describeRepo")
    val GetRecord = Action("com.atproto.repo.getRecord")
    val ImportRepo = Action("com.atproto.repo.importRepo")
    val ListMissingBlobs = Action("com.atproto.repo.listMissingBlobs")
    val ListRecords = Action("com.atproto.repo.listRecords")
    val PutRecord = Action("com.atproto.repo.putRecord")
    val UploadBlob = Action("com.atproto.repo.uploadBlob")


}