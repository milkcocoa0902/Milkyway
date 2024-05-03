## Milkyway

This library is a ATProtocol and Bluesly API client on kotlin.  



### core
ATProtocol core library.  


### bsky
ATProtocol extention for Bluesky.  
if you want to access Bluesky resources, you also need this module.


## Features


## Installation  

```kts
repositories {
    mavenCentral()
}
```


```kts
implementation("io.github.milkcocoa0902:milkyway-core:0.0.3")

// Bluesky extension
implementation("io.github.milkcocoa0902:milkyway-bsky:0.0.3")
```



## Usage

### Get App Instance
Milkyway provides all of api via client.  
so first, you need to get client.

```kotlin
val client = Milkyway.instance(domain = OfficialDomain.Bsky)
```

now you can use api.

### CreateSession
and then, create a session for getting a credentials which needed to authorize requests.  


```kotlin
val session =
    client.atProtocol()
          .server()
          .createSession(
              CreateSession.CreateSessionRequest(
                  identifier = System.getenv("BSKY_IDENTIFIER"), // replace your own identifier. example - bsky.milkcocoa.info
                  password = System.getenv("BSKY_PASSWORD")      // replace your own password
              )
          )


println(session.accessJwt)
// 
```

### RefreshSession

```kotlin
client.atProtocol()
    .server()
    .refreshSession(
        request =
            RefreshSession.RefreshSessionRequest(
                refreshJwt = refreshJwt
            )
    )
```


### PostFeed

```kotlin
client.atProtocol()
    .repo()
    .createRecord(request = CreateRecord.CreateRecordRequest(
        accessJwt = session.accessJwt,
        repo = session.handle.value,
        collection = NSID("app.bsky.feed.post"),
        record = FeedPostRecord(
            text = "post from milkyway!!!!",
            createdAt = LocalDateTime.now(),
        )
    ))
```

### GetProfile
```kotlin
client.bsky()
    .actor()
    .getProfile(request = GetProfile.GetProfileRequest(
        accessJwt = session.accessJwt,
        actor = session.did
    ))
```

### GetTimeline

```kotlin
val timeline =
    client.bsky()
        .feed()
        .getTimeline(
            GetTimeLine.GetTimelineRequest(
                accessJwt = session.accessJwt,
                cursor = "",
                limit = 10
            )
        )

for (f in timeline.feed) {
    when (f.post.record) {
        is FeedPostRecord -> println((f.post.record as FeedPostRecord).text)
        else -> println(f.post.record.type)
    }
}
}
```
