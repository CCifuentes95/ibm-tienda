package com.ibm.tienda.presentation.backingBeans;

import com.ibm.tienda.exceptions.*;
import com.ibm.tienda.modelo.*;
import com.ibm.tienda.modelo.dto.DetallePedidoDTO;
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
public class DetallePedidoView implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DetallePedidoView.class);
    private InputText txtCantidad;
    private InputText txtPrecio;
    private InputText txtId_Pedido;
    private InputText txtId_Producto;
    private InputText txtId;
    private CommandButton btnSave;
    private CommandButton btnModify;
    private CommandButton btnDelete;
    private CommandButton btnClear;
    private List<DetallePedidoDTO> data;
    private DetallePedidoDTO selectedDetallePedido;
    private DetallePedido entity;
    private boolean showDialog;
    @ManagedProperty(value = "#{BusinessDelegatorView}")
    private IBusinessDelegatorView businessDelegatorView;

    public DetallePedidoView() {
        super();
    }

    public String action_new() {
        action_clear();
        selectedDetallePedido = null;
        setShowDialog(true);

        return "";
    }

    public String action_clear() {
        entity = null;
        selectedDetallePedido = null;

        if (txtCantidad != null) {
            txtCantidad.setValue(null);
            txtCantidad.setDisabled(true);
        }

        if (txtPrecio != null) {
            txtPrecio.setValue(null);
            txtPrecio.setDisabled(true);
        }

        if (txtId_Pedido != null) {
            txtId_Pedido.setValue(null);
            txtId_Pedido.setDisabled(true);
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
            entity = (id != null) ? businessDelegatorView.getDetallePedido(id)
                                  : null;
        } catch (Exception e) {
            entity = null;
        }

        if (entity == null) {
            txtCantidad.setDisabled(false);
            txtPrecio.setDisabled(false);
            txtId_Pedido.setDisabled(false);
            txtId_Producto.setDisabled(false);
            txtId.setDisabled(false);
            btnSave.setDisabled(false);
        } else {
            txtCantidad.setValue(entity.getCantidad());
            txtCantidad.setDisabled(false);
            txtPrecio.setValue(entity.getPrecio());
            txtPrecio.setDisabled(false);
            txtId_Pedido.setValue(entity.getPedido().getId());
            txtId_Pedido.setDisabled(false);
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
        selectedDetallePedido = (DetallePedidoDTO) (evt.getComponent()
                                                       .getAttributes()
                                                       .get("selectedDetallePedido"));
        txtCantidad.setValue(selectedDetallePedido.getCantidad());
        txtCantidad.setDisabled(false);
        txtPrecio.setValue(selectedDetallePedido.getPrecio());
        txtPrecio.setDisabled(false);
        txtId_Pedido.setValue(selectedDetallePedido.getId_Pedido());
        txtId_Pedido.setDisabled(false);
        txtId_Producto.setValue(selectedDetallePedido.getId_Producto());
        txtId_Producto.setDisabled(false);
        txtId.setValue(selectedDetallePedido.getId());
        txtId.setDisabled(true);
        btnSave.setDisabled(false);
        setShowDialog(true);

        return "";
    }

    public String action_save() {
        try {
            if ((selectedDetallePedido == null) && (entity == null)) {
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
            entity = new DetallePedido();

            Long id = FacesUtils.checkLong(txtId);

            entity.setCantidad(FacesUtils.checkInteger(txtCantidad));
            entity.setId(id);
            entity.setPrecio(FacesUtils.checkDouble(txtPrecio));
            entity.setPedido((FacesUtils.checkLong(txtId_Pedido) != null)
                ? businessDelegatorView.getPedido(FacesUtils.checkLong(
                        txtId_Pedido)) : null);
            entity.setProducto((FacesUtils.checkLong(txtId_Producto) != null)
                ? businessDelegatorView.getProducto(FacesUtils.checkLong(
                        txtId_Producto)) : null);
            businessDelegatorView.saveDetallePedido(entity);
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
                Long id = new Long(selectedDetallePedido.getId());
                entity = businessDelegatorView.getDetallePedido(id);
            }

            entity.setCantidad(FacesUtils.checkInteger(txtCantidad));
            entity.setPrecio(FacesUtils.checkDouble(txtPrecio));
            entity.setPedido((FacesUtils.checkLong(txtId_Pedido) != null)
                ? businessDelegatorView.getPedido(FacesUtils.checkLong(
                        txtId_Pedido)) : null);
            entity.setProducto((FacesUtils.checkLong(txtId_Producto) != null)
                ? businessDelegatorView.getProducto(FacesUtils.checkLong(
                        txtId_Producto)) : null);
            businessDelegatorView.updateDetallePedido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            data = null;
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_datatable(ActionEvent evt) {
        try {
            selectedDetallePedido = (DetallePedidoDTO) (evt.getComponent()
                                                           .getAttributes()
                                                           .get("selectedDetallePedido"));

            Long id = new Long(selectedDetallePedido.getId());
            entity = businessDelegatorView.getDetallePedido(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public String action_delete_master() {
        try {
            Long id = FacesUtils.checkLong(txtId);
            entity = businessDelegatorView.getDetallePedido(id);
            action_delete();
        } catch (Exception e) {
            FacesUtils.addErrorMessage(e.getMessage());
        }

        return "";
    }

    public void action_delete() throws Exception {
        try {
            businessDelegatorView.deleteDetallePedido(entity);
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

    public String action_modifyWitDTO(Integer cantidad, Long id, Double precio,
        Long id_Pedido, Long id_Producto) throws Exception {
        try {
            entity.setCantidad(FacesUtils.checkInteger(cantidad));
            entity.setPrecio(FacesUtils.checkDouble(precio));
            businessDelegatorView.updateDetallePedido(entity);
            FacesUtils.addInfoMessage(ZMessManager.ENTITY_SUCCESFULLYMODIFIED);
        } catch (Exception e) {
            //renderManager.getOnDemandRenderer("DetallePedidoView").requestRender();
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

    public InputText getTxtPrecio() {
        return txtPrecio;
    }

    public void setTxtPrecio(InputText txtPrecio) {
        this.txtPrecio = txtPrecio;
    }

    public InputText getTxtId_Pedido() {
        return txtId_Pedido;
    }

    public void setTxtId_Pedido(InputText txtId_Pedido) {
        this.txtId_Pedido = txtId_Pedido;
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

    public List<DetallePedidoDTO> getData() {
        try {
            if (data == null) {
                data = businessDelegatorView.getDataDetallePedido();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }

    public void setData(List<DetallePedidoDTO> detallePedidoDTO) {
        this.data = detallePedidoDTO;
    }

    public DetallePedidoDTO getSelectedDetallePedido() {
        return selectedDetallePedido;
    }

    public void setSelectedDetallePedido(DetallePedidoDTO detallePedido) {
        this.selectedDetallePedido = detallePedido;
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
