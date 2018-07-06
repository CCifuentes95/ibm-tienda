package com.ibm.tienda.presentation.backingBeans;

import com.ibm.tienda.exceptions.*;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.DetalleInventarioDTO;
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
public class DetalleInventarioView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DetalleInventarioView.class);
    private InputText txtCantidad;
    private InputText txtPrecioBase;
    private InputText txtId_Inventario;
    private InputText txtId_Producto;
    private InputText txtId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<DetalleInventarioDTO> data;
    private DetalleInventarioDTO selectedDetalleInventario;
    private DetalleInventario entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DetalleInventarioView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedDetalleInventario = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedDetalleInventario = null;

        if (txtCantidad != null) {
            txtCantidad.setValue(null);
            txtCantidad.setDisabled(true);
        }

        if (txtPrecioBase != null) {
            txtPrecioBase.setValue(null);
            txtPrecioBase.setDisabled(true);
        }

        if (txtId_Inventario != null) {
            txtId_Inventario.setValue(null);
            txtId_Inventario.setDisabled(true);
        }

        if (txtId_Producto != null) {
            txtId_Producto.setValue(null);
            txtId_Producto.setDisabled(true);
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
                ? businessDelegatorView.getDetalleInventario(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCantidad.setDisabled(false);
            txtPrecioBase.setDisabled(false);
            txtId_Inventario.setDisabled(false);
            txtId_Producto.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCantidad.setValue(entity.getCantidad());
            txtCantidad.setDisabled(false);
            txtPrecioBase.setValue(entity.getPrecioBase());
            txtPrecioBase.setDisabled(false);
            txtId_Inventario.setValue(entity.getInventario().getId());
            txtId_Inventario.setDisabled(false);
            txtId_Producto.setValue(entity.getProducto().getId());
            txtId_Producto.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedDetalleInventario = (DetalleInventarioDTO) (evt.getComponent()
                                                               .getAttributes()
                                                               .get("selectedDetalleInventario"));
        txtCantidad.setValue(selectedDetalleInventario.getCantidad());
        txtCantidad.setDisabled(false);
        txtPrecioBase.setValue(selectedDetalleInventario.getPrecioBase());
        txtPrecioBase.setDisabled(false);
        txtId_Inventario.setValue(selectedDetalleInventario.getId_Inventario());
        txtId_Inventario.setDisabled(false);
        txtId_Producto.setValue(selectedDetalleInventario.getId_Producto());
        txtId_Producto.setDisabled(false);
        txtId.setValue(selectedDetalleInventario.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedDetalleInventario == null) && (entity == null)) {
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
            entity = new DetalleInventario();

            Long id = FacesUtils.checkLong(txtId);

            entity.setCantidad(FacesUtils.checkInteger(txtCantidad));
            entity.setId(id);
            entity.setPrecioBase(FacesUtils.checkDouble(txtPrecioBase));
            entity.setInventario((FacesUtils.checkLong(txtId_Inventario) != null)
                ? businessDelegatorView.getInventario(FacesUtils.checkLong(
                        txtId_Inventario)) : null);
            entity.setProducto((FacesUtils.checkLong(txtId_Producto) != null)
                ? businessDelegatorView.getProducto(FacesUtils.checkLong(
                        txtId_Producto)) : null);
            businessDelegatorView.saveDetalleInventario(entity);
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
                Long id = new Long(selectedDetalleInventario.getId());
                entity = businessDelegatorView.getDetalleInventario(id);
            }

            entity.setCantidad(FacesUtils.checkInteger(txtCantidad));
            entity.setPrecioBase(FacesUtils.checkDouble(txtPrecioBase));
            entity.setInventario((FacesUtils.checkLong(txtId_Inventario) != null)
                ? businessDelegatorView.getInventario(FacesUtils.checkLong(
                        txtId_Inventario)) : null);
            entity.setProducto((FacesUtils.checkLong(txtId_Producto) != null)
                ? businessDelegatorView.getProducto(FacesUtils.checkLong(
                        txtId_Producto)) : null);
            businessDelegatorView.updateDetalleInventario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedDetalleInventario = (DetalleInventarioDTO) (evt.getComponent()
                                                                   .getAttributes()
                                                                   .get("selectedDetalleInventario"));

            Long id = new Long(selectedDetalleInventario.getId());
            entity = businessDelegatorView.getDetalleInventario(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getDetalleInventario(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteDetalleInventario(entity);
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

    public String action_modifyWitDTO(Integer cantidad, Long id,
        Double precioBase, Long id_Inventario, Long id_Producto)
        throws Exception {
        try {
            entity.setCantidad(FacesUtils.checkInteger(cantidad));
            entity.setPrecioBase(FacesUtils.checkDouble(precioBase));
            businessDelegatorView.updateDetalleInventario(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("DetalleInventarioView").requestRender();
            FacesUtils.addErrorMessage(e.getMessage());
            throw e;
        }

        return "";
    }

    public InputText getTxtCantidad() {
        return txtCantidad;
    }

    public void setTxtCantidad(InputText txtCantidad) {
        this.txtCantidad = txtCantidad;
    }

    public InputText getTxtPrecioBase() {
        return txtPrecioBase;
    }

    public void setTxtPrecioBase(InputText txtPrecioBase) {
        this.txtPrecioBase = txtPrecioBase;
    }

    public InputText getTxtId_Inventario() {
        return txtId_Inventario;
    }

    public void setTxtId_Inventario(InputText txtId_Inventario) {
        this.txtId_Inventario = txtId_Inventario;
    }

    public InputText getTxtId_Producto() {
        return txtId_Producto;
    }

    public void setTxtId_Producto(InputText txtId_Producto) {
        this.txtId_Producto = txtId_Producto;
    }

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<DetalleInventarioDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataDetalleInventario();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<DetalleInventarioDTO> detalleInventarioDTO) {
        this.data = detalleInventarioDTO;
    }

    public DetalleInventarioDTO getSelectedDetalleInventario() {
        return selectedDetalleInventario;
    }

    public void setSelectedDetalleInventario(
        DetalleInventarioDTO detalleInventario) {
        this.selectedDetalleInventario = detalleInventario;
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
