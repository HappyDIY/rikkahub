package me.rerere.rikkahub.ui.pages.setting.components

import me.rerere.ai.provider.Model
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import kotlin.uuid.Uuid

class CodexProviderConfigureTest {
    @Test
    fun `model refresh preserves IDs for existing model identifiers`() {
        val existingId = Uuid.random()
        val existing = Model(
            modelId = "gpt-5-codex",
            displayName = "Old name",
            id = existingId,
        )
        val refreshed = Model(
            modelId = "gpt-5-codex",
            displayName = "Updated name",
        )
        val added = Model(modelId = "gpt-5.1-codex")

        val merged = mergeCodexModels(
            existing = listOf(existing),
            refreshed = listOf(refreshed, added),
        )

        assertEquals(existingId, merged[0].id)
        assertEquals("Updated name", merged[0].displayName)
        assertNotEquals(existingId, merged[1].id)
    }
}
