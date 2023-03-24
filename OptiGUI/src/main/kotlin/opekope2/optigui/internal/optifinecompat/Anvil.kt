package opekope2.optigui.internal.optifinecompat

import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import opekope2.filter.*
import opekope2.optifinecompat.properties.OptiFineProperties
import opekope2.optigui.interaction.Interaction
import opekope2.optigui.resource.Resource
import opekope2.optigui.service.RegistryLookupService
import opekope2.optigui.service.getService
import opekope2.util.TexturePath

private const val CONTAINER = "anvil"
private val texture = TexturePath.ANVIL

fun createAnvilFilter(resource: Resource): FilterInfo? {
    if (resource.properties["container"] != CONTAINER) return null
    val replacement = findReplacementTexture(resource) ?: return null

    val filter = FilterBuilder.build(resource) {
        setReplaceableTextures(texture)
        addGeneralFilters<AnvilProperties>()
    }

    return FilterInfo(
        PostProcessorFilter(
            DisjunctionFilter(
                filter,
                PreProcessorFilter.nullGuarded(
                    ::processAnvilInteraction,
                    FilterResult.Mismatch(),
                    filter
                )
            ),
            replacement
        ),
        setOf(texture)
    )
}

private typealias AnvilProperties = OptiFineProperties

private fun processAnvilInteraction(interaction: Interaction): Interaction? {
    val lookup = getService<RegistryLookupService>()

    val world = interaction.rawInteraction?.world ?: return null
    val pos = BlockPos((interaction.rawInteraction.hitResult as? BlockHitResult)?.blockPos ?: return null)

    return interaction.copy(
        data = AnvilProperties(
            name = null,
            biome = lookup.lookupBiome(world, pos),
            height = pos.y
        )
    )
}
