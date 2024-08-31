package com.jetbrains.simplelogin.shared

import java.util.UUID

actual fun randomUUID() = UUID.randomUUID().toString()