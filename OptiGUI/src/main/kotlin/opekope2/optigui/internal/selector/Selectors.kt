package opekope2.optigui.internal.selector

import opekope2.optigui.registry.LoadSelectorRegistry
import opekope2.optigui.registry.SelectorRegistry

internal fun registerSelectors() {
    SelectorRegistry.register("beacon.levels", BeaconLevelSelector())
    // TODO primary+secondary status effect selector

    SelectorRegistry.register("biomes", BiomeSelector())

    SelectorRegistry.register("book.page.count", BookPageCountSelector())
    SelectorRegistry.register("book.page.current", BookPageSelector())

    SelectorRegistry.register("chest.large", LargeChestSelector())

    SelectorRegistry.register("comparator.output", RedstoneComparatorOutputSelector())

    SelectorRegistry.register("date", DateSelector())

    SelectorRegistry.register("heights", HeightSelector())

    SelectorRegistry.register("donkey.has_chest", DonkeyChestSelector())
    SelectorRegistry.register("horse.has_saddle", HorseSaddleSelector())
    SelectorRegistry.register("horse.markings", HorseMarkingSelector())
    SelectorRegistry.register("horse.variants", HorseVariantSelector())
    SelectorRegistry.register("llama.colors", LlamaCarpetColorSelector())
    SelectorRegistry.register("llama.variants", LlamaVariantSelector())

    SelectorRegistry.register("interaction.hand", InteractionHandSelector())
    SelectorRegistry.register("interaction.texture", InteractionTextureSelector())

    SelectorRegistry.register("name", LiteralNameSelector())
    SelectorRegistry.register("name.regex", RegexNameSelector(false))
    SelectorRegistry.register("name.regex.ignore_case", RegexNameSelector(true))
    SelectorRegistry.register("name.wildcard", WildcardNameSelector(false))
    SelectorRegistry.register("name.wildcard.ignore_case", WildcardNameSelector(true))

    SelectorRegistry.register("villager.professions", VillagerProfessionSelector())
    SelectorRegistry.register("villager.type", VillagerTypeSelector())

    SelectorRegistry.register("player.biomes", PlayerBiomeSelector())
    SelectorRegistry.register("player.heights", PlayerHeightSelector())
    SelectorRegistry.register("player.name", LiteralPlayerNameSelector())
    SelectorRegistry.register("player.name.regex", RegexPlayerNameSelector(false))
    SelectorRegistry.register("player.name.regex.ignore_case", RegexPlayerNameSelector(true))
    SelectorRegistry.register("player.name.wildcard", WildcardPlayerNameSelector(false))
    SelectorRegistry.register("player.name.wildcard.ignore_case", WildcardPlayerNameSelector(true))
}

internal fun registerLoadSelectors() {
    LoadSelectorRegistry.register("if", ConditionalLoadSelector())
    LoadSelectorRegistry.register("if.mods", ModsLoadSelector())
}
