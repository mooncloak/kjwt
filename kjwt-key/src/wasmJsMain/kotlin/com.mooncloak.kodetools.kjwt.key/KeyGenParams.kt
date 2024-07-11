package com.mooncloak.kodetools.kjwt.key

import org.khronos.webgl.Uint8Array

public sealed external interface KeyGenParams : JsAny {

    public val name: String?
}

// https://developer.mozilla.org/en-US/docs/Web/API/HmacKeyGenParams
public external interface HmacKeyGenParams : KeyGenParams {

    public override val name: String?

    public val hash: String?

    public val length: Int?
}

// https://developer.mozilla.org/en-US/docs/Web/API/AesKeyGenParams
public external interface AesKeyGenParams : KeyGenParams {

    public override val name: String?

    public val length: Int?
}

// https://developer.mozilla.org/en-US/docs/Web/API/RsaHashedKeyGenParams
public external interface RsaHashedKeyGenParams : KeyGenParams {

    public override val name: String?

    public val modulusLength: Int?

    public val publicExponent: Uint8Array?

    public val hash: String?
}

// https://developer.mozilla.org/en-US/docs/Web/API/EcKeyGenParams
public external interface EcKeyGenParams : KeyGenParams {

    public override val name: String?

    public val namedCurve: String?
}
