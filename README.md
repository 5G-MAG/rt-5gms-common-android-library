<p align="center">
  <img src=".github/banner.svg" width="100%" alt="Reference Tools · 5G Media Streaming (5GMS): 5GMS Common Android Library">
</p>

<p align="center">
  An Android library of models and helper classes shared by the 5G-MAG client-side 5GMS Android
  applications.
</p>

<p align="center">
  <img alt="Status: under development"
    src="https://img.shields.io/badge/Status-Under%20Development-e67e22">
  <a href="https://github.com/5G-MAG/rt-5gms-common-android-library/releases"><img alt="Version"
    src="https://img.shields.io/github/v/release/5G-MAG/rt-5gms-common-android-library?label=Version"></a>
  <a href="License.md"><img alt="License: 5G-MAG Public License v1.0"
    src="https://img.shields.io/badge/License-5G--MAG%20PL%20v1.0-blue"></a>
</p>

<p align="center">
  <a href="https://www.5g-mag.com/reference-tools/5gms/">Project page</a> &nbsp;&middot;&nbsp;
  <a href="https://github.com/5G-MAG/rt-5gms-common-android-library/issues">Issues</a> &nbsp;&middot;&nbsp;
  <a href="https://www.5g-mag.com/contributing">Contributing</a>
</p>

---

## At a glance

|  |  |
|---|---|
| **Part of** | [5G Media Streaming (5GMS)](https://www.5g-mag.com/reference-tools/5gms/), alongside [cmcd-toolkit](https://github.com/5G-MAG/cmcd-toolkit), [rt-5gc-service-consumers](https://github.com/5G-MAG/rt-5gc-service-consumers), [rt-5gms-application](https://github.com/5G-MAG/rt-5gms-application), [rt-5gms-application-function](https://github.com/5G-MAG/rt-5gms-application-function), [rt-5gms-application-provider](https://github.com/5G-MAG/rt-5gms-application-provider), [rt-5gms-application-server](https://github.com/5G-MAG/rt-5gms-application-server), [rt-5gms-examples](https://github.com/5G-MAG/rt-5gms-examples), [rt-5gms-media-session-handler](https://github.com/5G-MAG/rt-5gms-media-session-handler), [rt-5gms-media-stream-handler](https://github.com/5G-MAG/rt-5gms-media-stream-handler), [rt-cmmf-encoder](https://github.com/5G-MAG/rt-cmmf-encoder), [rt-media-origin](https://github.com/5G-MAG/rt-media-origin) |

## Introduction

This repository is the 5GMS Common Library, an Android library with the models and helper classes
used by the client-side Android applications, such as the
[5GMSd-Aware Application](https://github.com/5G-MAG/rt-5gms-application), the
[5GMSd Media Stream Handler](https://github.com/5G-MAG/rt-5gms-media-stream-handler) and the
[5GMSd Media Session Handler](https://github.com/5G-MAG/rt-5gms-media-session-handler). Each of
them declares it as a Maven dependency.

More information is on the [project page](https://www.5g-mag.com/reference-tools/5gms/).

## Downloading

Release versions are on the [releases](https://github.com/5G-MAG/rt-5gms-common-android-library/releases)
page. The library is also published as a Maven package on the
[5G-MAG GitHub Packages](https://github.com/orgs/5G-MAG/packages?repo_name=rt-5gms-common-android-library).

To get the source, clone the repository:

```
cd ~
git clone https://github.com/5G-MAG/rt-5gms-common-android-library
```

## Building

To generate the `aar` bundles, run this command from the repository root:

````
./gradlew assemble
````

The `aar` bundles are written to `app/build/outputs/aar/`. A project can include one by specifying
the path to the bundle.

## Installing

The preferred way to include the 5GMS Common Library is from a local or remote Maven repository.

### Publish to local Maven repository

To include the library from a local Maven repository, first publish it locally:

````
./gradlew publishToMavenLocal
````

### Include from local Maven repository

To include the 5GMS Common Library from a local Maven repository, make the two changes below. The
other 5GMS client-side projects already include them; with those, the Common Library only needs to
be [published to the local Maven repository](#publish-to-local-maven-repository).

#### 1. Add `mavenLocal()` to your project gradle file

````
dependencyResolutionManagement {
   repositories {
   mavenLocal()
   }
}
````

#### 2. Include the 5GMS Common Library in your module gradle file

The example uses `1.3.0`, the version this repository publishes (`app/build.gradle`). Replace it
with the version you are using.

````
dependencies {
    // 5GMAG
    implementation 'com.fivegmag:a5gmscommonlibrary:1.3.0'
}
````

## Development

This project follows the
[Gitflow workflow](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow).
The `development` branch is the integration branch for new features, so switch to it before starting
work on a new feature.

## Contributing

Contributions are welcome. How to raise an issue, fork the repository and open a pull request, and
the Contributor License Agreement required before code can be merged, are described at
<https://www.5g-mag.com/contributing>.

## License

Distributed under the 5G-MAG Public License v1.0. See [License.md](License.md).
