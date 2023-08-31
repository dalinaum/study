버전을 맞추는 것이 중요합니다.

코틀린 멀티 플랫폼 모듈을 만들 때 아래와 같은 에러가 발생할 수 있음.

```sh
e: /Users/dalinaum/project/study/kmm-integration-sample/shared/build.gradle.kts:6:8: An annotation argument must be a compile-time constant
```

샘플에 있던 코틀린 버전을 1.8.10에서 1.8.21으로 올려서 해결.

```kotlin
kotlin("android").version("1.8.21").apply(false)
```

IOException 등을 RuntimeException으로 변경.