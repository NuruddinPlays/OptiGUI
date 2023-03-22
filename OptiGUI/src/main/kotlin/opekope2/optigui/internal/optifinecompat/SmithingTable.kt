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

private const val CONTAINER = "_smithing_table"
private val textures = setOf(TexturePath.SMITHING_TABLE, TexturePath.LEGACY_SMITHING_TABLE)

fun createSmithingTableFilter(resource: Resource): FilterInfo? {
    if (resource.properties["container"] != CONTAINER) return null
    val replacement = findReplacementTexture(resource) ?: return null

    val filters = createGeneralFilters(resource)

    filters += PreProcessorFilter(
        { it.texture },
        ContainingFilter(textures)
    )

    val filter = ConjunctionFilter(filters)

    return FilterInfo(
        PostProcessorFilter(
            DisjunctionFilter(
                filter,
                PreProcessorFilter.nullGuarded(
                    ::processSmithingTableInteraction,
                    FilterResult.Mismatch(),
                    filter
                )
            ),
            replacement
        ),
        textures
    )
}

private typealias SmithingTableProperties = OptiFineProperties

private fun processSmithingTableInteraction(interaction: Interaction): Interaction? {
    val lookup = getService<RegistryLookupService>()

    val world = interaction.rawInteraction?.world ?: return null
    val pos = BlockPos((interaction.rawInteraction.hitResult as? BlockHitResult)?.blockPos ?: return null)

    return interaction.copy(
        data = SmithingTableProperties(
            name = null,
            biome = lookup.lookupBiome(world, pos),
            height = pos.y
        )
    )
}
