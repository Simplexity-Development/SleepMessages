package adhdmc.sleepmessages.util;

import org.bukkit.permissions.Permission;

public enum SMPerm {
    RELOAD(new Permission("sm.reload")),
    BYPASS(new Permission("sm.message.bypass"));
    final Permission perm;

    SMPerm(org.bukkit.permissions.Permission perm) {
        this.perm = perm;
    }
    public Permission getPerm(){
        return perm;
    }
}
