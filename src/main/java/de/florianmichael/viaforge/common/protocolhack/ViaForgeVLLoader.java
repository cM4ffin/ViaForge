/*
 * This file is part of ViaForge - https://github.com/FlorianMichael/ViaForge
 * Copyright (C) 2021-2023 FlorianMichael/EnZaXD and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.florianmichael.viaforge.common.protocolhack;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.platform.providers.ViaProviders;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import com.viaversion.viaversion.protocols.protocol1_9to1_8.providers.MovementTransmitterProvider;
import de.florianmichael.viaforge.common.protocolhack.provider.ViaForgeVersionProvider;
import net.raphimc.vialoader.impl.viaversion.VLLoader;

public class ViaForgeVLLoader extends VLLoader {

    @Override
    public void load() {
        super.load();

        final ViaProviders providers = Via.getManager().getProviders();

        providers.use(VersionProvider.class, new ViaForgeVersionProvider());
        providers.use(MovementTransmitterProvider.class, new MovementTransmitterProvider() {

            @Override
            public void sendPlayer(UserConnection userConnection) {
                // We are on the client side, so we can handle the idle packet properly
            }
        });
    }
}
