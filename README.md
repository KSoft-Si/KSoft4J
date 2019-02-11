# KSoft4J
Java wrapper for the KSoft API

## Creating the KSoftAPI Object

You can create the KSoftAPI object by creating a new instance of `KSoftAPI` and passing the token.
An example of this is shown below.

**Example**:

```java
KSoftAPI api = new KSoftAPI("token");
```

#### Examples:

**Getting a random meme**:

```java
public class Main {
    public static void main(String[] args) {
        KSoftAPI api = new KSoftAPI("token");
        Reddit reddit = api.getRedditImage(ImageType.RANDOM_MEME).execute();
        System.out.printf("Title: %s\nURL: %s\nSubreddit: %s\nAuthor: %s", reddit.getTitle(), reddit.getSourceUrl(), reddit.getSubreddit(), reddit.getAuthor());
    }
}
```
**Getting a random meme from a custom subreddit**:

```java
public class Main {
    public static void main(String[] args) {
        KSoftAPI api = new KSoftAPI("token");
        Reddit reddit = api.getRedditImage(ImageType.RANDOM_REDDIT).setSubreddit("thathappened").execute();
        System.out.printf("Title: %s\nURL: %s\nSubreddit: %s\nAuthor: %s", reddit.getTitle(), reddit.getSourceUrl(), reddit.getSubreddit(), reddit.getAuthor());
    }
}
```
#### Troubleshooting this part
* Errors
    You may encounter a `MissingArgumentException` if the subreddit doesn't exist or it's not defined when using `ImageType.RANDOM_REDDIT`.<br>
    You can fix this by ensuring that the subreddit you're grabbing stuff from exists or by just defining the subreddit.
* Warnings
    You may encounter a `You're setting a subreddit, but ImageType is not RANDOM_REDDIT` warning when working with images.<br>
    You can fix this by removing `.setSubreddit()` when you aren't using an `ImageType.RANDOM_REDDIT`.
    
**Getting a ban**:

```java
public class Main {
    public static void main(String[] args) {
        KSoftAPI api = new KSoftAPI("token");
        Ban ban = api.getBan().setUserId("1234").execute();
        System.out.printf("Ban: %s\nModerator: %s\nReason: %s\nProof: %s", ban.getEffectiveName(), ban.getModId(), ban.getReason(), ban.getProof());
    }
}
```
**Reporting a user**:

```java
public class Main {
    public static void main(String[] args) {
        KSoftAPI api = new KSoftAPI("token");
        BanAction ban = api.getBan();
        AddBan addBan = ban.addBan();
        addBan
        .setUsername("username")
        .setDiscriminator("1234")
        .setUserId("4321").setReason("Being mean!")
        .setProof("google.com")
        .set().execute();
    }
}
```
#### Troubleshooting this part
You may encounter a `MissingArgumentException: Missing action value. Could not be parsed` error when working with bans.<br>
These might occur when trying to do something like this with AddBan
```java
public class Main {
    public static void main(String[] args) {
        KSoftAPI api = new KSoftAPI("token");
        BanAction ban = api.getBan();
        AddBan addBan = ban.addBan();
        addBan
        .setUsername("username")
        .setDiscriminator("1234")
        .setUserId("4321").setReason("Being mean!")
        .setProof("google.com")
        .set();
        ban.execute();
    }
}
```
Notice how `.set();` and `ban.execute();` are separate? Being able to make this function work means the `.set()` creates a new instance of `Ban` that allows for reporting a ban. Since the code above uses an already instantiated version of `Ban`, KSoft4J still thinks you're trying to grab stuff, not report.

## Download
Latest Stable Version: [Bintray Release](https://bintray.com/mattmalec/KSoft4J/KSoft4J/1.0.6/link) <br>
Latest Version: [ ![Download](https://api.bintray.com/packages/mattmalec/KSoft4J/KSoft4J/images/download.svg?version=1.0.6) ](https://bintray.com/mattmalec/KSoft4J/KSoft4J/1.0.6/link)

Be sure to replace the **VERSION** key below with the one of the versions shown above!

**Maven**
```xml
<dependency>
    <groupId>net.explodingbush.KSoft4J</groupId>
    <artifactId>KSoft4J</artifactId>
    <version>VERSION</version>
</dependency>
```
```xml
<repository>
    <id>jcenter</id>
    <name>jcenter-bintray</name>
    <url>http://jcenter.bintray.com</url>
</repository>

```

**Gradle**
```gradle
dependencies {
    compile 'net.explodingbush.KSoft4J:KSoft4J:VERSION'
}

repositories {
    jcenter()
}
```

The builds are distributed using JCenter through Bintray [KSoft4J JCenter Bintray](https://bintray.com/mattmalec/KSoft4J/KSoft4J)

## Getting Help

For general troubleshooting, you can view some troubleshooting steps in the examples (this will be soon moved to the wiki)
<br>If you need help, or just want to talk with the KSoft.Si or other devs, you can join the [KSoft.Si Discord server](https://discord.gg/EMjDawF)

## Documentation
The docs will have everything you need to know in order to use the wrapper.

[KSoft4J Docs](https://api.explodingbush.net/ksoft4j) can be found here.
It should be reletively easy to understand. If you have any questions regarding the documentation, please don't hesitate to contact me on Discord.

## Contributing to KSoft4J
If you want to contribute to KSoft4J, make sure to base your branch off of our **development** branch (when we make one)
and create your PR into that **same** branch.

It is recommended to get in touch with the devs before opening Pull Requests (either through an issue or on Discord).<br>

## Dependencies
This project requires **Java 8**.<br>
All dependencies are managed automatically by Gradle.

 * slf4j-api
   * Version: **1.6.1**
   * [Website](https://www.slf4j.org/)
   * [JCenter Repository](https://bintray.com/bintray/jcenter/org.slf4j%3Aslf4j-api/view)
 * OkHttp
   * Version: **3.11.0**
   * [Github](https://github.com/square/okhttp)
   * [JCenter Repository](https://bintray.com/bintray/jcenter/com.squareup.okhttp3:okhttp)
 * org.json
   * Version: **20160810**
   * [Github](https://github.com/douglascrockford/JSON-java)
   * [JCenter Repository](https://bintray.com/bintray/jcenter/org.json%3Ajson/view)
