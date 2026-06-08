/*
 * Copyright (C) 2021 thepro1604
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package io.github.thepro1604.advancedchatfilters.filters;

import io.github.thepro1604.advancedchatcore.interfaces.IMatchProcessor;
import io.github.thepro1604.advancedchatcore.util.SearchResult;
import io.github.thepro1604.advancedchatfilters.FiltersHandler;
import io.github.thepro1604.advancedchatfilters.interfaces.IFilter;
import io.github.thepro1604.advancedchatfilters.registry.MatchProcessorRegistry;
import java.util.Optional;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.Component;

@Environment(EnvType.CLIENT)
public class ForwardFilter implements IFilter {

    private final MatchProcessorRegistry registry;

    public ForwardFilter(MatchProcessorRegistry registry) {
        this.registry = registry;
    }

    @Override
    public Optional<Component> filter(ParentFilter filter, Component text, Component unfiltered, SearchResult search) {
        IMatchProcessor.Result result = null;
        for (MatchProcessorRegistry.MatchProcessorOption p : registry.getAll()) {
            if (!p.isActive()) {
                continue;
            }
            IMatchProcessor.Result r = null;
            if (!p.getOption().matchesOnly() && !search.getMatches().isEmpty()) {
                r = p.getOption().processMatches(text, unfiltered, null);
            } else if (!search.getMatches().isEmpty()) {
                r = p.getOption().processMatches(text, unfiltered, search);
            }
            if (r != null) {
                if (result == null || r.force) {
                    result = r;
                }
            }
        }
        if (result != null && !result.forward) {
            return Optional.of(FiltersHandler.TERMINATE);
        }
        return Optional.empty();
    }
}
