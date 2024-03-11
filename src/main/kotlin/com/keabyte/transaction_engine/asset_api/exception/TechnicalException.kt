package com.keabyte.transaction_engine.asset_api.exception

class TechnicalException(cause: Throwable, message: String) : RuntimeException(message, cause)