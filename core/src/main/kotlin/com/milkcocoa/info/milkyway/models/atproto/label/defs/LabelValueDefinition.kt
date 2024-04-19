package com.milkcocoa.info.milkyway.models.atproto.label.defs

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import javax.print.attribute.standard.Severity

@Serializable
data class LabelValueDefinition(
    /**
     * The value of the label being defined. Must only include lowercase ascii and the '-' character ([a-z-]+).
     */
    val identifier: String,
    /**
     * How should a client visually convey this label? 'inform' means neutral and informational; 'alert' means negative and warning; 'none' means show nothing.
     */
    val severity: Severity,
    /**
     * What should this label hide in the UI, if applied? 'content' hides all of the target; 'media' hides the images/video/audio; 'none' hides nothing.
     */
    val blurs: Blurs,
    /**
     * The default setting for this label.
     */
    val defaultSetting: DefaultSetting = DefaultSetting.Warn,
    /**
     * Does the user need to have adult content enabled in order to configure this label?
     */
    val adultOnly: Boolean? = null,
    val locales: List<LabelValueDefinitionStrings>? = null
) {
    @Serializable
    enum class Severity {
        @SerialName("inform")
        Inform,

        @SerialName("alert")
        Alert,

        @SerialName("none")
        None
    }

    @Serializable
    enum class Blurs {
        @SerialName("content")
        Content,

        @SerialName("media")
        Media,

        @SerialName("none")
        None
    }

    @Serializable
    enum class DefaultSetting {
        @SerialName("ignore")
        Ignore,

        @SerialName("warn")
        Warn,

        @SerialName("hide")
        Hide
    }
}