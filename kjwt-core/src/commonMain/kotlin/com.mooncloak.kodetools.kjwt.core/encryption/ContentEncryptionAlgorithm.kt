package com.mooncloak.kodetools.kjwt.core.encryption

import com.mooncloak.kodetools.kjwt.core.Header
import com.mooncloak.kodetools.kjwt.core.Jwe
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Represents the [Header.encryption] value for key management used by [Jwe], defined by the
 * [JWA Specification](https://datatracker.ietf.org/doc/html/rfc7518#section-4).
 *
 * JWE uses cryptographic algorithms to encrypt and integrity-protect the plaintext and to integrity-protect the
 * Additional Authenticated Data. This class represents the algorithm used to encrypt that text and data.
 *
 * @see [JWA Specification](https://datatracker.ietf.org/doc/html/rfc7518#section-5)
 * @see [KeyManagementAlgorithm] for a similar concept but for key encryption and agreement.
 * @see [SignatureAlgorithm] for a similar concept but for signing keys.
 */
@JvmInline
@Serializable
@ExperimentalJwtApi
public value class ContentEncryptionAlgorithm public constructor(
    public val value: String
) {

    public companion object {

        /**
         * ```
         * | "enc" Param Value | Content Encryption Algorithm                                                             | Implementation Requirements |
         * | ----------------- | ---------------------------------------------------------------------------------------- | --------------------------- |
         * | A128CBC-HS256     | AES_128_CBC_HMAC_SHA_256 authenticated encryption algorithm, as defined in Section 5.2.3 | Required                    |
         * ```
         */
        public val A128CBC_HS256: ContentEncryptionAlgorithm = ContentEncryptionAlgorithm(value = "A128CBC-HS256")

        /**
         * ```
         * | "enc" Param Value | Content Encryption Algorithm                                                             | Implementation Requirements |
         * | ----------------- | ---------------------------------------------------------------------------------------- | --------------------------- |
         * | A192CBC-HS384     | AES_192_CBC_HMAC_SHA_384 authenticated encryption algorithm, as defined in Section 5.2.4 | Optional                    |
         * ```
         */
        public val A192CBC_HS384: ContentEncryptionAlgorithm = ContentEncryptionAlgorithm(value = "A192CBC-HS384")

        /**
         * ```
         * | "enc" Param Value | Content Encryption Algorithm                                                             | Implementation Requirements |
         * | ----------------- | ---------------------------------------------------------------------------------------- | --------------------------- |
         * | A256CBC-HS512     | AES_256_CBC_HMAC_SHA_512 authenticated encryption algorithm, as defined in Section 5.2.5 | Required                    |
         * ```
         */
        public val A256CBC_HS512: ContentEncryptionAlgorithm = ContentEncryptionAlgorithm(value = "A256CBC-HS512")

        /**
         * ```
         * | "enc" Param Value | Content Encryption Algorithm | Implementation Requirements |
         * | ----------------- | ---------------------------- | --------------------------- |
         * | A128GCM           | AES GCM using 128-bit key    | Recommended                 |
         * ```
         */
        public val A128GCM: ContentEncryptionAlgorithm = ContentEncryptionAlgorithm(value = "A128GCM")

        /**
         * ```
         * | "enc" Param Value | Content Encryption Algorithm | Implementation Requirements |
         * | ----------------- | ---------------------------- | --------------------------- |
         * | A192GCM           | AES GCM using 192-bit key    | Optional                    |
         * ```
         */
        public val A192GCM: ContentEncryptionAlgorithm = ContentEncryptionAlgorithm(value = "A192GCM")

        /**
         * ```
         * | "enc" Param Value | Content Encryption Algorithm | Implementation Requirements |
         * | ----------------- | ---------------------------- | --------------------------- |
         * | A256GCM           | AES GCM using 256-bit key    | Recommended                 |
         * ```
         */
        public val A256GCM: ContentEncryptionAlgorithm = ContentEncryptionAlgorithm(value = "A256GCM")
    }
}
