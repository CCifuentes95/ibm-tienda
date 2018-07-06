package com.ibm.tienda.presentation.backingBeans;

import com.ibm.tienda.exceptions.*;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.InventarioDTO;
import com.ibm.tienda.presentation.businessDelegate.*;
import com.ibm.tienda.utilities.*;

import org.primefaces.component.calendar.*;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.inputtext.InputText;

import org.primefaces.event.RowEditEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

import java.sql.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;


/**
 * @author Zathura Code Generator http://zathuracode.org
 * www.zathuracode.org
 *
 */
@ManagedBean
@ViewScoped
public class InventarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(InventarioView.class);
    private InputText txtId_Empresa;
    private InputText txtId;
    private Calendar txtFechaCreacion;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<InventarioDTO> data;
    private InventarioDTO selectedInventario;
    private Inventario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public InventarioView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedInventario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedInventario = null;

        if (txtId_Empresa != null) {
            txtId_Empresa.setValue(null);
            txtId_Empresa.setDisabled(true);
        }

        if (txtFechaCreacion != null) {
            txtFechaCreacion.setValue(null);
            txtFechaCreacion.setDisabled(true);
        }

        if (txtId != null) {
            txtId.setValue(null);
            txtId.setDisabled(false);
        }

        if (btnSave != null) {
            btnSave.setDisabled(true);
        }

        if (btnDelete != null) {
            btnDelete.setDisabled(true);
        }

        return "";
    }

    public void listener_txtFechaCreacion() {
        Date inputDate = (Date) txtFechaCreacion.getValue();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        FacesContext.getCurrentInstance()
                    .addMessage("",
            new FacesMessage("Selected Date " + dateFormat.format(inputDate)));
    }

    public void listener_txtId() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = (id != null) ? businessDelegatorView.getInventario(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtId_Empresa.setDisabled(false);
            txtFechaCreacion.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtFechaCreacion.setValue(entity.getFechaCreacion());
            txtFechaCreacion.setDisabled(false);
            txtId_Empresa.setValue(entity.getEmpresa().getId());
            txtId_Empresa.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedInventario = (InventarioDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedInventario"));
        txtFechaCreacion.setValue(selectedInventario.getFechaCreacion());
        txtFechaCreacion.setDisabled(false);
        txtId_Empresa.setValue(selectedInventario.getId_Empresa());
        txtId_Empresa.setDisabled(false);
        txtId.setValue(selectedInventario.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedInventario == null) && (entity == null)) {
                action_create();
            } else {
                action_modify();
            }

            data = null;
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_create() {
        try {
            entity = new Inventario();

            Long id = FacesUtils.checkLong(txtId);

            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setId(id);
            entity.setEmpresa((FacesUtils.checkLong(txtId_Empresa) != null)
                ? businessDelegatorView.getEmpresa(FacesUtils.checkLong(
                        txtId_Empresa)) : null);
            businessDelegatorView.saveInventario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYSAVED);
            action_clear();
        } catch (Exception e) {
            entity = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_modify() {
        try {
            if (entity == null) {
                Long id = new Long(selectedInventario.getId());
                entity = businessDelegatorView.getInventario(id);
            }

            entity.setFechaCreacion(FacesUtils.checkDate(txtFechaCreacion));
            entity.setEmpresa((FacesUtils.checkLong(txtId_Empresa) != null)
                ? businessDelegatorView.getEmpresa(FacesUtils.checkLong(
                        txtId_Empresa)) : null);
            businessDelegatorView.updateInventario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedInventario = (InventarioDTO) (evt.getComponent()
                                                     .getAttributes()
                                                     .get("selectedInventario"));

            Long id = new Long(selectedInventario.getId());
            entity = businessDelegatorView.getInventario(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getInventario(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteInventario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYDELETED);
            action_clear();
            data = null;
        } catch (Exception e) {
            throw e;
        }
    }

    public String action_closeDialog() {
        setShowDialog(false);
        action_clear();

        return "";
    }

    public String action_modifyWitDTO(Date fechaCreacion, Long id,
        Long id_Empresa) throws Exception {
        try {
            entity.setFechaCreacion(FacesUtils.checkDate(fechaCreacion));
            businessDelegatorView.updateInventario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("InventarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtId_Empresa() {
        return txtId_Empresa;
    }

    public void setTxtId_Empresa(InputText txtId_Empresa) {
        this.txtId_Empresa = txtId_Empresa;
    }

    public Calendar getTxtFechaCreacion() {
        return txtFechaCreacion;
    }

    public void setTxtFechaCreacion(Calendar txtFechaCreacion) {
        this.txtFechaCreacion = txtFechaCreacion;
    }

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<InventarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataInventario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<InventarioDTO> inventarioDTO) {
        this.data = inventarioDTO;
    }

    public InventarioDTO getSelectedInventario() {
        return selectedInventario;
    }

    public void setSelectedInventario(InventarioDTO inventario) {
        this.selectedInventario = inventario;
    }

    public CommandButton getBtnSave() {
        return btnSave;
    }

    public void setBtnSave(CommandButton btnSave) {
        this.btnSave = btnSave;
    }

    public CommandButton getBtnModify() {
        return btnModify;
    }

    public void setBtnModify(CommandButton btnModify) {
        this.btnModify = btnModify;
    }

    public CommandButton getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(CommandButton btnDelete) {
        this.btnDelete = btnDelete;
    }

    public CommandButton getBtnClear() {
        return btnClear;
    }

    public void setBtnClear(CommandButton btnClear) {
        this.btnClear = btnClear;
    }

    public TimeZone getTimeZone() {
        return java.util.TimeZone.getDefault();
    }

    public IBusinessDelegatorView getBusinessDelegatorView() {
        return businessDelegatorView;
    }

    public void setBusinessDelegatorView(
        IBusinessDelegatorView businessDelegatorView) {
        this.businessDelegatorView = businessDelegatorView;
    }

    public boolean isShowDialog() {
        return showDialog;
    }

    public void setShowDialog(boolean showDialog) {
        this.showDialog = showDialog;
    }
}
