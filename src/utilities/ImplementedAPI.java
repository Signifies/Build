package utilities;

import SQLAPI.CreateSQLTables;
import SQLAPI.SQL;
import events.BuildEvents;
import events.BuildMode;
import events.InventoryManagement;
import me.ES96.com.Build;
import me.ES96.com.WorldMangement;
import org.bukkit.inventory.Inventory;

/**
 * Created by Evan on 10/23/2017.
 */
public class ImplementedAPI implements API {

    private boolean isTrashed;
   private static ImplementedAPI instance;

   private final Build buildInstance;
   private final BuildUtils buildUtils;
   private final BuildConfig buildConfig;
   private final BuildPermissions buildPermissions = BuildPermissions.BUILD;
   private final InventoryManagement inventoryManagement;
   private final WorldMangement worldMangement;
   private final BuildMode buildMode;
   private final BuildEvents buildEvents;
   private final SQL sql;
   private final CreateSQLTables sqlTables;



   private ImplementedAPI(Build binstance) throws Exception
   {
       buildInstance = binstance;
       buildUtils = new BuildUtils();
       buildConfig = new BuildConfig(buildInstance);
//       buildPermissions = new BuildPermissions("t");
       inventoryManagement = new InventoryManagement();
       buildMode = new BuildMode(buildInstance);
       buildEvents = new BuildEvents(buildInstance);
       sql = new SQL(buildInstance.isSQLEnabled(),buildInstance.getBConfig().getBuildConfig().getString("Database.host"), buildInstance.getBConfig().getBuildConfig().getString("Database.username"), buildInstance.getBConfig().getBuildConfig().getString("Database.password"), buildInstance.getBConfig().getBuildConfig().getString("Database.database"));
       sqlTables = new CreateSQLTables();
       worldMangement = new WorldMangement(buildInstance);

   }


    @Override
    public WorldMangement getWorldManagement() {
        return worldMangement;
    }

    @Override
    public BuildUtils getBuildUtilities() {
        return buildUtils;
    }

    @Override
    public BuildMode getBuildMode() {
        return buildMode;
    }

    @Override
    public BuildConfig getBuildConfiguration() {
        return buildConfig;
    }

    @Override
    public BuildPermissions getBuildPermissions() {
        return buildPermissions;
    }

    @Override
    public InventoryManagement getInventoryManagement() {
        return inventoryManagement;
    }

    @Override
    public BuildEvents getBuildEvents() {
        return  buildEvents;
    }

    @Override
    public SQL getBuildSQLSystem() {
        return sql;
    }

    @Override
    public CreateSQLTables getSQLTableSystem() {
        return sqlTables;
    }

    public void trash() throws Exception
    {
        if(isTrashed)
        {
            throw new IllegalArgumentException("API instance already killed...");
        }
    }

    public static ImplementedAPI boot(Build buildInstance) throws Exception
    {
        if(instance !=null)
        {
            throw new IllegalAccessException("Can't reset instance.");
        }

        return (instance = new ImplementedAPI(buildInstance));
    }

}
