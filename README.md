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

### 💩 Add bsky serialization strategy(experimental) 💩
if you use `milkyway-bsky`, one of action which shown below is needed.

#### 1. notify dependencies(explicitly)
telling the bsky serializersModules to `bsky-core`'s serializer.

```kotlin
Milkyway.installBskyDependencies()
```

#### 2. notify dependencies(implicitly)
when call bsky extension, same as above code will run inside constructor.

```kotlin
Milkyway.bsky()
```

#### 3. add hint when call create / list / putRecord
when you call getRecord which record of bluesky before telling the dependency to Milkyway, it fails because milkyway dont know subclass of `Record` which implemented in `milkyway-bsky`.  
so you have to tell the hint.  
in below case, `FeedPostRecord` registered as `Record` subclass.  
as a result, when API returns it, you can get it.(but if not, you cannot get. only affect as a hint)

```kotlin
client.atProtocol()
    .repo()
    .getRecord<FeedPostRecord>(
        request = GetRecord.GetRecordRequest(
            collection = uri.collection!!,
            repo = uri.did?.value!!,
            rkey = uri.rkey!!
        )
)
```

after it, you dont need to call with type. because `FeedPostRecord` was registered.
```kotlin
client.atProtocol()
    .repo()
    .getRecord(
        request = GetRecord.GetRecordRequest(
            collection = uri.collection!!,
            repo = uri.did?.value!!,
            rkey = uri.rkey!!
        )
)
```





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
