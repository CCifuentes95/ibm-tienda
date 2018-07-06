package com.ibm.tienda.presentation.backingBeans;

import com.ibm.tienda.exceptions.*;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.ProductoDTO;
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
public class ProductoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ProductoView.class);
    private InputText txtCodigo;
    private InputText txtNombre;
    private InputText txtId_TipoProducto;
    private InputText txtId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<ProductoDTO> data;
    private ProductoDTO selectedProducto;
    private Producto entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public ProductoView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedProducto = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedProducto = null;

        if (txtCodigo != null) {
            txtCodigo.setValue(null);
            txtCodigo.setDisabled(true);
        }

        if (txtNombre != null) {
            txtNombre.setValue(null);
            txtNombre.setDisabled(true);
        }

        if (txtId_TipoProducto != null) {
            txtId_TipoProducto.setValue(null);
            txtId_TipoProducto.setDisabled(true);
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
            entity = (id != null) ? businessDelegatorView.getProducto(id) : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCodigo.setDisabled(false);
            txtNombre.setDisabled(false);
            txtId_TipoProducto.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCodigo.setValue(entity.getCodigo());
            txtCodigo.setDisabled(false);
            txtNombre.setValue(entity.getNombre());
            txtNombre.setDisabled(false);
            txtId_TipoProducto.setValue(entity.getTipoProducto().getId());
            txtId_TipoProducto.setDisabled(false);
            txtId.setValue(entity.getId());
            txtId.setDisabled(true);
            btnSave.setDisabled(false);

            if (btnDelete != null) {
                btnDelete.setDisabled(false);
            }
        }
    }

    public String action_edit(ActionEvent evt) {
        selectedProducto = (ProductoDTO) (evt.getComponent().getAttributes()
                                             .get("selectedProducto"));
        txtCodigo.setValue(selectedProducto.getCodigo());
        txtCodigo.setDisabled(false);
        txtNombre.setValue(selectedProducto.getNombre());
        txtNombre.setDisabled(false);
        txtId_TipoProducto.setValue(selectedProducto.getId_TipoProducto());
        txtId_TipoProducto.setDisabled(false);
        txtId.setValue(selectedProducto.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedProducto == null) && (entity == null)) {
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
            entity = new Producto();

            Long id = FacesUtils.checkLong(txtId);

            entity.setCodigo(FacesUtils.checkString(txtCodigo));
            entity.setId(id);
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setTipoProducto((FacesUtils.checkLong(txtId_TipoProducto) != null)
                ? businessDelegatorView.getTipoProducto(FacesUtils.checkLong(
                        txtId_TipoProducto)) : null);
            businessDelegatorView.saveProducto(entity);
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
                Long id = new Long(selectedProducto.getId());
                entity = businessDelegatorView.getProducto(id);
            }

            entity.setCodigo(FacesUtils.checkString(txtCodigo));
            entity.setNombre(FacesUtils.checkString(txtNombre));
            entity.setTipoProducto((FacesUtils.checkLong(txtId_TipoProducto) != null)
                ? businessDelegatorView.getTipoProducto(FacesUtils.checkLong(
                        txtId_TipoProducto)) : null);
            businessDelegatorView.updateProducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedProducto = (ProductoDTO) (evt.getComponent().getAttributes()
                                                 .get("selectedProducto"));

            Long id = new Long(selectedProducto.getId());
            entity = businessDelegatorView.getProducto(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getProducto(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteProducto(entity);
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

    public String action_modifyWitDTO(String codigo, Long id, String nombre,
        Long id_TipoProducto) throws Exception {
        try {
            entity.setCodigo(FacesUtils.checkString(codigo));
            entity.setNombre(FacesUtils.checkString(nombre));
            businessDelegatorView.updateProducto(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("ProductoView").requestRender();
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

    public InputText getTxtId_TipoProducto() {
        return txtId_TipoProducto;
    }

    public void setTxtId_TipoProducto(InputText txtId_TipoProducto) {
        this.txtId_TipoProducto = txtId_TipoProducto;
    }

    public InputText getTxtId() {
        return txtId;
    }

    public void setTxtId(InputText txtId) {
        this.txtId = txtId;
    }

    public List<ProductoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataProducto();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<ProductoDTO> productoDTO) {
        this.data = productoDTO;
    }

    public ProductoDTO getSelectedProducto() {
        return selectedProducto;
    }

    public void setSelectedProducto(ProductoDTO producto) {
        this.selectedProducto = producto;
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
