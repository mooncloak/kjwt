package com.mooncloak.kodetools.kjwt.core.key

public external interface Json : JsAny {

    public operator fun get(propertyName: String): JsAny?

    public operator fun set(propertyName: String, value: JsAny?)
}

public external object JSON : JsAny {

    public fun stringify(o: JsAny?): String
}
