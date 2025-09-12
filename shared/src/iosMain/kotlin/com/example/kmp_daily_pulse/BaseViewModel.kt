package com.example.kmp_daily_pulse

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.cancel

actual open class BaseViewModel {

   actual val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)

   fun clear() {
      scope.cancel()
   }
}