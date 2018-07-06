package com.ibm.tienda.presentation.backingBeans;

import com.ibm.tienda.exceptions.*;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.TipoIdentificacionDTO;
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
public class TipoIdentificacionView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(TipoIdentificacionView.class);
    private InputText txtCodigo;
    private InputText txtNombre;
    private InputText txtId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<TipoIdentificacionDTO> data;
    private TipoIdentificacionDTO selectedTipoIdentificacion;
    private TipoIdentificacion entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public TipoIdentificacionView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedTipoIdentificacion = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedTipoIdentificacion = null;

        if (txtCodigo != null) {
            txtCodigo.setValue(null);
            txtCodigo.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
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

    public void listener_txtId() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = (id != null)
                ? businessDelegatorView.getTipoIdentificacion(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCodigo.setDisabled(false);
            txtNombre.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedTipoIdentificacion = (TipoIdentificacionDTO) (evt.getComponent()
                                                                 .getAttributes()
                                                                 .get("selectedTipoIdentificacion"));
        txtCodigo.setValue(selectedTipoIdentificacion.getCodigo());
        txtCodigo.setDisabled(false);
        txtNombre.setValue(selectedTipoIdentificacion.getNombre());
        txtNombre.setDisabled(false);
        txtId.setValue(selectedTipoIdentificacion.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedTipoIdentificacion == null) && (entity == null)) {
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
            entity = new TipoIdentificacion();

            Long id = FacesUtils.checkLong(txtId);

            entity.setCodigo(FacesUtils.checkString(txtCodigo));
            entity.setId(id);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.saveTipoIdentificacion(entity);
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
                Long id = new Long(selectedTipoIdentificacion.getId());
                entity = businessDelegatorView.getTipoIdentificacion(id);
            }

            entity.setCodigo(FacesUtils.checkString(txtCodigo));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            businessDelegatorView.updateTipoIdentificacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedTipoIdentificacion = (TipoIdentificacionDTO) (evt.getComponent()
                                                                     .getAttributes()
                                                                     .get("selectedTipoIdentificacion"));

            Long id = new Long(selectedTipoIdentificacion.getId());
            entity = businessDelegatorView.getTipoIdentificacion(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getTipoIdentificacion(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteTipoIdentificacion(entity);
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

    public String action_modifyWitDTO(String codigo, Long id, String nombre)
        throws Exception {
        try {
            entity.setCodigo(FacesUtils.checkString(codigo));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updateTipoIdentificacion(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("TipoIdentificacionView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCodigo() {
        return txtCodigo;
    }

    public void setTxtCodigo(InputText txtCodigo) {
        this.txtCodigo = txtCodigo;
    }

    public InputText getTxtNombre() {
        return txtNombre;
    }

    public void setTxtNombre(InputText txtNombre) {
        this.txtNombre = txtNombre;
    }

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<TipoIdentificacionDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataTipoIdentificacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<TipoIdentificacionDTO> tipoIdentificacionDTO) {
        this.data = tipoIdentificacionDTO;
    }

    public TipoIdentificacionDTO getSelectedTipoIdentificacion() {
        return selectedTipoIdentificacion;
    }

    public void setSelectedTipoIdentificacion(
        TipoIdentificacionDTO tipoIdentificacion) {
        this.selectedTipoIdentificacion = tipoIdentificacion;
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
