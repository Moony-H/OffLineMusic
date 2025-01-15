package com.moony.domain.type

sealed class PlayerError(val errorCodeName: String?, val message: String?) {
    class Network(errorCodeName: String?, message: String?) : PlayerError(errorCodeName, message)
    class Decoding(errorCodeName: String?, message: String?) : PlayerError(errorCodeName, message)
    class Unexpected(errorCodeName: String?, message: String?) : PlayerError(errorCodeName, message)
}
