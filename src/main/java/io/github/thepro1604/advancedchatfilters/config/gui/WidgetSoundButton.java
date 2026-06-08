/*
 * Copyright (C) 2021 thepro1604
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */
package io.github.thepro1604.advancedchatfilters.config.gui;

import fi.dy.masa.malilib.gui.GuiTextFieldGeneric;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;

public class WidgetSoundButton extends GuiTextFieldGeneric {

    public WidgetSoundButton(int x, int y, int width, int height, Font font) {
        super(x, y, width, height, font);
        Minecraft.getInstance().getSoundManager();
    }
}
