# Aster for Android

[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)
[![API](https://img.shields.io/badge/API-9%2B-green.svg?style=flat)](https://android-arsenal.com/api?level=9)
[![Readme](https://img.shields.io/badge/README-%E4%B8%AD%E6%96%87-brightgreen.svg)](https://github.com/Dsiner/Aster/blob/master/README-zh.md)

> A network request library based on `HttpURLConnection` + `OkHttp3` + `Volley` + `Retrofit2` + `RxJava2`

## Integration libraries
* HttpURLConnection
* OkHttp3
* Volley
    * HttpURLConnection
    * HttpClient
    * OkHttp3
* Retrofit

## Features
- A chain, full chain call `.func0().func1().func2()...` , `adaptive`, `simple`
- Two client forms (`Singleton` global configuration, `New instance` fully custom configuration)
- Three chain forms, fully extended

## Support
- [x] Support Get, Post, Head, Options, Put, Patch, Delete request protocol
- [x] Support file download, progress callback
- [x] Support file upload, progress callback
- [x] Support for adding fixed headers, dynamic headers
- [x] Support failure retry mechanism, you can specify the number of retries, retry interval
- [x] Support Tag, Cancel Request, Unsubscribe


### How do I use Aster?

See the [wiki](https://github.com/Dsiner/Aster/wiki).

Simple use cases will look something like this:
```java
        Params params = new Params("https://api.douban.com/v2/movie/top250");
        params.addParam("start", "0");
        params.addParam("count", "10");
        Aster.get("https://api.douban.com/v2/movie/top250", params)
                .request(new SimpleCallback<MovieInfo>() {
                    @Override
                    public void onSuccess(MovieInfo response) {
                        ...do something in main thread
                    }

                    @Override
                    public void onError(Throwable e) {
                        ...do something in main thread
                    }
                });
```

More usage see [Demo](app/src/main/java/com/d/aster/MainActivity.java)

## Latest Changes
- [Changelog.md](CHANGELOG.md)

## Licence

```txt
Copyright 2017 D

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
