/**
 * 
 */
package com.zingcrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author NeshTech
 */
@Entity
@Table(name = "roleinfo")
public class Role {

    /**
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "RoleID")
    private int roleId;

    /**
     */
    @Column(name = "RoleName")
    private String roleName;

    /**
     */
    @Column(name = "RolePage")
    private String rolePage;

    /**
     * @return the roleId
     */
    public final int getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public final void setRoleId(final int roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the roleName
     */
    public final String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName the roleName to set
     */
    public final void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the rolePage
     */
    public final String getRolePage() {
        return rolePage;
    }

    /**
     * @param rolePage the rolePage to set
     */
    public final void setRolePage(final String rolePage) {
        this.rolePage = rolePage;
    }
}
