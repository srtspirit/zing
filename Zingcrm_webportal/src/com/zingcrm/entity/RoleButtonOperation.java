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
 * 
 */
@Entity
@Table(name = "rolebuttonoperation")
public class RoleButtonOperation {

    /**
     * 
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int Id;

    /**
     * 
     */
    @Column(name = "RoleID")
    private int RoleId;

    /**
     * 
     */
    @Column(name = "FeatureID")
    private String FeatureId;

    /**
     * 
     */
    @Column(name = "OperationID")
    private String OperationId;

    /**
     * 
     */
    @Column(name = "OrgID")
    private int OrgId;

    /**
     * 
     */
    @Column(name = "ButtonID")
    private String ButtonId;

    /**
     * 
     */
    @Column(name = "ButtonTitle")
    private String ButtonTitle;

    /**
     * 
     */
    @Column(name = "ToolTip")
    private String ToolTip;

    /**
     * 
     */
    @Column(name = "ButtonOrder")
    private int ButtonOrder;

    /**
     * 
     */
    @Column(name = "ButtonGroup")
    private int ButtonGroup;

    /**
     * @return the buttonId
     */
    public final String getButtonId() {
        return ButtonId;
    }

    /**
     * @param buttonId
     *            the buttonId to set
     */
    public final void setButtonId(final String buttonId) {
        ButtonId = buttonId;
    }

    /**
     * @return the buttonTitle
     */
    public final String getButtonTitle() {
        return ButtonTitle;
    }

    /**
     * @param buttonTitle
     *            the buttonTitle to set
     */
    public final void setButtonTitle(final String buttonTitle) {
        ButtonTitle = buttonTitle;
    }

    /**
     * @return the toolTip
     */
    public final String getToolTip() {
        return ToolTip;
    }

    /**
     * @param toolTip
     *            the toolTip to set
     */
    public final void setToolTip(final String toolTip) {
        ToolTip = toolTip;
    }

    /**
     * @return the buttonOrder
     */
    public final int getButtonOrder() {
        return ButtonOrder;
    }

    /**
     * @param buttonOrder
     *            the buttonOrder to set
     */
    public final void setButtonOrder(final int buttonOrder) {
        ButtonOrder = buttonOrder;
    }

    /**
     * @return the buttonGroup
     */
    public final int getButtonGroup() {
        return ButtonGroup;
    }

    /**
     * @param buttonGroup
     *            the buttonGroup to set
     */
    public final void setButtonGroup(final int buttonGroup) {
        ButtonGroup = buttonGroup;
    }

    /**
     * @return the id
     */
    public final int getId() {
        return Id;
    }

    /**
     * @param id
     *            the id to set
     */
    public final void setId(final int id) {
        Id = id;
    }

    /**
     * @return the roleId
     */
    public final int getRoleId() {
        return RoleId;
    }

    /**
     * @param roleId
     *            the roleId to set
     */
    public final void setRoleId(final int roleId) {
        RoleId = roleId;
    }

    /**
     * @return the featureId
     */
    public final String getFeatureId() {
        return FeatureId;
    }

    /**
     * @param featureId
     *            the featureId to set
     */
    public final void setFeatureId(final String featureId) {
        FeatureId = featureId;
    }

    /**
     * @return the operationId
     */
    public final String getOperationId() {
        return OperationId;
    }

    /**
     * @param operationId
     *            the operationId to set
     */
    public final void setOperationId(final String operationId) {
        OperationId = operationId;
    }

    /**
     * @return the orgId
     */
    public final int getOrgId() {
        return OrgId;
    }

    /**
     * @param orgId
     *            the orgId to set
     */
    public final void setOrgId(final int orgId) {
        OrgId = orgId;
    }

}
