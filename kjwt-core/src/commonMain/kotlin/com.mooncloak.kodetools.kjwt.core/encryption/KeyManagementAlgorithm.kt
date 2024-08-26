package com.mooncloak.kodetools.kjwt.core.encryption

import com.mooncloak.kodetools.kjwt.core.Header
import com.mooncloak.kodetools.kjwt.core.Jwe
import com.mooncloak.kodetools.kjwt.core.signature.SignatureAlgorithm
import com.mooncloak.kodetools.kjwt.core.util.ExperimentalJwtApi
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

/**
 * Represents the [Header.algorithm] value for key management used by [Jwe], defined by the
 * [JWA Specification](https://datatracker.ietf.org/doc/html/rfc7518#section-4). This represents the algorithm that was
 * used to encrypt the CEK (content encryption key), producing the JWE Encrypted Key, or to use key agreement to agree
 * upon the CEK.
 *
 * @see [JWA Specification](https://datatracker.ietf.org/doc/html/rfc7518#section-4)
 * @see [ContentEncryptionAlgorithm] for a similar concept but for content encryption.
 * @see [SignatureAlgorithm] for a similar concept but for signing keys.
 */
@JvmInline
@Serializable
@ExperimentalJwtApi
public value class KeyManagementAlgorithm public constructor(
    public val value: String
) {

    public companion object {

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm | More Header Params | Implementation Requirements |
         * | ----------------- | ------------------------ | ------------------ | --------------------------- |
         * | RSA1_5            | RSAES-PKCS1-v1_5         | (none)             | Recommended -               |
         * ```
         */
        public val RSA1_5: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "RSA1_5")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm            | More Header Params | Implementation Requirements |
         * | ----------------- | ----------------------------------- | ------------------ | --------------------------- |
         * | RSA-OAEP          | RSAES OAEP using default parameters | (none)             | Recommended +               |
         * ```
         */
        public val RSA_OAEP: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "RSA-OAEP")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                       | More Header Params | Implementation Requirements |
         * | ----------------- | ---------------------------------------------- | ------------------ | --------------------------- |
         * | RSA-OAEP-256      | RSAES OAEP using SHA-256 and MGF1 with SHA-256 | (none)             | Optional                    |
         * ```
         */
        public val RSA_OAEP_256: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "RSA-OAEP-256")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                                  | More Header Params | Implementation Requirements |
         * | ----------------- | --------------------------------------------------------- | ------------------ | --------------------------- |
         * | A128KW            | AES Key Wrap with default initial value using 128-bit key | (none)             | Recommended                 |
         * ```
         */
        public val A128KW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "A128KW")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                                  | More Header Params | Implementation Requirements |
         * | ----------------- | --------------------------------------------------------- | ------------------ | --------------------------- |
         * | A192KW            | AES Key Wrap with default initial value using 192-bit key | (none)             | Optional                    |
         * ```
         */
        public val A192KW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "A192KW")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                                  | More Header Params | Implementation Requirements |
         * | ----------------- | --------------------------------------------------------- | ------------------ | --------------------------- |
         * | A256KW            | AES Key Wrap with default initial value using 256-bit key | (none)             | Recommended                 |
         * ```
         */
        public val A256KW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "A256KW")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                                  | More Header Params | Implementation Requirements |
         * | ----------------- | --------------------------------------------------------- | ------------------ | --------------------------- |
         * | dir               | Direct use of a shared symmetric key as the CEK           | (none)             | Recommended                 |
         * ```
         */
        public val Direct: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "dir")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                                     | More Header Params  | Implementation Requirements |
         * | ----------------- | ------------------------------------------------------------ | ------------------- | --------------------------- |
         * | ECDH-ES           | Elliptic Curve Diffie-Hellman Ephemeral Static key agreement | "epk", "apu", "apv" | Recommended +               |
         * ```
         */
        public val ECDH_ES: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "ECDH-ES")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                                     | More Header Params  | Implementation Requirements |
         * | ----------------- | ------------------------------------------------------------ | ------------------- | --------------------------- |
         * | ECDH-ES+A128KW    | ECDH-ES using Concat KDF and CEK wrapped with "A128KW"       | "epk", "apu", "apv" | Recommended                 |
         * ```
         */
        public val ECDH_ES_A128KW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "ECDH-ES+A128KW")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                                     | More Header Params  | Implementation Requirements |
         * | ----------------- | ------------------------------------------------------------ | ------------------- | --------------------------- |
         * | ECDH-ES+A192KW    | ECDH-ES using Concat KDF and CEK wrapped with "A192KW"       | "epk", "apu", "apv" | Optional                    |
         * ```
         */
        public val ECDH_ES_A192KW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "ECDH-ES+A192KW")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                                     | More Header Params  | Implementation Requirements |
         * | ----------------- | ------------------------------------------------------------ | ------------------- | --------------------------- |
         * | ECDH-ES+A256KW    | ECDH-ES using Concat KDF and CEK wrapped with "A256KW"       | "epk", "apu", "apv" | Recommended                 |
         * ```
         */
        public val ECDH_ES_A256KW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "ECDH-ES+A256KW")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                    | More Header Params  | Implementation Requirements |
         * | ----------------- | ------------------------------------------- | ------------------- | --------------------------- |
         * | A128GCMKW         | Key wrapping with AES GCM using 128-bit key | "iv", "tag"         | Optional                    |
         * ```
         */
        public val A128GCMKW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "A128GCMKW")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                    | More Header Params  | Implementation Requirements |
         * | ----------------- | ------------------------------------------- | ------------------- | --------------------------- |
         * | A192GCMKW         | Key wrapping with AES GCM using 192-bit key | "iv", "tag"         | Optional                    |
         * ```
         */
        public val A192GCMKW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "A192GCMKW")

        /**
         * ```
         * | "alg" Param Value | Key Management Algorithm                    | More Header Params  | Implementation Requirements |
         * | ----------------- | ------------------------------------------- | ------------------- | --------------------------- |
         * | A256GCMKW         | Key wrapping with AES GCM using 256-bit key | "iv", "tag"         | Optional                    |
         * ```
         */
        public val A256GCMKW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "A256GCMKW")

        /**
         * ```
         * | "alg" Param Value  | Key Management Algorithm                      | More Header Params  | Implementation Requirements |
         * | ------------------ | --------------------------------------------- | ------------------- | --------------------------- |
         * | PBES2-HS256+A128KW | PBES2 with HMAC SHA-256 and "A128KW" wrapping | "p2s", "p2c"        | Optional                    |
         * ```
         */
        public val PBES2_HS256_A128KW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "PBES2-HS256+A128KW")

        /**
         * ```
         * | "alg" Param Value  | Key Management Algorithm                      | More Header Params  | Implementation Requirements |
         * | ------------------ | --------------------------------------------- | ------------------- | --------------------------- |
         * | PBES2-HS384+A192KW | PBES2 with HMAC SHA-384 and "A192KW" wrapping | "p2s", "p2c"        | Optional                    |
         * ```
         */
        public val PBES2_HS384_A192KW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "PBES2-HS384+A192KW")

        /**
         * ```
         * | "alg" Param Value  | Key Management Algorithm                      | More Header Params  | Implementation Requirements |
         * | ------------------ | --------------------------------------------- | ------------------- | --------------------------- |
         * | PBES2-HS512+A256KW | PBES2 with HMAC SHA-512 and "A256KW" wrapping | "p2s", "p2c"        | Optional                    |
         * ```
         */
        public val PBES2_HS512_A256KW: KeyManagementAlgorithm = KeyManagementAlgorithm(value = "PBES2-HS512+A256KW")
    }
}
