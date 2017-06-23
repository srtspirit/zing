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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author NeshTech
 * 
 */
@Entity
@Table(name = "userrolesecurity")
public class Authority {

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserRoleID")
    private int id;

    /**
     *
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "UserID")
    private String userId;

    /**
     *
     */
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "Authority")
    private String authority;

    /**
     * @return the id
     */
    public final int getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(final int id) {
        this.id = id;
    }

    /**
     * @return the userId
     */
    public final String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public final void setUserId(final String userId) {
        this.userId = userId;
    }

    /**
     * @return the authority
     */
    public final String getAuthority() {
        return authority;
    }

    /**
     * @param authority
     *            the authority to set
     */
    public final void setAuthority(final String authority) {
        this.authority = authority;
    }

}
