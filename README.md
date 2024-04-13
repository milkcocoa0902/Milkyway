## Milkyway

This library is a ATProtocol and Bluesly API client on kotlin.  

core  
bsky

## Features

⭐ com.atproto.server.createSession  
⭐ com.atproto.repo.createRecord  
⭐ app.bsky.actor.getPreferences  
⭐ app.bsky.actor.getProfile  
⭐ app.bsky.actor.getProfiles  
⭐ app.bsky.actor.getSuggestions   
⭐ app.bsky.feed.getTimeline

## Installation  
TBD

## Usage

### CreateSession

```kotlin
val session = Milkyway.instance(domain = Domain("https://bsky.social"))
    .atProtocol()
    .server()
    .createSession(
        CreateSession.CreateSessionRequest(
            identifier = System.getenv("BSKY_IDENTIFIER"),
            password = System.getenv("BSKY_PASSWORD")
        )
    )
```

### PostFeed

```kotlin
Milkyway.instance(domain = Domain("https://bsky.social"))
    .atProtocol()
    .repo()
    .createRecord(
        CreateRecord.CreateRecordRequest(
            accessJwt = session.accessJwt,
            repo = session.handle,
            collection = "app.bsky.feed.post",
            record = CreateRecord.CreateRecordRequest.Record(
                text = "hello from Milkeyway",
                createdAt = LocalDateTime.now(ZoneOffset.UTC)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))
            )
        )
    )
```

### GetProfile

```kotlin
Milkyway.instance(domain = Domain("https://bsky.social"))
    .bsky()
    .actor()
    .getProfile(request = GetProfile.GetProfileRequest(
        accessJwt = session.accessJwt,
        actor = session.didDoc.id
    ))
```

### GetTimeline

```kotlin
Milkyway.instance(domain = Domain("https://bsky.social"))
    .bsky()
    .feed()
    .getTimeline(
        GetTimeLine.GetTimelineRequest(
            accessJwt = session.accessJwt,
            cursor = "",
            limit = 10
        )
    )
```
