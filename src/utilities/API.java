package utilities;

import SQLAPI.CreateSQLTables;
import SQLAPI.SQL;
import events.BuildEvents;
import events.BuildMode;
import events.InventoryManagement;
import me.ES96.com.WorldMangement;

/**
 * Created by Evan on 10/23/2017.
 */
public interface API
{
    WorldMangement getWorldManagement();

    BuildUtils getBuildUtilities();

    BuildMode getBuildMode();

    BuildConfig getBuildConfiguration();

    BuildPermissions getBuildPermissions(); //TODO this system may be heavily redone depending on how we want to handle ranks and permissions.

    InventoryManagement getInventoryManagement();

    BuildEvents getBuildEvents();

    SQL getBuildSQLSystem();

    CreateSQLTables getSQLTableSystem();

}
