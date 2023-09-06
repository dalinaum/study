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

iOS 프로젝트를 만들고 iosApp으로 디렉토리명을 변경.
다시 XCode로 열고, 프로젝트 명을 두번 클릭해 빌드 프레이즈를 열어야 함.

Build Phrase에 New Run Script를 눌러 다음과 같이 등록

```sh
cd "$SRCROOT/.."
./gradlew :shared:embedAndSignAppleFrameworkForXcode
```

Run Script 를 컴파일 앞으로 이동.

`Build Settings` 탭에서 `All` 클릭.

`Search Path`의 `Framework Search Path`에 

`$(SRCROOT)/../shared/build/xcode-frameworks/$(CONFIGURATION)/$(SDK_NAME)`를 추가.

`Linking`의 `Other Linker Flags`에

`$(inherited) -framework shared`를 추가.

`kotlin("plugin.serialization")`와 코틀린 버전 일치 주의

SQLDelight를 쓰는 경우 코틀린 버전도 맞춰야 함.

코틀린은 Unchecked excpetions이지만 Swift는 모두 Checked exceptions이니 `@Throws(Excpetion::class)`등의 어노테이션이 필요.

````
How to fix “Initializer 'init(_:rowContent:)' requires that ‘SomeType’ conform to 'Identifiable’”
```

아래 코드에서 에러가 발생했음.

```swift
return AnyView(List(launches) { launch in
    RocketLaunchRow(rocketLaunch: launch)
})
```

`id: \.self`를 추가

```swift
return AnyView(List(launches, id: \.self) { launch in
    RocketLaunchRow(rocketLaunch: launch)
})
```

[참고](https://www.hackingwithswift.com/quick-start/swiftui/how-to-fix-initializer-init-rowcontent-requires-that-sometype-conform-to-identifiable)

아니면 확장으로 추가한다.

```swift
extension RocketLaunch: Identifiable {}
```