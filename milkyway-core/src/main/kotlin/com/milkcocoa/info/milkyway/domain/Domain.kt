package com.milkcocoa.info.milkyway.domain

data class Domain(
    val domain: String
) {
    val asHttps by lazy { "https://$domain" }
    val asWss by lazy { "wss://$domain" }
}